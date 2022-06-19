package com.exam.exspring.bbs;

import java.util.List;

public interface BbsService {
	
	// 목록 가져오기
	List<BbsVo> selectBbsList();

	// 게시글 추가하기
	int insertBbs(BbsVo vo);
	
	//게시글 상세조회
	BbsVo selectBbs(int bbsNo);
	
	//게시글 수정하기
	int updateBbs(BbsVo vo);
	
	//게시글 삭제하기
	int delBbs(int bbsNo);
}