package com.naing.blog.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.naing.blog.domain.PostStatus;
import com.naing.blog.domain.entities.Category;
import com.naing.blog.domain.entities.Post;
import com.naing.blog.domain.entities.Tag;
import com.naing.blog.domain.entities.User;
import com.naing.blog.repositories.PostRepository;
import com.naing.blog.services.CategoryService;
import com.naing.blog.services.PostService;
import com.naing.blog.services.TagService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    @Override
    @Transactional
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
        if (categoryId != null && tagId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndCategoryAndTags(PostStatus.PUBLISHED,
                    category, tag);
        }

        if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED,
                    category);
        }

        if (tagId != null) {
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndTags(PostStatus.PUBLISHED,
                    tag);
        }

        return postRepository.findAllByStatus(PostStatus.PUBLISHED);
    }

    @Override
    public List<Post> getDraftPosts(User user) {
        return postRepository.findAllByAuthorAndStatus(user, PostStatus.DRAFT);
    }

}
