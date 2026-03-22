package com.sonnguyen.common.data.persistence.entityrepository;

import com.sonnguyen.common.data.persistence.entity.MessageLocaleEntity;
import com.sonnguyen.common.model.domain.MessageLocale;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageLocaleEntityRepository extends JpaRepository<MessageLocaleEntity, UUID> {
    @Query("FROM MessageLocaleEntity m WHERE m.domainType = :domainType and m.domainId in :domainIds and m.locale = :localeCode and m.deleted = FALSE")
    List<MessageLocaleEntity> findByDomainTypeAndDomainIdAndLocaleCode(DomainType domainType, Object domainIds, LocaleCode localeCode);


    @Query("FROM MessageLocaleEntity m WHERE m.domainType = :domainType and m.domainId in :domainIds and m.deleted = FALSE")
    List<MessageLocaleEntity> findAllByDomainIdAndDomainType(DomainType domainType, List<UUID> domainIds);
}
