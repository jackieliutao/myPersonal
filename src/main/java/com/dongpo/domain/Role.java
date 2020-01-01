package com.dongpo.domain;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private Integer roId;

    private String roName;

    private List<Authority> authorities;
}