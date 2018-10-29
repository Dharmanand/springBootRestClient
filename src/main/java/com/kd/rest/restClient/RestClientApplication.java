package com.kd.rest.restClient;

import java.util.Collections;

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

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		getServe();
	}
	
	public void getServe() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String plainCreds = "rxdsl:Cntl#2017";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		
		headers.add("Authorization", "Basic " + base64Creds);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		String resourceUrl = "https://cxg7d.test.qintra.com/test2/E/N/401RXMicro/services/rxProductInfo?etn=1000000191";
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity, String.class);
		
		System.out.println(response);
	}
	
}
