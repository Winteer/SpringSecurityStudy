package com.winter.security.controller;

import com.winter.security.order.OrderInfo;
import com.winter.security.order.PriceInfo;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @ClassName : OrderController
 * @Description :
 * @Author : Winter
 * @Date: 2020-10-26 11:04
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private Logger logger = Logger.getLogger(this.getClass());
    private RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/add")
    public OrderInfo create(@RequestBody OrderInfo info) {
        PriceInfo priceInfo = restTemplate.getForObject("http://localhost:8883/prices/" + info.getProductId(), PriceInfo.class);
        logger.info("price is " + priceInfo.getPrice());
        return info;
    }

}
