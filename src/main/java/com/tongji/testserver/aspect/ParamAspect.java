package com.tongji.testserver.aspect;

import com.tongji.testserver.domain.Destination;
import com.tongji.testserver.domain.Path;
import com.tongji.testserver.domain.User;
import com.tongji.testserver.domain.result.ExceptionMsg;
import com.tongji.testserver.domain.result.Response;
import com.tongji.testserver.domain.result.ResponseData;
import com.tongji.testserver.repository.DestinationRepository;
import com.tongji.testserver.repository.PathRepository;
import com.tongji.testserver.utils.ParameterNameUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.security.krb5.internal.crypto.Des;

import javax.servlet.http.HttpServletRequest;
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
public class ParamAspect extends BaseInterceptor{

    @Autowired
    private PathRepository pathRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    /**
     * 定义拦截规则：有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* com.tongji.testserver.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
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
        logger.info(methodName);

        Object result = null;

        Object[] args = pjp.getArgs();
        for(Object arg : args){
            //logger.debug("arg: {}", arg);
            if (arg instanceof Destination) {
                //检查destination
                @SuppressWarnings("unchecked")
                Destination paramVO = (Destination) arg;

                if(paramVO.getLongitude()==0||paramVO.getLatitude()==0||paramVO.getDesOrder()==0||paramVO.getDayOfWeek()==0||paramVO.getArriveTime()==null){
                    result = new ResponseData(ExceptionMsg.DesParamError);
                }

            }else if(arg instanceof Path){
                Path path = pathRepository.findById(((Path) arg).getId());
                if (path == null){
                    result = new ResponseData(ExceptionMsg.PathParamError);
                }
            }
        }

        for(Object arg : args){
            //logger.debug("arg: {}", arg);
            if (arg == null) {
                result = new ResponseData(ExceptionMsg.ParamNull);
            }
        }

        String[] parameterNames = ParameterNameUtils.getMethodParameterNamesByAnnotation(method);
        if (parameterNames!=null){
            for (int i=0;i<parameterNames.length;i++){
                if (parameterNames[i] == "pathId"){
                    Path path = pathRepository.findById(Long.valueOf(args[i].toString()));
                    if (path==null){
                        result = new ResponseData(ExceptionMsg.PathParamError);
                    }

                }else if (parameterNames[i] == "destinationId"){
                    Destination destination = destinationRepository.findById(Long.valueOf(args[i].toString()));
                    if (destination==null){
                        result = new ResponseData(ExceptionMsg.DesParamError);
                    }
                }
            }
        }


        try {
            if(result == null){
                // 一切正常的情况下，继续执行被拦截的方法
                result = pjp.proceed();
            }
        } catch (Throwable e) {
            logger.info("exception: ", e);
            result = new ResponseData( ExceptionMsg.ERROR );
        }

        if(result instanceof ResponseData){
            long costMs = System.currentTimeMillis() - beginTime;
            logger.info("请求结束");
        }

        return result;
    }



}
