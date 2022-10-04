package com.jang.biz.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.jang.biz.board.BoardService;
import com.jang.biz.board.BoardVO;

@Controller
@SessionAttributes("bData") // null로 업데이트 되는 것을 방지해보자
// bData 라는 이름으로 데이터를 주고 받고 있음
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	// 멤버변수를 BoardService 로 만들어도 @Autowired를 통해 BoardService인터페이스로 만들어진 BoardServiceImpl를 멤버변수에 주입
	
	@ModelAttribute("scMap") // 리턴 객체를 scMap이라는 이름으로 모델에 저장 > view가 꺼내 쓸 수 있음
	public Map<String,String> searchConditionMap(){
		Map<String,String> scMap = new HashMap<String,String>();
		scMap.put("제목", "TITLE"); // view에서 어떤 값들이 어떻게 보여지는지 C는 알고있다
		scMap.put("작성자", "WRITER");
		return scMap;
	}
	
	@RequestMapping("/main.do") // 사용자의 요청에 대해 HM과 VR을 해주는 역할
	public String selectAllBoard(
			@RequestParam(value="searchCondition",defaultValue="TITLE",required=false)String searchCondition,
			@RequestParam(value="searchContent",defaultValue="",required=false)String searchContent,
			BoardVO vo, Model model) {
			// @RP Command객체에는 없는 searchCondition과 searchContent을 일회성으로 사용해서 사용자의 요청 값을 꺼내 볼 수 있음
			// value="view에서 사용한 name속성 값", defaultValue="값이 없을 경우 기본 값", required=false (값이 없어도 된다)
		
		System.out.println("검색조건: "+searchCondition);
	    System.out.println("검색어: "+searchContent); // @RP로 인해 vo에 없는 변수를 사용
	    
	    vo.setTitle(searchCondition); // 검색 종류를 저장 (제목 or 작성자)
	    vo.setContent(searchContent); // 검색 내용을 저장

		model.addAttribute("bDatas", boardService.selectAllBoard(vo)); // 검색 정보를 갖고 selectAll 진행
		return "main.jsp";
	}
	
	@RequestMapping("/board.do")
	public String selectOneBoard(BoardVO vo, Model model) {
		
		System.out.println("로그: "+vo);
		
		model.addAttribute("bData", boardService.selectOneBoard(vo)); 
		// Model에 데이터 저장 @SessionAttribute가 이를 인식하고 Session에 저장
		
		return "boardOne.jsp";
	}
	
	@RequestMapping("/updateB.do")
	public String updateBoard(@ModelAttribute("bData")BoardVO vo, Model model) {
		// @MA bData라는 데이터와 BoardVO vo 가 같은 데이터가 된다. 이후에 사용자의 데이터를 덮어 씌움
		// => 사용자가 null 값을 보내줘도 이미 값들이 세팅되어 있기 때문에 null 값으로 update가 되는 것을 방지한다
		System.out.println("updateB.do 로그: "+vo);
		
		boardService.updateBoard(vo);
		
		return "main.do";
	}
	
	@RequestMapping("/insertB.do")
	public String insertBoard(BoardVO vo, Model model) {
		
		MultipartFile uploadFile = vo.getUploadFile(); // 객체가 보여짐
		
		if(!uploadFile.isEmpty()) { // 업로드한 파일 존재 여부 확인 있으면
			
			String fileName = uploadFile.getOriginalFilename(); // 업로드한 파일명
			try {
				uploadFile.transferTo(new File("/Users/dongwook/0607jang/eclipse-workspace2/test9/src/main/webapp/images/"+fileName));
				// transferTo메서드 인자 File 객체 인자를 통해서 사진을 경로에 저장
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		boardService.insertBoard(vo);
		
		return "main.do";
	}
	@RequestMapping("/deleteB.do")
	public String deleteBoard(BoardVO vo, Model model) {
		boardService.deleteBoard(vo);
		return "main.do";
	}
}
