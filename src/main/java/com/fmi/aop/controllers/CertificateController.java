package com.fmi.aop.controllers;

import com.fmi.aop.services.FibonacciNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1")
public class CertificateController {

    @Autowired
    private FibonacciNumbersService fibService;

    @PostMapping("/fibbonacci")
    public ResponseEntity<Object> getFibonnacciNumbers(){
        try{
            fibService.printAFibNumber(1, 1);
            return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
