package com.exam.exspring.reply;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReplyVo {
	private int repNo;
	private String repContent;
	private String repWriter;
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date repRegDate; //repRegDate을 json으로 변환할때 위의 시간형식으로 변환해라. (지정안하면 이상한 일련의 숫자로 자기멋대로 변환됨)
	private int repBbsNo;
	
	public int getRepNo() {
		return repNo;
	}
	public void setRepNo(int repNo) {
		this.repNo = repNo;
	}
	public String getRepContent() {
		return repContent;
	}
	public void setRepContent(String repContent) {
		this.repContent = repContent;
	}
	public String getRepWriter() {
		return repWriter;
	}
	public void setRepWriter(String repWriter) {
		this.repWriter = repWriter;
	}
	public Date getRepRegDate() {
		return repRegDate;
	}
	public void setRepRegDate(Date repRegDate) {
		this.repRegDate = repRegDate;
	}
	public int getRepBbsNo() {
		return repBbsNo;
	}
	public void setRepBbsNo(int repBbsNo) {
		this.repBbsNo = repBbsNo;
	}
	
	
		
}
