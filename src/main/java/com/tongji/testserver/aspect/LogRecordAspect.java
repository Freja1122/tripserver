package com.tongji.testserver.aspect;

import com.tongji.testserver.domain.Destination;
import com.tongji.testserver.domain.User;
import com.tongji.testserver.domain.result.ExceptionMsg;
import com.tongji.testserver.domain.result.Response;
import com.tongji.testserver.domain.result.ResponseData;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * @program: testserver
 * @description: 检查是否登录
 * @author: Annntn
 * @create: 2017-12-21 12:33
 **/
@Aspect
@Order(5)
@Component
public class LogRecordAspect extends BaseInterceptor{


    /**
     * 定义拦截规则：拦截controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("!execution(* com.tongji.testserver.controller.UserController.create(..)) && !execution(* com.tongji.testserver.controller.UserController.login(..)) && execution(* com.tongji.testserver.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut(){}

    /**
     * 拦截器具体实现
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp){
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名

        Set<Object> allParams = new LinkedHashSet<>(); //保存所有请求参数，用于输出到日志中

        logger.info(methodName);

        Object result = null;

        User loginUser = getUser();
        if (loginUser==null){
            result = new ResponseData(ExceptionMsg.GetUserError);
        }

        try {
            if(result == null){
                // 一切正常的情况下，继续执行被拦截的方法
                result = pjp.proceed();
            }
        } catch (Throwable e) {
            logger.info("exception: ", e);
            result = new ResponseData(ExceptionMsg.FAILED);
        }

        if(result instanceof ResponseData){
            long costMs = System.currentTimeMillis() - beginTime;
            logger.info("请求结束");
        }

        return result;
    }

}
