package com.sonnguyen.sncatalogue.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.domain.Deletable;
import com.sonnguyen.common.model.domain.InternationalizationDomain;
import com.sonnguyen.common.model.domain.MessageLocale;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.sncatalogue.domain.command.PersonCreateUpdateCmd;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@SuperBuilder
public class Person extends AuditingDomain implements InternationalizationDomain, Deletable {
    private UUID id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String description;
    private Double height;
    private Double weight;
    private String education;
    private Boolean deleted;
    private List<MessageLocale> messageLocales;

    public Person(PersonCreateUpdateCmd cmd) {
        this.id = UUID.randomUUID();
        this.fullName = cmd.getFullName();
        this.dateOfBirth = cmd.getDateOfBirth();
        this.description = cmd.getDescription();
        this.height = cmd.getHeight();
        this.weight = cmd.getWeight();
        this.education = cmd.getEducation();
        this.updateLocale(this.id, DomainType.CATALOGUE_PERSON, cmd.getMessageLocales());
    }

    public void update(PersonCreateUpdateCmd cmd) {
        this.fullName = cmd.getFullName();
        this.dateOfBirth = cmd.getDateOfBirth();
        this.description = cmd.getDescription();
        this.height = cmd.getHeight();
        this.weight = cmd.getWeight();
        this.education = cmd.getEducation();
        this.updateLocale(this.id, DomainType.CATALOGUE_PERSON, cmd.getMessageLocales());
    }

    @Override
    public void localize(MessageLocale messageLocale) {
        this.fullName = messageLocale.getProperty("fullName", this.fullName);
        this.description = messageLocale.getProperty("description", this.description);
        this.education = messageLocale.getProperty("education", this.education);


    }

    @Override
    public void enrichMessageLocales(List<MessageLocale> messageLocales) {
        this.messageLocales = messageLocales;
    }

    @Override
    public void delete() {
        this.deleted = true;
        if (Objects.nonNull(this.messageLocales)) {
            messageLocales.forEach(MessageLocale::delete);
        }
    }
}
