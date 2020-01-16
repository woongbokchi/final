package com.ex.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ex.domain.QnAReplyVO;

@Repository
public class QnAReplyDAOImpl implements QnAReplyDAO {
	@Inject
	SqlSession session;

	String namespace = "QnAReplyMapper";

	@Override
	public void answertext(QnAReplyVO vo) throws Exception {
		session.insert(namespace + ".answertext", vo);

	}

	@Override
	public List<QnAReplyVO> relist(int QnAno) throws Exception {
		return session.selectList(namespace + ".relist", QnAno);
	}

	@Override
	public void answerdelete(int QnAreno) throws Exception {
		session.delete(namespace + ".answerdelete", QnAreno);

	}

}