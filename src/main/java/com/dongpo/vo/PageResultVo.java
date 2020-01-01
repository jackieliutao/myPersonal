package com.dongpo.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageResultVo {
    private Integer code = 0;
    private String msg = "";
    private Long count;
    private List data;
}
