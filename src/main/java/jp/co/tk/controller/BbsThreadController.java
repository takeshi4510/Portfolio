package jp.co.tk.controller;

import java.util.List;

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

/**
 * bbsthread画面
 * @author Takeshi Nakasone
 *
 */
@Controller
public class BbsThreadController {
	@Autowired
	BbsThreadService service;

	/**
	 * スレッド画面初期表示
	 *
	 * @param request
	 * @param bbsId
	 * @param model
	 * @param msg
	 * @return
	 */
	@GetMapping("/bbs/thread")
	public String doGetBbsthread(
			HttpServletRequest request,
			Model model,
			@RequestParam("id")Integer bbsId,
			@ModelAttribute("msg") String msg) {

		//変数userIdにセッションIDを格納
		int userId = (int) request.getSession().getAttribute("sessionId");

		//BBSTABLEからidをもとに一件データを取得し変数bbsListに格納
		List<BbsHomeEntity> bbsList = service.findByBbs(bbsId);
		//BBSTHREADTABLEからidをもとにデータを取得し変数threadListに格納
		List<BbsThreadEntity> threadList = service.threadSelect(bbsId);

		//データをhtmlに渡す
		model.addAttribute("bbsList", bbsList);
		model.addAttribute("threadList", threadList);
		model.addAttribute("userId",userId);
		model.addAttribute("bbsId", bbsId);

		//thread画面に遷移
		return "/bbsthread";
	}


	/**
	 *  スレッド画面の登録処理
	 * @param request
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/bbs/thread/create")
	public String doPostUpdate(
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		//リクエストで送られたidを変数bbsIdに格納
		int bbsId = Integer.parseInt(request.getParameter("bbsId"));

		//入力チェック処理の戻り値であるメッセージを変数msgに格納
		String msg = service.validateThreadInputs(request);

		//入力チェックでmsgが空の場合はブロックの処理を開始
		if(msg.length() > 0) {
			//エラーメッセージを渡す
			redirectAttributes.addFlashAttribute("msg", msg);

			//BbsThread画面に遷移
			return "redirect:/bbs/thread" + "?id=" + bbsId;
		}


		//取得したセッションIdを変数userIdに格納
		int userId = (int) request.getSession().getAttribute("sessionId");

		//入力チェックがOKの場合スレッドの登録処理を開始
		service.insert(request, bbsId,userId);

		//BbsThread画面にリダイレクト
		return "redirect:/bbs/thread" + "?id=" + bbsId;

	}


	/**
	 * スレッド画面の削除処理
	 * @param request
	 * @return
	 */
	@GetMapping("/bbs/thread/remove")
	public String doPostRemove(HttpServletRequest request) {

		//リクエストで送られたidを変数bbsIdに格納
		int bbsId = Integer.parseInt(request.getParameter("bbsId"));

		//スレッドを元に削除処理開始
		service.removeThread(request.getParameter("threadId"));

		//BbsThread画面にリダイレクト
		return "redirect:/bbs/thread" + "?id=" + bbsId;

	}

}
