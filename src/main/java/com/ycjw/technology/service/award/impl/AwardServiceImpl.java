package com.ycjw.technology.service.award.impl;

import com.ycjw.technology.exception.ExceptionZyc;
import com.ycjw.technology.model.award.Award;
import com.ycjw.technology.model.request.award.AddAward;
import com.ycjw.technology.model.request.award.ModifyAward;
import com.ycjw.technology.model.user.Mentor;
import com.ycjw.technology.repository.award.AwardDao;
import com.ycjw.technology.repository.user.MentorDao;
import com.ycjw.technology.service.award.AwardService;
import com.ycjw.technology.util.ImageUtil;
import com.ycjw.technology.util.StringUtil;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AwardServiceImpl implements AwardService {
    @Autowired
    MentorDao mentorDao;
    @Autowired
    AwardDao awardDao;



    @Override
    public Award addAward(AddAward addAward, MultipartFile awardPhotos) throws ExceptionZyc {
        //判断参数信息
        if(StringUtil.isEmpty(addAward.getAwardRank(),addAward.getDepartment(),addAward.getAwardLevel().toString()
                ,addAward.getAwardTime().toString(),addAward.getMentorId() + ""
        )){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询导师
        Mentor mentor = mentorDao.findById(addAward.getMentorId()).orElse(null);
        //判断导师是否存在
        if(mentor == null){
            //导师为空，抛出异常
            throw ExceptionZyc.MENTOR_IS_NOT_EXIST;
        }
        //新建奖项
        Award award = new Award(addAward);
        //设置照片路径
        award.setAwardPhoto(ImageUtil.saveImg(awardPhotos));
        //保存
        award = awardDao.save(award);
        //获取导师获奖集合
        Set<Integer> awards = mentor.getAwards();
        //添加奖项
        awards.add(award.getId());
        //设置新获奖集合
        mentor.setAwards(awards);
        //保存导师信息
        mentorDao.save(mentor);
        //返回
        return award;
    }

    @Override
    public Award modifyAward(ModifyAward modifyAward) throws ExceptionZyc {
        //判断参数信息
        if(StringUtil.isEmpty(modifyAward.getAwardRank(),modifyAward.getDepartment(),modifyAward.getAwardLevel().toString()
                ,modifyAward.getAwardTime().toString(),modifyAward.getId() + ""
        )){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询奖项
        Award award = awardDao.findById(modifyAward.getId()).orElse(null);
        //判断是否为空
        if(award == null){
            //奖项为空，抛出错误
            throw ExceptionZyc.AWARD_IS_NOT_EXIST;
        }
        //修改奖项信息,修改排名
        award.setAwardRank(modifyAward.getAwardRank());
        //修改所属部门
        award.setDepartment(modifyAward.getDepartment());
        //修改奖项等级
        award.setAwardLevel(modifyAward.getAwardLevel());
        //修改获奖时间
        award.setAwardTime(modifyAward.getAwardTime());
        //保存并返回
        return awardDao.save(award);
    }

    @Override
    public Award modifyAwardPhoto(int awardId, MultipartFile awardPhotos) throws ExceptionZyc {
        //判断参数
        if(StringUtil.isEmpty(awardId + "")){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询奖项
        Award award = awardDao.findById(awardId).orElse(null);
        //判断是否为空
        if(award == null){
            //奖项为空，抛出错误
            throw ExceptionZyc.AWARD_IS_NOT_EXIST;
        }
        //删除旧照片
        try{
            File file = new File("F:\\img\\" + award.getAwardPhoto());
            file.delete();
        }catch (Exception e){}
        //设置新照片
        award.setAwardPhoto(ImageUtil.saveImg(awardPhotos));
        //保存并返回
        return awardDao.save(award);
    }

    @Override
    public Boolean deleteAward(int awardId) throws ExceptionZyc {
        //判断参数
        if(StringUtil.isEmpty(awardId + "")){
            //参数为空，抛出异常
            throw ExceptionZyc.PARAM_IS_NULL;
        }
        //查询奖项
        Award award = awardDao.findById(awardId).orElse(null);
        //判断是否为空
        if(award == null){
            //奖项为空，抛出错误
            throw ExceptionZyc.AWARD_IS_NOT_EXIST;
        }
        //查询导师
        Mentor mentor = mentorDao.findById(award.getMentorId()).orElse(null);
        //判断导师是否存在
        if(mentor == null){
            //导师为空，抛出异常
            throw ExceptionZyc.MENTOR_IS_NOT_EXIST;
        }
        //获取导师获奖集合
        Set<Integer> awards = mentor.getAwards();
        //移除奖项id
        if(awards.contains(awardId)){
            //移除奖项id
            awards.remove(awardId);
            //设置新获奖集合
            mentor.setAwards(awards);
            //保存导师信息
            mentorDao.save(mentor);
        }
        awardDao.delete(award);
        return true;
    }

    @Override
    public List<Award> findAward(int mentorId, Pageable pageable) throws ExceptionZyc {
        //如果查询条件为空
        if(StringUtil.isEmpty(mentorId + "")){
            //返回全部奖项信息
            return awardDao.findAll(pageable).getContent();
        }
        //根据导师id查询
        return awardDao.findByMentorId(mentorId,pageable).getContent();
    }
}
