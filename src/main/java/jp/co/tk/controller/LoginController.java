package jp.co.tk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		LoginService.Auth(name, password);

		return "login";
	}

}
