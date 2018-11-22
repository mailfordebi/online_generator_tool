package com.test.scriptgen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScriptController {
	@RequestMapping("/")
	public String index() {
		return "script";
	}

	@PostMapping("/convert")
	public String callService(@RequestParam("input") String name, Model model) {
		model.addAttribute("inputReq", name);
		String res = "hilll";
		try {
			System.out.println(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("result", res);
		return "script";
	}
}
