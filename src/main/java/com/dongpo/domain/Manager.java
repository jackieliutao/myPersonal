package com.dongpo.domain;

import lombok.Data;

@Data
public class Manager {
    private Integer id;

    private String username;

    private String password;

    private Boolean status;

}