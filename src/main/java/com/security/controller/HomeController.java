package com.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/normal")
    public ResponseEntity<String> normalUser() {
        return ResponseEntity.ok("Yes, I am normal user");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> adminUser() {
        return ResponseEntity.ok("Yes, I am admin user");
    }

    @GetMapping("/public")
    public ResponseEntity<String> publicUser() {
        return ResponseEntity.ok("Yes, I am public user");
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addOperation() {
        return ResponseEntity.ok("Add operation successful");
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'INTERMEDIATE')")
    public ResponseEntity<String> updateOperation() {
        return ResponseEntity.ok("Update operation successful");
    }

    @GetMapping("/view")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'INTERMEDIATE')")
    public ResponseEntity<String> viewOperation() {
        return ResponseEntity.ok("View operation successful");
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteOperation() {
        return ResponseEntity.ok("Delete operation successful");
    }

}
