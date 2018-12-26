package com.test.xmlformat.controller;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class XMLFormatController {
	@PostMapping("/formatxml")
	public String format(@RequestParam("input") String json, Model model) {
		model.addAttribute("inputReq", json);
		String formattedXML = "";
		try {
			formattedXML = prettyFormat(json, "2");
			System.out.println(formattedXML);

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("result", formattedXML);
		return "xml_format";
	}
	
	@GetMapping("/xmlformat")
	public String homeScriptGen() {
		return "xml_format";
	}
	
	public static String prettyFormat(String input, String indent) {
		Source xmlInput = new StreamSource(new StringReader(input));
		StringWriter stringWriter = new StringWriter();
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", indent);
			transformer.transform(xmlInput, new StreamResult(stringWriter));

			return stringWriter.toString().trim();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
