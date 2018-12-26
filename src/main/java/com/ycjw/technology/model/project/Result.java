package com.ycjw.technology.model.project;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(targetEntity = Patent.class)
    private List<Patent> patents;
    @ApiModelProperty("软件著作权")
    @OneToMany(targetEntity = SoftwareCopyright.class)
    private List<SoftwareCopyright> softwareCopyrights;

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

    public List<Patent> getPatents() {
        return patents;
    }

    public void setPatents(List<Patent> patents) {
        this.patents = patents;
    }

    public List<SoftwareCopyright> getSoftwareCopyrights() {
        return softwareCopyrights;
    }

    public void setSoftwareCopyrights(List<SoftwareCopyright> softwareCopyrights) {
        this.softwareCopyrights = softwareCopyrights;
    }
}
