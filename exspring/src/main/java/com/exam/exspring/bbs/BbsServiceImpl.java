package com.exam.exspring.bbs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//스프링에 등록하기 -> 애노테이션 -> service역할을 하니까 @Service 붙이기
@Service
public class BbsServiceImpl implements BbsService {
	@Autowired    //스프링에 등록되어 있는 객체중 찾아서 빈 주입 
	private BbsDao bbsDao;
	
	// 게시글 목록
	@Override
	public List<BbsVo> selectBbsList() {
		return bbsDao.selectBbsList();
	}
	
	// 게시글 추가하기
	@Override
	public int insertBbs(BbsVo vo) {
		return bbsDao.insertBbs(vo); 
	}
	
	//게시글 상세조회
	@Override
	public BbsVo selectBbs(int bbsNo) {
		return bbsDao.selectBbs(bbsNo); 
	}
	
	//게시글 수정하기
	@Override
	public int updateBbs(BbsVo vo) {
		return bbsDao.updateBbs(vo); 
	}

	//게시글 삭제하기
	@Override
	public int delBbs(int bbsNo) {
		return bbsDao.delBbs(bbsNo); 
	}
	
	


}
