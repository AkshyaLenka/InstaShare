package com.instashare.social.repository;

import com.instashare.social.model.Chat;
import com.instashare.social.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Integer> {
    public List<Chat> findByUsersId(Integer userId);

    @Query("select c from Chat c Where :user Member of c.users AND :reqUser Member of c.users")
    public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);
}
