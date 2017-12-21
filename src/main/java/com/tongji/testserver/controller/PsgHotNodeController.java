package com.tongji.testserver.controller;


import com.tongji.testserver.comm.aop.LoggerManage;
import com.tongji.testserver.domain.Path;
import com.tongji.testserver.domain.PsgHotNode;
import com.tongji.testserver.domain.User;
import com.tongji.testserver.domain.result.ExceptionMsg;
import com.tongji.testserver.domain.result.ResponseData;
import com.tongji.testserver.repository.NoticeRepository;
import com.tongji.testserver.repository.PsgHotNodeRepository;
import com.tongji.testserver.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/psgHotNode")
public class PsgHotNodeController extends BaseController{

    @Autowired
    private PsgHotNodeRepository psgHotNodeRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @LoggerManage(description = "增加新的候客热点")
    public ResponseData create(double latitude,double longitude){
        try {
            PsgHotNode psgHotNode = new PsgHotNode();
            psgHotNode.setLatitude(latitude);
            psgHotNode.setLongitude(longitude);
            psgHotNode.setHotExtent("");
            psgHotNode.setMapNodeId(0);
            psgHotNodeRepository.save(psgHotNode);

            return new ResponseData(ExceptionMsg.SUCCESS, psgHotNode);
        }catch (Exception e){
            logger.error("create path failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/surroundHot", method = RequestMethod.POST)
    @LoggerManage(description = "获得周围热点")
    public ResponseData getRecPath(double latitude, double longitude, int dayOfWeek, Long time){
        try {
            //服务器请求周围热点
            return new ResponseData(ExceptionMsg.SUCCESS, "现在还没有计算出来");
        }catch (Exception e){
            logger.error("failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

}
