package com.dengry.springbootshiro.valueObject;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Node {
    private Integer id;
    private String text;//节点文本
    private String url;
    private Integer pid;
}
