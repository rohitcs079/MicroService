package com.example;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.model.Manufacturer;

@RestController
public class ManufacturerMSLBController {
	
	@Autowired
	private LoadBalancerClient client;
	
	@RequestMapping(value="/",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getData()
	{
		ServiceInstance serviceInstance =
		client.choose("manufacturer-microservice");
		
		System.out.println(serviceInstance.getPort());
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(String.valueOf(serviceInstance.getPort()),HttpStatus.OK);
	
		Resources<Manufacturer> resources = null;
		
		try {
			 restTemplate.getForObject(new URI(serviceInstance.getUri().toString().concat("/manufacturer")), (Object.class));
		} catch (RestClientException | URISyntaxException e) {
			
			e.printStackTrace();
		}
		
	 return responseEntity;	
	}
	
	
	/*@RequestMapping(value ="/getByDate",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Resources<Manufacturer> getByDate(@RequestParam("startDate")@DateTimeFormat(iso=ISO.DATE) Date date){
		return client.findByDateBefore(date);
	}
*/
}
