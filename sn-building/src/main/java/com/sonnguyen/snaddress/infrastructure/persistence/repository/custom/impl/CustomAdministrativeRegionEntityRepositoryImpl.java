package com.sonnguyen.snaddress.infrastructure.persistence.repository.custom.impl;

import com.sonnguyen.snaddress.domain.query.AdministrativeRegionSearchQuery;
import com.sonnguyen.snaddress.infrastructure.persistence.entity.AdministrativeRegionEntity;
import com.sonnguyen.snaddress.infrastructure.persistence.repository.custom.CustomAdministrativeRegionEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class CustomAdministrativeRegionEntityRepositoryImpl implements CustomAdministrativeRegionEntityRepository {
    private final EntityManager entityManager;

    @Override
    public List<AdministrativeRegionEntity> search(AdministrativeRegionSearchQuery query) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AdministrativeRegionEntity> cq = cb.createQuery(AdministrativeRegionEntity.class);
        Root<AdministrativeRegionEntity> root = cq.from(AdministrativeRegionEntity.class);

        List<Predicate> predicates = buildPredicates(cb, root, query);
        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(root.get("level")), cb.asc(root.get("name")));

        TypedQuery<AdministrativeRegionEntity> typedQuery = entityManager.createQuery(cq);
        if (Objects.nonNull(query.getPageIndex()) && Objects.nonNull(query.getPageSize())) {
            typedQuery.setFirstResult(query.getPageIndex() * query.getPageSize());
            typedQuery.setMaxResults(query.getPageSize());
        }
        return typedQuery.getResultList();
    }

    @Override
    public long count(AdministrativeRegionSearchQuery query) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AdministrativeRegionEntity> root = cq.from(AdministrativeRegionEntity.class);

        List<Predicate> predicates = buildPredicates(cb, root, query);
        cq.select(cb.count(root));
        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getSingleResult();
    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<AdministrativeRegionEntity> root,
                                            AdministrativeRegionSearchQuery query) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("deleted"), Boolean.FALSE));

        if (Objects.nonNull(query.getCountryId())) {
            predicates.add(cb.equal(root.get("countryId"), query.getCountryId()));
        }
        if (Objects.nonNull(query.getParentId())) {
            predicates.add(cb.equal(root.get("parentId"), query.getParentId()));
        }
        if (Objects.nonNull(query.getType()) && !query.getType().isBlank()) {
            predicates.add(cb.equal(root.get("type"), query.getType()));
        }
        if (Objects.nonNull(query.getLevel())) {
            predicates.add(cb.equal(root.get("level"), query.getLevel()));
        }
        if (Objects.nonNull(query.getKeyword()) && !query.getKeyword().isBlank()) {
            String keyword = "%" + query.getKeyword().toLowerCase() + "%";
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get("name")), keyword),
                    cb.like(cb.lower(root.get("code")), keyword)
            ));
        }
        return predicates;
    }
}
