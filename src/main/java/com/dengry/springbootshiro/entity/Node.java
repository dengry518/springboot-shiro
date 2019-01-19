package com.dengry.springbootshiro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "t_node")
public class Node implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    private String url;

    public Node() {
    }

    public Node(Integer id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private Set<Node> chirldren = new HashSet<>();

    @ManyToOne
    private Node parent;

    @ManyToMany(mappedBy = "nodes")
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();


}
