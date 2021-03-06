package com.ycjw.technology.model.user;

import com.ycjw.technology.model.award.Award;
import com.ycjw.technology.model.project.Project;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "t_mentor")
public class Mentor extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("用户id")
    private int id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("盐")
    private String salt;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("角色")
    private Role role;
    @OneToMany(targetEntity = Project.class)
    private Set<Integer> projects = new HashSet<>();
    @OneToMany(targetEntity = Award.class)
    private Set<Integer> awards = new HashSet<>();

    public Set<Integer> getProjects() {
        return projects;
    }

    public void setProjects(Set<Integer> projects) {
        this.projects = projects;
    }

    public Set<Integer> getAwards() {
        return awards;
    }

    public void setAwards(Set<Integer> awards) {
        this.awards = awards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
