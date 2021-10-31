package jp.co.tk.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.tk.entity.BbsHomeEntity;
import jp.co.tk.service.BbsHomeService;
import jp.co.tk.service.LoginService;

@Controller
public class BbsHomeController {

	private int user_id;

	@Autowired
	BbsHomeService BbsHomeService;
	@Autowired
	LoginService LoginService;

	/**
	 * bbsホーム画面
	 * @param model
	 * @param msg
	 * @return
	 */
	@GetMapping("bbshome")
	public String index(HttpServletRequest request, Model model,
			@ModelAttribute("msg") String msg,@ModelAttribute("bbsTitle") String bbsTitle, @ModelAttribute("bbsName") String bbsName,
			@ModelAttribute("bbsMsg") String bbsMsg){

		//セッションスコープ
		if(request.getSession().getAttribute("session_id") == null || "".equals(request.getSession().getAttribute("id"))) {

			//セッションがnullまたは空の場合、ログイン画面に遷移
			return "redirect:/login";
		}

		//セッションIDを変数user_idに格納
		this.user_id = (int) request.getSession().getAttribute("session_id") ;

		//入力チェックがNGの場合変数msgにメッセージを格納
		model.addAttribute("msg", msg);
		model.addAttribute("bbsTitle", bbsTitle);
		model.addAttribute("bbsName", bbsName);
		model.addAttribute("bbsMsg", bbsMsg);


		String user_name = LoginService.id(user_id);

		//
		System.out.println(user_name);
		//全件検索
		List<BbsHomeEntity> bbsList = BbsHomeService.select();
		//ソート条件降順
		Collections.reverse(bbsList);
		//bbsの一覧をbbsホーム画面に渡す
		model.addAttribute("BbsList", bbsList);
		//ログイン中のユーザのIDをbbsホーム画面に渡す
		model.addAttribute("user_id", user_id);
		//ログインユーザをbbsホームに渡す
		model.addAttribute("user_name", user_name);
		return "bbshome";
	}

	/**
	 * 投稿を取得
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("bbshome")
	public String input(HttpServletRequest request, HttpServletResponse response, Model model,
			RedirectAttributes redirectAttributes) {
		Map<String, String> map = new HashMap<>();

		//入力チェックを行う
		map = BbsHomeService.check(request);
		if("1" == map.get("check")) {
			redirectAttributes.addFlashAttribute("msg", map.get("msg"));

			redirectAttributes.addFlashAttribute("bbsTitle", request.getParameter("title"));
			redirectAttributes.addFlashAttribute("bbsName", request.getParameter("name"));
			redirectAttributes.addFlashAttribute("bbsMsg", request.getParameter("contents"));

			return "redirect:/bbshome" + "?id=" + user_id;
		}

		//新規登録
		BbsHomeService.insert(request, user_id);
		return "redirect:/bbshome";
	}

	/**
	 * delete
	 * @param request
	 * @return
	 */
	@GetMapping("bbsremove")
	public String remove(HttpServletRequest request) {

		//bbshomeのデータをuser_idをもとに削除
		BbsHomeService.remove(request.getParameter("bbsList_id"));

		return "redirect:/bbshome";
	}

	@PostMapping("bbsupdate")
	public String update(HttpServletRequest request) {

		//		BbsHomeService.update(request.getParameter("useer_id"));

		return "redirect:/bbshome";
	}
}
