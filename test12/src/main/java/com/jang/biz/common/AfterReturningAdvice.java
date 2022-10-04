package com.jang.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import com.jang.biz.member.MemberVO;


public class AfterReturningAdvice {
	
	@AfterReturning(pointcut = "PointcutCommon.bPointcut()", returning = "returnObj")
	public void printLogAfterReturning(JoinPoint jp,Object returnObj) {
		String methodName = jp.getSignature().getName();
		// 현재 수행중인 포인터컷(핵심로직 ,CRUD)의 시그니처의 메서드명
		Object[] args = jp.getArgs();
		// 현재 수행중인 포인터컷(핵심로직 ,CRUD)이 사용하는 인자들의 정보
		System.out.println("로그: 수핸중인 핵심 메서드명 "+methodName);
		System.out.print("로그: 수핸중인 핵심 메서드의 인자: ");
		for(Object o:args) {
			System.out.print(o); // 동적바인딩으로 인해 VO의 toString이 나옴
		}
		System.out.println();
		
		// Object returnObj == 바인드변수 라고 함
		if(returnObj instanceof MemberVO) { // 멤버 VO타입의 객체가 맞니?
			MemberVO mvo = (MemberVO)returnObj;
			if(mvo.getRole().equals("admin")) {
				System.out.println("로그: 관리자 입니다.");
			}
			else {
				System.out.println("로그: 일반계정 입니다.");
			}
		}
		System.out.println("로그: 핵심메서드의 반환 값: "+returnObj);
	}
	
}
