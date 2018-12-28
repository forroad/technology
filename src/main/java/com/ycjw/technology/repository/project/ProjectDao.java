package com.ycjw.technology.repository.project;

import com.ycjw.technology.model.project.Patent;
import com.ycjw.technology.model.project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProjectDao extends JpaRepository<Project,Integer>, JpaSpecificationExecutor<Project> {
    Page<Project> findByIdIn(Set<Integer> ids, Pageable pageable);
}
