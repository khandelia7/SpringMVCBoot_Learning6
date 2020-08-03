package com.spring.rest.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("execution (public String com.spring.rest.controller.MainController.helloMethod())")
	public void logBefore() {
		// Don't go for println statement
		// System.out.println("get method of API hello");
		LOGGER.info("get method of API hello");
	}
	
	// Bydafult is finally, If u have error then also it will execute
	@AfterReturning("execution (public String com.spring.rest.controller.MainController.helloMethod())")
	public void logAfter() {
		// Don't go for println statement
		// System.out.println("get method of API hello");
		LOGGER.info("get method of API hello executed");
	}
	
	@AfterThrowing("execution (public String com.spring.rest.controller.MainController.helloMethod())")
	public void logException() {
		// Don't go for println statement
		// System.out.println("get method of API hello");
		LOGGER.info("issue");
	}

}
