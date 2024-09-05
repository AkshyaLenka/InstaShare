package com.instashare.social.service;

import com.instashare.social.model.Story;
import com.instashare.social.model.User;

import java.util.List;

public interface StoryService {
    public Story createStory(Story story, User user);

    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
