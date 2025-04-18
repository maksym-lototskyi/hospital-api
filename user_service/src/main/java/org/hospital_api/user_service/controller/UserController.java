package org.hospital_api.user_service.controller;

import org.hospital_api.user_service.dto.UserReqDto;
import org.hospital_api.user_service.dto.UserRespDto;
import org.hospital_api.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRespDto> registerUser(@RequestBody UserReqDto userReqDto){
        return new ResponseEntity<>(userService.createUser(userReqDto), HttpStatus.OK);
    }
    /*@GetMapping("/fetch/{email}")
    public ResponseEntity<UserRespDto> getUser(@PathVariable String email) {
        UserRespDto response = userService.getUserByEmail(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/
    @GetMapping("/fetch/{id}")
    public ResponseEntity<UserRespDto> getUser(@PathVariable Long id) {
        UserRespDto response = userService.getUserById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
