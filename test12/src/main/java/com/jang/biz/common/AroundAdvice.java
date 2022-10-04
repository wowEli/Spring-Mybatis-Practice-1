package com.jang.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

// around로 사용할 advice는 반드시 pjp를 input으로 가져야한다

public class AroundAdvice {
	
	@Around("PointcutCommon.aPointcut()")
	public Object printLogAround(ProceedingJoinPoint pjp) throws Throwable{
		// 필터의 동작과 똑같다
		// 작업을 pjp가 받아와서 여기서 수행할 로직을 수행하고 다시 그대로 반환해 주는데
		// 무슨 타입이 나올 지 모르니 Object 타입으로 설정한다
		String methodName = pjp.getSignature().getName();
		System.out.println("로그: 수핸중인 핵심 메서드명 "+methodName);
		
		// 자기가 핵심 메서드를 실행시켜야 해서 Proceeding으 사용해야만 한다
		
		StopWatch sw = new StopWatch(); 
		sw.start();
		Object returnObj = pjp.proceed(); // 수행할 포인트;
		sw.stop();
		System.out.println("수행시간: "+sw.getTotalTimeMillis()+"ms");
		// 메서드의 수행시간을 체크해 볼 수 있다

		// System.out.println("[After]");
		return returnObj;
	}
}
