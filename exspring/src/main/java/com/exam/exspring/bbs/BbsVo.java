package com.exam.exspring.bbs;

import java.util.Date;

// 게시글목록(글번호, 글제목, 작성자, 작성일) /bbs/list.do
// 게시글추가(글제목, 글내용, 작성자) /bbs/add.do
// 게시글변경(제목,내용) /bbs/edit.do
// 게시글삭제(변경화면에삭제버튼클릭시 ) /bbs/del.do
// BbsController.java, BbsService.java, BbsServiceImpl.java, BbsDao.java,
// BbsMapper.xml
// bbsList.jsp, bbsAdd.jsp, bbsEdit.jsp

public class BbsVo {
	private int bbsNo; 
	private String bbsTitle; 
	private String bbsContent; 
	private String bbsWriter; 
	private Date bbsRegDate; 
	private int bbsCount;
	
	public int getBbsNo() {
		return bbsNo;
	}
	public void setBbsNo(int bbsNo) {
		this.bbsNo = bbsNo;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}
	public String getBbsWriter() {
		return bbsWriter;
	}
	public void setBbsWriter(String bbsWriter) {
		this.bbsWriter = bbsWriter;
	}
	public Date getBbsRegDate() {
		return bbsRegDate;
	}
	public void setBbsRegDate(Date bbsRegDate) {
		this.bbsRegDate = bbsRegDate;
	}
	public int getBbsCount() {
		return bbsCount;
	}
	public void setBbsCount(int bbsCount) {
		this.bbsCount = bbsCount;
	} 
	
	
}
