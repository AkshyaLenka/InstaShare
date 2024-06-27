package com.instashare.social.service;

import com.instashare.social.model.Post;

import java.util.List;

public interface PostService {
    Post createNewPost(Post post, Integer userId) throws Exception;

    String deletePost(Integer postId, Integer userId) throws Exception;

    List<Post> findPostByUserId(Integer userId);

    Post findPostById(Integer postId) throws Exception;

    List<Post> findAllPost();

    Post savedPost(Integer postId, Integer UserId) throws Exception;

    Post likedPost(Integer postId, Integer UserId) throws Exception;

}
