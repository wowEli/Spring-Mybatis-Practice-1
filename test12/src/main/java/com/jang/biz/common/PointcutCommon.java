package com.jang.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

//결합을 위해서 써줘야함 핵심로직(포인트컷) + 횡단관심
@Aspect
public class PointcutCommon {
	@Pointcut("execution(* com.jang.biz..*Impl.*(..))")
	public void aPointcut() {}
	
	@Pointcut("execution(* com.jang.biz..*Impl.select*(..))")
	public void bPointcut() {}
}
