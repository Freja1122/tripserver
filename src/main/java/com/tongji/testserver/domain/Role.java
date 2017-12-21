package com.tongji.testserver.domain;

import com.tongji.testserver.domain.enums.RoleName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @program: testserver
 * @description: 角色
 * @author: Annntn
 * @create: 2017-12-20 13:23
 **/
@Entity
public class Role extends Entitys implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
    @Column(nullable = false)
    private String description;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
