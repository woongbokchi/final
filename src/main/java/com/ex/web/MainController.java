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

	// 사진업로드
	@Resource(name = "uploadPath")
	private String path;

	// 메인 페이지 이동
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

	// 게시물전체list( json값보내기
	@ResponseBody
	@RequestMapping("recipe.json")
	public HashMap<String, Object> recipeList() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rlist", dao.list());

		return map;
	}

	// 게시물 json
	@ResponseBody
	@RequestMapping(value = "best.json", method = RequestMethod.GET, produces = "application/JSON;charset=UTF-8")
	public HashMap<String, Object> bestList() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("blist", dao.best());
		// System.out.println(map.toString());
		// System.out.println("갯수:" + map.size());
		return map;
	}

	@RequestMapping("write") // 게시글 작성 페이지 이동
	public String write(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");

		model.addAttribute("vo", udao.loginPost(uemail));

		return "write";
	}

	// 게시글 작성
	@RequestMapping(value = "writer", method = RequestMethod.POST)
	public String writerPost(BoardVO vo, MultipartFile file) throws Exception {

		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + file.getOriginalFilename();
		File target = new File(path, savedName);
		FileCopyUtils.copy(file.getBytes(), target);
		vo.setThumbnail(savedName);

		dao.writer(vo);

		return "redirect:main"; // redirect를 사용하지않으면 데이터값을 가져가지못함.
	}

	@RequestMapping("read") // 글 불러오기 페이지 이동 public String read(Model model,
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
	// 카테고리(국가별)
	@RequestMapping("korean") // 국가 페이지 이동
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

	@RequestMapping("western") // 국가페이지이동
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

	@RequestMapping("chinese") // 국가 페이지 이동
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

	@RequestMapping("japanese") // 국가 페이지 이동
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

	@RequestMapping("other") // 국가 페이지 이동
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

	// 카테고리(유형별)
	@RequestMapping("dessert") // 유형별 페이지 이동
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

	@RequestMapping("etc") // 유형별 페이지 이동
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

	@RequestMapping("family") // 유형별 페이지 이동
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

	@RequestMapping("healthy") // 유형별 페이지 이동
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

	@RequestMapping("solo") // 유형별 페이지 이동
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

	// 카테고리(시간별)
	@RequestMapping("over") // 시간 페이지 이동
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

	@RequestMapping("o-hour") // 시간 페이지 이동
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

	@RequestMapping("h-hour") // 시간 페이지 이동
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

	@RequestMapping("q-hour") // 시간 페이지 이동
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

	@RequestMapping("helpWrite") // 문의 게시글 작성 페이지 이동
	public String helpWrite(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.myread(uemail));
		return "helpWrite";
	}

	@RequestMapping("myPage") // 마이 페이지 이동
	public String myread(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.myread(uemail));
		return "mypage/myPage";
	}

	@RequestMapping("myPost") // 내가 작성한 글 페이지 이동
	public String myPost(Model model, HttpSession session) throws Exception {
		int uno = (int) session.getAttribute("uno");
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.myread(uemail));
		model.addAttribute("list", dao.userList(uno));
		return "mypage/myPost";
	}

	@RequestMapping("bookmark") // 즐겨찾기 페이지 이동
	public String bookmark() throws Exception {
		return "mypage/bookmark";
	}

	@RequestMapping("help") // 문의 게시판 이동
	public String help(Model model, HttpSession session) throws Exception {
		String uemail = (String) session.getAttribute("uemail");
		model.addAttribute("vo", udao.myread(uemail));

		// int uno = (int) session.getAttribute("uno");
		// model.addAttribute("help", qdao.qlist());
		// System.out.println(qdao.qlist().toString());
		return "mypage/help";
	}

	@ResponseBody
	@RequestMapping("help.json") // 문의 게시글 데이터
	public HashMap<String, Object> helpList(Criteria cri) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 화면에 출력될 데이터 갯
		cri.setPerPageNum(10);

		// 페이지 작업
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(qdao.total());
		map.put("pm", pm);

		map.put("total", qdao.total());
		// 리스트를 담아서 출력
		map.put("qlist", qdao.qlist(cri));

		return map;
	}

	/*
	 * @RequestMapping("read") // 글 불러오기 페이지 이동 public String read(Model model,
	 * int bno) throws Exception { model.addAttribute("uno", udao.selectUser());
	 * 
	 * model.addAttribute("vo", dao.read(bno)); System.out.println(bno); return
	 * "read"; }
	 */

	// 검색결과 페이지 이동
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
		map.put("list", dao.search(cri)); // list에 담아서 search.jsp에서 c:foreach문으로
											// 반복하여 리스트를 띄우고 페이징처리함

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
