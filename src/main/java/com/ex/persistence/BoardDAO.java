package com.ex.persistence;

import java.util.List;

import com.ex.domain.BoardVO;
import com.ex.domain.SearchCriteria;
import com.ex.domain.UserVO;

public interface BoardDAO {
	// main
	public List<BoardVO> list() throws Exception;

	// best(���ƿ� ������ 3��)
	public List<BoardVO> best() throws Exception;

	// ������
	public List<BoardVO> nationalList(String bnational, SearchCriteria cri, String orederType) throws Exception;

	// �ð���
	public List<BoardVO> timeList(String btime, SearchCriteria cri, String orderType) throws Exception;

	// ������
	public List<BoardVO> sortList(String bsort, SearchCriteria cri, String orderType) throws Exception;

	// �۾���
	public void writer(BoardVO vo) throws Exception;

	// mypost dao
	public List<BoardVO> userList(int uno) throws Exception;

	// �� �б�
	public BoardVO read(int bno) throws Exception;

	// �˻�
	public List<BoardVO> search(SearchCriteria cri) throws Exception;

	// �˻� ��Ż
	public int total(SearchCriteria cri) throws Exception;

	// ī�װ� �˻� ��Ż
	public int ctotal(String bnational) throws Exception;

	public int stotal(String bsort) throws Exception;

	public int ttotal(String btime) throws Exception;

	// mypost�б�
	public BoardVO mypostread(int bno) throws Exception;

	// mypost����
	public void mypostupdate(BoardVO vo) throws Exception;

	// �ڱ�� ����
	public void delete(int bno) throws Exception;

	// blike ����
	public void blikeUp(int bno) throws Exception;

	// blike ����
	public void blikeDown(int bno) throws Exception;
}
