package com.jang.biz.member;

import java.util.ArrayList;

public interface MemberService {
	void signUp(MemberVO vo);
	void updateMember(MemberVO vo);
	void deleteMember(MemberVO vo);
	MemberVO login(MemberVO vo);
}
