package com.sonnguyen.sniam.application.dto.request;

import com.sonnguyen.common.model.application.request.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleCreateOrUpdateRequest extends Request {
    private String name;
    private String code;
    private String description;
    private List<UUID> permissionIds;
}
