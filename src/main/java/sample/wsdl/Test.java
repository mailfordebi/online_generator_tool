package sample.wsdl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, SOAPException {
		
		/*List<String> soapRequests=new ArrayList<>();
		WSDLParser parser = new WSDLParser();
		Definitions wsdl = parser.parse("http://dev.unity-payment.monamisoft.net/monamitech/NackUpdate.wsdl");
		StringWriter writer = new StringWriter();
		SOARequestCreator creator = new SOARequestCreator(wsdl, new RequestTemplateCreator(),
				new MarkupBuilder(writer));
		for (Service s : wsdl.getServices()) {
			for (Port port : s.getPorts()) {
				Binding binding = port.getBinding();
				PortType portType = binding.getPortType();
				for (Operation op : portType.getOperations()) {
					creator.createRequest(port.getName(), op.getName(), binding.getName());
					soapRequests.add(writer.toString());
					writer.getBuffer().setLength(0);
				}
			}
		}
		System.out.println(soapRequests);*/
		
		String str = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
				"    <SOAP-ENV:Header/>\r\n" + 
				"    <SOAP-ENV:Body>\r\n" + 
				"        <ns2:NackUpdateResponse xmlns:ns2=\"http://services.monamitech.com/NackUpdate/\">\r\n" + 
				"            <ns2:response>\r\n" + 
				"                <ns2:header>\r\n" + 
				"                    <ns2:RequestRef>WNAK221018000008</ns2:RequestRef>\r\n" + 
				"                    <ns2:Action>CB103NAK</ns2:Action>\r\n" + 
				"                    <ns2:RequestDate>2018-10-22T15:45:37.290</ns2:RequestDate>\r\n" + 
				"                    <ns2:ChannelID>ECH</ns2:ChannelID>\r\n" + 
				"                    <ns2:RequestType>NACK</ns2:RequestType>\r\n" + 
				"                </ns2:header>\r\n" + 
				"                <ns2:body>\r\n" + 
				"                    <ns2:TxnRef>TRX221018000002</ns2:TxnRef>\r\n" + 
				"                    <ns2:Status>0</ns2:Status>\r\n" + 
				"                    <ns2:ErrorDescription/>\r\n" + 
				"                </ns2:body>\r\n" + 
				"            </ns2:response>\r\n" + 
				"        </ns2:NackUpdateResponse>\r\n" + 
				"    </SOAP-ENV:Body>\r\n" + 
				"</SOAP-ENV:Envelope>";
		str=str.replaceAll("\n",""); 
		str=str.replaceAll("\t","");
		str=str.replaceAll(">\\s*<", "><");
		System.out.println(str.substring(1, str.indexOf("Envelope")));
		System.out.println(str);
		//Pattern p = Pattern.compile(Pattern.quote("<SOAP-ENV:Body>") + "(.*?)" + Pattern.quote("</SOAP-ENV:Body>"));
		 Pattern p = Pattern.compile("<SOAP-ENV:Body>(.+?)</SOAP-ENV:Body>");
		Matcher m = p.matcher(str);
		while (m.find()) {
		  System.out.println(m.group(1));
		}
		
	}
		
}
