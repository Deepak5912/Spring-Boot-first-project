package com.demo.frist.app;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")

public class UseController {
    private final Map<Integer, User> userDb = new HashMap<>();



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

        userDb.put(user.getId(), user);
        return ResponseEntity.status(HttpStatus.OK).body("user updated successfully");



    }
    // for deletelion of the data

    // callilng like this http://localhost:8080/user/1, where 1 is the id to be deleted
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        if(!userDb.containsKey(id))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();   // if no any body to return then use build()
        userDb.remove(id);
        System.out.println("id deleted successfully");
        System.out.println("id delete successfully");
        //return ResponseEntity.status(HttpStatus.OK).body("id deleted successfully");
                    // return  ResponseEntity.ok("id deleted successfully");// another way of writing the same  above line
        // if you  want to retun the status code content not found then use this line
        return  ResponseEntity.noContent().build();

    }
    //  for single user get by id
    // calling like this http://localhost:8080/user/1 , where 1 is the id to be fetched ///
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") int id) {
        User user = userDb.get(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

// for multiple user get by id
//    @GetMapping("/{userId}")
//    public ResponseEntity<User> getUserOrder(@PathVariable("userId") int id,@PathVariable int orderId) {
//        System.out.println("order id is "+ orderId);
//        User user = userDb.get(id);
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(user);
//    }

    // for request param :- means ?key=value request paramter used to filter the data and for sorting the data
    // calling like this http://localhost:8080/user/query?name=John Doe
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam(value = "name", required = false, defaultValue = "lily") String name,
        @RequestParam(value = "email", required = false, defaultValue = "dk@gmail.com") String email) {
        System.out.println("search name: " + name);
        List<User> result = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) {
            result.addAll(userDb.values());
        } else {
            String q = name.trim().toLowerCase();
            for (User u : userDb.values()) {
                if (u.getName() != null && u.getName().toLowerCase().contains(q)) {
                    result.add(u);
                } else if (u.getEMail() != null && u.getEMail().toLowerCase().contains(q)) {
                    result.add(u);
                }
            }
        }
        return ResponseEntity.ok(result);

    }
    @GetMapping("/info")
    public String getInfo(@RequestHeader("user-Agent") String userAgent) {

        return "User-Agent: " + userAgent;
    }


}
