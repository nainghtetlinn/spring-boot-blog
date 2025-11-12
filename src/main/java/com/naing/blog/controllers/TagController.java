package com.naing.blog.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naing.blog.domain.dtos.CreateTagsRequest;
import com.naing.blog.domain.dtos.TagResponse;
import com.naing.blog.domain.entities.Tag;
import com.naing.blog.mappers.TagMapper;
import com.naing.blog.services.TagService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @Operation(summary = "List all tags")
    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<Tag> tags = tagService.getTags();
        List<TagResponse> tagResponses = tags
                .stream()
                .map(tagMapper::toTagResponse)
                .toList();
        return ResponseEntity.ok(tagResponses);
    }

    @Operation(summary = "Create tags")
    @PostMapping()
    public ResponseEntity<List<TagResponse>> createTags(@Valid @RequestBody CreateTagsRequest createTagsRequest) {
        List<Tag> savedTags = tagService.createTags(createTagsRequest.getNames());
        List<TagResponse> createdTagResponses = savedTags.stream()
                .map(tagMapper::toTagResponse)
                .toList();
        return new ResponseEntity<>(createdTagResponses, HttpStatus.CREATED);
    }

}
