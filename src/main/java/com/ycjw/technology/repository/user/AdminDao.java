package com.ycjw.technology.repository.user;

import com.ycjw.technology.model.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String username);
}
