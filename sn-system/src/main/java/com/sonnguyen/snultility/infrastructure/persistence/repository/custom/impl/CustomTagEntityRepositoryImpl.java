package com.sonnguyen.snultility.infrastructure.persistence.repository.custom.impl;

import com.sonnguyen.common.util.CollectionUtils;
import com.sonnguyen.snultility.application.dto.request.FindAllTagByKeyRequest;
import com.sonnguyen.snultility.infrastructure.persistence.entity.TagEntity;
import com.sonnguyen.snultility.infrastructure.persistence.repository.custom.CustomTagEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class CustomTagEntityRepositoryImpl implements CustomTagEntityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<TagEntity> findByKey(FindAllTagByKeyRequest findAllTagByKeyRequest) {
        StringBuilder jpql = new StringBuilder("SELECT t FROM TagEntity t WHERE t.deleted = false");
        Map<String, Object> values = new HashMap<>();
        if (CollectionUtils.isNotEmpty(findAllTagByKeyRequest.getIds())) {
            jpql.append(" AND t.id IN :ids ");
            values.put("ids", findAllTagByKeyRequest.getIds());
        }
        if (CollectionUtils.isNotEmpty(findAllTagByKeyRequest.getDomainId())) {
            jpql.append(" AND t.domainId IN :domainIds ");
            values.put("domainIds", findAllTagByKeyRequest.getDomainId());
        }
        if (CollectionUtils.isNotEmpty(findAllTagByKeyRequest.getDomainTypes())) {
            jpql.append(" AND t.domainType IN :domainTypes ");
            values.put("domainTypes", findAllTagByKeyRequest.getDomainTypes());
        }

        var query = entityManager.createQuery(jpql.toString(), TagEntity.class);
        values.forEach(query::setParameter);
        return query.getResultList();
    }
}
