package com.sonnguyen.common.data.clickhouse.repository.impl;

import com.sonnguyen.common.data.clickhouse.repository.ClickHouseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractClickHouseRepository<T, ID> implements ClickHouseRepository<T, ID> {

    protected final JdbcTemplate jdbcTemplate;
    protected final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    protected final Class<T> entityClass;
    protected final String tableName;

    @SuppressWarnings("unchecked")
    public AbstractClickHouseRepository(JdbcTemplate jdbcTemplate, 
                                      NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                      String tableName) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.tableName = tableName;
    }

    @Override
    public <S extends T> S save(S entity) {
        String sql = generateInsertSQL(entity);
        SqlParameterSource params = new BeanPropertySqlParameterSource(entity);
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, params, keyHolder);
        
        // Set generated ID if available
        if (keyHolder.getKeys() != null && keyHolder.getKeys().containsKey("id")) {
            setIdValue(entity, (ID) keyHolder.getKeys().get("id"));
        }
        
        return entity;
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        List<S> entityList = new ArrayList<>();
        entities.forEach(entityList::add);
        batchInsert(entityList);
        return entityList;
    }

    @Override
    public Optional<T> findById(ID id) {
        String sql = String.format("SELECT * FROM %s WHERE id = ?", tableName);
        try {
            T entity = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(entityClass), id);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(ID id) {
        String sql = String.format("SELECT COUNT(*) FROM %s WHERE id = ?", tableName);
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public List<T> findAll() {
        String sql = String.format("SELECT * FROM %s", tableName);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(entityClass));
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        List<ID> idList = new ArrayList<>();
        ids.forEach(idList::add);
        
        String sql = String.format("SELECT * FROM %s WHERE id IN (%s)", 
            tableName, 
            idList.stream().map(id -> "?").collect(Collectors.joining(","))
        );
        
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(entityClass), idList.toArray());
    }

    @Override
    public long count() {
        String sql = String.format("SELECT COUNT(*) FROM %s", tableName);
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public void deleteById(ID id) {
        String sql = String.format("DELETE FROM %s WHERE id = ?", tableName);
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void delete(T entity) {
        ID id = getIdValue(entity);
        if (id != null) {
            deleteById(id);
        }
    }

    @Override
    public void deleteAllById(Iterable<ID> ids) {
        List<ID> idList = new ArrayList<>();
        ids.forEach(idList::add);
        
        String sql = String.format("DELETE FROM %s WHERE id IN (%s)", 
            tableName, 
            idList.stream().map(id -> "?").collect(Collectors.joining(","))
        );
        
        jdbcTemplate.update(sql, idList.toArray());
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        List<ID> ids = new ArrayList<>();
        entities.forEach(entity -> {
            ID id = getIdValue(entity);
            if (id != null) ids.add(id);
        });
        deleteAllById(ids);
    }

    @Override
    public void deleteAll() {
        String sql = String.format("DELETE FROM %s", tableName);
        jdbcTemplate.update(sql);
    }

    @Override
    public void executeQuery(String query) {
        jdbcTemplate.execute(query);
    }

    @Override
    public List<Map<String, Object>> executeQueryWithResult(String query) {
        return jdbcTemplate.queryForList(query);
    }

    @Override
    public <S extends T> void batchInsert(List<S> entities) {
        if (entities.isEmpty()) return;
        
        String sql = generateInsertSQL(entities.get(0));
        List<SqlParameterSource> params = entities.stream()
                .map(BeanPropertySqlParameterSource::new)
                .collect(Collectors.toList());
        
        namedParameterJdbcTemplate.batchUpdate(sql, params.toArray(new SqlParameterSource[0]));
    }

    @Override
    public List<T> findAll(Pageable pageable) {
        String sql = String.format("SELECT * FROM %s ORDER BY %s LIMIT ? OFFSET ?", 
            tableName, 
            getOrderByClause(pageable.getSort())
        );
        
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(entityClass), 
            pageable.getPageSize(), pageable.getOffset());
    }

    @Override
    public List<T> findAll(Sort sort) {
        String sql = String.format("SELECT * FROM %s ORDER BY %s", 
            tableName, 
            getOrderByClause(sort)
        );
        
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(entityClass));
    }

    // Utility methods
    private String generateInsertSQL(T entity) {
        Field[] fields = entityClass.getDeclaredFields();
        List<String> columnNames = new ArrayList<>();
        List<String> paramNames = new ArrayList<>();
        
        for (Field field : fields) {
            field.setAccessible(true);
            String columnName = camelToSnake(field.getName());
            columnNames.add(columnName);
            paramNames.add(":" + field.getName());
        }
        
        return String.format("INSERT INTO %s (%s) VALUES (%s)", 
            tableName, 
            String.join(", ", columnNames),
            String.join(", ", paramNames)
        );
    }

    private String getOrderByClause(Sort sort) {
        if (sort == null || !sort.iterator().hasNext()) {
            return "id";
        }
        
        return sort.stream()
                .map(order -> camelToSnake(order.getProperty()) + " " + order.getDirection())
                .collect(Collectors.joining(", "));
    }

    @SuppressWarnings("unchecked")
    private ID getIdValue(T entity) {
        try {
            Field idField = entityClass.getDeclaredField("id");
            idField.setAccessible(true);
            return (ID) idField.get(entity);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get ID value", e);
        }
    }

    private void setIdValue(T entity, ID id) {
        try {
            Field idField = entityClass.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(entity, id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to set ID value", e);
        }
    }

    private String camelToSnake(String str) {
        return str.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}
