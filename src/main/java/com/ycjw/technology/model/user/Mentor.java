package com.ycjw.technology.model.user;

import com.ycjw.technology.model.award.Award;
import com.ycjw.technology.model.project.Project;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "t_mentor")
public class Mentor extends User{
    @OneToMany
    private List<Project> projects;
    @OneToMany
    private List<Award> awards;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }
}
