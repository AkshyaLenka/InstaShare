package com.instashare.social.repository;

import com.instashare.social.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story,Integer> {
    List<Story> findByUserId(Integer userId);
}
