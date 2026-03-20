package com.stucrud.StuCrud.controller;

import com.stucrud.StuCrud.entity.Shoe;
import com.stucrud.StuCrud.repo.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/shoe")
public class ShoeController {

    @Autowired
    ShoeRepository shr;

    @PostMapping("/insertShoe")
    public ResponseEntity<String> insertShoe(@RequestBody Shoe sh){
        shr.save(sh);
        return  ResponseEntity.ok("Success");

    }


    @GetMapping("/getShoes")
    public List<Shoe> getShoes(){
        return  shr.findAll();
    }
}
