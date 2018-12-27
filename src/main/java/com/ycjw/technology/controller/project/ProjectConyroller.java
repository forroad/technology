package com.ycjw.technology.controller.project;

import com.ycjw.technology.exception.ExceptionZyc;
import com.ycjw.technology.model.Response;
import com.ycjw.technology.model.project.Project;
import com.ycjw.technology.model.request.project.AddProject;
import com.ycjw.technology.service.project.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectConyroller {
    @Autowired
    ProjectService projectService;
    @PutMapping("add")
    @ApiOperation(("添加项目"))
    public Response addProjrct(@RequestBody AddProject addProject) throws ExceptionZyc {
        return new Response("添加成功",projectService.addProject(addProject));
    }
}
