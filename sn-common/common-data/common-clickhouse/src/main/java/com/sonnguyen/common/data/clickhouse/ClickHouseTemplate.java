//package com.sonnguyen.common.data.clickhouse;
//
//import com.sonnguyen.common.data.clickhouse.jdbc.ClickHouseMetadataRegistry;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//public class ClickHouseTemplate {
//
//    private final JdbcTemplate jdbcTemplate;
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//    private final ClickHouseMetadataRegistry metadataRegistry;
//
//    public <T> List<T> query(String sql, Class<T> clazz, Object... params) {
//        RowMapper<T> rowMapper = createRowMapper(clazz);
//        return jdbcTemplate.query(sql, rowMapper, params);
//    }
//
//    public <T> Optional<T> queryForObject(String sql, Class<T> clazz, Object... params) {
//        try {
//            T result = jdbcTemplate.queryForObject(sql, clazz, params);
//            return Optional.ofNullable(result);
//        } catch (Exception e) {
//            return Optional.empty();
//        }
//    }
//
//    public void insert(Object entity) {
//        EntityMetadata metadata = metadataRegistry.getMetadata(entity.getClass());
//        String sql = generateInsertSQL(metadata);
//        // Implementation...
//    }
//
//    public <T> void batchInsert(List<T> entities) {
//        if (entities.isEmpty()) return;
//
//        EntityMetadata metadata = metadataRegistry.getMetadata(entities.get(0).getClass());
//        String sql = generateInsertSQL(metadata);
//
//        jdbcTemplate.batchUpdate(sql, entities.stream()
//                .map(entity -> createParameterSource(entity, metadata))
//                .collect(Collectors.toList()));
//    }
//
//    // Các method utility khác...
//}
