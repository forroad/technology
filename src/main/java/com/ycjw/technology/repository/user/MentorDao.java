package com.ycjw.technology.repository.user;

import com.ycjw.technology.model.project.Patent;
import com.ycjw.technology.model.user.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorDao extends JpaRepository<Mentor,Integer> {
    Mentor findByUsername(String username);
}
