package com.test.jsonformat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class JsonFormatController {
	@PostMapping("/format")
	public String format(@RequestParam("input") String json, Model model) {
		model.addAttribute("inputReq", json);
		String formattedJson = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			Object jsonFormat = mapper.readValue(json, Object.class);
			formattedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonFormat);
			System.out.println(formattedJson);

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("result", formattedJson);
		return "json_format";
	}
	
	@GetMapping("/jsonformat")
	public String homeScriptGen() {
		return "json_format";
	}
}
