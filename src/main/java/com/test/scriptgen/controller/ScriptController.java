package com.test.scriptgen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.scriptgen.generator.ScriptGenerator;

@Controller
public class ScriptController {

	@Autowired
	private ScriptGenerator scriptGenerator;

	@GetMapping("/scriptgen")
	public String homeScriptGen() {
		return "script_gen";
	}

	@PostMapping("/generate")
	public String callService(@RequestParam("input") String xml, @RequestParam("dbType") String dbType,
			Model model) {
		model.addAttribute("inputReq", xml);
		String res = "hilll";
		List<String> strings = null;
		try {
			strings = scriptGenerator.generate(xml, dbType);
			res = strings.get(0);
			System.out.println(res);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("result", strings);
		return "script_gen";
	}
}
