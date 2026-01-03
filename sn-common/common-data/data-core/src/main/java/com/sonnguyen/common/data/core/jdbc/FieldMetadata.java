package com.sonnguyen.common.data.core.jdbc;

import java.lang.reflect.Field;

public class FieldMetadata {
    private final Field field;
    private final String fieldName;
    private final String columnName;
    private final String columnType;
    private final boolean nullable;
    private final String defaultValue;
    private final boolean includeInQuery;

    public FieldMetadata(Field field, String columnName, String columnType, boolean nullable,
                         String defaultValue, boolean includeInQuery) {
        this.field = field;
        this.fieldName = field.getName();
        this.columnName = columnName;
        this.columnType = columnType;
        this.nullable = nullable;
        this.defaultValue = defaultValue;
        this.includeInQuery = includeInQuery;

        // Set accessible for performance
        this.field.setAccessible(true);
    }

    // Getters
    public Field getField() {
        return field;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public boolean isNullable() {
        return nullable;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public boolean isIncludeInQuery() {
        return includeInQuery;
    }

    /**
     * Get value from entity object - high performance
     */
    public Object getValue(Object entity) {
        try {
            return field.get(entity);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to get field value: " + fieldName, e);
        }
    }

    /**
     * Set value to entity object - high performance
     */
    public void setValue(Object entity, Object value) {
        try {
            field.set(entity, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to set field value: " + fieldName, e);
        }
    }
}
