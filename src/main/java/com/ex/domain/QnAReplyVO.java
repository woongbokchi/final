package com.ex.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class QnAReplyVO {
   private int QnAreno;
   private int QnAno;
   private int uno;
   private String unick;
   
   @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
   private Date QnAdate;
   private String QnAtitle;
   private String QnAcontent;
   public int getQnAreno() {
      return QnAreno;
   }
   public void setQnAreno(int qnAreno) {
      QnAreno = qnAreno;
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
   
   
   public String getUnick() {
      return unick;
   }
   public void setUnick(String unick) {
      this.unick = unick;
   }
   
   @Override
   public String toString() {
      return "QnAReplyVO [QnAreno=" + QnAreno + ", QnAno=" + QnAno + ", uno=" + uno + ", unick=" + unick
            + ", QnAdate=" + QnAdate + ", QnAtitle=" + QnAtitle + ", QnAcontent=" + QnAcontent + "]";
   }
   
}