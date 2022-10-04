package com.jang.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

//서비스류
@Service 
@Aspect
public class LogAdvice {
	
	// before 뿐만 아니라 after around 등 가능
	@Before("PointcutCommon.aPointcut()")
	public void printLog(JoinPoint jp) {
		System.out.println("로그: 로그함수 실행");
		String methodName = jp.getSignature().getName();
		// 현재 수행중인 포인터컷(핵심로직 ,CRUD)의 시그니처의 메서드명
		Object[] args = jp.getArgs();
		// 현재 수행중인 포인터컷(핵심로직 ,CRUD)이 사용하는 인자들의 정보
		
		System.out.println("로그: 수행중인 핵심 메서드명 "+methodName);
		System.out.print("로그: 수행중인 핵심 메서드의 인자: ");
		for(Object o:args) {
			System.out.print(o); // 동적바인딩으로 인해 VO의 toString이 나옴
		}
		System.out.println();
		
	}
}
