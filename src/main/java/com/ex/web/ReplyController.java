package com.ex.web;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ex.domain.BoardReplyVO;
import com.ex.persistence.BoardRDAO;

@Controller
public class ReplyController {
	@Inject
	BoardRDAO rdao;

	@ResponseBody
	@RequestMapping("reply")
	public HashMap<String, Object> reply(int bno, HttpSession session) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// int uno = (int) session.getAttribute("uno");
		// map.put("rw", uno);
		map.put("rlist", rdao.reply(bno));

		return map;
	}

	// ¥Ò±€¿‘∑¬
	@ResponseBody
	@RequestMapping("rinsert")
	public void rinsert(BoardReplyVO vo) throws Exception {
		rdao.insert(vo);
	}

	// ¥Ò±€ªË¡¶
	@ResponseBody
	@RequestMapping("rdelete")
	public void rdelete(int rno) throws Exception {
		rdao.delete(rno);
	}

}