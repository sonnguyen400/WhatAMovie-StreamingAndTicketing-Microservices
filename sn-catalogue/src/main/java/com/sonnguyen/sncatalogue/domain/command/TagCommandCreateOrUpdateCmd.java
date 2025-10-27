package com.sonnguyen.sncatalogue.domain.command;

import lombok.Data;

import java.util.List;

@Data
public class TagCommandCreateOrUpdateCmd {
    private String title;
    private List<TagMessageLocaleCreateOrUpdateCmd> localeMessages;
}
