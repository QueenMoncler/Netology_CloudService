//package com.example.netology_cloudservice.repository;
//
//
//import com.example.netology_cloudservice.model.StorageModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//
//import java.util.List;
//import java.util.Optional;
//
//import static com.example.netology_cloudservice.TestData.*;
//import static org.junit.Assert.*;
//
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class StorageFileRepositoryTest {
//
//    @Autowired
//    private StorageFileRepository storageFileRepository;
//
//    @Autowired
//    private Repository userRepository;
//
//    @BeforeEach
//    void setUp() {
//        userRepository.deleteAll();
//        storageFileRepository.deleteAll();
//        userRepository.save(USER_1);
//        storageFileRepository.save(STORAGE_FILE_1);
//        storageFileRepository.save(FOR_RENAME_STORAGE_FILE);
//    }
//
//    @Test
//    void deleteByUserAndFilename() {
//        Optional<StorageModel> beforeDelete = storageFileRepository.findById(FILENAME_1);
//        assertTrue(beforeDelete.isPresent());
//        storageFileRepository.deleteByUserAndFilename(USER_1, FILENAME_1);
//        Optional<StorageModel> afterDelete = storageFileRepository.findById(FILENAME_1);
//        assertFalse(afterDelete.isPresent());
//    }
//
//    @Test
//    void findByUserAndFilename() {
//        assertEquals(STORAGE_FILE_1, storageFileRepository.findByUserAndFilename(USER_1, FILENAME_1));
//    }
//
//    @Test
//    void editFilenameByUser() {
//        Optional<StorageModel> beforeEditName = storageFileRepository.findById(FOR_RENAME_FILENAME);
//        assertTrue(beforeEditName.isPresent());
//        assertEquals(FOR_RENAME_FILENAME, beforeEditName.get().getFilename());
//        storageFileRepository.editFileNameByUser(USER_1, FOR_RENAME_FILENAME, NEW_FILENAME);
//        Optional<StorageModel> afterEditName = storageFileRepository.findById(NEW_FILENAME);
//        assertTrue(afterEditName.isPresent());
//        assertEquals(NEW_FILENAME, afterEditName.get().getFilename());
//    }
//
//    @Test
//    void findAllByUser() {
//        assertEquals(List.of(STORAGE_FILE_1, FOR_RENAME_STORAGE_FILE), storageFileRepository.findAllByUser(USER_1));
//    }
//
//}
