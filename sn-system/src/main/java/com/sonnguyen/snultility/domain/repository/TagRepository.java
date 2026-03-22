package com.sonnguyen.snultility.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.snultility.application.dto.request.FindAllTagByKeyRequest;
import com.sonnguyen.snultility.domain.Tag;
import com.sonnguyen.snultility.domain.query.TagSearchQuery;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface TagRepository extends DomainRepository<Tag, UUID> {
    List<Tag> findByKey(FindAllTagByKeyRequest request);

    List<Tag> search(TagSearchQuery tagSearchQuery);
}
