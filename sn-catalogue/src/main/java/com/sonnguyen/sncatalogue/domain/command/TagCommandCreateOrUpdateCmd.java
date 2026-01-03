package com.sonnguyen.sncatalogue.domain.command;

import com.sonnguyen.common.model.domain.command.InternationalizationCmd;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TagCommandCreateOrUpdateCmd extends InternationalizationCmd {
    private String title;
}
