package com.jang.biz.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.jang.biz") // 아래 메서드를 쓸려면 객체화가 되야함 (횡단관심처럼 객체화 해주는 @)
public class CommonExceptionHandler {
	
	
	@ExceptionHandler(NullPointerException.class) // 에러에 반응하여 메서드 호출
	public ModelAndView aException(Exception e) { // null포인트 익셉션액션
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception",e);
		mav.setViewName("error/error.jsp");
		return mav;
	}
	
	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView bException(Exception e) { // 수학적인 문제
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception",e);
		mav.setViewName("error/error.jsp");
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView cException(Exception e) { // 미확인 예외
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception",e);
		mav.setViewName("error/error.jsp");
		return mav;
	}
}
