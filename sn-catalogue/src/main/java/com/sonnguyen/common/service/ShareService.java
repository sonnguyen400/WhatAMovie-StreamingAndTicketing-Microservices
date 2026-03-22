package com.sonnguyen.common.service;

import com.sonnguyen.common.whatamoviemodel.domain.ContentTag;
import com.sonnguyen.common.whatamoviemodel.domain.Tag;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ShareService {
    List<ContentTag> assignTagsToDomain(List<ContentTag> contentTags);

    Collection<Tag> getTagsByIds(List<UUID> tagIds);
}
