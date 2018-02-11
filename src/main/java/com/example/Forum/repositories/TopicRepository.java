package com.example.Forum.repositories;

import com.example.Forum.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}
