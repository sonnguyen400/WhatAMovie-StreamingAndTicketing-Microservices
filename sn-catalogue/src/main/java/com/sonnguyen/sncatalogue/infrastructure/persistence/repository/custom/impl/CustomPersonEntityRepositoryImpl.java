package com.sonnguyen.sncatalogue.infrastructure.persistence.repository.custom.impl;

import com.sonnguyen.common.util.StrUtils;
import com.sonnguyen.sncatalogue.domain.query.PersonSearchQuery;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.PersonEntity;
import com.sonnguyen.sncatalogue.infrastructure.persistence.repository.custom.CustomPersonEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class CustomPersonEntityRepositoryImpl implements CustomPersonEntityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PersonEntity> search(PersonSearchQuery query) {
        Map<String, Object> params = new java.util.HashMap<>();
        String jpql = "SELECT * FROM PersonEntity ps" + buildWhereQuery(query, params) + buildOrderByQuery(query);
        var typedQuery = entityManager.createNativeQuery(jpql, PersonEntity.class);
        params.forEach(typedQuery::setParameter);
        if (Objects.nonNull(query.getPageIndex()) && Objects.nonNull(query.getPageSize())) {
            typedQuery.setFirstResult(query.getPageIndex().intValue() * query.getPageSize().intValue());
            typedQuery.setMaxResults(query.getPageSize().intValue());
        }
        return typedQuery.getResultList();
    }

    @Override
    public Long count(PersonSearchQuery query) {
        Map<String, Object> params = new java.util.HashMap<>();
        String jpql = "SELECT COUNT(ps.id) FROM PersonEntity ps" + buildWhereQuery(query, params);
        var typedQuery = entityManager.createNativeQuery(jpql);
        params.forEach(typedQuery::setParameter);
        return ((Number) typedQuery.getSingleResult()).longValue();
    }


    private String buildWhereQuery(PersonSearchQuery query, Map<String, Object> params) {
        StringBuilder whereClause = new StringBuilder();

        if (Objects.nonNull(query.getRole()) || Objects.nonNull(query.getCatalogId())) {
            whereClause.append(" LEFT JOIN CatalogItemPersonEntity cip ON ps.id = cip.personId ");
        }
        if (StrUtils.isNotBlank(query.getKeyword())) {
            whereClause.append(" LEFT JOIN MessageLocaleEntity ml ON ps.id = ml.domainId AND ml.domainType = 'CATALOGUE_PERSON' ");
        }
        whereClause.append(" WHERE ps.deleted = false ");

        if (StrUtils.isNotBlank(query.getKeyword())) {
            whereClause.append(" AND ( ml.properties->>'fullName' ILIKE :keyword OR ps.fullName ILIKE :keyword ) ");
            params.put("keyword", "%" + query.getKeyword().trim() + "%");
        }

        if (Objects.nonNull(query.getRole())) {
            whereClause.append(" AND cip.role = :role ");
            params.put("role", query.getRole());
        }

        if (Objects.nonNull(query.getCatalogId())) {
            whereClause.append(" AND cip.catalogId = :catalogId ");
            params.put("catalogId", query.getCatalogId());
        }

        return whereClause.toString();
    }

    private String buildOrderByQuery(PersonSearchQuery query) {
        return "ORDER BY" + query.getSortBy().stream()
                .map(sortOrder -> "ps." + sortOrder.getField() + " " + sortOrder.getSort().name())
                .collect(Collectors.joining(", "));
    }
}
