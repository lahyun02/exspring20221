package com.exam.exspring.bbs;

//  따로 패키지 만들어 관리하는게 좋지만 일단 bbs 패키지에 작성.

public class SearchInfo {
	private String searchType; //변수이름은 파라미터이름과 동일해야 하므로 bbsList의 select태그 name이름으로.
	private String searchWord;

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	
	
	
}
