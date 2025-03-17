package com.example.demo.service.Impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.CreateUserReq;
import com.example.demo.dto.request.UpdateUserReq;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.dto.response.CreateUserDTO;
import com.example.demo.dto.response.GetUserByIdDTO;
import com.example.demo.dto.response.GetUserDTO;
import com.example.demo.dto.response.UpdateUserDTO;
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

            List<Users>  listUsers = restServiceUtils.postForListObject(apiUrl, Constant.ENDPOINT_USERS,null,Users.class);

            if(resp != null){
                resp.setData(listUsers);
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

            Users userObj = restServiceUtils.getForObject(apiUrl, Constant.ENDPOINT_USERS_WITH_PARAM+id, Users.class);

            if(resp != null){
                resp.setData(userObj);
                resp.setCode(Constant.STATUS_SUCCESS_CODE);
                resp.setMessage(Constant.STATUS_SUCCESS_MESSAGE);
            }
            
        } catch (Exception e) {
            logger.info("getUserById error:"+e.getMessage());
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

            Users userObject= restServiceUtils.postForObject(apiUrl, Constant.ENDPOINT_USERS, createUserReq, Users.class);

            if(userObject != null){
                resp.setData(userObject);
                resp.setCode(Constant.STATUS_SUCCESS_CODE);
                resp.setMessage(Constant.STATUS_SUCCESS_MESSAGE);
            }else{
                resp.setCode(Constant.STATUS_FAIL_CODE);
                resp.setMessage(Constant.STATUS_FAIL_CREATE);
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
    public BaseResponse updateUser(UpdateUserReq updateUserReq) {
        logger.info("Start updateUser");

        UpdateUserDTO resp = new UpdateUserDTO();

        try {

            Users userObj = restServiceUtils.updateForObject(apiUrl, Constant.ENDPOINT_USERS_WITH_PARAM+updateUserReq.getId(),updateUserReq, Users.class);
            if(userObj != null){
                resp.setData(userObj);
                resp.setCode(Constant.STATUS_SUCCESS_CODE);
                resp.setMessage(Constant.STATUS_SUCCESS_MESSAGE);
            }else{
                resp.setCode(Constant.STATUS_FAIL_CODE);
                resp.setMessage(Constant.STATUS_FAIL_UPDATE);
            }
            
        } catch (Exception e) {
            logger.info("updateUser error:"+e.getMessage());
            resp.setCode(Constant.STATUS_FAIL_CODE);
            resp.setMessage(Constant.STATUS_FAIL_MESSAGE);
        }

        logger.info("End updateUser");

        return resp;
    }

    @Override
    public BaseResponse deleteUser(Long id) {
        logger.info("Start deleteUser");

        BaseResponse resp = new BaseResponse();

        try {

            restServiceUtils.deleteForObject(apiUrl, Constant.ENDPOINT_USERS_WITH_PARAM+id, id);

            resp.setCode(Constant.STATUS_SUCCESS_CODE);
            resp.setMessage(Constant.STATUS_SUCCESS_MESSAGE);
            
        } catch (Exception e) {
            logger.info("deleteUser error:"+e.getMessage());
            resp.setCode(Constant.STATUS_FAIL_CODE);
            resp.setMessage(Constant.STATUS_FAIL_MESSAGE);
        }

        logger.info("End deleteUser");

        return resp;
    }

    
    
}
