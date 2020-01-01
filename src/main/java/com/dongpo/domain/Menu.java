package com.dongpo.domain;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private Integer id;

    private String icon;

    private String title;

    private Boolean spread;

    private String href;
    private Integer parentid;
    private List<Menu> children;

}