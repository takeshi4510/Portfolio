package jp.co.tk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginCompletedController {

	/*
	 * ログイン完了画面
	 */
	@GetMapping("/logincompleted")
	public String index() {
		return "logincompleted";
	}

}
