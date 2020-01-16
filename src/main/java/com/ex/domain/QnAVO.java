package com.ex.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class QnAVO {
	private int QnAno;
	private int uno;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date QnAdate;
	
	private String QnAtitle;
	private String QnAcontent;
	private String image;
	private char qdelete;
	private String unick;

	public String getUnick() {
		return unick;
	}

	public void setUnick(String unick) {
		this.unick = unick;
	}

	public char getQdelete() {
		return qdelete;
	}

	public void setQdelete(char qdelete) {
		this.qdelete = qdelete;
	}

	public int getQnAno() {
		return QnAno;
	}

	public void setQnAno(int qnAno) {
		QnAno = qnAno;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public Date getQnAdate() {
		return QnAdate;
	}

	public void setQnAdate(Date qnAdate) {
		QnAdate = qnAdate;
	}

	public String getQnAtitle() {
		return QnAtitle;
	}

	public void setQnAtitle(String qnAtitle) {
		QnAtitle = qnAtitle;
	}

	public String getQnAcontent() {
		return QnAcontent;
	}

	public void setQnAcontent(String qnAcontent) {
		QnAcontent = qnAcontent;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "QnAVO [QnAno=" + QnAno + ", uno=" + uno + ", QnAdate=" + QnAdate + ", QnAtitle="
				+ QnAtitle + ", QnAcontent=" + QnAcontent + ", image=" + image + ", qdelete=" + qdelete + "]";
	}

}
