package com.collectifjadore.api.controllers;

import com.collectifjadore.api.models.User;
import com.collectifjadore.api.payload.request.ResetPassword;
import com.collectifjadore.api.payload.request.UpdateUserInfo;
import com.collectifjadore.api.payload.response.MessageResponse;
import com.collectifjadore.api.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api2")
public class MainController {
    @Autowired
    UserRepository userRepository;


    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserInfo user, @PathVariable("id") String id) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setFirstName(user.getFirstName());
            _user.setLastName(user.getLastName());
            _user.setEmail(user.getEmail());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user/resetpassword")
    public ResponseEntity<?> resetPasswordFromBo(@Valid @RequestBody ResetPassword resetPassword) {
        Optional<User> userData = userRepository.findByUsername(resetPassword.getUsername());
        if (userData != null) {
            User user = userData.get();
            BCryptPasswordEncoder decoder = new BCryptPasswordEncoder();
            if (decoder.matches(resetPassword.getOldPassword(), user.getPassword())) {
                user.setPassword(encoder.encode(resetPassword.getNewPassword()));
                userRepository.save(user);
                return ResponseEntity.ok(new MessageResponse("User password updated successfully!"));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Your current password is incorrect"));
            }
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
        }
    }

 


}
