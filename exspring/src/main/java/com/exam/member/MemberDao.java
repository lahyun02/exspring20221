package com.exam.member;

import java.util.ArrayList;
import java.util.List;

public interface MemberDao {

	//db관련 코드와 ui관련 코드 분리
	List<MemberVo> selectMemberList();

	int insertMember(MemberVo vo);

	int delMember(String memId);

	MemberVo selectMember(String memId);

	int updateMember(MemberVo vo);

	MemberVo selectLoginMember(MemberVo vo);  

}