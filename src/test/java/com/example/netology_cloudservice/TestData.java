package com.example.netology_cloudservice;

import com.example.netology_cloudservice.dto.request.AuthenticationRequest;
import com.example.netology_cloudservice.dto.request.EditFileNameRequest;
import com.example.netology_cloudservice.dto.response.AuthenticationResponse;
import com.example.netology_cloudservice.model.StorageModel;
import com.example.netology_cloudservice.model.User;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.multipart.MultipartFile;

import ru.netology.dto.response.FileResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static final String TOKEN_1 = "Token1";
    public static final String TOKEN_2 = "Token2";
    public static final String BEARER_TOKEN = "Bearer Token";
    public static final String BEARER_TOKEN_SPLIT = BEARER_TOKEN.split(" ")[1];
    public static final String BEARER_TOKEN_SUBSTRING_7 = BEARER_TOKEN.substring(7);

    public static final String USERNAME_1 = "Username1";
    public static final String PASSWORD_1 = "Password1";
    public static final User USER_1 = new User(USERNAME_1, PASSWORD_1, null);

    public static final String USERNAME_2 = "Username2";
    public static final String PASSWORD_2 = "Password2";
    public static final User USER_2 = new User(USERNAME_2, PASSWORD_2, null);

    public static final String FILENAME_1 = "Filename1";
    public static final Long SIZE_1 = 100L;
    public static final byte[] FILE_CONTENT_1 = FILENAME_1.getBytes();
    public static final StorageModel STORAGE_FILE_1 = new StorageModel(FILENAME_1, LocalDateTime.now(), SIZE_1, FILE_CONTENT_1, USER_1);

    public static final String FILENAME_2 = "Filename2";
    public static final Long SIZE_2 = 101L;
    public static final byte[] FILE_CONTENT_2 = FILENAME_2.getBytes();
    public static final StorageModel STORAGE_FILE_2 = new StorageModel(FILENAME_2, LocalDateTime.now(), SIZE_2, FILE_CONTENT_2, USER_2);


    public static final String FOR_RENAME_FILENAME = "OldFilename";
    public static final Long FOR_RENAME_SIZE = 103L;
    public static final byte[] FOR_RENAME_FILE_CONTENT = FOR_RENAME_FILENAME.getBytes();
    public static final StorageModel FOR_RENAME_STORAGE_FILE = new StorageModel(FOR_RENAME_FILENAME, LocalDateTime.now(), FOR_RENAME_SIZE, FOR_RENAME_FILE_CONTENT, USER_1);


    public static final String NEW_FILENAME = "NewFilename";
    public static final EditFileNameRequest EDIT_FILE_NAME_REQUEST = new EditFileNameRequest(NEW_FILENAME);

    public static final MultipartFile MULTIPART_FILE = new MockMultipartFile(FILENAME_2, FILE_CONTENT_2);

    public static final FileResponse FILE_RESPONSE_1 = new FileResponse(FILENAME_1, SIZE_1);
    public static final FileResponse FILE_RESPONSE_2 = new FileResponse(FILENAME_2, SIZE_2);
    public static final List<FileResponse> FILE_RESPONSE_LIST = List.of(FILE_RESPONSE_1, FILE_RESPONSE_2);
    public static final Integer LIMIT = 100;

    public static final AuthenticationRequest AUTHENTICATION_REQUEST = new AuthenticationRequest(USERNAME_1, PASSWORD_1);
    public static final AuthenticationResponse AUTHENTICATION_RESPONSE = new AuthenticationResponse(TOKEN_1);

    public static final UsernamePasswordAuthenticationToken USERNAME_PASSWORD_AUTHENTICATION_TOKEN = new UsernamePasswordAuthenticationToken(USERNAME_1, PASSWORD_1);

}
