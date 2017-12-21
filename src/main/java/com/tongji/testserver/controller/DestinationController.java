package com.tongji.testserver.controller;

import com.tongji.testserver.comm.Const;
import com.tongji.testserver.comm.aop.LoggerManage;
import com.tongji.testserver.domain.Destination;
import com.tongji.testserver.domain.Path;
import com.tongji.testserver.domain.User;
import com.tongji.testserver.domain.result.ExceptionMsg;
import com.tongji.testserver.domain.result.Response;
import com.tongji.testserver.domain.result.ResponseData;
import com.tongji.testserver.repository.DestinationRepository;
import com.tongji.testserver.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.security.krb5.internal.crypto.Des;

/**
 * @program: testserver
 * @description:
 * @author: Annntn
 * @create: 2017-12-20 23:34
 **/
@RestController
@RequestMapping("/destinations")
@EnableSwagger2
public class DestinationController extends BaseController {

    @Autowired
    private DestinationRepository destinationRepository;

    @RequestMapping(value = "/hotValue", method = RequestMethod.POST)
    @LoggerManage(description = "获得全天拥挤分布")
    public ResponseData getRecPath(double latitude, double longitude, int dayOfWeek, Long time){
        try {
            //服务器请求全天拥挤分布
            return new ResponseData(ExceptionMsg.SUCCESS, "现在还没有计算出来");
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @LoggerManage(description = "获取des具体信息")
    public ResponseData create(long desId){
        try {
            Destination destination = destinationRepository.findById(desId);
            return new ResponseData(ExceptionMsg.SUCCESS, destination);
        }catch (Exception e) {
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/description", method = RequestMethod.POST)
    @LoggerManage(description = "设置描述")
    public ResponseData setDescription(long desId, String description){
        try {
            Destination destination = destinationRepository.findById(desId);
            destination.setDescription(description);
            destinationRepository.save(destination);
            return new ResponseData(ExceptionMsg.SUCCESS, destination);
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/title", method = RequestMethod.POST)
    @LoggerManage(description = "设置title")
    public ResponseData setTitle(long desId, String title){
        try {
            Destination destination = destinationRepository.findById(desId);
            destination.setTitle(title);
            destinationRepository.save(destination);
            return new ResponseData(ExceptionMsg.SUCCESS, destination);
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/type", method = RequestMethod.POST)
    @LoggerManage(description = "设置类型")
    public ResponseData setType(long desId, String type){
        try {
            Destination destination = destinationRepository.findById(desId);
            destination.setType(type);
            destinationRepository.save(destination);
            return new ResponseData(ExceptionMsg.SUCCESS, destination);
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

}
