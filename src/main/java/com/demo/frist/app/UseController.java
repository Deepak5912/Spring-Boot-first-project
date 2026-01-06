package com.demo.frist.app;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")

public class UseController {
    private Map<Integer, User> userDb = new HashMap<>();


    @GetMapping
    public Map<Integer, User> getUser() {
        return userDb;
    }



}

