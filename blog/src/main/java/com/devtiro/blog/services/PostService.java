package com.devtiro.blog.services;

import com.devtiro.blog.domain.CreatePostRequest;
import com.devtiro.blog.domain.Post;
import com.devtiro.blog.domain.UpdatePostRequest;
import com.devtiro.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId, UUID tagId);

    /*Page<Post> getAllPosts(UUID categoryId, UUID tagId, Pageable pageable);*/

    List<Post> getDraftPosts(User user);

    Post createPost(User user, CreatePostRequest createPostRequest);

    Post updatePost(UUID postId, UpdatePostRequest updatePostRequest);

    Post getPost(UUID postId);

    void deletePost(UUID id);

}
