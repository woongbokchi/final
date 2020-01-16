package com.ex.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ex.domain.SearchCriteria;
import com.ex.domain.UserVO;

@Repository
public class UserDAOI implements UserDAO {

	@Inject
	SqlSession session;
	String namespace = "UserMapper";

	// 네아로 로그인 성공시 db에 등록된 유저인지 조회
	@Override
	public UserVO loginPost(String uemail) throws Exception {
		return session.selectOne(namespace + ".login", uemail);
	}

	// 네아로 로그인 시 db에 등록안되어있으면 유저 등록
	@Override
	public void insert(UserVO vo) throws Exception {
		session.insert(namespace + ".userinsert", vo);

	}

	// 네아로 로그인 세션을 지움
	@Override
	public void logout(HttpSession session) throws Exception {
		// TODO Auto-generated method stub

	}

	// 네아로 로그인 시 DB와 비교
	@Override
	public boolean selectUser(UserVO vo) throws Exception {
		String uemail = session.selectOne(namespace + ".selectuser", vo);
		return (uemail == null) ? false : true;
	}

	// MY PAGE 내 프로필 값 가져오기
	@Override
	public UserVO myread(String uemail) throws Exception {
		return session.selectOne(namespace + ".myread", uemail);
	}

	// My page 내프로필 수정
	@Override
	public void myupdate(UserVO vo) throws Exception {
		session.update(namespace + ".myupdate", vo);
	}

	// 유저 경고
	@Override
	public void userlock(int uno) throws Exception {
		session.update(namespace + ".userlock", uno);

	}

	// 유저 바로 블랙처리
	@Override
	public void userblack(int uno) throws Exception {
		session.update(namespace + ".blacklist", uno);

	}

	// 블랙리스트 유저 해방
	@Override
	public void unblack(int uno) throws Exception {
		session.update(namespace + ".unblack", uno);
	}

	// 유저탈퇴
	@Override
	public void udelete(int uno) throws Exception {
		session.update(namespace + ".udelete", uno);

	}

	// 관리자 일반유저목록
	@Override
	public List<UserVO> userlistread(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".userlistread", cri);
	}

	// 유저목록 토탈
	@Override
	public int total() {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".total");
	}

	// 관리자 유저검색목록
	@Override
	public List<UserVO> suserlistread(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".suserlistread", cri);
	}

	// 검색 유저목록 토탈값.
	@Override
	public int stotal(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".stotal", cri);
	}

}
