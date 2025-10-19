package com.sonnguyen.common.model.application.request;

import java.util.List;

public class FindByNamesRequest extends Request {
    private List<String> names;

    public FindByNamesRequest(List<String> names) {
        this.names = names;
    }

    public static FindByNamesRequest of(List<String> names) {
        return new FindByNamesRequest(names);
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
