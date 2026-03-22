package com.sonnguyen.snultility.presentation.rest.impl;

import com.sonnguyen.common.model.application.request.FindByIdsRequest;
import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.snultility.application.dto.request.FindAllTagByKeyRequest;
import com.sonnguyen.snultility.application.dto.request.TagAssignmentRequest;
import com.sonnguyen.snultility.application.dto.request.TagCreateOrUpdateRequest;
import com.sonnguyen.snultility.application.dto.request.TagSearchRequest;
import com.sonnguyen.snultility.application.dto.response.TagDTO;
import com.sonnguyen.snultility.application.service.TagService;
import com.sonnguyen.snultility.domain.ContentTag;
import com.sonnguyen.snultility.domain.Tag;
import com.sonnguyen.snultility.presentation.rest.TagController;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class TagControllerImpl implements TagController {
    TagService tagService;

    @Override
    public Response<Tag> getTagById(UUID id) {
        return Response.of(this.tagService.getTagById(id));
    }

    @Override
    public Response<TagDTO> createTag(TagCreateOrUpdateRequest request) {
        return Response.of(this.tagService.createTag(request));
    }

    @Override
    public Response<Tag> updateTagById(UUID id, TagCreateOrUpdateRequest request) {
        return Response.of(this.tagService.updateById(id, request));
    }

    @Override
    public Response<Void> deleteTagById(UUID id) {
        this.tagService.deleteTagById(id);
        return Response.ok();
    }

    @Override
    public PagingResponse<Tag> searchAllTags(TagSearchRequest request) {
        return this.tagService.search(request);
    }

    @Override
    public Response<List<Tag>> findAllTagsByKey(FindAllTagByKeyRequest request) {
        return Response.of(this.tagService.findAllTagsByKey(request));
    }

    @Override
    public Response<Collection<Tag>> findAllTagsByIds(FindByIdsRequest findByIdsRequest) {
        return Response.of(this.tagService.findAllTagsByIds(findByIdsRequest));
    }

    @Override
    public Response<List<ContentTag>> assignTags(TagAssignmentRequest request) {
        return Response.of(this.tagService.assignTags(request));
    }
}
