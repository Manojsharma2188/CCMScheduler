package com.clientutil.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clientutil.entity.ClientEndpoint;
import com.clientutil.repoistory.ClientEndpointRepoistory;

@Service
public class ClientEndpointService {
	
	static Logger logger = Logger.getLogger("com.clientutil.service");
	boolean append = true;
	
	
	@Autowired
	ClientEndpointRepoistory clientEndpointRepoistory;

	
	public List<ClientEndpoint> findAllEndpointUrl() {
		return clientEndpointRepoistory.findAll();
	}

	

	public void getEndpointResult(HashMap<Integer, String> endpointMap) throws SecurityException, IOException {
		boolean append = true;
		FileHandler handler = new FileHandler("/Users/user/Documents/workspace-spring-tool-suite-4-4.12.0.RELEASE/ClientConnectivitySchdular/src/main/resources/Logs/ "+java.time.LocalDate.now()+"_App.log", append);
		logger.addHandler(handler);
		
		for (Map.Entry<Integer,String>  endpintentry : endpointMap.entrySet()) {
           
		          String url = endpintentry.getValue();
		          getStatusofURL(url);
		          
    }
		logger.info("Task completed...");

		
	}



	private void getStatusofURL(String url) {
		
		String result = "";
		int code = 200;
		try {
			URL siteURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(3000);
			connection.connect();

			code = connection.getResponseCode();
			if (code == 200) {
				result = "-> Green <-\t" + "Code: " + code;
				;
			} else {
				result = "-> Yellow <-\t" + "Code: " + code;
			}
		} catch (Exception e) {
			result = "-> Red <-\t" + "Wrong domain - Exception: " + e.getMessage();
			System.out.println("Exception Detail -: " + e);

		}
		logger.info(url + "\t\tStatus:" + result);

	}
		
	
	
	 

}
