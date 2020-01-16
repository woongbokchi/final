package com.ex.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.ex.domain.BoardVO;
import com.ex.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Inject
	SqlSession session;

	String namespace = "BoardMapper";

	@Override
	public List<BoardVO> best() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".best");
	}

	// main (�ֽż�����Ʈ)
	@Override
	public List<BoardVO> list() {
		return session.selectList(namespace + ".list");
	}

	// ������
	   @Override
	   public List<BoardVO> nationalList(String bnational, SearchCriteria cri,String orderType) throws Exception {
	      HashMap<String, Object> map = new HashMap<String, Object>();
	      map.put("bnational", bnational);
	      map.put("cri", cri);
	      map.put("orderType", orderType);
	      return session.selectList(namespace + ".national", map);
	   }

	// �ð���
	@Override
	public List<BoardVO> timeList(String btime, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("btime", btime);
		map.put("cri", cri);
		map.put("orderType", orderType);
		return session.selectList(namespace + ".time", map);
	}

	// ������
	@Override
	public List<BoardVO> sortList(String bsort, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bsort", bsort);
		map.put("cri", cri);
		map.put("orderType", orderType);
		return session.selectList(namespace + ".sort", map);
	}

	// �۾���
	@Override
	public void writer(BoardVO vo) throws Exception {
		session.insert(namespace + ".writer", vo);
	}

	// mypost daoi
	@Override
	public List<BoardVO> userList(int uno) throws Exception {
		return session.selectList(namespace + ".userList", uno);

	}

	// ������ �� �ҷ�����
	@Override
	public BoardVO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".read", bno);
	}

	// �˻�
	@Override
	public List<BoardVO> search(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".search", cri);
	}

	// �˻� ��Ż
	@Override
	public int total(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".total", cri);
	}

	// ī�װ��˻� ��Ż
	@Override
	public int ctotal(String bnational) throws Exception {
		return session.selectOne(namespace + ".ctotal", bnational);
	}

	// ī�װ��˻� ��Ż
	@Override
	public int stotal(String bsort) throws Exception {
		return session.selectOne(namespace + ".stotal", bsort);
	}

	// ī�װ��˻� ��Ż
	@Override
	public int ttotal(String btime) throws Exception {
		return session.selectOne(namespace + ".ttotal", btime);
	}

	// �� �б�
	@Override
	public BoardVO mypostread(int bno) throws Exception {
		return session.selectOne(namespace + ".mypostread", bno);
	}

	// �� ������Ʈ
	@Override
	public void mypostupdate(BoardVO vo) throws Exception {
		session.update(namespace + ".mypostupdate", vo);
	}

	@Override
	public void blikeUp(int bno) throws Exception {
		session.update(namespace + ".blikeUp", bno);

	}

	@Override
	public void blikeDown(int bno) throws Exception {
		session.update(namespace + ".blikeDown", bno);

	}

	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub
		session.delete(namespace + ".delete", bno);
	}

}
