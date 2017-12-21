package com.tongji.testserver.controller;

import com.tongji.testserver.comm.aop.LoggerManage;
import com.tongji.testserver.domain.*;
import com.tongji.testserver.domain.result.ExceptionMsg;
import com.tongji.testserver.domain.result.ResponseData;
import com.tongji.testserver.repository.*;
import com.tongji.testserver.service.HotNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: testserver
 * @description:
 * @author: Annntn
 * @create: 2017-12-21 18:39
 **/

@RestController
@RequestMapping("/favorites")
@EnableSwagger2
public class FavoritesController extends BaseController{

    @Autowired
    private PathRepository pathRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private LoadHotNodeRepository loadHotNodeRepository;
    @Autowired
    private PsgHotNodeRepository psgHotNodeRepository;

    @RequestMapping(value = "/path", method = RequestMethod.POST)
    @LoggerManage(description="增加路径收藏")
    public ResponseData addPathToFavorites(Long pathId) {
        try {
            User user = getUser();
            Path path = pathRepository.findById(pathId);
            if (path==null){
                return new ResponseData(ExceptionMsg.PathIdError);
            }
            user.getPathFavorites().addPath(path);
            userRepository.save(user);
            return new ResponseData(ExceptionMsg.SUCCESS, user.getPathFavorites());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("updateIntroduction failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/destination", method = RequestMethod.POST)
    @LoggerManage(description="增加目标点收藏")
    public ResponseData addDestinationToFavorites(Long desId) {
        try {
            User user = getUser();
            Destination destination = destinationRepository.findById(desId);
            if(destination==null){
                return new ResponseData(ExceptionMsg.DesIdError);
            }
            user.getDestinationFavorites().addDestination(destination);
            userRepository.save(user);
            return new ResponseData(ExceptionMsg.SUCCESS, user.getDestinationFavorites());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("updateIntroduction failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/loadHotNode", method = RequestMethod.POST)
    @LoggerManage(description="增加载客热点收藏")
    public ResponseData addLoadHotNodeToFavorites(Long id) {
        try {
            User user = getUser();
            LoadHotNode loadHotNode = loadHotNodeRepository.findById(id);
            if (loadHotNode==null){
                return  new ResponseData(ExceptionMsg.DesIdError);
            }
            user.getLoadHotFavorites().addLoadHotNode(loadHotNode);
            userRepository.save(user);
            return new ResponseData(ExceptionMsg.SUCCESS, user.getDestinationFavorites());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("updateIntroduction failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/psgHotNode", method = RequestMethod.POST)
    @LoggerManage(description="增加候车热点收藏")
    public ResponseData addPsgHotNodeToFavorites(Long id) {
        try {
            User user = getUser();
            PsgHotNode psgHotNode = psgHotNodeRepository.findById(id);
            if (psgHotNode==null){
                new ResponseData(ExceptionMsg.PsgHotIdError);
            }
            user.getPsgHotFavorites().addPsgHotNode(psgHotNode);
            userRepository.save(user);
            return new ResponseData(ExceptionMsg.SUCCESS, user.getDestinationFavorites());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("updateIntroduction failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }


    @RequestMapping(value = "/psgHotNode", method = RequestMethod.DELETE)
    @LoggerManage(description="删除候车热点收藏")
    public ResponseData deletePsgHotNodeToFavorites(Long id) {
        try {
            User user = getUser();
            PsgHotNode psgHotNode = psgHotNodeRepository.findById(id);
            if (psgHotNode==null){
                new ResponseData(ExceptionMsg.PsgHotIdError);
            }

            if(user.getPsgHotFavorites().deletePsgHotNode(psgHotNode)==null){
                return new ResponseData(ExceptionMsg.FAILED, "删除失败");
            }
            userRepository.save(user);
            return new ResponseData(ExceptionMsg.SUCCESS, user.getDestinationFavorites());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("updateIntroduction failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/destination", method = RequestMethod.DELETE)
    @LoggerManage(description="删除目标点收藏")
    public ResponseData deleteDestinationToFavorites(Long id) {
        try {
            User user = getUser();
            Destination destination = destinationRepository.findById(id);
            if (destination==null){
                new ResponseData(ExceptionMsg.DesIdError);
            }

            if(user.getDestinationFavorites().deleteDestination(destination)==null){
                return new ResponseData(ExceptionMsg.FAILED, "删除失败");
            }
            userRepository.save(user);
            return new ResponseData(ExceptionMsg.SUCCESS, user.getDestinationFavorites());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("updateIntroduction failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/loadHotNode", method = RequestMethod.DELETE)
    @LoggerManage(description="删除载客热点收藏")
    public ResponseData deleteloadHotNodeFromFavorites(Long id) {
        try {
            User user = getUser();
            LoadHotNode loadHotNode = loadHotNodeRepository.findById(id);
            if (loadHotNode==null){
                new ResponseData(ExceptionMsg.LoadHotIdError);
            }

            if(user.getLoadHotFavorites().deleteLoadHotNode(loadHotNode)==null){
                return new ResponseData(ExceptionMsg.FAILED, "删除失败");
            }
            userRepository.save(user);
            return new ResponseData(ExceptionMsg.SUCCESS, user.getDestinationFavorites());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("updateIntroduction failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping(value = "/path", method = RequestMethod.DELETE)
    @LoggerManage(description="删除路径收藏")
    public ResponseData deletePathFromFavorites(Long id) {
        try {
            User user = getUser();
            Path path = pathRepository.findById(id);
            if (path==null){
                new ResponseData(ExceptionMsg.PathIdError);
            }

            if(user.getPathFavorites().deletePath(path)==null){
                return new ResponseData(ExceptionMsg.FAILED, "删除失败");
            }
            userRepository.save(user);
            return new ResponseData(ExceptionMsg.SUCCESS, user.getDestinationFavorites());
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("updateIntroduction failed, ", e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }


}
