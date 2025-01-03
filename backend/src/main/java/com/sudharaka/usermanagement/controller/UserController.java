package com.sudharaka.usermanagement.controller;

import com.sudharaka.usermanagement.exception.SWException;
import com.sudharaka.usermanagement.model.dto.UserReqDto;
import com.sudharaka.usermanagement.model.dto.UserRespDto;
import com.sudharaka.usermanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "${base-url.context}/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserRespDto> getUserDetails(@PathVariable long id) throws SWException {
        log.info("Received request to get user details");
        return new ResponseEntity<>(userService.getUserDetails(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserRespDto>> getAllUsersDetails(){
        log.info("Received request to get all details");
        return new ResponseEntity<>(userService.getAllUsersDetails(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createNewUser(@RequestBody @Valid UserReqDto userCreateReqDto){
        log.info("Received request to create new user");
        userService.createNewUser(userCreateReqDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable long id, @RequestBody @Valid UserReqDto userUpdateReqDto) throws SWException {
        log.info("Received request to update new user");
        userService.updateUser(id, userUpdateReqDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
