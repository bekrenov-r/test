package com.lordsofcookies.moodapp.post;

import com.lordsofcookies.moodapp.model.Post;
import com.lordsofcookies.moodapp.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByUser(TelegramUser user);
}
