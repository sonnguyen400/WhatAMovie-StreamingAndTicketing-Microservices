package com.sonnguyen.sncatalogue.domain.command;

import lombok.Data;

@Data
public class TagMessageLocaleCreateOrUpdateCmd extends MessageLocaleCreateOrUpdateCmd{
    private String title;
}
