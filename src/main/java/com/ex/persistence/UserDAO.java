package com.ex.persistence;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ex.domain.SearchCriteria;
import com.ex.domain.UserVO;

public interface UserDAO {

	// 로그인 성공시 해당 유저 검색
	public UserVO loginPost(String uemail) throws Exception;

	// 네아로 로그인 성공 시 유저 등록
	public void insert(UserVO vo) throws Exception;

	// DB 체크
	public boolean selectUser(UserVO vo) throws Exception;

	// 네아로 로그인 세션을 지움
	public void logout(HttpSession session) throws Exception;

	// mypage read
	public UserVO myread(String uemail) throws Exception;

	// mypage update
	public void myupdate(UserVO vo) throws Exception;

	// user 경고
	public void userlock(int uno) throws Exception;

	// user 블랙리스트
	public void userblack(int uno) throws Exception;

	// user 블랙해방
	public void unblack(int uno) throws Exception;

	// user 탈퇴
	public void udelete(int uno) throws Exception;

	// 관리자 유저관리 리스트
	public List<UserVO> userlistread(SearchCriteria cri) throws Exception;

	// 관리자 유저관리 검색 리스트
	public List<UserVO> suserlistread(SearchCriteria cri) throws Exception;

	// 유저 리스트 토탈
	public int total() throws Exception;

	public int stotal(SearchCriteria cri) throws Exception;

}
