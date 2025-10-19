package com.sonnguyen.common.model.application.request;

import java.util.List;

public class FindByCodesRequest extends Request {
    private List<String> codes;

    public FindByCodesRequest(List<String> codes) {
        this.codes = codes;
    }

    public static FindByCodesRequest of(List<String> codes) {
        return new FindByCodesRequest(codes);
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }
}
