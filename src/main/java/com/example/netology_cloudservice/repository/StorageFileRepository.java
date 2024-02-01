package com.example.netology_cloudservice.repository;

import com.example.netology_cloudservice.model.StorageModel;
import com.example.netology_cloudservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.netology.dto.response.FileResponse;

import java.util.List;

@Repository
public interface StorageFileRepository extends JpaRepository<StorageModel, String> {

    void deleteByUserAndFilename(User user, String filename);

    StorageModel findByUserAndFilename(User user, String filename);

    @Modifying
    @Query("update StorageModel f set f.filename = :newName where f.filename = :filename and f.user = :user")
    void editFileNameByUser(@Param("user") User user, @Param("filename") String filename, @Param("newName") String newName);

    List<FileResponse> findAllByUser(User user);

}
