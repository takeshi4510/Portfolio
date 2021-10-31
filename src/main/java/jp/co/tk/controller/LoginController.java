package jp.co.tk.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.tk.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService LoginService;

	/**
	 * ログイン画面
	 * @param request
	 * @return
	 */
	@GetMapping("/login")
	public String index(HttpServletRequest request) {
		//セッション破棄
		request.getSession().invalidate();

		return "login";
	}

	/**
	 * ログインのチェック処理
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@PostMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		//エラーメッセージ格納用マップ作成
		Map<String, String> authMap = new HashMap<>();

		//入力チェック&ユーザの存在チェック
		authMap = LoginService.Auth(request);
		//ログイン時に入力された値を保持するための変数

		//入力チェックがNGの場合ログイン画面に遷移
		if("1".equals(authMap.get("Judg"))) {
			//エラーメッセージをログイン画面に渡す
			model.addAttribute("msg", authMap.get("msg"));
			model.addAttribute("name",  request.getParameter("name"));

			return "login";
		}

		//入力チェックと存在チェックがOKの場合セッションをsession_idに格納
		request.getSession().setAttribute("session_id", LoginService.selectId(request.getParameter("name"),request.getParameter("password")));

		//bbsホーム画面にリダイレクト
		return "redirect:/bbshome";

	}

}
