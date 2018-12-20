package com.test.jsonvalidate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JsonValidateController {
	@PostMapping("/validate")
	public String format(@RequestParam("jsonInputReq") String jsonInput,
			@RequestParam("jsonSchemaReq") String jsonSchema, Model model) {
		model.addAttribute("jsonInput", jsonInput);
		model.addAttribute("jsonSchema", jsonSchema);
		return "json_format";
	}

	@GetMapping("/jsonvalidate")
	public String homeScriptGen() {
		return "json_validate";
	}
}
