package com.exam.member;

public class MemberVo {
	//Value Object. 
	//테이블에 레코드 하나(한 행)을 담을 수 있는 객체.
	
	private String memId;
	private String memPass;
	private String memName;
	private int memPoint;
	
	@Override
	public String toString() {
		return memId + " : " + memPass + " : " + memName + " : " + memPoint;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPass() {
		return memPass;
	}

	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public int getMemPoint() {
		return memPoint;
	}

	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}
	
	//변수를 private으로 만들어 밖에서 직접 접근못하게 하고, (캡슐화) 
	// 메서드로 접근할 수 있게 함. 
	// 실무에선 변수를 private으로 만든 후, get, set 메소드를 활용하는 경우가 대부분.
//	public void setMemId(String id) {
//		this.memId = id;
//	}
	// id를 검사해서 유효한 값인지 검사한 후 저장하는 식으로 활용 가능
	
//	public String getMemId() {
//		return this.memId;
//	}
	
}
