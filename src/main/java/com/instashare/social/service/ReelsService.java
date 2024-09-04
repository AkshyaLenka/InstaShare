package com.instashare.social.service;

import com.instashare.social.model.Reels;
import com.instashare.social.model.User;

import java.util.List;

public interface ReelsService {
    public Reels createReel(Reels reel, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReel(Integer userId) throws Exception;
}
