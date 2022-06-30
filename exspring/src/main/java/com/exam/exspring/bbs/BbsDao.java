package com.exam.exspring.bbs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper // <- 실행되려면 BbsDao의 메소드 이름과 xml의 쿼리문 id 이름이 같아야 한다.
public interface BbsDao {
	//db관련 코드와 ui관련 코드 분리
	
	//목록 가져오기
	List<BbsVo> selectBbsList();
	
	//게시글 수정하기
	int insertBbs(BbsVo vo);
	
	//게시글 상세조회
	BbsVo selectBbs(int bbsNo);
	
	//게시글 수정하기
	int updateBbs(BbsVo vo);
	
	//게시글 삭제하기
	int delBbs(int bbsNo);
	
}