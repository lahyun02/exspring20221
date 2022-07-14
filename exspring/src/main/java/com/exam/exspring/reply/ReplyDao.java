package com.exam.exspring.reply;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper   // 구현체를 자동으로 생성시키기
public interface ReplyDao {

	int insertReply(ReplyVo vo);

	List<ReplyVo> selectReplyList(int repBbsNo); 

	
	
}
