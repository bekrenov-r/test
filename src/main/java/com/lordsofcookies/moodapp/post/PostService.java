package com.lordsofcookies.moodapp.post;

import com.lordsofcookies.moodapp.CurrentUserUtil;
import com.lordsofcookies.moodapp.model.Post;
import com.lordsofcookies.moodapp.model.TelegramUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CurrentUserUtil currentUserUtil;
    private final PostMapper postMapper;

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::entityToResponse)
                .toList();
    }

    public List<PostResponse> getMyPosts() {
        TelegramUser currentTgUser = currentUserUtil.getCurrentUser();
        return postRepository.findAllByUser(currentTgUser)
                .stream()
                .map(postMapper::entityToResponse)
                .toList();
    }

    public PostResponse addPost(Integer emojiId) {
        TelegramUser currentTgUser = currentUserUtil.getCurrentUser();
        Post post = new Post(null, currentTgUser, emojiId, LocalDateTime.now());
        return postMapper.entityToResponse(postRepository.save(post));
    }

    public void deletePost(UUID id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post with id" + id + "not found"));
        TelegramUser currentTgUser = currentUserUtil.getCurrentUser();
        if(!post.getUser().equals(currentTgUser)){
            throw new RuntimeException("You are not owner of this post");
        }
        postRepository.delete(post);
    }
}
