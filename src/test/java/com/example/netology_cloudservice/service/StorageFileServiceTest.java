package com.example.netology_cloudservice.service;

import com.example.netology_cloudservice.TestData;
import com.example.netology_cloudservice.exception.InputDataException;
import com.example.netology_cloudservice.exception.UnauthorizedException;
import com.example.netology_cloudservice.repository.AuthenticationRepository;
import com.example.netology_cloudservice.repository.Repository;
import com.example.netology_cloudservice.repository.StorageFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


import static org.junit.Assert.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StorageFileServiceTest {

    @InjectMocks
    private StorageFileService storageFileService;

    @Mock
    private StorageFileRepository storageFileRepository;

    @Mock
    private AuthenticationRepository authenticationRepository;

    @Mock
    private Repository userRepository;

    @BeforeEach
    void setUp() {
        Mockito.when(authenticationRepository.getUsernameByToken(TestData.BEARER_TOKEN_SPLIT)).thenReturn(TestData.USERNAME_1);
        Mockito.when(userRepository.findByUsername(TestData.USERNAME_1)).thenReturn(TestData.USER_1);
    }

    @Test
    void uploadFile() {
        assertTrue(storageFileService.uploadFile(TestData.BEARER_TOKEN, TestData.FILENAME_1, TestData.MULTIPART_FILE));
    }

    @Test
    void uploadFileUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> storageFileService.uploadFile(TestData.TOKEN_1, TestData.FILENAME_1, TestData.MULTIPART_FILE));
    }

    @Test
    void deleteFile() {
        storageFileService.deleteFile(TestData.BEARER_TOKEN, TestData.FILENAME_1);
        Mockito.verify(storageFileRepository, Mockito.times(1)).deleteByUserAndFilename(TestData.USER_1, TestData.FILENAME_1);
    }

    @Test
    void deleteFileUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> storageFileService.deleteFile(TestData.TOKEN_1, TestData.FILENAME_1));
    }

    @Test
    void deleteFileInputDataException() {
        Mockito.when(storageFileRepository.findByUserAndFilename(TestData.USER_1, TestData.FILENAME_1)).thenReturn(TestData.STORAGE_FILE_1);
        assertThrows(InputDataException.class, () -> storageFileService.deleteFile(TestData.BEARER_TOKEN, TestData.FILENAME_1));
    }

    @Test
    void downloadFile() {
        Mockito.when(storageFileRepository.findByUserAndFilename(TestData.USER_1, TestData.FILENAME_1)).thenReturn(TestData.STORAGE_FILE_1);
        assertEquals(TestData.FILE_CONTENT_1, storageFileService.downloadFile(TestData.BEARER_TOKEN, TestData.FILENAME_1));
    }

    @Test
    void downloadFileUnauthorized() {
        Mockito.when(storageFileRepository.findByUserAndFilename(TestData.USER_1, TestData.FILENAME_1)).thenReturn(TestData.STORAGE_FILE_1);
        assertThrows(UnauthorizedException.class, () -> storageFileService.downloadFile(TestData.TOKEN_1, TestData.FILENAME_1));
    }

    @Test
    void downloadFileInputDataException() {
        Mockito.when(storageFileRepository.findByUserAndFilename(TestData.USER_1, TestData.FILENAME_1)).thenReturn(TestData.STORAGE_FILE_1);
        assertThrows(InputDataException.class, () -> storageFileService.downloadFile(TestData.BEARER_TOKEN, TestData.FILENAME_2));
    }

    @Test
    void editFileName() {
        storageFileService.editFileName(TestData.BEARER_TOKEN, TestData.FILENAME_1, TestData.EDIT_FILE_NAME_REQUEST);
        Mockito.verify(storageFileRepository, Mockito.times(1)).editFileNameByUser(TestData.USER_1, TestData.FILENAME_1, TestData.NEW_FILENAME);
    }

    @Test
    void editFileNameUnauthorized() {
        assertThrows(UnauthorizedException.class, () -> storageFileService.editFileName(TestData.TOKEN_1, TestData.FILENAME_1, TestData.EDIT_FILE_NAME_REQUEST));
    }

    @Test
    void editFileNameInputDataException() {
        Mockito.when(storageFileRepository.findByUserAndFilename(TestData.USER_1, TestData.FILENAME_1)).thenReturn(TestData.STORAGE_FILE_1);
        assertThrows(InputDataException.class, () -> storageFileService.deleteFile(TestData.BEARER_TOKEN, TestData.FILENAME_1));
    }

    @Test
    void getAllFiles() {
        Mockito.when(storageFileRepository.findAllByUser(TestData.USER_1)).thenReturn(TestData.FILE_RESPONSE_LIST);
        assertEquals(TestData.FILE_RESPONSE_LIST, storageFileService.getAllFiles(TestData.BEARER_TOKEN, TestData.LIMIT));
    }

    @Test
    void getAllFilesUnauthorized() {
        Mockito.when(storageFileRepository.findAllByUser(TestData.USER_1)).thenReturn(TestData.FILE_RESPONSE_LIST);
        assertThrows(UnauthorizedException.class, () -> storageFileService.getAllFiles(TestData.TOKEN_1, TestData.LIMIT));
    }

}
