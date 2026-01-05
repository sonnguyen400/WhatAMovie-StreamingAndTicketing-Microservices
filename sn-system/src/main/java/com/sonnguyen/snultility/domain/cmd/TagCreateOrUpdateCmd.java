package com.sonnguyen.snultility.domain.cmd;

import com.sonnguyen.common.model.domain.command.InternationalizationCmd;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TagCreateOrUpdateCmd extends InternationalizationCmd {
    private String title;
}
