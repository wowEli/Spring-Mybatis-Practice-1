package com.jang.biz.common;

import java.util.List;

import com.jang.biz.board.BoardVO;
import com.jang.biz.board.impl.BoardDAO3;

public class MybatisClient {

	public static void main(String[] args) {
		BoardDAO3 dao = new BoardDAO3();
		
		BoardVO vo = new BoardVO();
		
		vo.setTitle("마이바티스");
		vo.setWriter("티모");
		vo.setContent("마이바티스 어렵다");
		
		dao.insertBoard(vo);
		
		vo.setContent("마이");
	      List<BoardVO> datas = dao.selectAllBoard(vo);
	      for(BoardVO v:datas) {
	         System.out.println(v);
	      }
		
	}

}
