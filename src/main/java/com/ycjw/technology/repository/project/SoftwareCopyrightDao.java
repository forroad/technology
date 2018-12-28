package com.ycjw.technology.repository.project;

import com.ycjw.technology.model.project.Patent;
import com.ycjw.technology.model.project.SoftwareCopyright;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SoftwareCopyrightDao extends JpaRepository<SoftwareCopyright,Integer>, JpaSpecificationExecutor<SoftwareCopyright> {
}
