package com.ycjw.technology.service.project.impl;

import com.ycjw.technology.exception.ExceptionZyc;
import com.ycjw.technology.model.project.*;
import com.ycjw.technology.model.request.project.*;
import com.ycjw.technology.model.user.Mentor;
import com.ycjw.technology.repository.project.*;
import com.ycjw.technology.repository.user.MentorDao;
import com.ycjw.technology.service.project.ProjectService;
import com.ycjw.technology.util.ImageUtil;
import com.ycjw.technology.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    @Autowired
    ProjectDao projectDao;
    @Autowired
    MentorDao mentorDao;
    @Autowired
    DealDao dealDao;
    @Autowired
    PatentDao patentDao;
    @Autowired
    SoftwareCopyrightDao softwareCopyrightDao;
    @Autowired
    ResultDao resultDao;
    @Value("${img.location}")
    private String location;

    @Override
    public Project addProject(AddProject addProject) throws ExceptionZyc {
        //判断添加信息
        if(StringUtils.isEmpty(addProject.getName()) || StringUtils.isEmpty(addProject.getProjectLeader())
           || StringUtils.isEmpty(addProject.getStartTime().toString()) || StringUtils.isEmpty(addProject.getLastTime().toString())
           || StringUtils.isEmpty(addProject.getProjectDetail())){
            //参数为空抛出错误
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //判断项目经费
        if(addProject.getFunding() < 0){
            //经费异常抛出，错误
            throw ExceptionZyc.FUNDING_IS_WRONG;
        }
        //查询项目负责人
        Mentor mentor = mentorDao.findByUsername(addProject.getProjectLeader());
        //判断项目负责人是否存在
        if(mentor == null){
            //项目负责人不存在，抛出异常
            throw  ExceptionZyc.LEADER_IS_NOT_EXIST;
        }
        //判断是否有其他参与导师
        if(addProject.getMentorUsernames().size() > 0){
            for (String username:addProject.getMentorUsernames()
                 ) {
                //查询项目参与导师
                 mentor = mentorDao.findByUsername(username);
                //判断导师是否存在
                if(mentor == null){
                    //导师不存在，抛出异常
                    throw  ExceptionZyc.MENTOR_IS_NOT_EXIST;
                }
            }
        }
        //条件满足，添加项目
        Project project = new Project(addProject);
        //保存信息
        projectDao.save(project);
        //项目负责人添加项目
        mentor = mentorDao.findByUsername(project.getProjectLeader());
        Set<Integer> projects = mentor.getProjects();
        projects.add(project.getId());
        mentor.setProjects(projects);
        mentorDao.save(mentor);
        //参与导师添加项目
        for (String username:project.getMentorUsernames()
             ) {
            mentor = mentorDao.findByUsername(username);
            projects = mentor.getProjects();
            projects.add(project.getId());
            mentor.setProjects(projects);
            mentorDao.save(mentor);
        }
        return project;
    }

    @Override
    public Boolean deleteProject(int projectId) throws ExceptionZyc {
        //判断参数
        if(StringUtils.isEmpty(projectId +"")){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询项目信息
        Project project = projectDao.findById(projectId).orElse(null);
        //判断项目是否为空
        if(project == null){
            //项目为空，抛出异常
            throw ExceptionZyc.PROJECT_IS_NOT_EXIST;
        }
        //条件满足，删除项目
        projectDao.delete(project);
        //返回信息
        return true;
    }

    @Override
    public Project modifyProject(ModifyProject modifyProject) throws ExceptionZyc {
        //判断条件是否满足
        if(StringUtils.isEmpty(modifyProject.getName()) || StringUtils.isEmpty(modifyProject.getLastTime().toString())
          || StringUtils.isEmpty(modifyProject.getProjectDetail())){
            //有参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //判断经费情况
        if(modifyProject.getFunding() < 0){
            //经费异常，抛出异常
            throw ExceptionZyc.FUNDING_IS_WRONG;
        }
        //查询项目
        Project project = projectDao.findById(modifyProject.getId()).orElse(null);
        //判断项目
        if(project == null){
            //项目不存在，抛出异常
            throw ExceptionZyc.PROJECT_IS_NOT_EXIST;
        }
        //条件满足，修改信息
        //修改项目名称
        project.setName(modifyProject.getName());
        //修改项目截止时间
        project.setLastTime(modifyProject.getLastTime());
        //修改项目描述
        project.setProjectDetail(modifyProject.getProjectDetail());
        //修改项目经费
        project.setFunding(modifyProject.getFunding());
        //保存并返回信息
        return projectDao.save(project);
    }

    @Override
    public Project modifyProjectLeader(int projectId, String username) throws ExceptionZyc {
        //检查参数是否为空
        if(StringUtils.isEmpty(projectId + "") || StringUtils.isEmpty(username)){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询项目
        Project project = projectDao.findById(projectId).orElse(null);
        //判断项目
        if(project == null){
            //项目不存在，抛出异常
            throw ExceptionZyc.PROJECT_IS_NOT_EXIST;
        }
        //查询新负责人
        Mentor mentor = mentorDao.findByUsername(username);
        //判断新负责人是否存在
        if(mentor == null){
            //导师不存在，抛出异常
            throw ExceptionZyc.MENTOR_IS_NOT_EXIST;
        }
        //项目存在,设置新负责人
        project.setProjectLeader(username);
        //保存返回信息
        return projectDao.save(project);
    }

    @Override
    public Project addMentor(int projectId, String username) throws ExceptionZyc {
        //检查参数是否为空
        if(StringUtils.isEmpty(projectId + "") || StringUtils.isEmpty(username)){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询项目
        Project project = projectDao.findById(projectId).orElse(null);
        //判断项目
        if(project == null){
            //项目不存在，抛出异常
            throw ExceptionZyc.PROJECT_IS_NOT_EXIST;
        }
        //查询参与导师
        Mentor mentor = mentorDao.findByUsername(username);
        //判断导师是否存在
        if(mentor == null){
            //导师不存在，抛出异常
            throw ExceptionZyc.MENTOR_IS_NOT_EXIST;
        }
        //条件满足
        Set<String> mentorUsernames = project.getMentorUsernames();//获取参与导师计划人
        //判断用户导师是否已参与
        if(mentorUsernames.contains(username)){
            //导师已参与，直接返回
            return project;
        }
        //导师为参与，添加导师
        mentorUsernames.add(username);
        //设置新集合
        project.setMentorUsernames(mentorUsernames);
        //保存并返回
        return projectDao.save(project);
    }

    @Override
    public Project deleteMentor(int projectId, String username) throws ExceptionZyc {
        //检查参数是否为空
        if(StringUtils.isEmpty(projectId + "") || StringUtils.isEmpty(username)){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询项目
        Project project = projectDao.findById(projectId).orElse(null);
        //判断项目
        if(project == null){
            //项目不存在，抛出异常
            throw ExceptionZyc.PROJECT_IS_NOT_EXIST;
        }
        //查询参与导师
        Mentor mentor = mentorDao.findByUsername(username);
        //判断导师是否存在
        if(mentor == null){
            //导师不存在，抛出异常
            throw ExceptionZyc.MENTOR_IS_NOT_EXIST;
        }
        //条件满足
        Set<String> mentorUsernames = project.getMentorUsernames();//获取参与导师计划人
        //判断用户导师是否已参与
        if(!mentorUsernames.contains(username)){
            //导师未参与，抛出异常
            throw ExceptionZyc.MENTOR_IS_NOT_JOIN;
        }
        //导师参与，移除导师
        mentorUsernames.remove(username);
        //设置新集合
        project.setMentorUsernames(mentorUsernames);
        //保存并返回
        return projectDao.save(project);
    }

    @Override
    public Project projectDeal(int projectId, String username, double funding, String task) throws ExceptionZyc {
        //检查参数是否为空
        if(StringUtils.isEmpty(projectId + "") || StringUtils.isEmpty(username) || StringUtils.isEmpty(task)){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //判断经费
        if(funding < 0){
            //经费异常，抛出错误
            throw ExceptionZyc.FUNDING_IS_WRONG;
        }
        //查询项目
        Project project = projectDao.findById(projectId).orElse(null);
        //判断项目
        if(project == null){
            //项目不存在，抛出异常
            throw ExceptionZyc.PROJECT_IS_NOT_EXIST;
        }
        //设置新经费安排
        Deal deal = new Deal();
        //设置项目
        deal.setProjectId(projectId);
        //设置安排导师
        deal.setMentorUsername(username);
        //设置安排经费
        deal.setFunding(funding);
        //设置任务
        deal.setTask(task);
        //保存安排
        deal = dealDao.save(deal);
        //项目添加安排
        Set<Integer> deals = project.getDealIds();
        //添加新安排
        deals.add(deal.getId());
        //设置新安排
        project.setDealIds(deals);
        //保存并退出
        return projectDao.save(project);
    }

    @Override
    public Project modifyProjectProgress(int projectId, String projectProgress) throws ExceptionZyc {
        //检查参数是否为空
        if(StringUtils.isEmpty(projectId + "") || StringUtils.isEmpty(projectProgress)){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询项目
        Project project = projectDao.findById(projectId).orElse(null);
        //判断项目
        if(project == null){
            //项目不存在，抛出异常
            throw ExceptionZyc.PROJECT_IS_NOT_EXIST;
        }
        //项目存在，修改项目进度
        project.setProjectProgress(projectProgress);
        //保存并退出
        return projectDao.save(project);
    }

    @Override
    public Project modifyFinish(int projectId, Boolean finish) throws ExceptionZyc {
        //检查参数是否为空
        if(StringUtils.isEmpty(projectId + "") || StringUtils.isEmpty(finish.toString())){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询项目
        Project project = projectDao.findById(projectId).orElse(null);
        //判断项目
        if(project == null){
            //项目不存在，抛出异常
            throw ExceptionZyc.PROJECT_IS_NOT_EXIST;
        }
        //项目存在，修改是否完成
        project.setFinsh(finish);
        //保存并退出
        return projectDao.save(project);
    }

    @Override
    public Patent addPatent(AddPatent addPatent, MultipartFile patentPhoto) throws ExceptionZyc {
        //检测参数是否为空
        if(StringUtil.isEmpty(addPatent.getName(),addPatent.getCreater(),addPatent.getPatentee(),
                addPatent.getPatentId(),addPatent.getApplyTime().toString(),addPatent.getAuthorizationTime().toString(),addPatent.getProjectId() + "")
                  || patentPhoto.isEmpty()){
                //有参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询项目
        Project project = projectDao.findById(addPatent.getProjectId()).orElse(null);
        //判断项目
        if(project == null){
            //项目不存在，抛出异常
            throw ExceptionZyc.PROJECT_IS_NOT_EXIST;
        }
        //新建专利
        Patent patent = new Patent(addPatent);
        //保存照片
        patent.setPatentPhoto(ImageUtil.saveImg(patentPhoto));
        //保存专利信息
        patent = patentDao.save(patent);
        //更改成果
        Result result = project.getResult();
        //判断成果是否为空
        if(result == null){
            //成果为空，新建成果
            result = new Result();
        }
        //获取专利信息
        Set<Integer> patentIds = result.getPatentIds();
        //添加专利信息
        patentIds.add(patent.getId());
        //设置专利集合
        result.setPatentIds(patentIds);
        //保存成果
        result = resultDao.save(result);
        //项目保存成果
        project.setResult(result);
        //保存项目信息
        projectDao.save(project);
        return patent;
    }

    @Override
    public Patent modifyPatent(ModifyPatent modifyPatent) throws ExceptionZyc {
        //判断参数是否为空
        if(StringUtil.isEmpty(modifyPatent.getId() + "",modifyPatent.getCreater(),modifyPatent.getName()
        ,modifyPatent.getPatentee(),modifyPatent.getPatentId(),modifyPatent.getApplyTime().toString()
        ,modifyPatent.getAuthorizationTime().toString())){
            //有参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询专利
        Patent patent = patentDao.findById(modifyPatent.getId()).orElse(null);
        //判断专利是否为空
        if(patent == null){
            //专利为空，抛出异常
            throw ExceptionZyc.PATENT_IS_NOT_EXIST;
        }
        //专利存在，修改专利,修改发明者
        patent.setCreater(modifyPatent.getCreater());
        //修改专利名
        patent.setName(modifyPatent.getName());
        //修改专利权人
        patent.setPatentee(modifyPatent.getPatentee());
        //修改专利号
        patent.setPatentId(modifyPatent.getPatentId());
        //修改申请时间
        patent.setApplyTime(modifyPatent.getApplyTime());
        //修改授权时间
        patent.setAuthorizationTime(modifyPatent.getAuthorizationTime());
        //保存并返回
        return patentDao.save(patent);
    }

    @Override
    public Patent modifyPatentPhoto(int patentId, MultipartFile patentPhoto) throws ExceptionZyc {
        //判断参数
        if(StringUtil.isEmpty(patentId + "")){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询专利
        Patent patent = patentDao.findById(patentId).orElse(null);
        //判断专利是否为空
        if(patent == null){
            //专利为空，抛出异常
            throw ExceptionZyc.PATENT_IS_NOT_EXIST;
        }
        try {
            //获取旧文件
            File file = new File("F:\\img\\" + patent.getPatentPhoto());
            //删除旧文件
            file.delete();
        }catch (Exception e){ }
        //保存新图片
        patent.setPatentPhoto(ImageUtil.saveImg(patentPhoto));
        //保存并返回
        return patentDao.save(patent);
    }

    @Override
    public Boolean deletePatent(int patentId) throws ExceptionZyc {
        //判断参数
        if(StringUtil.isEmpty(patentId + "")){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询专利
        Patent patent = patentDao.findById(patentId).orElse(null);
        //判断专利是否为空
        if(patent == null){
            //专利为空，抛出异常
            throw ExceptionZyc.PATENT_IS_NOT_EXIST;
        }
        //查询项目对应的id
        Project project = projectDao.findById(patent.getProjectId()).orElse(null);
        //检查项目
        if(project == null){
            //项目为空，直接删除专利
            patentDao.delete(patent);
            return true;
        }
        //项目不为空，先删除成果内专利信息
        Result result = project.getResult();
        //如果成果为空，直接删除专利
        if(result == null){
            patentDao.delete(patent);
            return true;
        }
        //获取专利集合
        Set<Integer> patentIds = result.getPatentIds();
        //判断是否包含专利
        if(patentIds.contains(patentId)){
            //包含，删除专利
            patentDao.delete(patent);
            //移除专利信息
            patentIds.remove(patentId);
            //成果保存专利集合
            result.setPatentIds(patentIds);
            //保存成果
            project.setResult(resultDao.save(result));
            //保存项目
            projectDao.save(project);
        }
        return true;
    }

    @Override
    public SoftwareCopyright addSoftwareCopyright(AddSoftwareCopyright addSoftwareCopyright, MultipartFile softwareCopyrightPhoto) throws ExceptionZyc {
        //检测参数是否为空
        if(StringUtil.isEmpty(addSoftwareCopyright.getName(),addSoftwareCopyright.getRightRange(),addSoftwareCopyright.getAuthorizationWay()
                ,addSoftwareCopyright.getCopyrightOwner(),addSoftwareCopyright.getSoftwareId(),addSoftwareCopyright.getFinishTime().toString()
                ,addSoftwareCopyright.getProjectId()+"",addSoftwareCopyright.getPublishTime().toString())
                || softwareCopyrightPhoto.isEmpty()){
            //有参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询项目
        Project project = projectDao.findById(addSoftwareCopyright.getProjectId()).orElse(null);
        //判断项目
        if(project == null){
            //项目不存在，抛出异常
            throw ExceptionZyc.PROJECT_IS_NOT_EXIST;
        }
        //新建软件著作权
        SoftwareCopyright softwareCopyright = new SoftwareCopyright(addSoftwareCopyright);
        //保存照片
        softwareCopyright.setSoftwareCopyrightPhoto(ImageUtil.saveImg(softwareCopyrightPhoto));
        //保存软件著作权信息
        softwareCopyright = softwareCopyrightDao.save(softwareCopyright);
        //更改成果
        Result result = project.getResult();
        //判断成果是否为空
        if(result == null){
            //成果为空，新建成果
            result = new Result();
        }
        //获取软件著作权信息
        Set<Integer> softwareCopyrightIds = result.getSoftwareCopyrightIds();
        //添加软件著作权信息
        softwareCopyrightIds.add(softwareCopyright.getId());
        //设置权健著作权集合
        result.setSoftwareCopyrightIds(softwareCopyrightIds);
        //保存成果
        result = resultDao.save(result);
        //项目保存成果
        project.setResult(result);
        //保存项目信息
        projectDao.save(project);
        return softwareCopyright;
    }

    @Override
    public SoftwareCopyright modifySoftwareCopyright(ModifySoftwareright modifySoftwareright) throws ExceptionZyc {
        //判断参数是否为空
        if(StringUtil.isEmpty(modifySoftwareright.getAuthorizationWay(),modifySoftwareright.getCopyrightOwner(),modifySoftwareright.getName(),
                modifySoftwareright.getRightRange(),modifySoftwareright.getSoftwareId(),modifySoftwareright.getFinishTime().toString()
                ,modifySoftwareright.getId() + "",modifySoftwareright.getPublishTime().toString())){
            //有参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询软件著作权
        SoftwareCopyright softwareCopyright = softwareCopyrightDao.findById(modifySoftwareright.getId()).orElse(null);
        //判断软件著作权是否为空
        if(softwareCopyright == null){
            //软件著作权为空，抛出异常
            throw ExceptionZyc.SOFTWARECOPYRIGHT_IS_NOT_EXIST;
        }
        //软件著作权存在，修改软件著作权信息,修改授权方式
        softwareCopyright.setAuthorizationWay(modifySoftwareright.getAuthorizationWay());
        //修改著作权人
        softwareCopyright.setCopyrightOwner(modifySoftwareright.getCopyrightOwner());
        //修改软件名称
        softwareCopyright.setName(modifySoftwareright.getName());
        //修改授权范围
        softwareCopyright.setRightRange(modifySoftwareright.getRightRange());
        //修改软件著作权号
        softwareCopyright.setSoftwareId(modifySoftwareright.getSoftwareId());
        //修改完成时间
        softwareCopyright.setFinishTime(modifySoftwareright.getFinishTime());
        //修改首次发布时间
        softwareCopyright.setPublishTime(modifySoftwareright.getPublishTime());
        //保存并返回
        return softwareCopyrightDao.save(softwareCopyright);
    }

    @Override
    public SoftwareCopyright modifySoftwareCopyrightPhoto(int softwarerightId, MultipartFile softwareCopyrightPhoto) throws ExceptionZyc {
        //判断参数
        if(StringUtil.isEmpty(softwarerightId + "")){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询软件著作权
        SoftwareCopyright softwareCopyright = softwareCopyrightDao.findById(softwarerightId).orElse(null);
        //判断软件著作权是否为空
        if(softwareCopyright == null){
            //软件著作权为空，抛出异常
            throw ExceptionZyc.SOFTWARECOPYRIGHT_IS_NOT_EXIST;
        }
        try {
            //获取旧文件
            File file = new File("F:\\img\\" + softwareCopyright.getSoftwareCopyrightPhoto());
            //删除旧文件
            file.delete();
        }catch (Exception e){ }
        //保存新图片
        softwareCopyright.setSoftwareCopyrightPhoto(ImageUtil.saveImg(softwareCopyrightPhoto));
        //保存并返回
        return softwareCopyrightDao.save(softwareCopyright);
    }

    @Override
    public Boolean deleteSoftwareCopyright(int softwareCopyrightId) throws ExceptionZyc {
        //判断参数
        if(StringUtil.isEmpty(softwareCopyrightId + "")){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询软件著作权
        SoftwareCopyright softwareCopyright = softwareCopyrightDao.findById(softwareCopyrightId).orElse(null);
        //判断软件著作权是否为空
        if(softwareCopyright == null){
            //软件著作权为空，抛出异常
            throw ExceptionZyc.SOFTWARECOPYRIGHT_IS_NOT_EXIST;
        }
        //查询项目对应的id
        Project project = projectDao.findById(softwareCopyright.getProjectId()).orElse(null);
        //检查项目
        if(project == null){
            //项目为空，直接删除软件著作权
            softwareCopyrightDao.delete(softwareCopyright);
            return true;
        }
        //项目不为空，先删除成果内软件著作权信息
        Result result = project.getResult();
        //如果成果为空，直接删除软件著作权
        if(result == null){
            softwareCopyrightDao.delete(softwareCopyright);
            return true;
        }
        //获取软件著作权
        Set<Integer> softwareCopyrightIds = result.getSoftwareCopyrightIds();
        //判断是否包含软件著作权
        if(softwareCopyrightIds.contains(softwareCopyrightId)){
            //包含，删除软件著作权
            softwareCopyrightDao.delete(softwareCopyright);
            //移除软件著作权信息
            softwareCopyrightIds.remove(softwareCopyrightId);
            //成果保存软件著作权集合
            result.setSoftwareCopyrightIds(softwareCopyrightIds);
            //保存成果
            project.setResult(resultDao.save(result));
            //保存项目
            projectDao.save(project);
        }
        return true;
    }

    @Override
    public List<Project> findProjectByMentor(int mentorId, Pageable pageable) throws ExceptionZyc {
        //判断参数
        if(StringUtil.isEmpty(mentorId +"")){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询导师
        Mentor mentor = mentorDao.findById(mentorId).orElse(null);
        //判断导师是否为空
        if(mentor == null){
            //导师不存在
            throw ExceptionZyc.MENTOR_IS_NOT_EXIST;
        }
        //返回查询结果
        return projectDao.findByIdIn(mentor.getProjects(),pageable).getContent();
    }

    @Override
    public List<Project> findProjects(String name, String projectLeader, int mentorId, Pageable pageable) throws ExceptionZyc {
        List<Project> result = projectDao.findAll(new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb){
                List<Predicate> list = new ArrayList<Predicate>();

                if(!StringUtil.isEmpty(name)){
                    list.add(cb.like(root.get("name").as(String.class),"%" + name + "%"));
                }

                if(!StringUtil.isEmpty(projectLeader)){
                    list.add(cb.equal(root.get("projectLeader").as(String.class),projectLeader));
                }

                if(!StringUtil.isEmpty(mentorId + "")){
                    //查询导师
                    Mentor mentor = mentorDao.findById(mentorId).orElse(null);
                    //判断导师是否为空
                    if(mentor != null){
                        CriteriaBuilder.In<Integer> in = cb.in(root.get("id"));
                        for(int id:mentor.getProjects()){
                            in.value(id);
                        }
                        list.add(in);
                    }
                }

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        },pageable).getContent();
        //返回结果
        return result;
    }

    @Override
    public List<Patent> findPatentByProject(String name, String patentId, int projectId,Pageable pageable) throws ExceptionZyc {
        return patentDao.findAll(new Specification<Patent>(){
            @Override
            public Predicate toPredicate(Root<Patent> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //查询条件几个
                List<Predicate> list = new ArrayList<>();

                if(!StringUtil.isEmpty(name)){
                    list.add(criteriaBuilder.like(root.get("name").as(String.class),"%" + name + "%"));
                }

                if(!StringUtil.isEmpty(patentId)){
                    list.add(criteriaBuilder.equal(root.get("patentId").as(String.class),patentId));
                }

                if(!StringUtil.isEmpty(projectId + "")){
                    list.add(criteriaBuilder.equal(root.get("projectId").as(Integer.class),projectId));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable).getContent();
    }

    @Override
    public List<SoftwareCopyright> findSoftwareByProject(String name, String softwareCpoyrightId, int projectId,Pageable pageable) throws ExceptionZyc {
        return softwareCopyrightDao.findAll(new Specification<SoftwareCopyright>(){
            @Override
            public Predicate toPredicate(Root<SoftwareCopyright> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //查询条件几个
                List<Predicate> list = new ArrayList<>();

                if(!StringUtil.isEmpty(name)){
                    list.add(criteriaBuilder.like(root.get("name").as(String.class),"%" + name + "%"));
                }

                if(!StringUtil.isEmpty(softwareCpoyrightId)){
                    list.add(criteriaBuilder.equal(root.get("softwareId").as(String.class),softwareCpoyrightId));
                }

                if(!StringUtil.isEmpty(projectId + "")){
                    list.add(criteriaBuilder.equal(root.get("projectId").as(Integer.class),projectId));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable).getContent();
    }

    @Override
    public List<Deal> findDealByProject(int projectId, String mentorUsername,Pageable pageable) throws ExceptionZyc {
        return dealDao.findAll(new Specification<Deal>(){
            @Override
            public Predicate toPredicate(Root<Deal> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //查询条件几个
                List<Predicate> list = new ArrayList<>();

                if(!StringUtil.isEmpty(mentorUsername)){
                    list.add(criteriaBuilder.equal(root.get("mentorUsername").as(String.class),mentorUsername));
                }

                if(!StringUtil.isEmpty(projectId + "")){
                    list.add(criteriaBuilder.equal(root.get("projectId").as(Integer.class),projectId));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        });
    }
}
