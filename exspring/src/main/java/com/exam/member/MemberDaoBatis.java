package com.exam.member;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.exam.comm.MyBatisUtil;

//MemberDaoBatis도 하나만 만들어 생성 가능. 인스턴스 여러개 생성해도 차이점은 sqlSessionFactory인데, 
// static으로 하나를 공유해서 사용하는 거니까 차이점이 없음.
// 여러 인스턴스 -> 자원낭비, 트랜젝션 문제. 전체 프로젝트에서 딱 하나의 인스턴스만 만들어 사용하는 게 좋음. -> 싱글톤 패턴 

public class MemberDaoBatis implements MemberDao {
	//싱글톤(Singleton)패턴 (대부분 Dao는 싱글톤패턴사용)
	//클래스의 인스턴스를 1개만 생성하여 애플리케이션 전체에서 공유하여 사용하고 싶을 때,
	//눈에 보이지 않는 생성자를 못만들게한다. -> 접근제한자를 private으로.
	// 스프링-> 알아서 객체를 1를 만들어 주입해주게 됨.
	private MemberDaoBatis() {}	//클래스외부에서 인스턴스 생성(생성자 호출)을 금지 
	private static MemberDaoBatis memberDaoBatis = new MemberDaoBatis(); // 클래스 내부에서 인스턴스 생성 및 보관
	public static MemberDaoBatis getInstance() {	//클래스 외부에서 필요한 경우(호출), 보관한 인스턴스를 제공하는 메서드(외부에서 호출 가능-> 메소드와 위에 생성자 static으로)
		return memberDaoBatis; 
	}
	
	// 같은 sqlSessionFactory를 공유해서 사용 
	private SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory(); 
	

	@Override
	public List<MemberVo> selectMemberList() {
		List<MemberVo> list = null;
		// 마이바티스를 통한 데이터베이스와의 세션(연결)을 가져와서
		try (SqlSession session = sqlSessionFactory.openSession()) {
			//해당 세션을 통해 마이바티스에 등록된 SQL문을 실행 
			//실행할 SQL문의 종류에 따라서 메서드를 선택
			//"namespace.id"로 실행할 SQL문을 지정 
			//com.exam.member.MemberDao 집합 안에 있는 selectMemberList문을 실행!
			list = session.selectList("com.exam.member.MemberDao.selectMemberList");
			}
		return list;
	}
	
	@Override
	public MemberVo selectMember(String memId) {
		MemberVo vo = null;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			vo = session.selectOne("com.exam.member.MemberDao.selectMember", memId);
			// 결과가 하나로 나올땐 selectOne메소드. 결과가 여러개 나올땐 selectList
			}
		return vo;
	}

	@Override
	public int insertMember(MemberVo vo) {
		int num = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			// insert, delete, update는 보통 반환값이 숫자임.
			//SQL문 실행에 필요한 데이터는 두번째 인자로 전달 
			num = session.insert("com.exam.member.MemberDao.insertMember", vo);
			session.commit();
			// insert, update, delete 후에는 커밋 필요. 커밋완료해야 남들이 변경된 데이터를 볼 수 있음.
			//jdbc에선 기본설정이 오토커밋이 true, mybatis에선 기본설정이 오토커밋이 false로 되어 있어 직접 커밋해야함.
			} 
		return num;
	}

	@Override
	public int delMember(String memId) {
		int num = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			num = session.delete("com.exam.member.MemberDao.delMember", memId);
			//insert, delete를 하는 메소드는 내부적으로 들어가면 MemberDaoJdbc에서처럼 pstmt.executeQuery();를 쓰기때문에 
			//둘중 뭐를 써도 에러는 안나지만 명확히 하기 위해 delete메소드명을 작성.
			session.commit();
			} 
		return num;
	}

	@Override
	public int updateMember(MemberVo vo) {
		int num = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			num = session.update("com.exam.member.MemberDao.updateMember", vo);
			session.commit();
			} 
		return num;
	}

	@Override
	public MemberVo selectLoginMember(MemberVo vo) {
		MemberVo memVo = null;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			memVo = session.selectOne("com.exam.member.MemberDao.selectLoginMember", vo);
			// vo객체 안에 담긴 아이디와 비밀번호를 줘서 sql문 실행해서 로그인 조건에 맞는 사람 검색
			// 여러개의 데이터를 줘야 될때 mybatis는 데이터를 하나만 보낼 수 있기 때문에 객체나 map에 담아서 인자로 줘야함.
			}
		return memVo;
	}

	
}
