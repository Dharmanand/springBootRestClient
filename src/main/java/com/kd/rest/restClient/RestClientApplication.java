package com.kd.rest.restClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kd.rest.restClient.entity.Data;
import com.kd.rest.restClient.entity.Employee;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		getRxProductInfo();
//		getServe();
//		getStudents();
	}
	
	public void getServe() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String plainCreds = "Username:Password";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		
		headers.add("Authorization", "Basic " + base64Creds);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String resourceUrl = "http://applicationContextPath";
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity, String.class);
		
		System.out.println(response);
	}
	
	public void getRxProductInfo() throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String plainCreds = "Username:Password";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		
		headers.add("Authorization", "Basic " + base64Creds);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String resourceUrl = "http://applicationContextPath";
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<Data> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity, Data.class);
		
		System.out.println(response);
		System.out.println("apiResponse : -- > "+response.getBody().getRxSessionIdentifier());
		System.out.println(response.getBody().getOptional());
		System.out.println("recordsChoice : -- > "+((Map<String, Object>)((ArrayList<Object>)response.getBody().getOptional("recordsChoice")).get(0)).get("listedName"));
		System.out.println("serviceLines : -- > "+response.getBody().getOptional().get("serviceLines"));
		
	}
	
	public void getStudents() throws JsonParseException, JsonMappingException, IOException {
		final String uri = "http://localhost:8080/restClient/api/students";
	    RestTemplate restTemplate = new RestTemplate();
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode result = (JsonNode) restTemplate.getForObject(uri, JsonNode.class);
	    
	    List<Employee> stdList = mapper.readValue(
	    	    mapper.treeAsTokens(result), 
	    	    new TypeReference<List<Employee>>(){}
	    	);
	    
	     System.out.println(result);
	    for(Employee std:stdList) {
	    	System.out.println(std.getId());
	    	System.out.println(std.getName());
	    	System.out.println("-----------------------------");
	    }
	    
	}
	
}
