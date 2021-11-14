package jp.co.tk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.tk.service.LoginRegistrationService;

/**
 * ログイン登録
 * @author Takeshi Nakasone
 *
 */
@Controller
public class LoginRegistrationController {
	@Autowired
	private LoginRegistrationService service;

	/**
	 * ログイン登録画面初期表示
	 * @return
	 */
	@GetMapping("/bbs/login/registration")
	public String doGetLoginRegistration(
			@ModelAttribute("msg") String msg,
			@ModelAttribute("name") String name) {

		//ログイン登録画面に遷移
		return "/loginregistration";
	}

	/**
	 * ログイン登録のチェック処理
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping("/bbs/login/create")
	public String doPostLoginInsert(
			HttpServletRequest request,
			Model model,
			RedirectAttributes redirectAttributes) {

		//入力チェック&ユーザの存在チェック
		String msg = service.validateResistration(
				request.getParameter("name"), request.getParameter("password"));

		//入力チェックがNGの場合登録画面に遷移
		if(msg.length() > 0) {

			//エラーメッセージをhtmlに渡す
			redirectAttributes.addFlashAttribute("msg", msg);
			redirectAttributes.addFlashAttribute("name", request.getParameter("name"));

			//ログイン登録画面に遷移
			return "redirect:/bbs/login/registration";
		}

		//入力チェックがOKの場合ログイン登録処理を実行
		service.insertLoginResistration(request);

		//ログイン完了画面に登録したユーザ名とパスワードを渡す
		redirectAttributes.addFlashAttribute("name", request.getParameter("name"));
		redirectAttributes.addFlashAttribute("password", request.getParameter("password"));

		//ログイン完了画面に遷移
		return "redirect:/bbs/login/completed";
	}

}
