package com.sonnguyen.snaddress.domain.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdministrativeRegionUpdateCmd {
    private UUID parentId;
    private String name;
    private String code;
    private String type;
    private Integer level;
}
