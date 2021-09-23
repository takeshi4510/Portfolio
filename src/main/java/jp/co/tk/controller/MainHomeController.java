package jp.co.tk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainHomeController {

	@GetMapping("/home")
	public String index() {
		return "mainhome";
	}

	@PostMapping("/inquiry")
	public String inquiry() {
		return "redirect:/mainhome";
	}

}
