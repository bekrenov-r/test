package com.lordsofcookies.moodapp.post;

import com.lordsofcookies.moodapp.model.Post;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
    public ResponseEntity<List<Post>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/my")
    public ResponseEntity<List<Post>> getMyPosts(){
        return ResponseEntity.ok(postService.getMyPosts());
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@RequestParam @NotNull Integer emojiId){
        return ResponseEntity.ok(postService.addPost(emojiId));
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable UUID id){
        postService.deletePost(id);
    }
}
