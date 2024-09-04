package com.instashare.social.repository;

import com.instashare.social.model.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels, Integer> {
    public List<Reels> findByUserId(Integer userId);
}
