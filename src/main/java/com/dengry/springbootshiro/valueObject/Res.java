package com.dengry.springbootshiro.valueObject;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Res {
    private Integer id;
    private String name;
    private boolean checked = false;
    private String action;
}
