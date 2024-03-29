package com.tongji.testserver.comm.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManage {

	public String description();
}
