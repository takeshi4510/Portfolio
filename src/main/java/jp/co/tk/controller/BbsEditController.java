package jp.co.tk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.tk.service.BbsHomeService;

/**
 *
 * bbsの更新画面
 *
 */
@Controller
public class BbsEditController {
	@Autowired
	BbsHomeService service;


	/**
	 * bbs編集画面
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping("/bbs/edit")
	public String doPostBbsEdit(
			HttpServletRequest request,
			Model model){

		//セッションスコープ
		if(request.getSession().getAttribute("sessionId") == null || "".equals(request.getSession().getAttribute("sessionId"))) {

			//セッションがnullまたは空の場合、ログイン画面に遷移
			return "redirect:/bbs/login";
		}

		model.addAttribute("bbsId", request.getParameter("bbsId"));
		model.addAttribute("bbsTitle",request.getParameter("title"));
		model.addAttribute("bbsName",request.getParameter("name"));
		model.addAttribute("bbsContents",request.getParameter("contents"));

		//bbs変更画面に遷移
		return "/bbsedit";
	}

	/**
	 * bbs更新処理
	 * @param request
	 * @return
	 */
	@PostMapping("/bbs/update")
	public String doPostBbsUpdate(HttpServletRequest request) {

		//編集処理を呼び出し
		service.updateBbs(request);

		//bbs画面に遷移
		return "redirect:/bbs/home";

	}

}
