package sample.wsdl;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test1 {

	public static void main(String[] args) {
		/*int[] recentScores = { 77, 72, 23, 57, 54, 36, 74, 17 };
		Player cricketer = new Player("Virat", "cricket", 25, 121, recentScores);
		ObjectMapper mapper = new ObjectMapper();
		try {
			 String test = "{\"age\":29,\"messages\":[\"msg 1\",\"msg 2\",\"msg 3\"],\"name\":\"mkyong\"}";
			  Object json = mapper.readValue(test, Object.class);	
			  System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		 try {
		       ObjectMapper mapper = new ObjectMapper();
		       String test = "{\"age\":29,\"messages\":[\"msg 1\",\"msg 2\",\"msg 3\"],\"name\":\"mkyong\"";
		       mapper.readTree(test);
		       System.out.println("valid");
		       
		       
		    } catch (IOException e) {
		      System.out.println(e.getMessage().substring(0, e.getMessage().indexOf(":")));
		    }
		

	}

}
