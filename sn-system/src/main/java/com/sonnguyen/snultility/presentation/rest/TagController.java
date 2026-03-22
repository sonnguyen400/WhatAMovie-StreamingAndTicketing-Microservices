package com.sonnguyen.snultility.presentation.rest;

import com.sonnguyen.common.model.application.request.FindByIdsRequest;
import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.snultility.application.dto.request.FindAllTagByKeyRequest;
import com.sonnguyen.snultility.application.dto.request.TagAssignmentRequest;
import com.sonnguyen.snultility.application.dto.request.TagCreateOrUpdateRequest;
import com.sonnguyen.snultility.application.dto.request.TagSearchRequest;
import com.sonnguyen.snultility.application.dto.response.TagDTO;
import com.sonnguyen.snultility.domain.ContentTag;
import com.sonnguyen.snultility.domain.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api")
public interface TagController {

    @PostMapping("/v1/tags/{id}")
    Response<Tag> getTagById(@PathVariable UUID id);

    @PostMapping("/v1/tags/create")
    Response<TagDTO> createTag(@RequestBody TagCreateOrUpdateRequest request);

    @PostMapping("/v1/tags/{id}/update")
    Response<Tag> updateTagById(@PathVariable UUID id, @RequestBody TagCreateOrUpdateRequest request);

    @PostMapping("/v1/tags/{id}/delete")
    Response<Void> deleteTagById(@PathVariable UUID id);

    @PostMapping("/v1/tags/search")
    PagingResponse<Tag> searchAllTags(@RequestBody TagSearchRequest request);

    @PostMapping("/v1/tags/find-by-key")
    Response<List<Tag>> findAllTagsByKey(@RequestBody FindAllTagByKeyRequest request);

    @PostMapping("/v1/tags/find-by-ids")
    Response<Collection<Tag>> findAllTagsByIds(@RequestBody FindByIdsRequest findByIdsRequest);

    @PostMapping("/v1/tags/assign-to-contents")
    Response<List<ContentTag>> assignTags(TagAssignmentRequest request);
}
