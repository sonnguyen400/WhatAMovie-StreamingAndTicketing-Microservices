package com.sonnguyen.snultility.application.mapper;

import com.sonnguyen.snultility.application.dto.request.TagAssignmentRequest;
import com.sonnguyen.snultility.application.dto.request.TagCreateOrUpdateRequest;
import com.sonnguyen.snultility.application.dto.request.TagSearchRequest;
import com.sonnguyen.snultility.domain.cmd.TagAssignmentCmd;
import com.sonnguyen.snultility.domain.cmd.TagCreateOrUpdateCmd;
import com.sonnguyen.snultility.domain.query.TagSearchQuery;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DTOMapper {
    TagCreateOrUpdateCmd from(TagCreateOrUpdateRequest request);

    TagAssignmentCmd from(TagAssignmentRequest request);

    TagSearchQuery from(TagSearchRequest request);
}
