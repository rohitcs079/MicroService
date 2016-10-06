package com.example.dao;

import java.util.Date;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Manufacturer;




/**
 * This is a feign client to interact with micro services
 * @author admin
 *
 */

@FeignClient("manufacturer-microservice")/* Name of the micro service to which feign client will interact*/
public interface ManufacturerFeignClient {
	
	@RequestMapping(value="/manufacturer",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
		Resources<Manufacturer> findAll();
	
	@RequestMapping(value="/manufacturer/search/findByStartDateBefore",method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	Resources<Manufacturer> findByDateBefore(@RequestParam("date")@DateTimeFormat(iso = ISO.DATE) Date date);

}
