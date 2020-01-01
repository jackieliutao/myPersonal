package com.dongpo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Study {
    @JsonProperty(value = "sId")
    private Integer sId;
    @JsonProperty(value = "sContent")
    private String sContent;
    @JsonProperty(value = "sAddress")
    private String sAddress;

    private Employee employee;

}