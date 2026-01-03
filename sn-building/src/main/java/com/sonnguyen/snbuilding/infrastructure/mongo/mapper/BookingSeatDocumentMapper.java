package com.sonnguyen.snbuilding.infrastructure.mongo.mapper;

import com.sonnguyen.common.data.mongo.mapper.DocumentMapper;
import com.sonnguyen.snbuilding.domain.BookingSeat;
import com.sonnguyen.snbuilding.infrastructure.mongo.document.BookingSeatDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingSeatDocumentMapper extends DocumentMapper<BookingSeat, BookingSeatDocument> {

}
