package com.demo.frist.app;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println(user.getEMail());
        System.out.println(user.getName());
        userDb.putIfAbsent(user.getId(), user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user) ;
    }

    @PutMapping  // for update the data
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        System.out.println(user.getId());
        System.out.println(user.getEMail());
        System.out.println(user.getName());
        if(!userDb.containsKey(user.getId()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");

        if (userDb.containsKey(user.getId()))
            userDb.put(user.getId(), user);
        return ResponseEntity.status(HttpStatus.OK).body("user updated successfully");



    }
    // for deletelion of the data

    // callilng like this http://localhost:8080/user/1, where 1 is the id to be deleted
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        if(!userDb.containsKey(id))
                return "user not found";
        userDb.remove(id);
        System.out.println("id deleted successfully");
        return "id deleted successfully";

    }





}

