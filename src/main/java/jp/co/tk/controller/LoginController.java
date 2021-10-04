package jp.co.tk.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	//ログイン画面
	@GetMapping("/login")
	public String index() {
		return "login";
	}

	//ログインのチェック処理
	@PostMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		//入力チェックの戻り値として値を入れるMapを作成
		Map<String, String> authMap = new HashMap<>();
		//入力チェックの戻り値を格納
		authMap = LoginService.Auth(request.getParameter("name"), request.getParameter("password"));

		//存在チェック
		if(LoginService.login(request.getParameter("name"), request.getParameter("password")) == true) {
			return "redirect:/bbshome";
		}
		model.addAttribute("msg", authMap.get("msg"));

		return "login";
	}

}
