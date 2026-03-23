package com.sonnguyen.snaddress.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.domain.Deletable;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.snaddress.domain.cmd.CountryCreateCmd;
import com.sonnguyen.snaddress.domain.cmd.CountryUpdateCmd;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Getter
@NoArgsConstructor
public class Country extends AuditingDomain implements Deletable {
    private UUID id;
    private String name;
    private String code;
    private String phoneCode;
    private Boolean deleted;

    public Country(CountryCreateCmd cmd) {
        this.id = IdUtils.nextId();
        this.name = cmd.getName();
        this.code = cmd.getCode();
        this.phoneCode = cmd.getPhoneCode();
        this.deleted = Boolean.FALSE;
    }

    public void update(CountryUpdateCmd cmd) {
        this.name = cmd.getName();
        this.code = cmd.getCode();
        this.phoneCode = cmd.getPhoneCode();
    }

    @Override
    public void delete() {
        this.deleted = Boolean.TRUE;
    }

    @Override
    public void unDelete() {
        this.deleted = Boolean.FALSE;
    }
}
