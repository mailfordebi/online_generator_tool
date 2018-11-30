package com.test.soapuui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.soapuui.client.SoapServiceClient;

@Controller
public class SoapUIClientController {

	@Autowired
	private SoapServiceClient soapServiceClient;

	@GetMapping("/soapclient")
	public String homeSoapClient() {
		return "soap_client";
	}

	@PostMapping("/callService")
	public String callService(@RequestParam("input") String name, Model model) {
		model.addAttribute("inputReq", name);
		String res = "";
		try {
			res = soapServiceClient.callService(name);
			System.out.println(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("result", res);
		return "soap_client";
	}

	@PostMapping("/processwsdl")
	public String processwsdl(@RequestParam("wsdlName") String wsdlName, Model model) {
		model.addAttribute("inputReq", wsdlName);
		String res = "";
		try {
			res = soapServiceClient.processwsdl(wsdlName);
			System.out.println(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("inputReq", res);
		return "soap_client";
	}
}