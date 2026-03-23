package com.sonnguyen.snaddress.domain.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryCreateCmd {
    private String name;
    private String code;
    private String phoneCode;
}
