package com.sonnguyen.snstorage.infrastructure.persistence.entity;

import java.util.UUID;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.snstorage.infrastructure.support.enums.StorageProvider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "file_entity")
public class FileEntity extends AuditingEntity {
	@Id
	private UUID id;
	
	@Column(name = "original_name")
	private String originalName;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "access_counter")
	private Integer accessCounter;
}
