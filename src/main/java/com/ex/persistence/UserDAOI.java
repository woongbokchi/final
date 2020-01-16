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

	// �׾Ʒ� �α��� ������ db�� ��ϵ� �������� ��ȸ
	@Override
	public UserVO loginPost(String uemail) throws Exception {
		return session.selectOne(namespace + ".login", uemail);
	}

	// �׾Ʒ� �α��� �� db�� ��ϾȵǾ������� ���� ���
	@Override
	public void insert(UserVO vo) throws Exception {
		session.insert(namespace + ".userinsert", vo);

	}

	// �׾Ʒ� �α��� ������ ����
	@Override
	public void logout(HttpSession session) throws Exception {
		// TODO Auto-generated method stub

	}

	// �׾Ʒ� �α��� �� DB�� ��
	@Override
	public boolean selectUser(UserVO vo) throws Exception {
		String uemail = session.selectOne(namespace + ".selectuser", vo);
		return (uemail == null) ? false : true;
	}

	// MY PAGE �� ������ �� ��������
	@Override
	public UserVO myread(String uemail) throws Exception {
		return session.selectOne(namespace + ".myread", uemail);
	}

	// My page �������� ����
	@Override
	public void myupdate(UserVO vo) throws Exception {
		session.update(namespace + ".myupdate", vo);
	}

	// ���� ���
	@Override
	public void userlock(int uno) throws Exception {
		session.update(namespace + ".userlock", uno);

	}

	// ���� �ٷ� ��ó��
	@Override
	public void userblack(int uno) throws Exception {
		session.update(namespace + ".blacklist", uno);

	}

	// ������Ʈ ���� �ع�
	@Override
	public void unblack(int uno) throws Exception {
		session.update(namespace + ".unblack", uno);
	}

	// ����Ż��
	@Override
	public void udelete(int uno) throws Exception {
		session.update(namespace + ".udelete", uno);

	}

	// ������ �Ϲ��������
	@Override
	public List<UserVO> userlistread(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".userlistread", cri);
	}

	// ������� ��Ż
	@Override
	public int total() {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".total");
	}

	// ������ �����˻����
	@Override
	public List<UserVO> suserlistread(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".suserlistread", cri);
	}

	// �˻� ������� ��Ż��.
	@Override
	public int stotal(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".stotal", cri);
	}

}
