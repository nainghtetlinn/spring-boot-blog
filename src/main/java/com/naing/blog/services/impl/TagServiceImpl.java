package com.naing.blog.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.naing.blog.domain.entities.Tag;
import com.naing.blog.repositories.TagRepository;
import com.naing.blog.services.TagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }

}
