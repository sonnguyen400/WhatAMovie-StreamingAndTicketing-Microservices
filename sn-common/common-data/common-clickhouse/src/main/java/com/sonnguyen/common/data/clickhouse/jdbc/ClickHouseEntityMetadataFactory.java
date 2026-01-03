package com.sonnguyen.common.data.clickhouse.jdbc;

import com.sonnguyen.common.data.clickhouse.annotation.ClickHouseEntity;
import com.sonnguyen.common.data.clickhouse.annotation.ClickHouseField;
import com.sonnguyen.common.data.clickhouse.support.util.ClickHouseAnnotationUtil;
import com.sonnguyen.common.data.core.jdbc.EntityMetadata;
import com.sonnguyen.common.data.core.jdbc.EntityMetadataFactory;
import com.sonnguyen.common.data.core.jdbc.FieldMetadata;
import com.sonnguyen.common.util.StrUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClickHouseEntityMetadataFactory implements EntityMetadataFactory<ClickHouseEntity, ClickHouseField> {

    /**
     * Create EntityMetadata from entity class (one-time reflection)
     */
    public EntityMetadata createMetadata(Class<?> entityClass) {
        ClickHouseEntity entityAnnotation = entityClass.getAnnotation(ClickHouseEntity.class);
        if (entityAnnotation == null) {
            throw new IllegalArgumentException("Class must be annotated with @ClickHouseEntity: " + entityClass.getName());
        }

        String tableName = getTableName(entityClass, entityAnnotation);
        List<FieldMetadata> fieldMetadata = createFieldMetadata(entityClass);
        FieldMetadata primaryKeyField = findPrimaryKeyField(fieldMetadata);

        return new EntityMetadata(entityClass, tableName, fieldMetadata, primaryKeyField);
    }

    public String getTableName(Class<?> entityClass, ClickHouseEntity annotation) {
        if (StrUtils.isNotBlank(annotation.tableName())) {
            return annotation.tableName();
        }
        return ClickHouseAnnotationUtil.camelToSnake(entityClass.getSimpleName());
    }

    public List<FieldMetadata> createFieldMetadata(Class<?> entityClass) {
        List<FieldMetadata> fields = new ArrayList<>();
        List<Field> fieldsList = ClickHouseAnnotationUtil.getAllFields(entityClass);
        for (Field field : fieldsList) {
            ClickHouseField fieldAnnotation = field.getAnnotation(ClickHouseField.class);
            if (fieldAnnotation != null && fieldAnnotation.includeInQuery()) {
                FieldMetadata fieldMetadata = createFieldMetadata(field, fieldAnnotation);
                fields.add(fieldMetadata);
            }
        }

        return fields;
    }

    public FieldMetadata createFieldMetadata(Field field, ClickHouseField annotation) {
        String columnName = getColumnName(field, annotation);
        String columnType = getColumnType(field, annotation);
        return new FieldMetadata(
                field,
                columnName,
                columnType,
                annotation.nullable(),
                annotation.defaultValue(),
                annotation.includeInQuery()
        );
    }

    public String getColumnName(Field field, ClickHouseField annotation) {
        if (StrUtils.isNotBlank(annotation.name())) {
            return annotation.name();
        }
        return ClickHouseAnnotationUtil.camelToSnake(field.getName());
    }

    public String getColumnType(Field field, ClickHouseField annotation) {
        if (StrUtils.isNotBlank(annotation.type())) {
            return annotation.type();
        }
        return ClickHouseAnnotationUtil.getColumnType(field);
    }

    public FieldMetadata findPrimaryKeyField(List<FieldMetadata> fields) {
        return fields.stream()
                .findFirst()
                .orElse(null);
    }
}
