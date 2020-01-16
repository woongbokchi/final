package com.ex.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BoardReplyVO {
   private int rno;
   private int bno;
   private int uno;
   private String rcontent;
   @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
   private Date rwdate;
   private String unick;

   public int getRno() {
      return rno;
   }

   public String getUnick() {
      return unick;
   }

   public void setUnick(String unick) {
      this.unick = unick;
   }

   public void setRno(int rno) {
      this.rno = rno;
   }

   public int getBno() {
      return bno;
   }

   public void setBno(int bno) {
      this.bno = bno;
   }

   public int getUno() {
      return uno;
   }

   public void setUno(int uno) {
      this.uno = uno;
   }

   public String getRcontent() {
      return rcontent;
   }

   public void setRcontent(String rcontent) {
      this.rcontent = rcontent;
   }

   public Date getRwdate() {
      return rwdate;
   }

   public void setRwdate(Date rwdate) {
      this.rwdate = rwdate;
   }

   @Override
   public String toString() {
      return "BoardReplyVO [rno=" + rno + ", bno=" + bno + ", uno=" + uno + ", rcontent=" + rcontent + ", rwdate="
            + rwdate + ", unick=" + unick + "]";
   }

   

}