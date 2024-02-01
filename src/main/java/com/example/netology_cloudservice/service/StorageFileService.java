package com.example.netology_cloudservice.service;

import com.example.netology_cloudservice.dto.request.EditFileNameRequest;
import com.example.netology_cloudservice.exception.InputDataException;
import com.example.netology_cloudservice.exception.UnauthorizedException;
import com.example.netology_cloudservice.model.StorageModel;
import com.example.netology_cloudservice.model.User;
import com.example.netology_cloudservice.repository.AuthenticationRepository;
import com.example.netology_cloudservice.repository.Repository;
import com.example.netology_cloudservice.repository.StorageFileRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ru.netology.dto.response.FileResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class StorageFileService {

    private StorageFileRepository storageFileRepository;
    private AuthenticationRepository authenticationRepository;
    private Repository userRepository;


    public boolean uploadFile(String authToken, String filename, MultipartFile file) {
        final User user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Upload file: Unauthorized");
            throw new UnauthorizedException("Upload file: Unauthorized");
        }

        try {
            storageFileRepository.save(new StorageModel(filename, LocalDateTime.now(), file.getSize(), file.getBytes(), user));
            log.info("Success upload file. User {}", user.getUsername());
            return true;
        } catch (IOException e) {
            log.error("Upload file: Input data exception");
            throw new InputDataException("Upload file: Input data exception");
        }
    }

    @Transactional
    public void deleteFile(String authToken, String filename) {
        final User user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Delete file: Unauthorized");
            throw new UnauthorizedException("Delete file: Unauthorized");
        }

        storageFileRepository.deleteByUserAndFilename(user, filename);

        final StorageModel tryingToGetDeletedFile = storageFileRepository.findByUserAndFilename(user, filename);
        if (tryingToGetDeletedFile != null) {
            log.error("Delete file: Input data exception");
            throw new InputDataException("Delete file: Input data exception");
        }
        log.info("Success delete file. User {}", user.getUsername());
    }

    @Transactional
    public byte[] downloadFile(String authToken, String filename) {
        final User user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Delete file: Unauthorized");
            throw new UnauthorizedException("Delete file: Unauthorized");
        }

        final StorageModel file = storageFileRepository.findByUserAndFilename(user, filename);
        if (file == null) {
            log.error("Download file: Input data exception");
            throw new InputDataException("Download file: Input data exception");
        }
        log.info("Success download file: User {}", user.getUsername());
        return file.getFileContent();
    }

    @Transactional
    public void editFileName(String authToken, String filename, EditFileNameRequest editFileNameRequest) {
        final User user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Delete file: Unauthorized");
            throw new UnauthorizedException("Delete file: Unauthorized");
        }

        storageFileRepository.editFileNameByUser(user, filename, editFileNameRequest.getFilename());

        final StorageModel fileWithOldName = storageFileRepository.findByUserAndFilename(user, filename);
        if (fileWithOldName != null) {
            log.error("Edit file name. Input data exception");
            throw new InputDataException("Edit file name. Input data exception");
        }
        log.info("Success edit file name. User {}", user.getUsername());
    }

    public List<FileResponse> getAllFiles(String authToken, Integer limit) {
        final User user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Delete file: Unauthorized");
            throw new UnauthorizedException("Delete file: Unauthorized");
        }
        log.info("Success get all files. User {}", user.getUsername());
        return storageFileRepository.findAllByUser(user).stream()
                .map(o -> new FileResponse(o.getFilename(), o.getSize()))
                .collect(Collectors.toList());
    }

    private User getUserByAuthToken(String authToken) {
        if (authToken.startsWith("Bearer ")) {
            final String authTokenWithoutBearer = authToken.split(" ")[1];
            final String username = authenticationRepository.getUsernameByToken(authTokenWithoutBearer);
            return userRepository.findByUsername(username);
        }
        return null;
    }
}
