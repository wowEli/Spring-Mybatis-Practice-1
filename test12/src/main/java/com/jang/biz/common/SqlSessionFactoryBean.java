package com.jang.biz.common;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryBean {
	// MyBaits로 DAO 클래스의 CRUD 메서드를 사용하려면,
	// MyBatis에서 제공하는 SqlSession 객체를 사용해야한다 (DAO2에 JDBCTemplate처럼)
	// -> Factory 패턴으로 관리 하므로 Factory 객체가 먼저 필요하다
	
	private static SqlSessionFactory sessionFactory = null;
	static {
		try { // 거대한 try/catch 문은 외부요인 때문에 걸어뒀을 가능성이 크다
			if(sessionFactory == null) {
				// 이곳의 작업은 스트림을 사용
				// 외부요인(PC성능이나 여러가지)으로 인한 에러가 자주 발생하기 때문에 예외처리를 했다
				
				// builder는 MyBatis설정파일(sql-map-config.xml) 을 로딩하면서 SSF객체를 생성함
				
				// 설정파일(sql-map-config.xml) 로딩을 위해 입력스트림(Reader)을 사용
				Reader reader = Resources.getResourceAsReader("sql-map-config.xml");
				sessionFactory = new SqlSessionFactoryBuilder().build(reader);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSqlSessionInstance() {
		return sessionFactory.openSession(); // 팩토리로 세션을 만듦 (나중에는 spring에서 제공하기 때문에 이렇게 하드코딩안함)
	}
}
