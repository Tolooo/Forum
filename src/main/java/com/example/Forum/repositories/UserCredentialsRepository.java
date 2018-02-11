package com.example.Forum.repositories;

import com.example.Forum.models.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {

    UserCredentials findByUsername(String username);

}
