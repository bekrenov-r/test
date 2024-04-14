package com.lordsofcookies.moodapp.post;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Validated
public class PostController {
    public final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/my")
    public ResponseEntity<List<PostResponse>> getMyPosts(){
        return ResponseEntity.ok(postService.getMyPosts());
    }

    @PostMapping
    public ResponseEntity<PostResponse> addPost(@RequestParam @NotNull Integer emojiId){
        return ResponseEntity.ok(postService.addPost(emojiId));
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable UUID id){
        postService.deletePost(id);
    }
}
