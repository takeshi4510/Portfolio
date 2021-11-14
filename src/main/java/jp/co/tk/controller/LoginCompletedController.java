package jp.co.tk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * ログイン完了画面
 * @author Takeshi Nakasone
 *
 */
@Controller
public class LoginCompletedController {

	/**
	 * ログイン完了画面初期表示
	 * @return
	 */
	@GetMapping("/bbs/login/completed")
	public String doGetLoginCpl(
			@ModelAttribute("name") String name,
			@ModelAttribute("password") String password,
			HttpServletRequest request) {

		//ログイン完了画面に遷移
		return "/logincompleted";
	}

}
