package com.ex.persistence;

import java.util.List;

import com.ex.domain.BoardReplyVO;

public interface BoardRDAO {
	public List<BoardReplyVO> reply(int bno) throws Exception;

	public void insert(BoardReplyVO vo) throws Exception;

	public void delete(int rno) throws Exception;

	public void rAlldelete(int bno) throws Exception;

}