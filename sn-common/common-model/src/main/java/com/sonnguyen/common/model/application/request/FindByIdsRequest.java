package com.sonnguyen.common.model.application.request;

import java.util.List;
import java.util.UUID;

public class FindByIdsRequest extends Request{
    private List<UUID> ids;

    public FindByIdsRequest(List<UUID> ids) {
        this.ids = ids;
    }

    public static FindByIdsRequest of(List<UUID> ids) {
        return new FindByIdsRequest(ids);
    }

    public List<UUID> getIds() {
        return ids;
    }

    public void setIds(List<UUID> ids) {
        this.ids = ids;
    }
}
