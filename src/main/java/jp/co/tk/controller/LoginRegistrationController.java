package jp.co.tk.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.tk.service.LoginRegistrationService;

@Controller
public class LoginRegistrationController {
	@Autowired
	private LoginRegistrationService LoginRegistrationService;

	/**
	 * ログイン登録画面
	 * @return
	 */
	@GetMapping("/loginregistration")
	public String index() {
		return "loginregistration";
	}

	/**
	 * ログイン登録のチェック
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping("/loginregistration")
	public String login(HttpServletRequest request,Model model) {
		//エラーメッセージ格納用マップ作成
		Map<String, String> authMap = new HashMap<>();

		//入力チェック&ユーザの存在チェック
		authMap = LoginRegistrationService.Auth(request.getParameter("name"), request.getParameter("password"));

		//入力チェックがNGの場合登録画面に遷移
		if("1" == authMap.get("Judg")) {
			model.addAttribute("msg", authMap.get("msg"));
			model.addAttribute("name", request.getParameter("name"));
			return "loginregistration";
		}

		//入力チェックがOKの場合ログイン登録処理を実行
		LoginRegistrationService.insert(request);

		//完了画面に登録したユーザ名とパスワードを渡す
		model.addAttribute("user", request.getParameter("name"));
		model.addAttribute("password", request.getParameter("password"));

		//完了画面に遷移
		return "logincompleted";
	}

}
