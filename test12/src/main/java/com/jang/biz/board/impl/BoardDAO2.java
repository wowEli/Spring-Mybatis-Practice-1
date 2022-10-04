package com.jang.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jang.biz.board.BoardVO;

@Repository("boardDAO") // DAO는 @Repository를 사용해서 더 빨리 사용
public class BoardDAO2 {

	@Autowired // 메모리에 만들어져 있는 jdbcTemplate 대입
	private JdbcTemplate jdbcTemplate;

	final String sql_insert ="INSERT INTO BOARD (TITLE,WRITER,CONTENT,IMG) VALUES (?,?,?,?)";
	final String sql_update ="UPDATE BOARD SET TITLE=?,CONTENT=? WHERE BID=?";
	final String sql_delete = "DELETE FROM BOARD WHERE BID=?";
	final String sql_selectOne ="SELECT * FROM BOARD WHERE BID =?";
	final String sql_selectAll ="SELECT * FROM BOARD WHERE TITLE LIKE CONCAT('%',?,'%') AND WRITER LIKE CONCAT('%',?,'%') ORDER BY BID DESC";

	// === INSERT , UPDATE , DELETE 는 jdbcTemplate.update를 사용
	void insertBoard(BoardVO vo) {
		System.out.println("로그: dao2에 insert함수 실행");
		jdbcTemplate.update(sql_insert,vo.getTitle(),vo.getWriter(),vo.getContent(),vo.getUploadFile().getOriginalFilename());
	}
	void updateBoard(BoardVO vo) {
		jdbcTemplate.update(sql_update,vo.getTitle(),vo.getContent(),vo.getBid());
	}
	void deleteBoard(BoardVO vo) {
		jdbcTemplate.update(sql_delete,vo.getBid());
	}
	
	// === SELECT 는 아웃풋이 있으므로 아웃풋에 따라 사용하는 함수가 다르다
	// 아웃풋이 하나다 -> queryForObject
	// 아웃풋이 여러 개다 -> query 
	// 인자는 Object[] 배열을 사용
	// + 매핑정보 필요
	BoardVO selectOneBoard(BoardVO vo) {
		Object[] args= {vo.getBid()};
		return jdbcTemplate.queryForObject(sql_selectOne,args,new BoardRowMapper());
	}
	List<BoardVO> selectAllBoard(BoardVO vo) {
		
		if(vo.getTitle().equals("TITLE")) {
			Object[] args = {vo.getContent(),""};
			return jdbcTemplate.query(sql_selectAll,args,new BoardRowMapper());
		}
		else {
			Object[] args = {"",vo.getContent()};
			return jdbcTemplate.query(sql_selectAll,args,new BoardRowMapper());
		}
		
	}
}
// SELECT 결과 값인 ResultSet 과 Object 타입을 매핑시켜주기
class BoardRowMapper implements RowMapper<BoardVO>{

	@Override // 인터페이스로 인해 강제 오버라이딩
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// SELECT 결과는 rs에 저장 그 데이터들의 개수를 rowNum에 저장해서 값 만큼 반복
		BoardVO data=new BoardVO();
		data.setBid(rs.getInt("BID"));
		data.setContent(rs.getString("CONTENT"));
		data.setTitle(rs.getString("TITLE"));
		data.setWriter(rs.getString("WRITER"));
		data.setCnt(rs.getInt("CNT"));
		data.setRegdate(rs.getString("REGDATE"));
		data.setImgsrc(rs.getString("IMG"));
		return data;
	}

}