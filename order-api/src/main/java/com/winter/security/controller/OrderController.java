package com.winter.security.controller;

import com.winter.security.order.OrderInfo;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName : OrderController
 * @Description : 订单服务Controller
 * @Author : Winter
 * @Date: 2020-10-26 11:04
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private Logger logger = Logger.getLogger(this.getClass());
//    private RestTemplate restTemplate = new RestTemplate();

    //2020-10-29 在资源服务器中获取用户信息
    //之前是拿到用户名
    //@AuthenticationPrincipal 拿到了用户名。目前写String，拿到用户名。
//    @PostMapping("/add")
//    public OrderInfo create(@RequestBody OrderInfo info, @AuthenticationPrincipal User user) {
//        logger.info("Username: " + user.getUsername()+"Userid = " + user.getId());
////        PriceInfo priceInfo = restTemplate.getForObject("http://localhost:8883/prices/" + info.getProductId(), PriceInfo.class);
////        logger.info("price is " + priceInfo.getPrice());
//        return info;
//    }

    @PostMapping("/add")
//    public OrderInfo create(@RequestBody OrderInfo info, @AuthenticationPrincipal(expression = "#this.id") Long userId) { //删除了oauth的依赖
    public OrderInfo create(@RequestBody OrderInfo info, @RequestHeader String username) {
        logger.info("UserName  = " + username);
        return info;
    }

    @GetMapping("/info/{id}")
    public OrderInfo getInfo(@PathVariable Long id) {
        logger.info("orderid is " + id);
        return new OrderInfo();
    }

}
