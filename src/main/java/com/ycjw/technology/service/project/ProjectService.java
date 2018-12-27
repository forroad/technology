package com.ycjw.technology.service.project;

import com.ycjw.technology.exception.ExceptionZyc;
import com.ycjw.technology.model.project.Patent;
import com.ycjw.technology.model.project.Project;
import com.ycjw.technology.model.project.SoftwareCopyright;
import com.ycjw.technology.model.request.project.*;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectService {
    /**
     *  添加项目
     * @param project 添加的项目信息
     * @return 添加成功后的项目信息
     * @throws ExceptionZyc 可能产生的异常
     */
    Project addProject(AddProject project) throws ExceptionZyc;

    /**
     *  删除项目
     * @param projectId 删除的项目id
     * @return 删除状态
     * @throws ExceptionZyc 可能产生的错误
     */
    Boolean deleteProject(int projectId) throws ExceptionZyc;

    /**
     *  管理员修改项目信息
     * @param modifyProject 修改的信息
     * @return 修改后的项目信息
     * @throws ExceptionZyc 可能产生的异常
     */
    Project modifyProject(ModifyProject modifyProject) throws ExceptionZyc;

    /**
     *  修改项目负责人
     * @param projectId 项目id
     * @param username 新负责人的用户名
     * @return 修改后的项目信息
     * @throws ExceptionZyc 可能产生的异常
     */
    Project modifyProjectLeader(int projectId,String username) throws ExceptionZyc;

    /**
     * 添加项目参与导师
     * @param projectId 项目id
     * @param username 添加的导师用户名
     * @return 添加后的用户信息
     * @throws ExceptionZyc 抛出异常
     */
    Project addMentor(int projectId,String username) throws ExceptionZyc;

    /**
     *  删除参与项目导师
     * @param projectId 项目id
     * @param username 产出的导师用户名
     * @return 删除到时候的项目信息
     * @throws ExceptionZyc 跑出的异常
     */
    Project deleteMentor(int projectId,String username) throws ExceptionZyc;

    /**
     * 添加项目经费安排
     * @param projectId 项目id
     * @param username 安排导师的用户名
     * @param funding 分配的经费
     * @param task 分配的任务
     * @return 安排后的项目信息
     * @throws ExceptionZyc 抛出的异常
     */
    Project projectDeal(int projectId,String username,double funding,String task) throws ExceptionZyc;

    /**
     *  修改项目进度
     * @param projectId 项目id
     * @param projectProgress 项目进度
     * @return 修改后的项目信息
     * @throws ExceptionZyc 抛出异常
     */
    Project modifyProjectProgress(int projectId,String projectProgress) throws ExceptionZyc;

    /**
     *  修改项目是否完成
     * @param projectId 项目id
     * @param finish 项目是否完成
     * @return 修改后的项目信息
     * @throws ExceptionZyc 抛出异常
     */
    Project modifyFinish(int projectId,Boolean finish) throws ExceptionZyc;

    /**
     *  添加专利信息
     * @param addPatent 添加的专利信息
     * @param patentPhoto 专利证书图片
     * @return 添加后的专利信息
     * @throws ExceptionZyc 抛出的异常
     */
    Patent addPatent(AddPatent addPatent, MultipartFile patentPhoto) throws ExceptionZyc;

    /**
     *  修改专利信息
     * @param modifyPatent 修改的专利信息
     * @return 修改后的专利信息
     * @throws ExceptionZyc 抛出的异常
     */
    Patent modifyPatent(ModifyPatent modifyPatent) throws ExceptionZyc;

    /**
     *  修改专利照片
     * @param patentId 专利id
     * @param patentPhoto 专利照片
     * @return 修改后的专利信息
     * @throws ExceptionZyc 抛出的异常
     */
    Patent modifyPatentPhoto(int patentId,MultipartFile patentPhoto ) throws ExceptionZyc;

    /**
     *  删除专利嘻嘻
     * @param patentId 专利id
     * @return 删除状态
     * @throws ExceptionZyc 抛出的异常
     */
    Boolean deletePatent(int patentId) throws ExceptionZyc;

    /**
     *  添加软件著作权
     * @param addSoftwareCopyright 添加的软件著作权信息
     * @param softwareCopyrightPhoto 软甲著作权照片
     * @return 添加后的软件著作权照片
     * @throws ExceptionZyc 抛出的异常
     */
    SoftwareCopyright addSoftwareCopyright(AddSoftwareCopyright addSoftwareCopyright, MultipartFile softwareCopyrightPhoto) throws ExceptionZyc;

    /**
     *  修改软件著作权信息
     * @param modifySoftwareright 修改的软件主权信息
     * @return 修改后返回的信息
     * @throws ExceptionZyc 跑出的异常
     */
    SoftwareCopyright modifySoftwareCopyright(ModifySoftwareright modifySoftwareright) throws ExceptionZyc;

    /**
     *
     * @param softwarerightId 软件著作权id
     * @param softwareCopyrightPhoto 软件著作权照片
     * @return 修改后返回的信息
     * @throws ExceptionZyc 跑出的异常
     */
    SoftwareCopyright modifySoftwareCopyright(int softwarerightId, MultipartFile softwareCopyrightPhoto) throws ExceptionZyc;

    /**
     *  删除软件著作权
     * @param softwareCopyrightId 软件著作权的id
     * @return 删除状态
     * @throws ExceptionZyc 抛出的异常
     */
    Boolean deleteSoftwareCopyright(int softwareCopyrightId) throws ExceptionZyc;
}
