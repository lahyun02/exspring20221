package com.exam.exspring.member;

import java.util.ArrayList;
import java.util.List;

public interface MemberService {

	List<MemberVo> selectMemberList();

	int insertMember(MemberVo vo);

	int delMember(String memId);

	MemberVo selectMember(String memId);

	int updateMember(MemberVo vo);

	MemberVo selectLoginMember(MemberVo vo);  

}