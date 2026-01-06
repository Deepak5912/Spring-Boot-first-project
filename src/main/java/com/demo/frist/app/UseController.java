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

    @PostMapping  // for create the data
    public String createUser(@RequestBody User user) {
        System.out.println(user.getEMail());
        System.out.println(user.getName());
        userDb.putIfAbsent(user.getId(), user);
        return "User created successfully";
    }

    @PutMapping  // for update the data
    public String updateUser(@RequestBody User user) {
        System.out.println(user.getId());
        System.out.println(user.getEMail());
        System.out.println(user.getName());

        if (userDb.containsKey(user.getId()))
            userDb.put(user.getId(), user);
        return "user updated successfully";

    }






}

