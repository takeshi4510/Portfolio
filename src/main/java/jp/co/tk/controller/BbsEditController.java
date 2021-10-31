package jp.co.tk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.tk.service.BbsHomeService;
import jp.co.tk.service.BbsThreadService;
import jp.co.tk.service.LoginService;

@Controller
public class BbsEditController {

	@Autowired
	BbsHomeService BbsHomeService;
	@Autowired
	BbsThreadService BbsThreadService;
	@Autowired
	LoginService LoginService;

	/**
	 * bbs編集画面
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping("bbsedit")
	public String bbsedit(HttpServletRequest request, Model model){

		model.addAttribute("bbsId", request.getParameter("bbs_id"));
		model.addAttribute("bbsTitle",request.getParameter("title"));
		model.addAttribute("bbsName",request.getParameter("name"));
		model.addAttribute("bbsContents",request.getParameter("contents"));

		return "bbsedit";
	}

	@PostMapping("edit")
	public String bbsedit(HttpServletRequest request) {

		BbsHomeService.edit(request);
		return "redirect:/bbshome";

	}
	/**
	 * スレッド編集
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping("threadedit")
	public String thrededit(HttpServletRequest request, Model model) {
		model.addAttribute("thread_id", request.getParameter("thread_id"));
		model.addAttribute("threadName",request.getParameter("name"));
		model.addAttribute("threadContents",request.getParameter("contents"));
		model.addAttribute("bbs_id", request.getParameter("bbs_id"));
		return "threadedit";
	}

	/*
	 * 編集処理
	 */
	@PostMapping("threadedits")
	public String threadedit(HttpServletRequest request) {

		BbsThreadService.edit(request);
		return "redirect:/bbsthread" + "?id=" + request.getParameter("bbs_id");

	}

}
