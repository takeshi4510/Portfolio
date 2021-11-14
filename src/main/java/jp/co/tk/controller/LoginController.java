package jp.co.tk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.tk.service.LoginService;

/**
 * ログイン画面
 * @author Takeshi Nakasone
 *
 */
@Controller
public class LoginController {
	@Autowired
	private LoginService service;

	/**
	 * ログイン画面初期表示
	 * @param request
	 * @return
	 */
	@GetMapping("/bbs/login")
	public String doGetLogin(
			@ModelAttribute("msg") String msg,
			@ModelAttribute("name") String name,
			HttpServletRequest request) {

		//セッション破棄
		request.getSession().invalidate();

		//ログイン画面に遷移
		return "/login";
	}

	/**
	 * ログインチェック処理
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@PostMapping("bbs/login/validate")
	public String doPostLoginValidate(
			HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			Model model) {

		//入力チェック&ユーザの存在チェック
		String msg = service.validateLogin(request);

		//入力チェックがNGの場合ログイン画面に遷移
		if(msg.length() > 0) {

			//エラーメッセージをログイン画面に渡す
			redirectAttributes.addFlashAttribute("msg", msg);
			redirectAttributes.addFlashAttribute("name",  request.getParameter("name"));

			//ログイン画面に遷移
			return "redirect:/bbs/login";
		}

		//入力チェックと存在チェックがOKの場合セッションを変数sessionIdに格納
		request.getSession().setAttribute("sessionId", service.selectId(request.getParameter("name"),request.getParameter("password")));

		//bbsホーム画面にリダイレクト
		return "redirect:/bbs/home";

	}

}
