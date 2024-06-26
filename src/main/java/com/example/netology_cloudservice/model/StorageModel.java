package com.example.netology_cloudservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "files", schema = "netology")
public class StorageModel{

    @Id
    @Column(nullable = false, unique = true)
    private String filename;

    @Column(nullable = true)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private Long size;

    @Lob
    @Column(nullable = false)
    private byte[] fileContent;

    @ManyToOne
    private User user;

}