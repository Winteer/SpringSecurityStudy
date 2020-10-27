package com.winter.security.controller;


import com.winter.security.model.Master;
import com.winter.security.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName : MasterController  //类名
 * @Description : 雇主Controller  //描述
 * @Author : Winter  //作者
 * @Date: 2020-08-25 15:05  //时间
 */
@RestController
@RequestMapping("/master")
public class MasterController {

    @Autowired
    private MasterRepository masterRepository;

    @GetMapping("/findById")
    public Master findById(int id){
        return masterRepository.findByMaId(id);
    }


    @PostMapping("/save")
    public void save(@RequestBody Master master) {
        masterRepository.save(master);
    }


}
