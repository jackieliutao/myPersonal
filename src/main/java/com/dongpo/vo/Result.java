package com.dongpo.vo;

import lombok.Data;

@Data
public class Result {
    public Result(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    private Boolean success;
    private String msg;
}
