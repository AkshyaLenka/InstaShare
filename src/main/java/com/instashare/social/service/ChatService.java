package com.instashare.social.service;

import com.instashare.social.model.Chat;
import com.instashare.social.model.User;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser, User user2);

    public Chat findChatById(Integer chatId) throws Exception;

    public List<Chat> findUsersChat(Integer userId);
}
