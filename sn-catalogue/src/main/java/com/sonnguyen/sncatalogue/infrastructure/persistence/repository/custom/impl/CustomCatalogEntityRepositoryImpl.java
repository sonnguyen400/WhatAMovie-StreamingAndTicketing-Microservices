package com.sonnguyen.sncatalogue.infrastructure.persistence.repository.custom.impl;

import com.sonnguyen.common.util.StrUtils;
import com.sonnguyen.sncatalogue.domain.CatalogItem;
import com.sonnguyen.sncatalogue.domain.query.CatalogItemSearchQuery;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.CatalogItemEntity;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.PersonEntity;
import com.sonnguyen.sncatalogue.infrastructure.persistence.repository.custom.CustomCatalogEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class CustomCatalogEntityRepositoryImpl implements CustomCatalogEntityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<CatalogItemEntity> search(CatalogItemSearchQuery query) {
        Map<String, Object> params = new HashMap<>();
        String jpql = "SELECT * FROM CatalogItemEntity ct" + this.buildWhereQuery(query, params) + this.buildOrderByQuery(query);
        var typedQuery = entityManager.createNativeQuery(jpql, PersonEntity.class);
        params.forEach(typedQuery::setParameter);
        if (Objects.nonNull(query.getPageIndex()) && Objects.nonNull(query.getPageSize())) {
            typedQuery.setFirstResult(query.getPageIndex().intValue() * query.getPageSize().intValue());
            typedQuery.setMaxResults(query.getPageSize().intValue());
        }
        return typedQuery.getResultList();
    }

    @Override
    public Long count(CatalogItemSearchQuery query) {
        Map<String, Object> params = new HashMap<>();
        String jpql = "SELECT COUNT(ct.id) FROM CatalogItemEntity ct" + this.buildWhereQuery(query, params) + this.buildOrderByQuery(query);
        var typedQuery = entityManager.createNativeQuery(jpql, PersonEntity.class);
        params.forEach(typedQuery::setParameter);
        return (long) typedQuery.getFirstResult();
    }

    private String buildWhereQuery(CatalogItemSearchQuery query, Map<String, Object> params) {
        StringBuilder whereClause = new StringBuilder();

        if (StrUtils.isNotBlank(query.getKeyword())) {
            whereClause.append(" LEFT JOIN MessageLocaleEntity ml ON ct.id = ml.domainId AND ml.domainType = 'CATALOGUE_ITEM' ");
        }

        whereClause.append(" WHERE deleted = false ");

        if (Objects.nonNull(query.getChannels())) {
            whereClause.append(" AND ct.distributionChannel IN :channels ");
            params.put("channels", query.getChannels());
        }

        if (Objects.nonNull(query.getContentStatuses())) {
            whereClause.append(" AND ct.status IN :contentStatuses ");
            params.put("contentStatuses", query.getContentStatuses());
        }

        if (Objects.nonNull(query.getContentTypes())) {
            whereClause.append(" AND ct.contentType IN :contentTypes ");
            params.put("contentTypes", query.getContentTypes());
        }

        if (Objects.nonNull(query.getParentId())) {
            whereClause.append(" AND ct.parentId = :parentId ");
            params.put("parentId", query.getParentId());
        }

        if (StrUtils.isNotBlank(query.getKeyword())) {
            whereClause.append(" AND ( ct.title ILIKE :keyword OR ct.description ILIKE :keyword or ml.properties ILIKE :keyword) ");
            params.put("keyword", "%" + query.getKeyword().trim() + "%");
        }

        return whereClause.toString();
    }

    private String buildOrderByQuery(CatalogItemSearchQuery query) {
        return "ORDER BY" + query.getSortBy().stream()
                .map(sortOrder -> "ps." + sortOrder.getField() + " " + sortOrder.getSort().name())
                .collect(Collectors.joining(", "));
    }
}
