package com.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.model.Manufacturer;

@RestController
public class OrderMSController {
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value="/mfrPost",method=RequestMethod.GET)
	public ResponseEntity<String> getData()
	{
		List<ServiceInstance> serviceList = client.getInstances("manufacturer-microservice");
		
		if(serviceList != null && serviceList.size()>0)
		{
			URI uri  = serviceList.get(0).getUri();
			
			if(uri != null)
			{
				Manufacturer manufacturer1 = new Manufacturer(20, new Date(),"MS1");
				Manufacturer manufacturer2 = new Manufacturer(21, new Date(),"MS2");
				
				List<Manufacturer> mrfList = new LinkedList<>();
				mrfList.add(manufacturer1);
				mrfList.add(manufacturer2);
				
				
					try {
						return (new RestTemplate()).postForEntity(new URI(uri.toString().concat("/manufacturer")), mrfList.get(0), String.class);
					} catch (RestClientException  | URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		}
		return null;
		
	}

}
