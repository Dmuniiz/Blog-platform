package com.devtiro.blog.services.impl;

import com.devtiro.blog.domain.*;
import com.devtiro.blog.domain.dtos.PostDto;
import com.devtiro.blog.repositories.PostRepository;
import com.devtiro.blog.repositories.PostSpecs;
import com.devtiro.blog.services.CategoryService;
import com.devtiro.blog.services.PostService;
import com.devtiro.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    /*@Override
    @Transactional(readOnly = true)
    public Page<Post> getAllPosts(UUID categoryId, UUID tagId, Pageable pageable) {

        Specification<Post> spec = Specification.where(PostSpecs.hasStatus(PostStatus.PUBLISHED));

        if (categoryId != null) {
            spec = spec.and(PostSpecs.hasCategory(categoryId));
        }

        if (tagId != null) {
            spec = spec.and(PostSpecs.hasTag(tagId));
        }

        // The repository handles the LIMIT and OFFSET logic automatically
        return postRepository.findAll(spec, pageable);
    }*/

    @Transactional(readOnly = true)
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
        if(categoryId != null && tagId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED,
                    category,
                    tag
            );
        }
        if(categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategory(
                    PostStatus.PUBLISHED,
                    category
            );
        }
        if(tagId != null) {
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndTagsContaining(
                    PostStatus.PUBLISHED,
                    tag
            );
        }
        return postRepository.findAllByStatus(PostStatus.PUBLISHED);
    }

    @Override
    public List<Post> getDraftPosts(User user) {
       return postRepository.findAllByAuthorAndStatus(user, PostStatus.DRAFT);
    }

}
