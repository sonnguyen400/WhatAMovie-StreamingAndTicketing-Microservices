package com.sonnguyen.sniam.application.dto.request;

import com.sonnguyen.common.model.application.request.Request;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RoleCreateOrUpdateRequest extends Request {
    private String name;
    private String code;
    private String description;
    private List<UUID> permissionIds;
}
