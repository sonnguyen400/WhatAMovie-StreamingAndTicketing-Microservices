package com.sonnguyen.snaddress.infrastructure.persistence.repository.custom.impl;

import com.sonnguyen.snaddress.domain.query.CountrySearchQuery;
import com.sonnguyen.snaddress.infrastructure.persistence.entity.CountryEntity;
import com.sonnguyen.snaddress.infrastructure.persistence.repository.custom.CustomCountryEntityRepository;
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
public class CustomCountryEntityRepositoryImpl implements CustomCountryEntityRepository {
    private final EntityManager entityManager;

    @Override
    public List<CountryEntity> search(CountrySearchQuery query) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CountryEntity> cq = cb.createQuery(CountryEntity.class);
        Root<CountryEntity> root = cq.from(CountryEntity.class);

        List<Predicate> predicates = buildPredicates(cb, root, query);
        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(root.get("name")));

        TypedQuery<CountryEntity> typedQuery = entityManager.createQuery(cq);
        if (Objects.nonNull(query.getPageIndex()) && Objects.nonNull(query.getPageSize())) {
            typedQuery.setFirstResult(query.getPageIndex() * query.getPageSize());
            typedQuery.setMaxResults(query.getPageSize());
        }
        return typedQuery.getResultList();
    }

    @Override
    public long count(CountrySearchQuery query) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CountryEntity> root = cq.from(CountryEntity.class);

        List<Predicate> predicates = buildPredicates(cb, root, query);
        cq.select(cb.count(root));
        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getSingleResult();
    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<CountryEntity> root, CountrySearchQuery query) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get("deleted"), Boolean.FALSE));

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
