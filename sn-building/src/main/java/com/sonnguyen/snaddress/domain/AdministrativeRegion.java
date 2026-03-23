package com.sonnguyen.snaddress.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.domain.Deletable;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.snaddress.domain.cmd.AdministrativeRegionCreateCmd;
import com.sonnguyen.snaddress.domain.cmd.AdministrativeRegionUpdateCmd;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Getter
@NoArgsConstructor
public class AdministrativeRegion extends AuditingDomain implements Deletable {
    private UUID id;
    private UUID countryId;
    private UUID parentId;
    private String name;
    private String code;
    private String type;
    private Integer level;
    private Boolean deleted;

    public AdministrativeRegion(AdministrativeRegionCreateCmd cmd) {
        this.id = IdUtils.nextId();
        this.countryId = cmd.getCountryId();
        this.parentId = cmd.getParentId();
        this.name = cmd.getName();
        this.code = cmd.getCode();
        this.type = cmd.getType();
        this.level = cmd.getLevel();
        this.deleted = Boolean.FALSE;
    }

    public void update(AdministrativeRegionUpdateCmd cmd) {
        this.parentId = cmd.getParentId();
        this.name = cmd.getName();
        this.code = cmd.getCode();
        this.type = cmd.getType();
        this.level = cmd.getLevel();
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
