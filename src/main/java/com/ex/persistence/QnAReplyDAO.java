package com.ex.persistence;

import java.util.List;

import com.ex.domain.QnAReplyVO;

public interface QnAReplyDAO {
	public void answertext(QnAReplyVO vo) throws Exception; // ��۾���

	public List<QnAReplyVO> relist(int QnAno) throws Exception;

	public void answerdelete(int QnAreno) throws Exception;
}