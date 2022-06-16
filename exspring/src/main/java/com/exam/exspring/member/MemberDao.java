package com.exam.exspring.member;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

//원랜 개발자가 직접 애노테이션을 정의해서 사용하는 게 좋음.
@Mapper
public interface MemberDao {

	//db관련 코드와 ui관련 코드 분리
	List<MemberVo> selectMemberList();

	int insertMember(MemberVo vo);

	int delMember(String memId);

	MemberVo selectMember(String memId);

	int updateMember(MemberVo vo);

	MemberVo selectLoginMember(MemberVo vo);  

}