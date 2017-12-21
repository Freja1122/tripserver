package com.tongji.testserver.controller;

import com.tongji.testserver.comm.aop.LoggerManage;
import com.tongji.testserver.domain.Destination;
import com.tongji.testserver.domain.Path;
import com.tongji.testserver.domain.Tag;
import com.tongji.testserver.domain.User;
import com.tongji.testserver.domain.result.ExceptionMsg;
import com.tongji.testserver.domain.result.ResponseData;
import com.tongji.testserver.repository.DestinationRepository;
import com.tongji.testserver.repository.PathRepository;
import com.tongji.testserver.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @program: testserver
 * @description: 处理路径
 * @author: Annntn
 * @create: 2017-12-20 23:54
 **/
@RestController
@RequestMapping("/path")
@EnableSwagger2
public class PathController extends BaseController {
    @Autowired
    private PathRepository pathRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @LoggerManage(description = "增加新的路径")
    public ResponseData create(){
        try {
            //无条件增加并返回一个创建的pathId
            Path path = new Path();
            path.setCreateTime(DateUtils.getCurrentTime());
            User loginUser = getUser();
            loginUser.addPath(path);
//            path.setUser(loginUser);
            pathRepository.save(path);
            return new ResponseData(ExceptionMsg.SUCCESS, path);
        }catch (Exception e){
            logger.error("create path failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @LoggerManage(description = "获取path具体信息")
    public ResponseData create(long pathId){
        try {
            Path path = pathRepository.findById(pathId);
            return new ResponseData(ExceptionMsg.SUCCESS, path);
        }catch (Exception e) {
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }


    @RequestMapping(value = "/destination", method = RequestMethod.POST)
    @LoggerManage(description = "为路径新增目标节点")
    public ResponseData addDes(long pathId, Destination destination){
        try {
            Path path = pathRepository.findById(pathId);
            if (path==null){
                return new ResponseData(ExceptionMsg.GetPathError);
            }
            User loginUser = getUser();
            destination.setUser(loginUser);
            destination.setCreateTime(DateUtils.getCurrentTime());
            destinationRepository.save(destination);
            path.getDestinations().add(destination);
            return new ResponseData(ExceptionMsg.SUCCESS, path);
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/recommendPath", method = RequestMethod.POST)
    @LoggerManage(description = "获得推荐路径")
    public ResponseData getRecPath(long pathId){
        try {
            Path path = pathRepository.findById(pathId);
            User loginUser = getUser();
            //服务器请求推荐路径
            return new ResponseData(ExceptionMsg.SUCCESS, "现在还没有计算出来");
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/recommendTime", method = RequestMethod.POST)
    @LoggerManage(description = "获得推荐时间")
    public ResponseData getRecTime(long pathId){
        try {
            Path path = pathRepository.findById(pathId);
            User loginUser = getUser();
            User user = getUser();//本句话可以丢掉
            //服务器请求推荐时间
            return new ResponseData(ExceptionMsg.SUCCESS, "现在还没有计算出来");
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/preferLeaveTime", method = RequestMethod.POST)
    @LoggerManage(description = "设置偏好出行时间")
    public ResponseData setPreferTime(long pathId, Long preferLeaveTime){
        try {
            Path path = pathRepository.findById(pathId);
            path.setPreferLeaveTime(preferLeaveTime);
            return new ResponseData(ExceptionMsg.SUCCESS, path);
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }


    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @LoggerManage(description = "删除一条轨迹")
    public ResponseData deletePath(long pathId){
        try {
            Path path = pathRepository.findById(pathId);
//            pathRepository.delete(path);
            return new ResponseData(ExceptionMsg.SUCCESS,pathId);
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED,pathId);
        }
    }

    @RequestMapping(value = "/allPath", method = RequestMethod.DELETE)
    @LoggerManage(description = "删除当前用户的所有轨迹")
    public ResponseData deleteAllPath(){
        try {
            User user = getUser();
            if(user.getPaths()!=null){
                user.getPaths().clear();
            }
            return new ResponseData(ExceptionMsg.SUCCESS);
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }


    @RequestMapping(value = "/description", method = RequestMethod.POST)
    @LoggerManage(description = "设置描述")
    public ResponseData setDescription(long pathId, String description){
        try {
            Path path = pathRepository.findById(pathId);
            path.setDescription(description);
            pathRepository.save(path);
            return new ResponseData(ExceptionMsg.SUCCESS, path);
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/title", method = RequestMethod.POST)
    @LoggerManage(description = "设置title")
    public ResponseData setTitle(long pathId, String title){
        try {
            Path path = pathRepository.findById(pathId);
            path.setTitle(title);
            pathRepository.save(path);
            return new ResponseData(ExceptionMsg.SUCCESS, path);
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/type", method = RequestMethod.POST)
    @LoggerManage(description = "设置类型")
    public ResponseData setType(long pathId, String type){
        try {
            Path path = pathRepository.findById(pathId);
            path.setType(type);
            pathRepository.save(path);
            return new ResponseData(ExceptionMsg.SUCCESS, path);
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }




}
