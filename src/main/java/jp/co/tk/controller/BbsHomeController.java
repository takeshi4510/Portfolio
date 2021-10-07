package jp.co.tk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.tk.entity.BbsHomeEntity;
import jp.co.tk.service.BbsHomeService;

@Controller
public class BbsHomeController {

	@Autowired
	BbsHomeService BbsHomeService;
	//bbsホーム画面
	@GetMapping("bbshome")
	public String index(Model model) {
		//全件検索
		List<BbsHomeEntity> list = BbsHomeService.select();
		model.addAttribute("list", list);
		return "bbshome";
	}

	//投稿を取得
	@PostMapping("bbshome")
	public String input(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, String> map = new HashMap<>();

		map = BbsHomeService.check(request);
		model.addAttribute("msg", map.get("msg"));

		//新規登録
		BbsHomeService.insert(request);
		return "redirect:/bbshome";
	}
}
