package com.ex.persistence;

import java.util.List;

import com.ex.domain.Criteria;
import com.ex.domain.QnAVO;

public interface QnADAO {
	public void insert(QnAVO vo) throws Exception;
	public List<QnAVO> qlist(Criteria cri) throws Exception;
	public int total() throws Exception; // 전체 데이터
	public QnAVO read(int QnAno) throws Exception;
}
