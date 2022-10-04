package com.jang.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jang.biz.board.BoardVO;
import com.jang.biz.common.SqlSessionFactoryBean;

public class BoardDAO3 {
	private SqlSession mybatis;
	
	public BoardDAO3() {
		mybatis = SqlSessionFactoryBean.getSqlSessionInstance(); // 의존성 주입(DI) 생성자 인젝션
	}
	
	public void insertBoard(BoardVO vo) {
		System.out.println("로그: dao3에 insert함수 실행");
		mybatis.insert("BoardDAO.insertBoard", vo);
		mybatis.commit();
	}
	public void updateBoard(BoardVO vo) {
		mybatis.insert("BoardDAO.updateBoard", vo);
		mybatis.commit();
	}
	public void deleteBoard(BoardVO vo) {
		mybatis.insert("BoardDAO.deleteBoard", vo);
		mybatis.commit();
	}
	
	// commit을 안하는 이유는 DB에 데이터가 바뀌지 않기 때문
	public BoardVO selectOneBoard(BoardVO vo) { 
		return mybatis.selectOne("BoardDAO.selectOneBoard", vo);
	}
	public List<BoardVO> selectAllBoard(BoardVO vo) {
		
		return mybatis.selectList("BoardDAO.selectAllBoard", vo);
	}
}
