package com.test.htmleditor.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTMLEditorController {
	@RequestMapping("/richtext")
	public ResponseEntity<String> greeting()
			throws IOException {
		File fi = new File(getClass().getClassLoader().getResource("test.txt").getFile());
		InputStream is = new FileInputStream(fi);
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		String line = buf.readLine();
		StringBuilder sb = new StringBuilder();
		while (line != null) {
			sb.append(line).append("\n");
			line = buf.readLine();
		}
		String fileAsString = sb.toString();
		buf.close();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("content-type", "text/javascript;charset=UTF-8");
		return ResponseEntity.ok().headers(responseHeaders).body(fileAsString);
	}

	@RequestMapping("/html-editor")
	public String edit() {
		return "edit";
	}

}