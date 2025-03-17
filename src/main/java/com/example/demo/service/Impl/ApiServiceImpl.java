package com.example.demo.service.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.CreateUserReq;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.dto.response.CreateUserDTO;
import com.example.demo.dto.response.GetAllUsersResp;
import com.example.demo.dto.response.GetUserByIdDTO;
import com.example.demo.dto.response.GetUserDTO;
import com.example.demo.dto.response.Users;
import com.example.demo.service.ApiService;
import com.example.demo.service.RESTServiceUtils;
import com.example.demo.utils.Constant;


@Service
public class ApiServiceImpl implements ApiService{

    private static final Logger logger = LogManager.getLogger(ApiServiceImpl.class);

    @Value("${api.jsonplaceholder.url}")
    private String apiUrl;

    @Autowired
    private RESTServiceUtils restServiceUtils;

    @Override
    public GetUserDTO getAllUser() {
        logger.info("Start getAllUser");

        GetUserDTO resp = new GetUserDTO();

        try {

            GetAllUsersResp usersList = restServiceUtils.getForObject(apiUrl, "/posts", GetAllUsersResp.class);

            if(resp != null){
                // resp.setData(usersList);
                resp.setCode(Constant.STATUS_SUCCESS_CODE);
                resp.setMessage(Constant.STATUS_SUCCESS_MESSAGE);
            }
            
        } catch (Exception e) {
            logger.info("getAllUser error:"+e.getMessage());
            resp.setCode(Constant.STATUS_FAIL_CODE);
            resp.setMessage(Constant.STATUS_FAIL_MESSAGE);
        }

        logger.info("End getAllUser");

        return resp;
    }

    @Override
    public GetUserByIdDTO getUserById(Long id) {
        logger.info("Start getUserById");

        GetUserByIdDTO resp = new GetUserByIdDTO();

        try {

            Users userObj = restServiceUtils.getForObject(apiUrl, "/posts/"+id, Users.class);

            if(resp != null){
                resp.setData(userObj);
                resp.setCode(Constant.STATUS_SUCCESS_CODE);
                resp.setMessage(Constant.STATUS_SUCCESS_MESSAGE);
            }
            
        } catch (Exception e) {
            logger.info("getAllUser error:"+e.getMessage());
            resp.setCode(Constant.STATUS_FAIL_CODE);
            resp.setMessage(Constant.STATUS_FAIL_MESSAGE);
        }

        logger.info("End getUserById");

        return resp;
    }

    @Override
    public CreateUserDTO createUser(CreateUserReq createUserReq) {
        logger.info("Start createUser");

        CreateUserDTO resp = new CreateUserDTO();

        try {

            Users userObject= restServiceUtils.postForObject(apiUrl, "/posts", createUserReq, Users.class);

            if(userObject != null){
                resp.setData(userObject);
                resp.setCode(Constant.STATUS_SUCCESS_CODE);
                resp.setMessage(Constant.STATUS_SUCCESS_MESSAGE);
            }
            
        } catch (Exception e) {
            logger.info("createUser error:"+e.getMessage());
            resp.setCode(Constant.STATUS_FAIL_CODE);
            resp.setMessage(Constant.STATUS_FAIL_MESSAGE);
        }

        logger.info("End createUser");

        return resp;
    }

    @Override
    public BaseResponse updateUser(CreateUserReq createUserReq) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public BaseResponse deleteUser(Long id) {
        logger.info("Start deleteUser");

        BaseResponse resp = new BaseResponse();

        try {

            restServiceUtils.getForObject(apiUrl, "/posts/"+id, CreateUserReq.class);

            resp.setCode(Constant.STATUS_SUCCESS_CODE);
            resp.setMessage(Constant.STATUS_SUCCESS_MESSAGE);
            
        } catch (Exception e) {
            logger.info("createUser error:"+e.getMessage());
            resp.setCode(Constant.STATUS_FAIL_CODE);
            resp.setMessage(Constant.STATUS_FAIL_MESSAGE);
        }

        logger.info("End deleteUser");

        return resp;
    }

    
    
}
