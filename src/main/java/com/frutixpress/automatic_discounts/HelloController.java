package com.frutixpress.automatic_discounts;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @Autowired
    private DiscountManager manager;

    

    int time = 0;

	@RequestMapping("/")
	public String index() {

        return "Greetings from Spring Boot! Time: "+time+", Products: " + manager.getAllProducts();
    }
    
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        time++;
    }

}