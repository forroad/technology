package com.ycjw.technology.controller.project;

import com.ycjw.technology.exception.ExceptionZyc;
import com.ycjw.technology.model.Response;
import com.ycjw.technology.model.project.Deal;
import com.ycjw.technology.model.project.Patent;
import com.ycjw.technology.model.project.Project;
import com.ycjw.technology.model.project.SoftwareCopyright;
import com.ycjw.technology.model.request.project.*;
import com.ycjw.technology.service.project.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    @ApiOperation(("删除项目"))
    @DeleteMapping("delete")
    public Response deleteProject(@RequestParam("projectId") int projectId) throws ExceptionZyc{
        return new Response("删除成功",projectService.deleteProject(projectId));
    }

    @ApiOperation(("管理员修改项目"))
    @PostMapping("modifyProject")
    public Response modifyProject(@RequestBody  ModifyProject modifyProject) throws ExceptionZyc{
        return new Response("修改成功",projectService.modifyProject(modifyProject));
    }

    @ApiOperation("修改项目负责人")
    @PostMapping("modifyLeader")
    public Response modifyProjectLeader(@RequestParam("projectId") int projectId,
                                        @RequestParam("username") String username) throws ExceptionZyc{
        return new Response("修改成功",projectService.modifyProjectLeader(projectId, username));
    }

    @ApiOperation("项目添加参与导师")
    @PutMapping("addMentor")
    public Response addMentor(@RequestParam("projectId") int projectId,
                              @RequestParam("username") String username) throws ExceptionZyc{
        return new Response("添加成功",projectService.addMentor(projectId, username));
    }

    @ApiOperation("项目删除参与导师")
    @DeleteMapping("deleteMentor")
    public Response deleteMentor(@RequestParam("projectId") int projectId,
                                 @RequestParam("username") String username) throws ExceptionZyc{
        return new Response("删除成功",projectService.deleteMentor(projectId,username));
    }

    @ApiOperation(("添加项目安排"))
    @PutMapping("projectDeal")
    public Response projectDeal(@RequestParam("projectId") int projectId,
                                @RequestParam("username") String username,
                                @RequestParam("funding")double funding,
                                @RequestParam("task") String task) throws ExceptionZyc{
        return new Response("添加成功",projectService.projectDeal(projectId, username, funding, task));
    }

    @ApiOperation("修改项目进度")
    @PostMapping("modifyProjectProgress")
    public Response modifyProjectProgress(@RequestParam("projectId") int projectId,
                                          @RequestParam("projectProgress") String projectProgress) throws ExceptionZyc{
        return new Response("修改成功",projectService.modifyProjectProgress(projectId, projectProgress));
    }

    @ApiOperation("修改项目状态")
    @PostMapping("modifyFinish")
    public Response modifyFinish(@RequestParam("projectId") int projectId,
                                 @RequestParam("finish") Boolean finish) throws ExceptionZyc{
        return new Response("修改成功",projectService.modifyFinish(projectId, finish));
    }

    @ApiOperation("添加专利")
    @PutMapping("addPatent")
    public Response addPatent(@RequestBody AddPatent addPatent,
                              @RequestParam("patentPhoto") MultipartFile patentPhoto) throws ExceptionZyc{
        return new Response("添加成功",projectService.addPatent(addPatent, patentPhoto));
    }

    @ApiOperation("修改专利信息")
    @PostMapping("modifyPatent")
    public Response modifyPatent(@RequestBody ModifyPatent modifyPatent) throws ExceptionZyc{
        return new Response("修改成功",projectService.modifyPatent(modifyPatent));
    }

    @ApiOperation("修改专利证书图片")
    @PostMapping("modifyPatentPhoto")
    public Response modifyPatentPhoto(@RequestParam("patentId") int patentId,
                                      @RequestParam("patentPhoto") MultipartFile patentPhoto ) throws ExceptionZyc{
        return new Response("修改成功",projectService.modifyPatentPhoto(patentId, patentPhoto));
    }

    @ApiOperation("删除专利信息")
    @DeleteMapping("deletePatent")
    public Response deletePatent(@RequestParam("patentId") int patentId) throws ExceptionZyc{
        return new Response("删除成功",projectService.deletePatent(patentId));
    }

    @ApiOperation("添加软件著作权")
    @PutMapping("addSoftwareCopyright")
    public Response addSoftwareCopyright(@RequestBody AddSoftwareCopyright addSoftwareCopyright,
                                         @RequestParam("softwareCopyrightPhoto") MultipartFile softwareCopyrightPhoto) throws ExceptionZyc{
        return new Response("添加成功",projectService.addSoftwareCopyright(addSoftwareCopyright, softwareCopyrightPhoto));
    }

    @ApiOperation("修改软件著作权信息")
    @PostMapping("modifySoftwareCopyright")
    public Response modifySoftwareCopyright(@RequestBody ModifySoftwareright modifySoftwareright) throws ExceptionZyc{
        return new Response("修改成功",projectService.modifySoftwareCopyright(modifySoftwareright));
    }

    @ApiOperation("修改软件著作权信息图片")
    @PostMapping("modifySoftwareCopyrightPhoto")
    public Response modifySoftwareCopyrightPhoto(@RequestParam("softwarerightId") int softwarerightId,
                                                 @RequestParam("softwareCopyrightPhoto") MultipartFile softwareCopyrightPhoto) throws ExceptionZyc{
        return new Response("修改成功",projectService.modifySoftwareCopyrightPhoto(softwarerightId, softwareCopyrightPhoto));
    }

    @ApiOperation("删除软件著作权")
    @DeleteMapping("deleteSoftwareCopyright")
    public Response deleteSoftwareCopyright(@RequestParam("softwareCopyrightId") int softwareCopyrightId) throws ExceptionZyc{
        return new Response("删除成功",projectService.deleteSoftwareCopyright(softwareCopyrightId));
    }

    @ApiOperation("通过参与导师查询项目")
    @GetMapping("findProjectByMentor")
    public Response findProjectByMentor(@RequestParam("mentorId") int mentorId, Pageable pageable) throws ExceptionZyc{
        return new Response("查询成功",projectService.findProjectByMentor(mentorId, pageable));
    }

    @ApiOperation(("多条件查询项目"))
    @GetMapping("findProjects")
    public Response findProjects(@RequestParam(value = "name",required = false) String name,
                                 @RequestParam(value = "projectLeader",required = false) String projectLeader,
                                 @RequestParam(value = "mentorId",required = false) int mentorId, Pageable pageable) throws ExceptionZyc{
        return new Response("查询成功",projectService.findProjects(name, projectLeader, mentorId, pageable));
    }

    @ApiOperation("查询项目产生的专利")
    @GetMapping("findPatentByProject")
    public Response findPatentByProject(@RequestParam(value = "name",required = false) String name,
                                        @RequestParam(value = "patentId",required = false)String patentId,
                                        @RequestParam(value = "projectId",required = false)int projectId,Pageable pageable) throws ExceptionZyc{
        return new Response("查询成功",projectService.findPatentByProject(name, patentId, projectId, pageable));
    }

    @ApiOperation("查询项目产生的软件著作权")
    @GetMapping("findSoftwareByProject")
    public Response findSoftwareByProject(@RequestParam(value = "name",required = false) String name,
                                          @RequestParam(value = "softwareCpoyrightId",required = false)String softwareCpoyrightId,
                                          @RequestParam(value = "projectId",required = false)int projectId,Pageable pageable) throws ExceptionZyc{
        return new Response("查询成功",projectService.findSoftwareByProject(name, softwareCpoyrightId, projectId, pageable));
    }

    @ApiOperation("查询项目安排")
    @GetMapping("findDealByProject")
    public Response findDealByProject(@RequestParam(value = "projectId",required = false) int projectId,
                                      @RequestParam(value = "mentorUsername",required = false)String mentorUsername, Pageable pageable) throws ExceptionZyc{
        return new Response("查询成功",projectService.findDealByProject(projectId, mentorUsername, pageable));
    }
}
