package com.sonnguyen.common.whatamoviemodel.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.domain.MessageLocale;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Tag extends AuditingDomain {
    private UUID id;
    private String title;
    private List<MessageLocale> messageLocales;
    private List<ContentTag> contents;
    private Boolean deleted;
}
