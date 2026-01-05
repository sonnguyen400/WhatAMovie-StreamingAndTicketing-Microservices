package com.sonnguyen.common.model.domain.command;

import lombok.Data;

import java.util.List;

@Data
public abstract class InternationalizationCmd {
    private List<MessageLocaleCmd> messageLocales;
}
