//package com.sonnguyen.common.data.clickhouse;
//
//import com.sonnguyen.common.data.clickhouse.annotation.ClickHouseQuery;
//import org.springframework.data.repository.core.RepositoryMetadata;
//import org.springframework.data.repository.query.RepositoryQuery;
//import org.springframework.data.repository.query.ResultProcessor;
//import org.springframework.data.repository.query.Parameters;
//import org.springframework.data.repository.query.ParametersParameterAccessor;
//import org.springframework.data.repository.query.QueryMethod;
//import org.springframework.data.repository.query.RepositoryMetadata;
//import org.springframework.jdbc.core.ResultSetExtractor;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.lang.reflect.Method;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//public class StringBasedClickHouseQuery implements RepositoryQuery {
//
//    private final QueryMethod queryMethod;
//    private final ClickHouseTemplate template;
//    private final String queryString;
//    private final Class<?> domainClass;
//
//    public StringBasedClickHouseQuery(Method method, RepositoryMetadata metadata,
//                                    ClickHouseTemplate template, String queryString) {
//        this.queryMethod = new QueryMethod(method, metadata);
//        this.template = template;
//        this.domainClass = metadata.getDomainType();
//
//        // Parse query annotation
//        ClickHouseQuery queryAnnotation = method.getAnnotation(ClickHouseQuery.class);
//        this.queryString = queryString;
//    }
//
//    public StringBasedClickHouseQuery(Method method, RepositoryMetadata metadata,
//                                    ClickHouseTemplate template, String queryString,
//                                    boolean isNativeQuery) {
//        this.queryMethod = new QueryMethod(method, metadata);
//        this.template = template;
//        this.domainClass = metadata.getDomainType();
//        this.queryString = queryString;
//    }
//
//    @Override
//    public Object execute(Object[] parameters) {
//        Parameters<?> methodParameters = queryMethod.getParameters();
//        String processedQuery = processQueryWithParameters(parameters, methodParameters);
//
//        ResultProcessor resultProcessor = queryMethod.getResultProcessor();
//        Class<?> returnType = resultProcessor.getReturnedType().getReturnedType();
//
//        return executeQuery(processedQuery, parameters, returnType, methodParameters);
//    }
//
//    private String processQueryWithParameters(Object[] parameters, Parameters<?> methodParameters) {
//        String query = this.queryString;
//
//        // Xử lý named parameters (:name)
//        if (query.contains(":")) {
//            Map<String, Object> paramMap = createParameterMap(parameters, methodParameters);
//            query = replaceNamedParameters(query, paramMap);
//        }
//
//        return query;
//    }
//
//    private Map<String, Object> createParameterMap(Object[] parameters, Parameters<?> methodParameters) {
//        ParametersParameterAccessor accessor = new ParametersParameterAccessor(methodParameters, parameters);
//
//        return methodParameters.getBindableParameters().stream()
//                .collect(Collectors.toMap(
//                    param -> {
//                        // Ưu tiên @Param annotation, sau đó đến tên parameter
//                        if (param.isNamedParameter()) {
//                            return param.getName().orElse(String.valueOf(param.getIndex()));
//                        }
//                        return String.valueOf(param.getIndex());
//                    },
//                    accessor::getValue
//                ));
//    }
//
//    private String replaceNamedParameters(String query, Map<String, Object> paramMap) {
//        String processedQuery = query;
//
//        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
//            String paramName = entry.getKey();
//            Object paramValue = entry.getValue();
//
//            // Thay thế :paramName bằng giá trị
//            if (paramValue != null) {
//                processedQuery = processedQuery.replace(":" + paramName, paramValue.toString());
//            } else {
//                processedQuery = processedQuery.replace(":" + paramName, "NULL");
//            }
//        }
//
//        return processedQuery;
//    }
//
//    private Object executeQuery(String query, Object[] parameters, Class<?> returnType,
//                               Parameters<?> methodParameters) {
//
//        Object[] queryParameters = extractBindableParameters(parameters, methodParameters);
//
//        // Xử lý các kiểu return type khác nhau
//        if (queryMethod.isModifyingQuery()) {
//            return executeModifyingQuery(query, queryParameters);
//        } else if (queryMethod.isCollectionQuery()) {
//            return executeCollectionQuery(query, returnType, queryParameters);
//        } else if (queryMethod.isQueryForEntity()) {
//            return executeEntityQuery(query, returnType, queryParameters);
//        } else if (queryMethod.isStreamQuery()) {
//            return executeStreamQuery(query, returnType, queryParameters);
//        } else if (isCountQuery(returnType)) {
//            return executeCountQuery(query, queryParameters);
//        } else if (isExistsQuery(returnType)) {
//            return executeExistsQuery(query, queryParameters);
//        } else {
//            // Projection, DTO, hoặc custom return type
//            return executeProjectionQuery(query, returnType, queryParameters);
//        }
//    }
//
//    private Object[] extractBindableParameters(Object[] parameters, Parameters<?> methodParameters) {
//        return methodParameters.getBindableParameters().stream()
//                .map(param -> parameters[param.getIndex()])
//                .toArray();
//    }
//
//    private int executeModifyingQuery(String query, Object[] parameters) {
//        return template.update(query, parameters);
//    }
//
//    @SuppressWarnings("unchecked")
//    private Object executeCollectionQuery(String query, Class<?> returnType, Object[] parameters) {
//        if (List.class.isAssignableFrom(returnType)) {
//            Class<?> elementType = queryMethod.getReturnedObjectType();
//            return template.query(query, elementType, parameters);
//        } else if (Map.class.isAssignableFrom(returnType)) {
//            return template.queryForMap(query, parameters);
//        } else {
//            return template.query(query, domainClass, parameters);
//        }
//    }
//
//    private Object executeEntityQuery(String query, Class<?> returnType, Object[] parameters) {
//        if (Optional.class.isAssignableFrom(returnType)) {
//            Class<?> actualType = queryMethod.getReturnedObjectType();
//            return template.queryForObject(query, actualType, parameters);
//        } else {
//            return template.queryForObject(query, returnType, parameters)
//                    .orElseThrow(() -> new RuntimeException("No result found for query: " + query));
//        }
//    }
//
//    private Object executeStreamQuery(String query, Class<?> returnType, Object[] parameters) {
//        List<?> results = template.query(query, domainClass, parameters);
//        return results.stream();
//    }
//
//    private Long executeCountQuery(String query, Object[] parameters) {
//        // Đảm bảo query là COUNT query
//        String countQuery = ensureCountQuery(query);
//        return template.queryForObject(countQuery, Long.class, parameters)
//                .orElse(0L);
//    }
//
//    private Boolean executeExistsQuery(String query, Object[] parameters) {
//        String existsQuery = "SELECT 1 FROM (" + query + ") LIMIT 1";
//        Optional<Integer> result = template.queryForObject(existsQuery, Integer.class, parameters);
//        return result.isPresent();
//    }
//
//    private Object executeProjectionQuery(String query, Class<?> returnType, Object[] parameters) {
//        // Xử lý projection interfaces và DTOs
//        if (isProjectionInterface(returnType)) {
//            return template.query(query, createProjectionRowMapper(returnType), parameters);
//        } else if (isCustomDto(returnType)) {
//            return template.query(query, createDtoRowMapper(returnType), parameters);
//        } else {
//            // Default: trả về List<Map>
//            return template.queryForList(query, parameters);
//        }
//    }
//
//    private String ensureCountQuery(String originalQuery) {
//        String upperQuery = originalQuery.toUpperCase().trim();
//
//        // Nếu đã là COUNT query thì giữ nguyên
//        if (upperQuery.startsWith("SELECT COUNT")) {
//            return originalQuery;
//        }
//
//        // Chuyển đổi thành COUNT query
//        if (upperQuery.startsWith("SELECT")) {
//            int fromIndex = upperQuery.indexOf("FROM");
//            if (fromIndex > 0) {
//                return "SELECT COUNT(*) " + originalQuery.substring(fromIndex);
//            }
//        }
//
//        // Fallback: wrap query
//        return "SELECT COUNT(*) FROM (" + originalQuery + ")";
//    }
//
//    private boolean isCountQuery(Class<?> returnType) {
//        return long.class.equals(returnType) || Long.class.equals(returnType);
//    }
//
//    private boolean isExistsQuery(Class<?> returnType) {
//        return boolean.class.equals(returnType) || Boolean.class.equals(returnType);
//    }
//
//    private boolean isProjectionInterface(Class<?> returnType) {
//        return returnType.isInterface() && returnType.getSimpleName().contains("Projection");
//    }
//
//    private boolean isCustomDto(Class<?> returnType) {
//        return !returnType.isInterface() &&
//               !returnType.isPrimitive() &&
//               !returnType.getName().startsWith("java.");
//    }
//
//    private RowMapper<?> createProjectionRowMapper(Class<?> projectionType) {
//        return (rs, rowNum) -> {
//            // Tạo proxy cho projection interface
//            return createProjectionProxy(projectionType, rs);
//        };
//    }
//
//    private RowMapper<?> createDtoRowMapper(Class<?> dtoType) {
//        return (rs, rowNum) -> {
//            try {
//                Object dto = dtoType.getDeclaredConstructor().newInstance();
//                // Map ResultSet columns to DTO fields
//                mapResultSetToDto(rs, dto);
//                return dto;
//            } catch (Exception e) {
//                throw new RuntimeException("Failed to create DTO instance", e);
//            }
//        };
//    }
//
//    private Object createProjectionProxy(Class<?> projectionType, ResultSet rs) {
//        // Implementation cho Spring Data Projections
//        // Có thể sử dụng Proxy.newProxyInstance()
//        throw new UnsupportedOperationException("Projections not yet implemented");
//    }
//
//    private void mapResultSetToDto(ResultSet rs, Object dto) throws SQLException {
//        // Simple mapping từ ResultSet sang DTO fields
//        // Có thể sử dụng BeanWrapper hoặc reflection
//    }
//
//    @Override
//    public QueryMethod getQueryMethod() {
//        return queryMethod;
//    }
//}
