package com.ycjw.technology.service.award;

import com.ycjw.technology.exception.ExceptionZyc;
import com.ycjw.technology.model.award.Award;
import com.ycjw.technology.model.request.award.AddAward;
import com.ycjw.technology.model.request.award.ModifyAward;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AwardService {

    /**
     *  添加科研奖项
     * @param addAward 添加的奖项信息
     * @param awardPhotos 获奖证书
     * @return 添加的奖项信息
     * @throws ExceptionZyc 抛出的异常
     */
    Award addAward(AddAward addAward, MultipartFile awardPhotos) throws ExceptionZyc;

    /**
     * 修改奖项信息
     * @param modifyAward 修改的奖项信息
     * @return 修改后的奖项信息
     * @throws ExceptionZyc 抛出的异常
     */
    Award modifyAward(ModifyAward modifyAward) throws ExceptionZyc;

    /**
     *  修改获奖证书
     * @param awardId 奖项id
     * @param awardPhotos 更改的照片
     * @return 修改后的奖项信息
     * @throws ExceptionZyc 抛出的异常
     */
    Award modifyAwardPhoto(int awardId,MultipartFile awardPhotos) throws ExceptionZyc;

    /**
     * 删除奖项信息
     * @param awardId 奖项Id
     * @return 删除状态
     * @throws ExceptionZyc 抛出的异常
     */
    Boolean deleteAward(int awardId) throws ExceptionZyc;

    /**
     *  查询奖项信息
     * @param mentorId 导师id
     * @return 查询的集合
     * @throws ExceptionZyc 抛出的异常
     */
    List<Award> findAward(int mentorId, Pageable pageable) throws ExceptionZyc;
}
