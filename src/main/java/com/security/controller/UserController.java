package com.security.controller;

import com.security.dto.UserSecurityDto;
import com.security.service.UserSecurityDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserSecurityDtoService userService;

    @Autowired
    public UserController(UserSecurityDtoService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserSecurityDto> addUser(@RequestBody UserSecurityDto user) {
        UserSecurityDto savedUser = userService.saveOrUpdate(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'INTERMEDIATE')")
    public ResponseEntity<UserSecurityDto> updateUser(@RequestBody UserSecurityDto user) {
        UserSecurityDto updatedUser = userService.saveOrUpdate(user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/view")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'INTERMEDIATE')")
    public ResponseEntity<List<UserSecurityDto>> viewUsers() {
        List<UserSecurityDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResponseEntity.ok("Delete operation successful");
    }

    @GetMapping("/getByEmail/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserSecurityDto> getUserByEmail(@PathVariable String email) {
        Optional<UserSecurityDto> user = userService.getByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
