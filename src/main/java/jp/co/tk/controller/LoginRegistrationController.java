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
public class LoginRegistrationController {
	@Autowired
	private LoginService LoginService;

	//ログイン登録画面
	@GetMapping("/loginregistration")
	public String index() {
		return "loginregistration";
	}

	//ログイン登録のチェック
	@PostMapping("/loginregistration")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, String> authMap = new HashMap<>();

		authMap = LoginService.Auth(request.getParameter("name"), request.getParameter("password"));

		if("normal" == authMap.get("Judg")) {
			model.addAttribute("user", request.getParameter("name"));
			model.addAttribute("password", request.getParameter("password"));

			if(LoginService.existenceCheck(request.getParameter("name")) == true) {
				LoginService.insert(request);
				return "logincompleted";
			}
		}


		model.addAttribute("msg", authMap.get("msg"));
		return "loginregistration";
	}

}
