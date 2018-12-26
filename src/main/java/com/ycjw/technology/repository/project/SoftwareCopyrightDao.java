package com.ycjw.technology.repository.project;

import com.ycjw.technology.model.project.Patent;
import com.ycjw.technology.model.project.SoftwareCopyright;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareCopyrightDao extends JpaRepository<SoftwareCopyright,Integer> {
}
