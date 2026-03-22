package com.sonnguyen.snultility.application.service;

import com.sonnguyen.common.model.application.request.FindByIdsRequest;
import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.snultility.application.dto.request.FindAllTagByKeyRequest;
import com.sonnguyen.snultility.application.dto.request.TagAssignmentRequest;
import com.sonnguyen.snultility.application.dto.request.TagCreateOrUpdateRequest;
import com.sonnguyen.snultility.application.dto.request.TagSearchRequest;
import com.sonnguyen.snultility.application.dto.response.TagDTO;
import com.sonnguyen.snultility.domain.ContentTag;
import com.sonnguyen.snultility.domain.Tag;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface TagService {
    Tag getTagById(UUID id);

    TagDTO createTag(TagCreateOrUpdateRequest request);

    List<Tag> findAllTagsByKey(FindAllTagByKeyRequest request);

    List<ContentTag> assignTags(TagAssignmentRequest request);

    Tag updateById(UUID id, TagCreateOrUpdateRequest request);

    Collection<Tag> findAllTagsByIds(FindByIdsRequest request);

    PagingResponse<Tag> search(TagSearchRequest request);

    void deleteTagById(UUID id);
}
