package jp.co.tk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.tk.entity.BbsHomeEntity;
import jp.co.tk.entity.BbsThreadEntity;
import jp.co.tk.service.BbsThreadService;

@Controller
public class BbsThreadController {
	@Autowired
	BbsThreadService BbsThreadService;

	//ログインしたユーザID 格納用
	private int user_id;
	private int bbs_id;

	/*
	 * スレッド画面
	 */
	@GetMapping("bbsthread")
	public String index(HttpServletRequest request, @RequestParam("id")Integer bbs_id,Model model
			,@ModelAttribute("msg") String msg) {
		this.user_id = (int) request.getSession().getAttribute("session_id");
		this.bbs_id = bbs_id;
		List<BbsHomeEntity> bbsList = BbsThreadService.select(bbs_id);
		List<BbsThreadEntity> threadList = BbsThreadService.threadSelect(bbs_id);
		model.addAttribute("list", bbsList);
		model.addAttribute("threadList", threadList);
		model.addAttribute("user_id",user_id);
		model.addAttribute("bbs_id", bbs_id);
		return "bbsthread";
	}

	/*
	 * スレッド画面の登録処理
	 */
	@PostMapping("bbsthread")
	public String thread(HttpServletRequest request, Model model,RedirectAttributes redirectAttributes) {

		Map<String, String> authMap = new HashMap<>();
		authMap = BbsThreadService.check(request);

		if("1" == authMap.get("check")) {
			//エラーメッセージを渡す
			redirectAttributes.addFlashAttribute("msg", authMap.get("msg"));
			//BbsThread画面に遷移
			return "redirect:/bbsthread" + "?id=" + bbs_id;
		}

		BbsThreadService.insert(request, bbs_id,user_id);
		return "redirect:/bbsthread" + "?id=" + bbs_id;

	}

	/*
	 * 削除処理
	 */
	@GetMapping("remove")
	public String remove(HttpServletRequest request) {
		System.out.println(request.getParameter("thread_id"));
		BbsThreadService.remove(request.getParameter("thread_id"));

		return "redirect:/bbsthread" + "?id=" + bbs_id;

	}

}
