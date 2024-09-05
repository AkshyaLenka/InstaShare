package com.instashare.social.contoller;

import com.instashare.social.model.Story;
import com.instashare.social.model.User;
import com.instashare.social.service.StoryService;
import com.instashare.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {
    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt){
        User reqUser = userService.findUserByJwt(jwt);
        Story createdStory = storyService.createStory(story,reqUser);
        return createdStory;
    }

    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUserStory(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
        List<Story> stories = storyService.findStoryByUserId(userId);
        return stories;
    }
}
