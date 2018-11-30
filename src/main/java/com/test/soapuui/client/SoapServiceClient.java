package com.test.soapuui.client;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.predic8.wsdl.Binding;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.Port;
import com.predic8.wsdl.PortType;
import com.predic8.wsdl.Service;
import com.predic8.wsdl.WSDLParser;
import com.predic8.wstool.creator.RequestTemplateCreator;
import com.predic8.wstool.creator.SOARequestCreator;
import com.test.sopauui.util.XmlFormatter;

import groovy.xml.MarkupBuilder;

@Qualifier("soapServiceClient")
public class SoapServiceClient extends WebServiceGatewaySupport {
	private final static String FTS_PRO_SOAP_URL = "http://dev.unity-payment.monamisoft.net:80/monamitech/nackupdate";

	public String callService(String request) throws Exception {
		request=request.replaceAll("\n",""); 
		request=request.replaceAll("\t","");
		request=request.replaceAll(">\\s*<", "><");
		System.out.println(request);
		String subString=request.substring(1, request.indexOf("Envelope"));
		System.out.println(subString);
		String pattern="<"+subString+"Body>(.+?)</"+subString+"Body>";
		System.out.println(pattern);
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(request);
		m.find();
		StreamSource source = new StreamSource(new StringReader(m.group(1)));
		StringWriter stringWriter = new StringWriter();
		StreamResult result = new StreamResult(stringWriter);
		WebServiceTemplate webServiceTemplate = getWebServiceTemplate();
		// webServiceTemplate.setMessageSender(webServiceMessageSender());
		webServiceTemplate.sendSourceAndReceiveToResult(FTS_PRO_SOAP_URL, source, result);
		String soapResponse = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/><SOAP-ENV:Body>";
		String response = stringWriter.toString().replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
		soapResponse = soapResponse + response + "</SOAP-ENV:Body></SOAP-ENV:Envelope>";
		soapResponse = new XmlFormatter().format(soapResponse);
		System.out.println(soapResponse);
		return soapResponse;

	}

	public WebServiceMessageSender webServiceMessageSender() {
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
		httpComponentsMessageSender.setConnectionTimeout(2000);
		httpComponentsMessageSender.setReadTimeout(2000);
		return httpComponentsMessageSender;
	}

	public String processwsdl(String request) throws Exception {
		List<String> soapRequests=new ArrayList<>();
		WSDLParser parser = new WSDLParser();
		Definitions wsdl = parser.parse(request);
		StringWriter writer = new StringWriter();
		SOARequestCreator creator = new SOARequestCreator(wsdl, new RequestTemplateCreator(),
				new MarkupBuilder(writer));
		for (Service s : wsdl.getServices()) {
			for (Port port : s.getPorts()) {
				Binding binding = port.getBinding();
				PortType portType = binding.getPortType();
				for (Operation op : portType.getOperations()) {
					creator.createRequest(port.getName(), op.getName(), binding.getName());
					System.out.println(writer);
					soapRequests.add(writer.toString());
					writer.getBuffer().setLength(0);
				}
			}
		}
		return soapRequests.get(0);
	}

}
