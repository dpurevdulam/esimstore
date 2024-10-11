package com.mn.esimstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mn.esimstore.service.EsimStoreService;

@RestController
@RequestMapping(path = "/esim")
public class EsimStoreController {
    @Autowired
    EsimStoreService esimStoreService;

    @GetMapping(value = "/token")
    public Object getToken() {
        return esimStoreService.getToken();
    }
}
