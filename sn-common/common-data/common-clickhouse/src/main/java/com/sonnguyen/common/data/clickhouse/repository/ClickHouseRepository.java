package com.sonnguyen.common.data.clickhouse.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ClickHouseRepository<T, ID> {

    <S extends T> S save(S entity);

    <S extends T> List<S> saveAll(Iterable<S> entities);

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    List<T> findAll();

    List<T> findAllById(Iterable<ID> ids);

    long count();

    void deleteById(ID id);

    void delete(T entity);

    void deleteAllById(Iterable<ID> ids);

    void deleteAll(Iterable<? extends T> entities);

    void deleteAll();

    // ClickHouse specific operations
    void executeQuery(String query);

    List<Map<String, Object>> executeQueryWithResult(String query);

    <S extends T> void batchInsert(List<S> entities);

    // Pagination and sorting
    List<T> findAll(Pageable pageable);

    List<T> findAll(Sort sort);
}
