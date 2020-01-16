package com.ex.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ex.domain.QnAReplyVO;
import com.ex.domain.QnAVO;
import com.ex.domain.UserVO;
import com.ex.persistence.QnADAO;
import com.ex.persistence.QnAReplyDAO;
import com.ex.persistence.UserDAO;

@Controller
public class QnAController {
	@Inject
	QnADAO dao;

	@Inject
	QnAReplyDAO rdao;

	@Inject
	UserDAO udao;

	@Resource(name = "uploadPath")
	private String path;

	@RequestMapping(value = "helpWritePost", method = RequestMethod.POST)
	public String helpWritePost(QnAVO vo, MultipartFile file) throws Exception {

		// 새로운 이미지 업로드
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + file.getOriginalFilename();
		File target = new File(path, savedName);
		FileCopyUtils.copy(file.getBytes(), target);
		// 업로드한 파일명 변경
		vo.setImage(savedName);

		dao.insert(vo);
		return "redirect:help";
	}

	@RequestMapping("helpRead")
	public String helpRead(Model model, int QnAno, QnAReplyVO vo, HttpSession session) throws Exception {
		model.addAttribute("read", dao.read(QnAno));
		int uno = (int) session.getAttribute("uno"); // 댓글작성하는(자기자신의) 사람의 uno
		String uemail = (String) session.getAttribute("uemail");
		// System.out.println(uno);
		model.addAttribute("wuno", uno);
		model.addAttribute("lo", udao.loginPost(uemail));
		return "mypage/helpRead";
	}

	@ResponseBody
	@RequestMapping("qnareply")
	public HashMap<String, Object> reply(int QnAno, HttpSession session) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// int uno = (int) session.getAttribute("uno");
		// map.put("rw", uno);
		map.put("rlist", rdao.relist(QnAno));

		return map;
	}

	// 댓글입력
	@ResponseBody
	@RequestMapping("qnarinsert")
	public void rinsert(QnAReplyVO vo) throws Exception {
		rdao.answertext(vo);
	}

	// 댓글삭제
	@ResponseBody
	@RequestMapping("qnardelete")
	public void rdelete(int QnAreno) throws Exception {
		rdao.answerdelete(QnAreno);
	}

	/*
	 * //QnA 덧글쓰기
	 * 
	 * @RequestMapping("answertext") public String answertext(@ModelAttribute
	 * QnAReplyVO vo, HttpServletRequest request)throws Exception{ HttpSession
	 * session = request.getSession(); int uno =
	 * (int)session.getAttribute("uno"); int QnAno =
	 * (int)session.getAttribute("QnAno"); vo.getUno();
	 * System.out.println(vo.toString()); return "mypage/helpRead"; }
	 * 
	 * 
	 * 
	 * 
	 * //QnA 삭제
	 * 
	 * @RequestMapping(value="answerdelete", method=RequestMethod.POST) public
	 * String answerdelete(int uno)throws Exception{ rdao.answerdelete(uno);
	 * return "mypage/helpRead"; }
	 * 
	 */
}