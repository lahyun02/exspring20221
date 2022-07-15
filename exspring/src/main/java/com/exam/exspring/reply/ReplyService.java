package com.exam.exspring.reply;

import java.util.List;

public interface ReplyService {

	int insertReply(ReplyVo vo);

	List<ReplyVo> selectReplyList(int repBbsNo);

	int deleteReply(ReplyVo vo);

}
