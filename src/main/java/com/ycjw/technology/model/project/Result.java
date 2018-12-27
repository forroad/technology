package com.ycjw.technology.model.project;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("成果ID")
    private int id;
    @ApiModelProperty("成果介绍")
    private String resultDetail;
    @ApiModelProperty("专利集合")
    @ElementCollection
    private Set<Integer> patentIds = new HashSet<>();
    @ApiModelProperty("软件著作权")
    @ElementCollection
    private Set<Integer> softwareCopyrightIds = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResultDetail() {
        return resultDetail;
    }

    public void setResultDetail(String resultDetail) {
        this.resultDetail = resultDetail;
    }

    public Set<Integer> getPatentIds() {
        return patentIds;
    }

    public void setPatentIds(Set<Integer> patentIds) {
        this.patentIds = patentIds;
    }

    public Set<Integer> getSoftwareCopyrightIds() {
        return softwareCopyrightIds;
    }

    public void setSoftwareCopyrightIds(Set<Integer> softwareCopyrightIds) {
        this.softwareCopyrightIds = softwareCopyrightIds;
    }
}
