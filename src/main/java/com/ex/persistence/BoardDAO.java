package com.ex.persistence;

import java.util.List;

import com.ex.domain.BoardVO;
import com.ex.domain.SearchCriteria;
import com.ex.domain.UserVO;

public interface BoardDAO {
	// main
	public List<BoardVO> list() throws Exception;

	// best(좋아요 많은순 3개)
	public List<BoardVO> best() throws Exception;

	// 국가별
	public List<BoardVO> nationalList(String bnational, SearchCriteria cri, String orederType) throws Exception;

	// 시간별
	public List<BoardVO> timeList(String btime, SearchCriteria cri, String orderType) throws Exception;

	// 유형별
	public List<BoardVO> sortList(String bsort, SearchCriteria cri, String orderType) throws Exception;

	// 글쓰기
	public void writer(BoardVO vo) throws Exception;

	// mypost dao
	public List<BoardVO> userList(int uno) throws Exception;

	// 글 읽기
	public BoardVO read(int bno) throws Exception;

	// 검색
	public List<BoardVO> search(SearchCriteria cri) throws Exception;

	// 검색 토탈
	public int total(SearchCriteria cri) throws Exception;

	// 카테고리 검색 토탈
	public int ctotal(String bnational) throws Exception;

	public int stotal(String bsort) throws Exception;

	public int ttotal(String btime) throws Exception;

	// mypost읽기
	public BoardVO mypostread(int bno) throws Exception;

	// mypost수정
	public void mypostupdate(BoardVO vo) throws Exception;

	// 자기글 삭제
	public void delete(int bno) throws Exception;

	// blike 증가
	public void blikeUp(int bno) throws Exception;

	// blike 감소
	public void blikeDown(int bno) throws Exception;
}
