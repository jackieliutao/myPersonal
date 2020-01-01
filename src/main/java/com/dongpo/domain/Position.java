package com.dongpo.domain;
import lombok.Data;

@Data
public class Position {
    private Integer poId;

    private String poNum;

    private String poName;

    private Department department;

}