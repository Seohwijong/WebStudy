package com.sist.vo;
/*
 * FNO                NOT NULL NUMBER
 CNO                  NUMBER
 NAME                  NOT NULL VARCHAR2(100)
 SCORE                 NUMBER(2,1)
 ADDRESS               NOT NULL VARCHAR2(300)
 PHONE               NOT NULL VARCHAR2(20)
 TYPE                NOT NULL VARCHAR2(30)
 PRICE             VARCHAR2(30)
 PARKING              VARCHAR2(30)
 TIME                  VARCHAR2(20)
 MENU                    CLOB
 GOOD                   NUMBER
 SOSO                   NUMBER
 BAD                   NUMBER
 POSTER                NOT NULL VARCHAR2(4000)
 */
public class FoodVO {
	private int no;
	private String title,addr,poster;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
}