package com.dengry.springbootshiro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private List<Role> roles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "node_resource", joinColumns = {@JoinColumn(name = "node_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")})
    private List<Resource> resources = new ArrayList<>();

}
