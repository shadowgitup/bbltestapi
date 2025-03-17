package com.example.demo.service;

import com.example.demo.dto.request.CreateUserReq;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.dto.response.CreateUserDTO;
import com.example.demo.dto.response.GetUserByIdDTO;
import com.example.demo.dto.response.GetUserDTO;

public interface ApiService {

    public GetUserDTO getAllUser();
    public GetUserByIdDTO getUserById(Long id);
    public CreateUserDTO createUser(CreateUserReq createUserReq);
    public BaseResponse updateUser(CreateUserReq createUserReq);
    public BaseResponse deleteUser(Long id);
}
