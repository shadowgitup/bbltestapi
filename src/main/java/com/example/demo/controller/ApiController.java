package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.CreateUserReq;
import com.example.demo.dto.request.UpdateUserReq;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.service.ApiService;
import com.example.demo.utils.Constant;

import jakarta.validation.Valid;

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/users")
	public ResponseEntity<?>  listAllUsers() {

		return ResponseEntity.ok().body(apiService.getAllUser());
	}

    @GetMapping("/users/{id}")
	public ResponseEntity<?>  GetUsersById(@PathVariable("id") Long id) {

		return ResponseEntity.ok().body(apiService.getUserById(id));
	}

    @PostMapping("/users")
	public ResponseEntity<?>  createUsers(@Valid @RequestBody CreateUserReq createUserReq, BindingResult result) {

        // Check if there are validation errors
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            result.getAllErrors().forEach(error -> {
                errorMessage.append(error.getDefaultMessage()).append(" ");
            });

            BaseResponse res = new BaseResponse();
            res.setCode(Constant.STATUS_FAIL_CODE);
            res.setMessage(errorMessage.toString());
            return ResponseEntity.badRequest().body(res);
        }

		return ResponseEntity.ok().body(apiService.createUser(createUserReq));
	}

    @PutMapping("/users")
	public ResponseEntity<?>  updateUsers(@Valid @RequestBody UpdateUserReq updateUserReq, BindingResult result) {
        
        // Check if there are validation errors
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            result.getAllErrors().forEach(error -> {
                errorMessage.append(error.getDefaultMessage()).append(" ");
            });

            BaseResponse res = new BaseResponse();
            res.setCode(Constant.STATUS_FAIL_CODE);
            res.setMessage(errorMessage.toString());
            return ResponseEntity.badRequest().body(res);
        }

		return ResponseEntity.ok().body(apiService.updateUser(updateUserReq));
	}

    @DeleteMapping("/users/{id}")
	public ResponseEntity<?>  deleteUser(@PathVariable("id") Long id) {

		return ResponseEntity.ok().body(apiService.deleteUser(id));
	}

}
