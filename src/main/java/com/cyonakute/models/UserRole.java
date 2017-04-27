package com.cyonakute.models;

import java.util.List;

/**
 * Created by Opeyemi.Akinnawo on 4/26/2017.
 */
public class UserRole {

    private int roleId;
    private String name;
    private String description;
    private List<User> users;

    public UserRole(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
