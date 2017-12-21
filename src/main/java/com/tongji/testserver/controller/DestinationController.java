package com.tongji.testserver.controller;

import com.tongji.testserver.comm.Const;
import com.tongji.testserver.comm.aop.LoggerManage;
import com.tongji.testserver.domain.Destination;
import com.tongji.testserver.domain.User;
import com.tongji.testserver.domain.result.ExceptionMsg;
import com.tongji.testserver.domain.result.Response;
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


}
