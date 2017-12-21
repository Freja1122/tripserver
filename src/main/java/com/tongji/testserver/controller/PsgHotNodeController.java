package com.tongji.testserver.controller;


import com.tongji.testserver.comm.aop.LoggerManage;
import com.tongji.testserver.domain.result.ExceptionMsg;
import com.tongji.testserver.domain.result.ResponseData;
import com.tongji.testserver.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/psgHotNode")
public class PsgHotNodeController extends BaseController{

    @RequestMapping(value = "/hotValue", method = RequestMethod.POST)
    @LoggerManage(description = "获得周围热点")
    public ResponseData getRecPath(double latitude, double longitude){
        try {

            //服务器请求周围热点
            return new ResponseData(ExceptionMsg.SUCCESS, "现在还没有计算出来");
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

}
