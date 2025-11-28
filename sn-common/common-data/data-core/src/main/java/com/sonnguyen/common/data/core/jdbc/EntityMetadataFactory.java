package com.sonnguyen.common.data.core.jdbc;

import java.lang.reflect.Field;
import java.util.List;


public interface EntityMetadataFactory<EntityAnnotation, FieldAnnotation> {
    EntityMetadata createMetadata(Class<?> entityClass);

    String getTableName(Class<?> entityClass, EntityAnnotation annotation);

    List<FieldMetadata> createFieldMetadata(Class<?> entityClass);

    FieldMetadata createFieldMetadata(Field field, FieldAnnotation annotation);

    String getColumnName(Field field, FieldAnnotation annotation);

    String getColumnType(Field field, FieldAnnotation annotation);

    FieldMetadata findPrimaryKeyField(List<FieldMetadata> fields);
}
