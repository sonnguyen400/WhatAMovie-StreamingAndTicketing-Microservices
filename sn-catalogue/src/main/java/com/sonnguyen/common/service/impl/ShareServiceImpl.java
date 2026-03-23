package com.sonnguyen.common.service.impl;

import com.sonnguyen.common.model.application.request.FindByIdsRequest;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.common.service.ShareService;
import com.sonnguyen.common.util.CollectionUtils;
import com.sonnguyen.common.whatamovieclient.client.SystemClient;
import com.sonnguyen.common.whatamoviemodel.application.request.TagAssignmentRequest;
import com.sonnguyen.common.whatamoviemodel.domain.ContentTag;
import com.sonnguyen.common.whatamoviemodel.domain.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ShareServiceImpl implements ShareService {
    private final SystemClient systemClient;

    @Override
    public List<ContentTag> assignTagsToDomain(List<ContentTag> contentTags) {
        if (CollectionUtils.isEmpty(contentTags)) return List.of();
        TagAssignmentRequest request = TagAssignmentRequest.builder()
                .domainId(contentTags.get(0).getDomainId())
                .domainType(contentTags.get(0).getDomainType())
                .tagIds(contentTags.stream().map(ContentTag::getTagId).toList())
                .build();
        Response<List<ContentTag>> response = this.systemClient.assignTags(request);
        if (response.isSuccess() && response.getData() != null) {
            return response.getData();
        }
        return List.of();
    }

    @Override
    public Collection<Tag> getTagsByIds(List<UUID> tagIds) {
        if (CollectionUtils.isEmpty(tagIds)) return List.of();
        Response<Collection<Tag>> response = this.systemClient.findAllTagsByIds(new FindByIdsRequest(tagIds));
        if (response.isSuccess() && response.getData() != null) {
            return response.getData();
        }
        return List.of();
    }
}
