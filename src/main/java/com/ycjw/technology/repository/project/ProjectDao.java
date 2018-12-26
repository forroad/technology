package com.ycjw.technology.repository.project;

import com.ycjw.technology.model.project.Patent;
import com.ycjw.technology.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectDao extends JpaRepository<Project,Integer> {
}
