package jp.co.tk.controller;

import java.util.List;

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

/**
 *
 *bbs一覧画面
 *
 */
@Controller
public class BbsHomeController {

	@Autowired
	BbsHomeService service;
	@Autowired
	LoginService loginService;

	/**
	 * bbsホーム画面
	 * @param model
	 * @param msg
	 * @return
	 */
	@GetMapping("/bbs/home")
	public String doGetBbsHome(
			Model model,
			HttpServletRequest request,
			@ModelAttribute("msg") String msg,
			@ModelAttribute("bbsTitle") String bbsTitle,
			@ModelAttribute("bbsName") String bbsName,
			@ModelAttribute("bbsMsg") String bbsMsg){

		//セッションスコープ
		if(request.getSession().getAttribute("sessionId") == null || "".equals(request.getSession().getAttribute("sessionId"))) {

			//セッションがnullまたは空の場合、ログイン画面に遷移
			return "redirect:/bbs/login";
		}


		//入力チェックがNGの場合変数msgにメッセージを格納
		model.addAttribute("msg", msg);
		model.addAttribute("bbsTitle", bbsTitle);
		model.addAttribute("bbsName", bbsName);
		model.addAttribute("bbsMsg", bbsMsg);

		//セッションIDを変数userIdに格納
		int userId = (int) request.getSession().getAttribute("sessionId");
		//変数userNameにユーザ名を格納
		String userName = loginService.getUserName(userId);

		//全件検索
		List<BbsHomeEntity> bbsList = service.findByBbsAll();
		//全件取得したデータをhtmlに渡す
		model.addAttribute("bbsList", bbsList);
		//ログイン中のユーザIDをhtmlに渡す
		model.addAttribute("userId", userId);
		//ログイン中のユーザ名をhtmlに渡す
		model.addAttribute("userName", userName);

		//bbshome画面に遷移
		return "/bbshome";
	}

	/**
	 * 投稿を取得
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/bbs/create")
	public String doPostBbsInput(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			RedirectAttributes redirectAttributes) {

		//セッションIDを変数userIdに格納
		int userId = (int) request.getSession().getAttribute("sessionId");

		//入力チェックを行う
		String msg = service.validateBbsInput(request);

		if(msg.length() > 0) {
			redirectAttributes.addFlashAttribute("msg", msg);
			redirectAttributes.addFlashAttribute("bbsTitle", request.getParameter("title"));
			redirectAttributes.addFlashAttribute("bbsName", request.getParameter("name"));
			redirectAttributes.addFlashAttribute("bbsMsg", request.getParameter("contents"));

			return "redirect:/bbs/home";
		}

		//新規登録処理を呼び出す
		service.insertBbs(request, userId);

		//bbshome画面にリダイレクト
		return "redirect:/bbs/home";
	}

	/**
	 * 削除処理
	 * @param request
	 * @return
	 */
	@GetMapping("/bbs/remove")
	public String doGetRemove(HttpServletRequest request) {

		//ユーザIDをもとに削除処理処理を行う
		service.removeBbs(request.getParameter("bbsListId"));

		//bbshome画面にリダイレクト
		return "redirect:/bbs/home";
	}

}
