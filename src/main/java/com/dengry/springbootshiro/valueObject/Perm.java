package com.dengry.springbootshiro.valueObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Perm {
    private Integer id;
    private String name;
    private Integer pid;
    private List<Res> functions;
}
