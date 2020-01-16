package com.ex.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ex.domain.Criteria;
import com.ex.domain.QnAVO;

@Repository
public class QnADAOImpl implements QnADAO{
	@Inject
	SqlSession session;

	String namespace="QnAMapper";
	
	@Override
	public void insert(QnAVO vo) {
		// TODO Auto-generated method stub
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public List<QnAVO> qlist(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".list", cri);
	}

	@Override
	public QnAVO read(int QnAno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".read", QnAno);
	}

	@Override
	public int total() throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".total");
	}

}
