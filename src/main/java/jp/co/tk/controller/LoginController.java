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

	@GetMapping("/login")
	public String index() {
		return "login";
	}

	@PostMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, String> authMap = new HashMap<>();

		authMap = LoginService.Auth(request.getParameter("name"), request.getParameter("password"));

		if("normal" == authMap.get("Judg")) {
			return "bbshome";
		}

		model.addAttribute("msg", authMap.get("msg"));
		return "login";
	}

}
