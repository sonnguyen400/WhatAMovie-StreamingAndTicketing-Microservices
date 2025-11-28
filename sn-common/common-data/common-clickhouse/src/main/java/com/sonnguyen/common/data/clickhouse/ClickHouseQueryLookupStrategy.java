package com.sonnguyen.common.data.clickhouse;

import com.sonnguyen.common.data.clickhouse.annotation.ClickHouseQuery;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.RepositoryQuery;

import java.lang.reflect.Method;

public class ClickHouseQueryLookupStrategy implements QueryLookupStrategy {

    private final ClickHouseTemplate template;

    public ClickHouseQueryLookupStrategy(ClickHouseTemplate template) {
        this.template = template;
    }

    @Override
    public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata,
                                        ProjectionFactory factory, NamedQueries namedQueries) {

        // 1. Kiểm tra @Query annotation
        ClickHouseQuery queryAnnotation = method.getAnnotation(ClickHouseQuery.class);
        if (queryAnnotation != null) {
            return new StringBasedClickHouseQuery(method, metadata, template, queryAnnotation.value());
        }
        return null;
    }
}
