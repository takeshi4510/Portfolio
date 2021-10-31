package jp.co.tk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.tk.service.MailService;

@Controller
public class MainHomeController {
	@Autowired
	MailService MailService;

	/**
	 * ポートフォリオ画面
	 * @return
	 */
	@GetMapping("/home")
	public String index() {
		return "mainhome";
	}

	/**
	 * お問い合わせフォーム
	 * @param request　
	 * @param response
	 * @return
	 */
	@PostMapping("/inquiry")
	public String mail(HttpServletRequest request, HttpServletResponse response) {
		MailService.sendMail(request);

		return "redirect:/home#4";
	}
}
