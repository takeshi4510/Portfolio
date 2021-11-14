package jp.co.tk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.tk.service.MailService;

/**
 * ポートフォリオ画面
 * @author Takeshi Nakasone
 *
 */
@Controller
public class MainHomeController {
	@Autowired
	MailService MailService;

	/**
	 * ポートフォリオ画面初期表
	 * @return
	 */
	@GetMapping("/portfolio")
	public String doGetHome() {

		//ポートフォリオ画面に遷移
		return "mainhome";
	}

	/**
	 * お問い合わせフォーム処理
	 * @param request　
	 * @param response
	 * @return
	 */
	@PostMapping("/portfolio/inquiry")
	public String doPostMail(
			HttpServletRequest request,
			HttpServletResponse response) {

		//メール処理
		MailService.sendMail(request);

		//ホーム画面にリダイレクト
		return "redirect:/portfolio#4";
	}
}
