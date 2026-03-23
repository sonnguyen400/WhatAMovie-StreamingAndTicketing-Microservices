package com.sonnguyen.common.data.core.jdbc;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class EntityMetadata {
    private final Class<?> entityClass;
    private final String tableName;
    private final List<FieldMetadata> fields;
    private final FieldMetadata primaryKeyField;
    private final Map<String, FieldMetadata> fieldByName;

    public EntityMetadata(Class<?> entityClass, String tableName, List<FieldMetadata> fields,
                          FieldMetadata primaryKeyField) {
        this.entityClass = entityClass;
        this.tableName = tableName;
        this.fields = fields;
        this.primaryKeyField = primaryKeyField;
        this.fieldByName = new ConcurrentHashMap<>();

        for (FieldMetadata field : fields) {
            fieldByName.put(field.getFieldName(), field);
            fieldByName.put(field.getColumnName(), field);
        }
    }

    // Getters
    public Class<?> getEntityClass() {
        return entityClass;
    }

    public String getTableName() {
        return tableName;
    }

    public List<FieldMetadata> getFields() {
        return fields;
    }

    public FieldMetadata getPrimaryKeyField() {
        return primaryKeyField;
    }

    public FieldMetadata getField(String name) {
        return fieldByName.get(name);
    }

    public List<String> getColumnNames() {
        return fields.stream()
                .map(FieldMetadata::getColumnName)
                .collect(Collectors.toList());
    }

    public List<String> getFieldNames() {
        return fields.stream()
                .map(FieldMetadata::getFieldName)
                .collect(Collectors.toList());
    }
}
