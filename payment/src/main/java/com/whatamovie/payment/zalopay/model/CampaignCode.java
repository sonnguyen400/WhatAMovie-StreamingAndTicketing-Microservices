package com.whatamovie.payment.zalopay.model;

import com.whatamovie.payment.utils.Jsonable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CampaignCode implements Jsonable {
    private String campaigncode;
}
