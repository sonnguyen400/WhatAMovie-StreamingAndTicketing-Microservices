package com.sonnguyen.snultility.application.dto.response;

import com.sonnguyen.common.model.domain.MessageLocale;
import com.sonnguyen.snultility.domain.Tag;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public class TagDTO {
    private UUID id;
    private String title;
    private List<MessageLocale> messageLocales;

    public static TagDTO from(Tag tag) {
        return TagDTO.builder()
                .id(tag.getId())
                .title(tag.getTitle())
                .messageLocales(tag.getMessageLocales())
                .build();
    }
}
