package com.sonnguyen.snticket.domain;

import com.sonnguyen.common.model.domain.TenancyDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.CurrencyUnit;
import com.sonnguyen.common.model.infrastructure.support.enums.FeeType;
import com.sonnguyen.common.model.infrastructure.support.enums.ScreenType;
import com.sonnguyen.common.model.infrastructure.support.enums.SeatType;
import com.sonnguyen.common.model.infrastructure.support.enums.SoundSystem;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class SurPrice extends TenancyDomain {
    private UUID id;
    private String code;
    private String name;
    private FeeType feeType;
    private SeatType seatType;
    private ScreenType screenType;
    private SoundSystem soundSystem;
    private BigDecimal amount;
    private CurrencyUnit currencyUnit;
}
