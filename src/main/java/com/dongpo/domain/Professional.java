package com.dongpo.domain;

import lombok.Data;

@Data
public class Professional {
    private Integer proId;

    private String proName;

    private Department department;
}