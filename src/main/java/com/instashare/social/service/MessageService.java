package com.instashare.social.service;

import com.instashare.social.model.Chat;
import com.instashare.social.model.Message;
import com.instashare.social.model.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message req) throws Exception;
    public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
