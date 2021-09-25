package jp.co.tk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BbsHomeController {

	@GetMapping("bbshome")
	public String index() {
		return "bbshome";
	}
}
