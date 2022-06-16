package com.exam.exspring.member;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


//싱글톤패턴을 하기 위해 썼던 코드 삭제하기
//스프링에 MemberDaoBatis 등록시키기 - dao역할이니까 @Repository 붙이기

//@Repository -> 스프링에 등록되지 않았고, 사용 안하는 클래스. 이 클래스 삭제해도 됨.
public class MemberDaoBatis implements MemberDao {
	@Autowired 
//	private SqlSessionFactory sqlSessionFactory; 
	//injection등 3개 중에서 하나에서 애노테이션 . 
	//스프링에 등록된 애들 중에서 sqlSessionFactory의 클래스타입에 맞는 애를 찾아서 그 클래스에 주입됨
	private SqlSession session;   //private SqlSessionTemplate(구현체) session; 해도 됨. SqlSession:인터페이스
	//세션 템플릿에 구현이 다 되어있어서 try구문(open session)과 commit을 삭제 
	
	@Override
	public List<MemberVo> selectMemberList() {
		List<MemberVo> list = null;
		// 마이바티스를 통한 데이터베이스와의 세션(연결)을 가져와서
		return session.selectList("com.exam.exspring.member.MemberDao.selectMemberList");
		//규칙에 맞춰 자동생성 가능하도록하기 위해 com.exam.exspring.member로 경로를 바꿔줌.
	}
	
	@Override
	public MemberVo selectMember(String memId) {
		MemberVo vo = null;
		return vo = session.selectOne("com.exam.exspring.member.MemberDao.selectMember", memId);
			// 결과가 하나로 나올땐 selectOne메소드. 결과가 여러개 나올땐 selectList
	}

	@Override
	public int insertMember(MemberVo vo) {
		int num = 0;
		return session.insert("com.exam.exspring.member.MemberDao.insertMember", vo);
	}

	@Override
	public int delMember(String memId) {
		int num = 0;
		return session.delete("com.exam.exspring.member.MemberDao.delMember", memId);
	}

	@Override
	public int updateMember(MemberVo vo) {
		int num = 0;
		return session.update("com.exam.exspring.member.MemberDao.updateMember", vo);
	}

	@Override
	public MemberVo selectLoginMember(MemberVo vo) {
		MemberVo memVo = null;
		return session.selectOne("com.exam.exspring.member.MemberDao.selectLoginMember", vo);
	}
	
}
