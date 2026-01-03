package com.sonnguyen.common.data.clickhouse.jdbc;

import com.sonnguyen.common.data.core.jdbc.EntityMetadata;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ClickHouseMetadataRegistry {
    
    private final Map<Class<?>, EntityMetadata> metadataMap = new ConcurrentHashMap<>();
    private final Map<String, EntityMetadata> metadataByTableName = new ConcurrentHashMap<>();
    
    /**
     * Register entity metadata
     */
    public void register(EntityMetadata metadata) {
        metadataMap.put(metadata.getEntityClass(), metadata);
        metadataByTableName.put(metadata.getTableName(), metadata);
    }
    
    /**
     * Get metadata by entity class
     */
    public EntityMetadata getMetadata(Class<?> entityClass) {
        EntityMetadata metadata = metadataMap.get(entityClass);
        if (metadata == null) {
            throw new IllegalArgumentException("No metadata found for entity: " + entityClass.getName());
        }
        return metadata;
    }
    
    /**
     * Get metadata by table name
     */
    public EntityMetadata getMetadata(String tableName) {
        EntityMetadata metadata = metadataByTableName.get(tableName);
        if (metadata == null) {
            throw new IllegalArgumentException("No metadata found for table: " + tableName);
        }
        return metadata;
    }
    
    /**
     * Check if entity class is registered
     */
    public boolean contains(Class<?> entityClass) {
        return metadataMap.containsKey(entityClass);
    }
    
    /**
     * Get all registered entity classes
     */
    public Set<Class<?>> getRegisteredEntities() {
        return metadataMap.keySet();
    }
    
    /**
     * Pre-load common entities (optional)
     */
    @PostConstruct
    public void preLoadCommonEntities() {
        // Có thể pre-load các entity thông dụng ở đây
        System.out.println("ClickHouseMetadataRegistry initialized with " + metadataMap.size() + " entities");
    }
}
