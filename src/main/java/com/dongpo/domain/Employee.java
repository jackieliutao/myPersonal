package com.dongpo.domain;

import lombok.Data;

@Data
public class Employee {
    private Integer empId;

    private String empName;

    private String empSex;

    private Integer empAge;

    private String empBirthday;

    private Boolean empIsblack;

    private String empImg;

    private Position position;

    private String empPhone;

    private String empQq;

    private String empEmail;

    private Professional professional;

    private  Department department;

}