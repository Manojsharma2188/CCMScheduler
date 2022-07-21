package com.clientutil.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.annotation.PostConstruct;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clientutil.entity.ClientEndpoint;
import com.clientutil.entity.URL1;
import com.clientutil.entity.URLList;
import com.clientutil.service.ClientEndpointService;

@RestController
@RequestMapping("/url")
public class ClientEndpointController {

	@Autowired
	ClientEndpointService clientEndpointService;

	boolean append = true;

	static Logger logger = Logger.getLogger("com.clientutil.controller");
	/*
	 * @PostConstruct public List<ClientEndpoint> listEndpointUrl() {
	 * 
	 * List<ClientEndpoint> list = new ArrayList<>(); list =
	 * clientEndpointService.findAllEndpointUrl(); for (ClientEndpoint
	 * ClientEndpoint : list) { endpointMap.put(ClientEndpoint.getId(),
	 * ClientEndpoint.getEndpointurl()); } return list; }
	 * 
	 * @Scheduled(fixedRate = 1500) public void getHeadValue() throws
	 * SecurityException, IOException {
	 * clientEndpointService.getEndpointResult(endpointMap); }
	 */
	
	
	 @PostMapping(path= "/process", consumes = "application/json", produces = "application/json")
	    public StringBuffer addEmployee(@RequestBody URLList urllist) throws SecurityException, IOException
	    {
		    StringBuffer st = new StringBuffer();
		   
		    boolean append = true;			
		    FileHandler handler = new FileHandler("/Users/user/Documents/workspace-spring-tool-suite-4-4.12.0.RELEASE/ClientConnectivitySchdular/src/main/resources/Logs/ "+java.time.LocalDateTime.now()+"_App.log", append);
		   
			logger.addHandler(handler);
		//	SimpleFormatter formatter = new SimpleFormatter();  
			//handler.setFormatter(formatter);  
		    for(URL1 url1 : urllist.getURLList()) {
		    	 String str =null;
		    	 str =getStatusofURL(url1.toString());		    	
		    	 st.append(str);
		 
		 }
		 
		    return st;
		    
	    }


	private String getStatusofURL(String url1) {
		String result = "";
		int code = 200;
		JSONObject jsonObject = new JSONObject();
		try {
			URL siteURL = new URL(url1);
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(3000);
			connection.connect();

			code = connection.getResponseCode();
			if (code == 200) {
				result = "Code: " + code;
				jsonObject.put("Status_Code", code);
				jsonObject.put("Description", "Success hit to URL");
				
			} else {
				result = "Code: " + code;
				jsonObject.put("Status_Code", code);
				jsonObject.put("Description", connection.getContent());
			}
		} catch (Exception e) {
			result = "Wrong domain - Exception: " + e.getMessage();
			jsonObject.put("Description", e.getMessage());
		//	System.out.println("Exception Detail -: " + e);

		}
		logger.info(url1 + "\t\tStatus:" + result);
        return jsonObject.toString();
		
	}
	

}
