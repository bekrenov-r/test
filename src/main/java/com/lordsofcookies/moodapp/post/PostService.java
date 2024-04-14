package com.lordsofcookies.moodapp.post;

import com.lordsofcookies.moodapp.model.Post;
import com.lordsofcookies.moodapp.model.TelegramUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getMyPosts() {
        TelegramUser currentTgUser = null; // todo: get from security context
        return postRepository.findAllByUser(currentTgUser);
    }

    public Post addPost(Integer emojiId) {
        TelegramUser currentTgUser = null; // todo: get from security context
        Post post = new Post(null, currentTgUser, emojiId, LocalDateTime.now());
        return postRepository.save(post);
    }

    public void deletePost(UUID id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post with id" + id + "not found"));
        TelegramUser currentUserTgId = null; // todo: get from security context
        if(!post.getUser().equals(currentUserTgId)){
            throw new RuntimeException("You are not owner of this post");
        }
        postRepository.delete(post);
    }
}
