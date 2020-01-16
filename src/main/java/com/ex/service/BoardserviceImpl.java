package com.ex.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex.persistence.BoardDAO;
import com.ex.persistence.BoardRDAO;

@Service
public class BoardserviceImpl implements Boardservice {

	@Inject
	BoardDAO bdao;
	@Inject
	BoardRDAO brdao;

	@Transactional
	@Override
	public void delete(int bno) throws Exception {
		brdao.rAlldelete(bno);
		bdao.delete(bno);

	}

}