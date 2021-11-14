package jp.co.tk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.tk.service.BbsThreadService;

/**
 * スレッド編集
 * @author Takeshi Nakasone
 *
 */
@Controller
public class BbsThreadEditController {
	@Autowired
	BbsThreadService service;


	/**
	 * スレッド編集画面初期表示
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping("/bbs/thread/edit")
	public String doPostEdit(
			HttpServletRequest request,
			Model model) {

		//セッションスコープ
		if(request.getSession().getAttribute("sessionId") == null || "".equals(request.getSession().getAttribute("sessionId"))) {

			//セッションがnullまたは空の場合、ログイン画面に遷移
			return "redirect:/bbs/login";
		}

		//リクエストで送られたデータをhtmlに渡す
		model.addAttribute("threadId", request.getParameter("threadId"));
		model.addAttribute("threadName",request.getParameter("name"));
		model.addAttribute("threadContents",request.getParameter("contents"));
		model.addAttribute("bbsId", request.getParameter("bbsId"));

		//スレッド編集画面に遷移
		return "/threadedit";
	}

	/**
	 *スレッド編集更新処理
	 * @param request
	 * @return
	 */
	@PostMapping("/bbs/thread/update")
	public String doPostUpdate(
			HttpServletRequest request) {

		//更新処理を開始
		service.updateThreadEdit(request);

		//スレッド画面にリダイレクト
		return "redirect:/bbs/thread" + "?id=" + request.getParameter("bbsId");

	}

}
