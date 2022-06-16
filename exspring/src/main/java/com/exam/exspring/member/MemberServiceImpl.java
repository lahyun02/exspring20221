package com.exam.exspring.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//스프링에 등록하기 -> 애노테이션 -> service역할을 하니까 @Service 붙이기
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired    //스프링에 등록되어 있는 객체중 찾아서 빈 주입 
	private MemberDao memberDao;
	
	@Override
	public List<MemberVo> selectMemberList() {
		return memberDao.selectMemberList();
	}

	@Override
	public int insertMember(MemberVo vo) {
		return memberDao.insertMember(vo); 
	}

	@Override
	public int delMember(String memId) {
		return memberDao.delMember(memId); 
	}

	@Override
	public MemberVo selectMember(String memId) {
		return memberDao.selectMember(memId); 
	}

	@Override
	public int updateMember(MemberVo vo) {
		return memberDao.updateMember(vo);
	}

	@Override
	public MemberVo selectLoginMember(MemberVo vo) {
		return memberDao.selectLoginMember(vo); 
	}

}
