package com.jang.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterThrowingAdvice {
	
	@AfterThrowing(pointcut = "PointcutCommon.aPointcut()", throwing="exceptionObj")
	public void printLogAfterThrowingAdvice(JoinPoint jp,Exception exceptionObj) {
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
		
		System.out.println("로그: 핵심메서드의 반환 값: "+exceptionObj);
		
		// 사용예시
		if(exceptionObj instanceof IllegalArgumentException) {
			System.out.println("올바르지 않은 인자 값을 사용했습니다.");
		}
		else if(exceptionObj instanceof NumberFormatException) {
			System.out.println("숫자형식이 아닌 값을 사용했습니다.");
		}
		else if(exceptionObj instanceof Exception){
			System.out.println("예외가 발생했습니다.");
		}
		else {
			System.out.println("확인되지 않은 예외가 발생했습니다.");
		}
	}
}
