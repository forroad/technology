package com.ycjw.technology.controller.award;

import com.ycjw.technology.exception.ExceptionZyc;
import com.ycjw.technology.model.Response;
import com.ycjw.technology.model.award.Award;
import com.ycjw.technology.model.request.award.AddAward;
import com.ycjw.technology.model.request.award.ModifyAward;
import com.ycjw.technology.service.award.AwardService;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("award")
public class AwardController {
    @Autowired
    AwardService awardService;

   @ApiOperation("添加奖项信息")
   @PutMapping("addAward")
    public Response addAward(@RequestBody AddAward addAward,
                             @RequestParam("awardPhotos") MultipartFile awardPhotos) throws ExceptionZyc{
       return new Response("添加成功",awardService.addAward(addAward, awardPhotos));
   }

    @ApiOperation("修改奖项信息")
    @PostMapping("modifyAward")
    public Response modifyAward(@RequestBody  ModifyAward modifyAward) throws ExceptionZyc{
       return new Response("修改成功",awardService.modifyAward(modifyAward));
    }

    @ApiOperation("修改奖项照片")
    @PostMapping("modifyAwardPhoto")
    public Response modifyAwardPhoto(@RequestParam("awardId") int awardId,
                                     @RequestParam("awardPhotos") MultipartFile awardPhotos) throws ExceptionZyc{
       return new Response("修改成功",awardService.modifyAwardPhoto(awardId, awardPhotos));
    }

   @ApiOperation(("删除奖项"))
   @DeleteMapping("deleteAward")
    public Response deleteAward(@RequestParam("awardId") int awardId) throws ExceptionZyc{
       return new Response("删除照片",awardService.deleteAward(awardId));
   }

    @ApiOperation("查询奖项信息")
    @GetMapping("findAward")
    public Response findAward(@RequestParam("mentorId") int mentorId, Pageable pageable) throws ExceptionZyc{
       return new Response("查询成功",awardService.findAward(mentorId, pageable));
    }

}
