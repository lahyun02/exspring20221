package com.exam.exspring.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDao replyDao;
	
	@Override
	public int insertReply(ReplyVo vo) {
		return replyDao.insertReply(vo);
	}

}
