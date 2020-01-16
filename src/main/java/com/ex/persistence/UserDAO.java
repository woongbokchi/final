package com.ex.persistence;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ex.domain.SearchCriteria;
import com.ex.domain.UserVO;

public interface UserDAO {

	// �α��� ������ �ش� ���� �˻�
	public UserVO loginPost(String uemail) throws Exception;

	// �׾Ʒ� �α��� ���� �� ���� ���
	public void insert(UserVO vo) throws Exception;

	// DB üũ
	public boolean selectUser(UserVO vo) throws Exception;

	// �׾Ʒ� �α��� ������ ����
	public void logout(HttpSession session) throws Exception;

	// mypage read
	public UserVO myread(String uemail) throws Exception;

	// mypage update
	public void myupdate(UserVO vo) throws Exception;

	// user ���
	public void userlock(int uno) throws Exception;

	// user ������Ʈ
	public void userblack(int uno) throws Exception;

	// user ���ع�
	public void unblack(int uno) throws Exception;

	// user Ż��
	public void udelete(int uno) throws Exception;

	// ������ �������� ����Ʈ
	public List<UserVO> userlistread(SearchCriteria cri) throws Exception;

	// ������ �������� �˻� ����Ʈ
	public List<UserVO> suserlistread(SearchCriteria cri) throws Exception;

	// ���� ����Ʈ ��Ż
	public int total() throws Exception;

	public int stotal(SearchCriteria cri) throws Exception;

}
