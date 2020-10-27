package com.winter.security.controller;

import com.winter.security.price.PriceInfo;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @ClassName : PriceController
 * @Description :
 * @Author : Winter
 * @Date: 2020-10-26 11:20
 */
@RestController
@RequestMapping("/prices")
public class PriceController {
    private Logger logger = Logger.getLogger(this.getClass());

    @GetMapping("/{id}")
    public PriceInfo getPrice(@PathVariable Long id) {
        logger.info("productId is " + id);
        PriceInfo info = new PriceInfo();
        info.setId(id);
        info.setPrice(new BigDecimal(100));
        return info;
    }

}
