//package com.sonnguyen.common.data.clickhouse;
//
//import com.sonnguyen.common.data.clickhouse.repository.ClickHouseRepository;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//import java.util.List;
//import java.util.Optional;
//
//public class SimpleClickHouseRepository<T, ID> implements ClickHouseRepository<T, ID> {
//
//    private final ClickHouseEntityInformation<T, ID> entityInformation;
//    private final ClickHouseTemplate template;
//    private final Class<T> domainClass;
//
//    public SimpleClickHouseRepository(ClickHouseEntityInformation<T, ID> entityInformation,
//                                    ClickHouseTemplate template) {
//        this.entityInformation = entityInformation;
//        this.template = template;
//        this.domainClass = entityInformation.getJavaType();
//    }
//
//    @Override
//    public <S extends T> S save(S entity) {
//        if (entityInformation.isNew(entity)) {
//            template.insert(entity);
//        } else {
//            template.update(entity);
//        }
//        return entity;
//    }
//
//    @Override
//    public <S extends T> List<S> saveAll(Iterable<S> entities) {
//        List<S> entityList = StreamSupport.stream(entities.spliterator(), false)
//                .collect(Collectors.toList());
//        template.batchInsert(entityList);
//        return entityList;
//    }
//
//    @Override
//    public Optional<T> findById(ID id) {
//        String sql = String.format("SELECT * FROM %s WHERE %s = ?",
//            entityInformation.getTableName(),
//            entityInformation.getIdAttribute().getName());
//
//        return template.queryForObject(sql, domainClass, id);
//    }
//
//    @Override
//    public boolean existsById(ID id) {
//        String sql = String.format("SELECT count(*) FROM %s WHERE %s = ?",
//            entityInformation.getTableName(),
//            entityInformation.getIdAttribute().getName());
//
//        Long count = template.queryForObject(sql, Long.class, id);
//        return count != null && count > 0;
//    }
//
//    @Override
//    public List<T> findAll() {
//        String sql = String.format("SELECT * FROM %s", entityInformation.getTableName());
//        return template.query(sql, domainClass);
//    }
//
//    @Override
//    public List<T> findAllById(Iterable<ID> ids) {
//        List<ID> idList = StreamSupport.stream(ids.spliterator(), false)
//                .collect(Collectors.toList());
//
//        String sql = String.format("SELECT * FROM %s WHERE %s IN (%s)",
//            entityInformation.getTableName(),
//            entityInformation.getIdAttribute().getName(),
//            idList.stream().map(id -> "?").collect(Collectors.joining(",")));
//
//        return template.query(sql, domainClass, idList.toArray());
//    }
//
//    @Override
//    public long count() {
//        String sql = String.format("SELECT count(*) FROM %s", entityInformation.getTableName());
//        return template.queryForObject(sql, Long.class);
//    }
//
//    @Override
//    public void deleteById(ID id) {
//        String sql = String.format("DELETE FROM %s WHERE %s = ?",
//            entityInformation.getTableName(),
//            entityInformation.getIdAttribute().getName());
//
//        template.update(sql, id);
//    }
//
//    @Override
//    public void delete(T entity) {
//        ID id = entityInformation.getId(entity);
//        deleteById(id);
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends ID> ids) {
//        List<ID> idList = StreamSupport.stream(ids.spliterator(), false)
//                .collect(Collectors.toList());
//
//        String sql = String.format("DELETE FROM %s WHERE %s IN (%s)",
//            entityInformation.getTableName(),
//            entityInformation.getIdAttribute().getName(),
//            idList.stream().map(id -> "?").collect(Collectors.joining(",")));
//
//        template.update(sql, idList.toArray());
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends T> entities) {
//        List<ID> ids = StreamSupport.stream(entities.spliterator(), false)
//                .map(entityInformation::getId)
//                .collect(Collectors.toList());
//        deleteAllById(ids);
//    }
//
//    @Override
//    public void deleteAll() {
//        String sql = String.format("DELETE FROM %s", entityInformation.getTableName());
//        template.update(sql);
//    }
//
//    @Override
//    public List<T> findAll(Sort sort) {
//        String sql = String.format("SELECT * FROM %s ORDER BY %s",
//            entityInformation.getTableName(),
//            buildOrderByClause(sort));
//
//        return template.query(sql, domainClass);
//    }
//
//    @Override
//    public Page<T> findAll(Pageable pageable) {
//        // Implementation for pagination
//        return template.queryForPage(
//            String.format("SELECT * FROM %s", entityInformation.getTableName()),
//            domainClass,
//            pageable
//        );
//    }
//
//    private String buildOrderByClause(Sort sort) {
//        if (sort == null || !sort.iterator().hasNext()) {
//            return entityInformation.getIdAttribute().getName();
//        }
//
//        return sort.stream()
//                .map(order -> order.getProperty() + " " + order.getDirection())
//                .collect(Collectors.joining(", "));
//    }
//}
