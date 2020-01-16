package com.ex.web;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ex.domain.BoardVO;
import com.ex.domain.PageMaker;
import com.ex.domain.SearchCriteria;
import com.ex.domain.UserVO;
import com.ex.persistence.BoardDAO;
import com.ex.persistence.UserDAO;
import com.ex.service.Boardservice;

@Controller
public class UserController {
	@Inject
	BoardDAO dao;

	@Inject
	UserDAO udao;
	
	@Inject
	Boardservice bsdao;

	// ȸ�� ������ �������ε�
	@Resource(name = "uploadPath")
	private String path;

	// �׾Ʒ� callback ����� ������ͼ� db�� ȸ������
	@RequestMapping("navbar")
	public String navbar(HttpSession session, UserVO vo) throws Exception {
		boolean result = udao.selectUser(vo);
		if (result == true) {
			session.setAttribute("unick", vo.getUnick());

			session.setAttribute("uemail", vo.getUemail());
			session.setAttribute("uno", vo.getUno());
		} else if (result == false) {
			session.setAttribute("unick", vo.getUnick());
			session.setAttribute("uemail", vo.getUemail());
			session.setAttribute("uno", vo.getUno());
			udao.insert(vo);
		}
		return "redirect:main";
	}

	// �׾Ʒ� �α��� ������ api ���� String������ ������
	@RequestMapping("callback")
	public String callback(HttpServletRequest request, UserVO vo) throws Exception {
		HttpSession session = request.getSession();
		String uemail = (String) session.getAttribute("uemail");
		String unick = (String) session.getAttribute("unick");
		String uno = (String) session.getAttribute("uno");

		return "callback";
	}

	// �׾Ʒ� �α��� session���� ������ , naver�α׾ƿ��� �Ұ�����
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
		udao.logout(session);
		return "redirect:main";
	}

	// Mypage �� ������ ����
	@RequestMapping(value = "myupdate", method = RequestMethod.POST)
	public String myupdate(HttpServletRequest request, UserVO vo, MultipartFile file) throws Exception {
		HttpSession session = request.getSession();
		String uemail = (String) session.getAttribute("uemail");
		String unick = (String) session.getAttribute("unick");

		if (file.getOriginalFilename() != "") {
			String fileName = vo.getProfileimage();
			new File(path + "/" + fileName).delete();

			System.out.println(path + "           " + fileName);

			// ���ε�
			UUID uid = UUID.randomUUID();
			String savedName = uid.toString() + "_" + file.getOriginalFilename();
			File target = new File(path, savedName);
			FileCopyUtils.copy(file.getBytes(), target);
			vo.setProfileimage(savedName);
		}

		udao.myupdate(vo);
		return "redirect:myPage";
	}

	// �������� ������ �̵�
	@RequestMapping("userlist") // �������� ������ �̵�
	public String member(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));
		return "manager/userlist";
	}

	// �����ڸ� ������ ���� ����Ʈ
	@ResponseBody
	@RequestMapping(value = "userlist.json", method = RequestMethod.POST)
	public Map<String, Object> list(SearchCriteria cri) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(udao.stotal(cri));
		map.put("pm", pm);
		map.put("total", udao.stotal(cri));
		map.put("list", udao.suserlistread(cri));

		return map;
	}

	// ���� ���
	@ResponseBody
	@RequestMapping(value = "ulockupdate", method = RequestMethod.POST)
	public void ulockupdate(int uno) throws Exception {
		udao.userlock(uno);

	}

	// ���� ������ ���
	@ResponseBody
	@RequestMapping(value = "userblack", method = RequestMethod.POST)
	public void userblack(int uno) throws Exception {
		udao.userblack(uno);

	}

	// ���� ������ �ع�
	@ResponseBody
	@RequestMapping(value = "unblack", method = RequestMethod.POST)
	public void unblack(int uno) throws Exception {
		udao.unblack(uno);

	}

	/*
	 * @RequestMapping(value = "read", method = RequestMethod.GET) // �� �ҷ����� ������
	 * // �̵�(myPost-read) public String read(int bno, Model model, HttpSession
	 * session) throws Exception { String uno = (String)
	 * session.getAttribute("uno"); //BoardVO vo = dao.mypostread(bno);
	 * 
	 * System.out.println(uno); model.addAttribute("vo", dao.mypostread(bno));
	 * return "read"; }
	 */

	@RequestMapping("update") // �۾��� ����
	public String update(int bno, Model model) throws Exception {
		model.addAttribute("vo", dao.mypostread(bno));
		return "update";
	}

	@RequestMapping(value = "mypostupdate", method = RequestMethod.POST)
	public String mypostupdate(HttpServletRequest request, BoardVO vo, MultipartFile file) throws Exception {
		HttpSession session = request.getSession();
		int uno = (int) session.getAttribute("uno");
		System.out.println("...............................");
		if (file.getOriginalFilename() != "") {
			String fileName1 = vo.getThumbnail();
			new File(path + "/" + fileName1).delete();

			System.out.println(path + "           " + fileName1);

			// ���ε�
			UUID uid = UUID.randomUUID();
			String savedName = uid.toString() + "_" + file.getOriginalFilename();
			File target = new File(path, savedName);
			FileCopyUtils.copy(file.getBytes(), target);
			vo.setThumbnail(savedName);
		}

		// System.out.println(vo.toString());
		dao.mypostupdate(vo);
		// System.out.println(vo.toString());
		return "redirect:myPost";
	}

	@RequestMapping("delete")
	public String delete(int bno) throws Exception{		
		bsdao.delete(bno);
		return "redirect:myPost";
	}

	@RequestMapping(value = "loginF")
	public ModelAndView loginF() throws Exception {
		ModelAndView mav = new ModelAndView("loginF");
		mav.addObject("msg", "�α��� �� �̿����ֽñ� �ٶ��ϴ�.");
		System.out.println("prehandle........................................");
		return mav;
	}

	@RequestMapping(value = "blackF")
	public ModelAndView blackF() throws Exception {
		ModelAndView mav = new ModelAndView("blackF");
		mav.addObject("msg", "Ȱ�������� ȸ���Դϴ�.");
		System.out.println("prehandle........................................");
		return mav;
	}

}
