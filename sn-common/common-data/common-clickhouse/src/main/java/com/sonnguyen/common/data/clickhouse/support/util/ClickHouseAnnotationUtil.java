package com.sonnguyen.common.data.clickhouse.support.util;

import com.sonnguyen.common.data.clickhouse.annotation.ClickHouseEntity;
import com.sonnguyen.common.data.clickhouse.annotation.ClickHouseField;
import com.sonnguyen.common.data.clickhouse.annotation.ClickHouseId;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Utility class để extract thông tin từ ClickHouse annotations
 */
public class ClickHouseAnnotationUtil {

    /**
     * Lấy thông tin ClickHouseEntity từ class
     */
    public static ClickHouseEntity getClickHouseEntity(Class<?> clazz) {
        return clazz.getAnnotation(ClickHouseEntity.class);
    }

    /**
     * Lấy tên bảng từ annotation
     */
    public static String getTableName(Class<?> clazz) {
        ClickHouseEntity entity = getClickHouseEntity(clazz);
        if (entity != null && !entity.tableName().isEmpty()) {
            return entity.tableName();
        }
        // Mặc định là tên class chuyển sang snake_case
        return camelToSnake(clazz.getSimpleName());
    }

    /**
     * Lấy tên cột từ field
     */
    public static String getColumnName(Field field) {
        ClickHouseField annotation = field.getAnnotation(ClickHouseField.class);
        if (annotation != null && !annotation.name().isEmpty()) {
            return annotation.name();
        }
        return camelToSnake(field.getName());
    }

    /**
     * Lấy kiểu dữ liệu ClickHouse từ field
     */
    public static String getColumnType(Field field) {
        ClickHouseField annotation = field.getAnnotation(ClickHouseField.class);
        if (annotation != null && !annotation.type().isEmpty()) {
            return annotation.type();
        }

        // Mapping tự động từ Java type sang ClickHouse type
        Class<?> fieldType = field.getType();
        if (fieldType == String.class) {
            return "String";
        } else if (fieldType == Long.class || fieldType == long.class) {
            return "UInt64";
        } else if (fieldType == Integer.class || fieldType == int.class) {
            return "UInt32";
        } else if (fieldType == LocalDateTime.class || fieldType == java.time.Instant.class) {
            return "DateTime";
        } else if (fieldType == java.time.LocalDate.class) {
            return "Date";
        } else if (fieldType == Boolean.class || fieldType == boolean.class) {
            return "UInt8";
        } else if (fieldType == Double.class || fieldType == double.class) {
            return "Float64";
        } else if (fieldType == Float.class || fieldType == float.class) {
            return "Float32";
        } else if (Map.class.isAssignableFrom(fieldType)) {
            return "String"; // Mặc định lưu dạng JSON string
        } else {
            return "String"; // Fallback
        }
    }

    /**
     * Chuyển camelCase sang snake_case
     */
    public static String camelToSnake(String str) {
        return str.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }

    /**
     * Lấy danh sách tất cả các field được đánh dấu bằng @ClickHouseField
     */
    public static List<Field> getClickHouseFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ClickHouseField.class)) {
                fields.add(field);
            }
        }
        return fields;
    }

    /**
     * Lấy primary key field
     */
    public static Field getPrimaryKeyField(Class<?> clazz) {
        List<Field> fields = getAllFields(clazz);
        for (Field field : fields) {
            ClickHouseId annotation = field.getAnnotation(ClickHouseId.class);
            if (Objects.nonNull(annotation)) {
                return field;
            }
        }
        return null;
    }

    public static List<Field> getAllFields(Class<?> entityClass) {
        List<Field> fields = new ArrayList<>();
        Class<?> current = entityClass;
        while (Objects.nonNull(current) && !Objects.equals(current, Object.class)) {
            Field[] declaredFields = current.getDeclaredFields();
            fields.addAll(Arrays.asList(declaredFields));
            current = current.getSuperclass();
        }
        return fields;
    }
}
