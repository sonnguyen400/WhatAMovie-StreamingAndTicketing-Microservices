package com.sonnguyen.snultility.infrastructure.persistence.repository.custom;

import com.sonnguyen.snultility.application.dto.request.FindAllTagByKeyRequest;
import com.sonnguyen.snultility.domain.query.TagSearchQuery;
import com.sonnguyen.snultility.infrastructure.persistence.entity.TagEntity;

import java.util.List;

public interface CustomTagEntityRepository {
    List<TagEntity> findByKey(FindAllTagByKeyRequest request);

    List<TagEntity> search(TagSearchQuery tagSearchQuery);

    Long count(TagSearchQuery tagSearchQuery);
}
