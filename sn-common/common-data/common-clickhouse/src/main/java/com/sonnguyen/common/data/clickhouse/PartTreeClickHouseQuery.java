//package com.sonnguyen.common.data.clickhouse;
//
//import org.springframework.data.repository.query.parser.PartTree;
//
//public class PartTreeClickHouseQuery implements RepositoryQuery {
//
//    private final Method method;
//    private final Class<?> domainClass;
//    private final ClickHouseTemplate template;
//    private final PartTree tree;
//
//    public PartTreeClickHouseQuery(Method method, RepositoryMetadata metadata,
//                                  ClickHouseTemplate template, PartTree tree,
//                                  ProjectionFactory factory) {
//        this.method = method;
//        this.domainClass = metadata.getDomainType();
//        this.template = template;
//        this.tree = tree;
//    }
//
//    @Override
//    public Object execute(Object[] parameters) {
//        String query = buildQuery();
//        Class<?> returnType = method.getReturnType();
//
//        // Xử lý các kiểu return type khác nhau
//        if (tree.isCountProjection()) {
//            return template.queryForObject(query, Long.class, parameters);
//        } else if (tree.isExistsProjection()) {
//            Long count = template.queryForObject(query, Long.class, parameters);
//            return count != null && count > 0;
//        } else if (Iterable.class.isAssignableFrom(returnType)) {
//            return template.query(query, domainClass, parameters);
//        } else if (Optional.class.isAssignableFrom(returnType)) {
//            return template.queryForObject(query, domainClass, parameters);
//        } else {
//            return template.queryForObject(query, domainClass, parameters);
//        }
//    }
//
//    private String buildQuery() {
//        StringBuilder query = new StringBuilder();
//
//        // SELECT clause
//        if (tree.isCountProjection()) {
//            query.append("SELECT COUNT(*)");
//        } else if (tree.isExistsProjection()) {
//            query.append("SELECT COUNT(*)");
//        } else {
//            query.append("SELECT *");
//        }
//
//        // FROM clause
//        String tableName = resolveTableName(domainClass);
//        query.append(" FROM ").append(tableName);
//
//        // WHERE clause
//        if (tree.hasPredicate()) {
//            query.append(" WHERE ").append(buildWhereClause());
//        }
//
//        // ORDER BY clause
//        if (tree.hasSort()) {
//            query.append(" ORDER BY ").append(buildOrderByClause());
//        }
//
//        // LIMIT clause
//        if (tree.isLimiting()) {
//            query.append(" LIMIT ").append(tree.getMaxResults());
//        }
//
//        return query.toString();
//    }
//
//    private String buildWhereClause() {
//        return tree.getParts().stream()
//                .map(part -> {
//                    String column = camelToSnake(part.getProperty().toDotPath());
//                    String operator = resolveOperator(part.getType());
//                    return column + " " + operator + " ?";
//                })
//                .collect(Collectors.joining(" AND "));
//    }
//
//    private String resolveOperator(Part.Type type) {
//        switch (type) {
//            case EQUALS: return "=";
//            case CONTAINING: return "LIKE";
//            case STARTING_WITH: return "LIKE";
//            case ENDING_WITH: return "LIKE";
//            case GREATER_THAN: return ">";
//            case LESS_THAN: return "<";
//            case GREATER_THAN_EQUAL: return ">=";
//            case LESS_THAN_EQUAL: return "<=";
//            case IN: return "IN";
//            case NOT_IN: return "NOT IN";
//            case IS_NULL: return "IS NULL";
//            case IS_NOT_NULL: return "IS NOT NULL";
//            default: return "=";
//        }
//    }
//
//    private String camelToSnake(String str) {
//        return str.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
//    }
//}
