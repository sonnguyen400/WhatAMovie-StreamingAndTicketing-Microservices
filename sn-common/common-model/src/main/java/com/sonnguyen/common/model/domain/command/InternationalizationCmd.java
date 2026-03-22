package com.sonnguyen.common.model.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true, builderMethodName = "localeBuilder")
public abstract class InternationalizationCmd {
    private List<MessageLocaleCmd> messageLocales;
}
