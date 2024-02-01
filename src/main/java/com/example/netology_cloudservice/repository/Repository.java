package com.example.netology_cloudservice.repository;


import com.example.netology_cloudservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.transaction.annotation.Transactional;


@org.springframework.stereotype.Repository
@Transactional
public interface Repository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
