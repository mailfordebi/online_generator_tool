package com.test.soapuui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.soapuui.client.SoapServiceClient;
import com.test.sopauui.util.ServiceDetails;

@Controller
public class SoapUIClientController {
	ServiceDetails serviceDetails = null;

	@Autowired
	private SoapServiceClient soapServiceClient;

	@GetMapping("/soapclient")
	public String homeSoapClient() {
		return "soap_client";
	}

	@PostMapping("/callService")
	public String callService(@RequestParam("soapReq") String name, Model model,
			@RequestParam("endPoint") String endPoint) {
		model.addAttribute("inputReq", name);
		model.addAttribute("serviceMapDetails", serviceDetails.getServiceMapDetails());
		String res = "";
		try {
			res = soapServiceClient.callService(name, endPoint);
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
		// model.addAttribute("inputReq", wsdlName);
		try {
			serviceDetails = soapServiceClient.processwsdl(wsdlName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("serviceNames", serviceDetails.getServiceNames());
		model.addAttribute("operationNames", serviceDetails.getOperationNames());
		model.addAttribute("serviceMapDetails", serviceDetails.getServiceMapDetails());
		return "soap_client";
	}
}