package com.test.jsonvalidate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.jsonvalidation.util.ValidationUtils;

@Controller
public class JsonValidateController {
	@PostMapping("/validate")
	public String format(@RequestParam("jsonData") String jsonInput, @RequestParam("jsonSchema") String jsonSchema,
			Model model) {
		model.addAttribute("jsonData", jsonInput);
		model.addAttribute("jsonSchema", jsonSchema);
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.readTree(jsonInput);
			mapper.readTree(jsonSchema);
			ValidationUtils.isJsonValid(jsonSchema, jsonInput);

		} catch (Exception e) {
			model.addAttribute("errorInfo", e.getMessage().substring(0, e.getMessage().indexOf(":")));
			model.addAttribute("error", "error");
			return "json_validate";
		}
		model.addAttribute("isValid", "Valid");
		return "json_validate";
	}

	@GetMapping("/jsonvalidate")
	public String homeScriptGen() {
		return "json_validate";
	}
}
