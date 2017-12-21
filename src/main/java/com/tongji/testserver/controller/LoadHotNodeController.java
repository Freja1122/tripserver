package com.tongji.testserver.controller;


import com.tongji.testserver.comm.aop.LoggerManage;
import com.tongji.testserver.domain.LoadHotNode;
import com.tongji.testserver.domain.Path;
import com.tongji.testserver.domain.PsgHotNode;
import com.tongji.testserver.domain.User;
import com.tongji.testserver.domain.result.ExceptionMsg;
import com.tongji.testserver.domain.result.ResponseData;
import com.tongji.testserver.repository.LoadHotNodeRepository;
import com.tongji.testserver.repository.PsgHotNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/loadHotNode")
public class LoadHotNodeController extends BaseController{

    @Autowired
    private LoadHotNodeRepository loadHotNodeRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @LoggerManage(description = "增加新的载客")
    public ResponseData create(double latitude,double longitude){
        try {
            LoadHotNode loadHotNode = new LoadHotNode();
            loadHotNode.setLatitude(latitude);
            loadHotNode.setLongitude(longitude);
            loadHotNode.setHotExtent("");
            loadHotNode.setMapNodeId(0);
            loadHotNodeRepository.save(loadHotNode);

            return new ResponseData(ExceptionMsg.SUCCESS, loadHotNode);
        }catch (Exception e){
            logger.error("create path failed", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/surroundHot", method = RequestMethod.POST)
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
