package jp.co.tk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BbsHomeController {

	//bbsホーム画面
	@GetMapping("bbshome")
	public String index() {
		return "bbshome";
	}
	
	//
}
