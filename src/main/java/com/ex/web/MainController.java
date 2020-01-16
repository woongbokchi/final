package com.ex.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ex.domain.BoardVO;
import com.ex.domain.Criteria;
import com.ex.domain.PageMaker;
import com.ex.domain.SearchCriteria;
import com.ex.persistence.BoardDAO;
import com.ex.persistence.QnADAO;
import com.ex.persistence.UserDAO;

@Controller
public class MainController {
	@Inject
	BoardDAO dao;

	@Inject
	UserDAO udao;

	@Inject
	QnADAO qdao;

	// �������ε�
	@Resource(name = "uploadPath")
	private String path;

	// ���� ������ �̵�
	@RequestMapping("main")
	public String main(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");

		if (uemail != null) {
			session.setAttribute("uauth", udao.loginPost(uemail).getUauth());
			model.addAttribute("vo", udao.loginPost(uemail));
			model.addAttribute("blist", dao.best());
			model.addAttribute("rlist", dao.list());
			return "main";
		}
		model.addAttribute("blist", dao.best());
		model.addAttribute("rlist", dao.list());
		return "main";
	}

	// �Խù���ülist( json��������
	@ResponseBody
	@RequestMapping("recipe.json")
	public HashMap<String, Object> recipeList() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rlist", dao.list());

		return map;
	}

	// �Խù� json
	@ResponseBody
	@RequestMapping(value = "best.json", method = RequestMethod.GET, produces = "application/JSON;charset=UTF-8")
	public HashMap<String, Object> bestList() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("blist", dao.best());
		// System.out.println(map.toString());
		// System.out.println("����:" + map.size());
		return map;
	}

	@RequestMapping("write") // �Խñ� �ۼ� ������ �̵�
	public String write(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");

		model.addAttribute("vo", udao.loginPost(uemail));

		return "write";
	}

	// �Խñ� �ۼ�
	@RequestMapping(value = "writer", method = RequestMethod.POST)
	public String writerPost(BoardVO vo, MultipartFile file) throws Exception {

		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + file.getOriginalFilename();
		File target = new File(path, savedName);
		FileCopyUtils.copy(file.getBytes(), target);
		vo.setThumbnail(savedName);

		dao.writer(vo);

		return "redirect:main"; // redirect�� ������������� �����Ͱ��� ������������.
	}

	@RequestMapping("read") // �� �ҷ����� ������ �̵� public String read(Model model,
	public String read(HttpSession session, int bno, Model model) throws Exception {

		int uno = (int) session.getAttribute("uno");
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("wuno", uno);
		model.addAttribute("lo", udao.loginPost(uemail));
		model.addAttribute("vo", dao.read(bno));

		// String unick = (String) session.getAttribute("unick");
		// model.addAttribute("unick", unick);

		return "read";
	}

	////////////////////
	// ī�װ�(������)
	@RequestMapping("korean") // ���� ������ �̵�
	public String korean(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));

		return "category/national/korean";
	}

	@ResponseBody
	@RequestMapping("koreanJSON")
	public HashMap<String, Object> koreanList(String bnational, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		bnational = "korean";
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(dao.ctotal(bnational));
		map.put("klist", dao.nationalList(bnational, cri, orderType));
		map.put("pm", pm);
		return map;
	}

	@RequestMapping("western") // �����������̵�
	public String western(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));

		return "category/national/western";
	}

	@ResponseBody
	@RequestMapping("westernJSON")
	public HashMap<String, Object> westernList(String bnational, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		bnational = "western";
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(dao.ctotal(bnational));
		map.put("wlist", dao.nationalList(bnational, cri, orderType));
		map.put("pm", pm);

		return map;
	}

	@RequestMapping("chinese") // ���� ������ �̵�
	public String chinese(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));

		return "category/national/chinese";
	}

	@ResponseBody
	   @RequestMapping("chineseJSON")
	   public HashMap<String, Object> chineseList(String bnational, SearchCriteria cri, String orderType) throws Exception {
	      HashMap<String, Object> map = new HashMap<String, Object>();
	      bnational = "chinese";
	      cri.setPerPageNum(5);
	      PageMaker pm = new PageMaker();
	      pm.setCri(cri);
	      pm.setTotalCount(dao.ctotal(bnational));
	      map.put("clist", dao.nationalList(bnational, cri, orderType));
	      map.put("pm", pm);
	      
	      System.out.println(orderType);

	      return map;
	   }

	@RequestMapping("japanese") // ���� ������ �̵�
	public String japanese(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));

		return "category/national/japanese";
	}

	@ResponseBody
	@RequestMapping("japaneseJSON")
	public HashMap<String, Object> japaneseList(String bnational, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		bnational = "japanese";
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(dao.ctotal(bnational));
		map.put("jlist", dao.nationalList(bnational, cri, orderType));
		map.put("pm", pm);
		return map;
	}

	@RequestMapping("other") // ���� ������ �̵�
	public String other(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));

		return "category/national/other";
	}

	@ResponseBody
	@RequestMapping("otherJSON")
	public HashMap<String, Object> otherList(String bnational, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		bnational = "other";
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(dao.ctotal(bnational));
		map.put("olist", dao.nationalList(bnational, cri, orderType));
		map.put("pm", pm);
		return map;
	}

	// ī�װ�(������)
	@RequestMapping("dessert") // ������ ������ �̵�
	public String dessert(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));

		return "category/sort/dessert";

	}

	@ResponseBody
	@RequestMapping("dessertJSON")
	public HashMap<String, Object> dessertList(String bsort, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		bsort = "dessert";
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(dao.stotal(bsort));
		map.put("dlist", dao.sortList(bsort, cri, orderType));
		map.put("pm", pm);
		return map;
	}

	@RequestMapping("etc") // ������ ������ �̵�
	public String etc(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));

		return "category/sort/etc";
	}

	@ResponseBody
	@RequestMapping("etcJSON")
	public HashMap<String, Object> etcList(String bsort, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		bsort = "etc";
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(dao.stotal(bsort));
		map.put("elist", dao.sortList(bsort, cri, orderType));
		map.put("pm", pm);
		return map;
	}

	@RequestMapping("family") // ������ ������ �̵�
	public String family(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));

		return "category/sort/family";
	}

	@ResponseBody
	@RequestMapping("familyJSON")
	public HashMap<String, Object> familyList(String bsort, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		bsort = "family";
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(dao.stotal(bsort));
		map.put("flist", dao.sortList(bsort, cri, orderType));
		map.put("pm", pm);
		return map;
	}

	@RequestMapping("healthy") // ������ ������ �̵�
	public String healthy(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));

		return "category/sort/healthy";
	}

	@ResponseBody
	@RequestMapping("healthyJSON")
	public HashMap<String, Object> healthyList(String bsort, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		bsort = "healthy";
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(dao.stotal(bsort));
		map.put("hlist", dao.sortList(bsort, cri, orderType));
		map.put("pm", pm);
		return map;
	}

	@RequestMapping("solo") // ������ ������ �̵�
	public String solo(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));

		return "category/sort/solo";
	}

	@ResponseBody
	@RequestMapping("soloJSON")
	public HashMap<String, Object> soloList(String bsort, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		bsort = "solo";
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(dao.stotal(bsort));
		map.put("slist", dao.sortList(bsort, cri, orderType));
		map.put("pm", pm);
		return map;
	}

	// ī�װ�(�ð���)
	@RequestMapping("over") // �ð� ������ �̵�
	public String over(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));

		return "category/time/over";
	}

	@ResponseBody
	@RequestMapping("overJSON")
	public HashMap<String, Object> overList(String btime, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		btime = "over";
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(dao.ttotal(btime));
		map.put("overlist", dao.timeList(btime, cri, orderType));
		map.put("pm", pm);
		return map;
	}

	@RequestMapping("o-hour") // �ð� ������ �̵�
	public String ohour(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));

		return "category/time/o-hour";
	}

	@ResponseBody
	@RequestMapping("ohourJSON")
	public HashMap<String, Object> ohourList(String btime, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		btime = "o-hour";
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(dao.ttotal(btime));
		map.put("olist", dao.timeList(btime, cri, orderType));
		map.put("pm", pm);
		return map;
	}

	@RequestMapping("h-hour") // �ð� ������ �̵�
	public String hhour(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));

		return "category/time/h-hour";
	}

	@ResponseBody
	@RequestMapping("hhourJSON")
	public HashMap<String, Object> hhourList(String btime, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		btime = "h-hour";
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(dao.ttotal(btime));
		map.put("hlist", dao.timeList(btime, cri, orderType));
		map.put("pm", pm);
		return map;
	}

	@RequestMapping("q-hour") // �ð� ������ �̵�
	public String qhour(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.loginPost(uemail));

		return "category/time/q-hour";
	}

	@ResponseBody
	@RequestMapping("qhourJSON")
	public HashMap<String, Object> qhourList(String btime, SearchCriteria cri, String orderType) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		btime = "q-hour";
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(dao.ttotal(btime));
		map.put("qlist", dao.timeList(btime, cri, orderType));
		map.put("pm", pm);
		return map;
	}
	/////////////////////////

	@RequestMapping("helpWrite") // ���� �Խñ� �ۼ� ������ �̵�
	public String helpWrite(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.myread(uemail));
		return "helpWrite";
	}

	@RequestMapping("myPage") // ���� ������ �̵�
	public String myread(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.myread(uemail));
		return "mypage/myPage";
	}

	@RequestMapping("myPost") // ���� �ۼ��� �� ������ �̵�
	public String myPost(Model model, HttpSession session) throws Exception {
		int uno = (int) session.getAttribute("uno");
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.myread(uemail));
		model.addAttribute("list", dao.userList(uno));
		return "mypage/myPost";
	}

	@RequestMapping("bookmark") // ���ã�� ������ �̵�
	public String bookmark() throws Exception {
		return "mypage/bookmark";
	}

	@RequestMapping("help") // ���� �Խ��� �̵�
	public String help(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.myread(uemail));

		// int uno = (int) session.getAttribute("uno");
		// model.addAttribute("help", qdao.qlist());
		// System.out.println(qdao.qlist().toString());
		return "mypage/help";
	}

	@ResponseBody
	@RequestMapping("help.json") // ���� �Խñ� ������
	public HashMap<String, Object> helpList(Criteria cri) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// ȭ�鿡 ��µ� ������ ��
		cri.setPerPageNum(10);

		// ������ �۾�
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(qdao.total());
		map.put("pm", pm);

		map.put("total", qdao.total());
		// ����Ʈ�� ��Ƽ� ���
		map.put("qlist", qdao.qlist(cri));

		return map;
	}

	/*
	 * @RequestMapping("read") // �� �ҷ����� ������ �̵� public String read(Model model,
	 * int bno) throws Exception { model.addAttribute("uno", udao.selectUser());
	 * 
	 * model.addAttribute("vo", dao.read(bno)); System.out.println(bno); return
	 * "read"; }
	 */

	// �˻���� ������ �̵�
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public Map<String, Object> search(SearchCriteria cri, HttpSession session, Model model) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.myread(uemail));
		Map<String, Object> map = new HashMap<String, Object>();
		cri.setPerPageNum(5);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(dao.total(cri));
		map.put("pm", pm);
		map.put("total", dao.total(cri));
		map.put("list", dao.search(cri)); // list�� ��Ƽ� search.jsp���� c:foreach������
											// �ݺ��Ͽ� ����Ʈ�� ���� ����¡ó����

		return map;
	}

	@ResponseBody
	@RequestMapping("/display")
	public byte[] display(String fileName) throws Exception {
		InputStream in = new FileInputStream(path + "/" + fileName);
		byte[] image = IOUtils.toByteArray(in);
		in.close();
		return image;
	}

	@ResponseBody
	@RequestMapping("blikeupJSON")
	public void blikeup(int bno) throws Exception {
		dao.blikeUp(bno);
	}

	@ResponseBody
	@RequestMapping("blikedownJSON")
	public void blikedown(int bno) throws Exception {
		dao.blikeDown(bno);
	}

}
