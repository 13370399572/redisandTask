package com.test.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "PROVIDER")
public interface ConsumerDao {
	
	@PostMapping("/provider/getName")
	public String getNmae();
    
}
