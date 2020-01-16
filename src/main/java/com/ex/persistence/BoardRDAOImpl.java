package com.ex.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ex.domain.BoardReplyVO;

@Repository
public class BoardRDAOImpl implements BoardRDAO {
	@Inject
	SqlSession session;

	String namespace = "BoardRMapper";

	@Override
	public List<BoardReplyVO> reply(int bno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".reply", bno);
	}

	@Override
	public void insert(BoardReplyVO vo) throws Exception {
		session.insert(namespace + ".rinsert", vo);

	}

	@Override
	public void delete(int rno) throws Exception {
		session.delete(namespace + ".rdelete", rno);

	}

	@Override
	public void rAlldelete(int bno) throws Exception {
		session.delete(namespace + ".rAlldelete", bno);

	}

}