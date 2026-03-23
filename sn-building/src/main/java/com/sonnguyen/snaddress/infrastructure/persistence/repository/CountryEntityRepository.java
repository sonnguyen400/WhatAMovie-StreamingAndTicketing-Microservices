package com.sonnguyen.snaddress.infrastructure.persistence.repository;

import com.sonnguyen.snaddress.infrastructure.persistence.entity.CountryEntity;
import com.sonnguyen.snaddress.infrastructure.persistence.repository.custom.CustomCountryEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CountryEntityRepository extends JpaRepository<CountryEntity, UUID>, CustomCountryEntityRepository {
}
