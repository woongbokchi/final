package com.ex.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserVO {
	private int uno;
	private String uemail;
	private String unick;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date uregdate;
	private int ulock;
	private int uauth;
	private String uint;
	private char udelete;
	private String profileimage;
	
	public String getProfileimage() {
		return profileimage;
	}
	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}
	public char getUdelete() {
		return udelete;
	}
	public void setUdelete(char udelete) {
		this.udelete = udelete;
	}
	public String getUint() {
		return uint;
	}
	public void setUint(String uint) {
		this.uint = uint;
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUnick() {
		return unick;
	}
	public void setUnick(String unick) {
		this.unick = unick;
	}
	public Date getUregdate() {
		return uregdate;
	}
	public void setUregdate(Date uregdate) {
		this.uregdate = uregdate;
	}
	public int getUlock() {
		return ulock;
	}
	public void setUlock(int ulock) {
		this.ulock = ulock;
	}
	public int getUauth() {
		return uauth;
	}
	public void setUauth(int uauth) {
		this.uauth = uauth;
	}
	
	@Override
	public String toString() {
		return "UserVO [uno=" + uno + ", uemail=" + uemail + ", unick=" + unick + ", uregdate=" + uregdate + ", ulock="
				+ ulock + ", uauth=" + uauth + ", uint=" + uint + ", udelete=" + udelete + ", profileimage="
				+ profileimage + "]";
	}
}
