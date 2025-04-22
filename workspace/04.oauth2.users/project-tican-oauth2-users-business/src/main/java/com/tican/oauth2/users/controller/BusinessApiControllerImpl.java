package com.tican.oauth2.users.controller;

import com.tican.lib.security.profiles.CanReadUsers;
import com.tican.lib.security.profiles.CanRootUsers;
import com.tican.lib.security.profiles.CanWriteUsers;
import com.tican.oauth2.users.api.server.handler.BusinessApiDelegate;
import com.tican.oauth2.users.api.server.model.UserResponse;
import com.tican.oauth2.users.api.server.model.UserResponsePageable;
import com.tican.oauth2.users.mapper.UserBusinessMapper;
import com.tican.oauth2.users.service.UserService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
                name = "bearerAuth",
                scheme = "bearer",
                bearerFormat = "JWT",
                in = SecuritySchemeIn.HEADER)
public class BusinessApiControllerImpl implements BusinessApiDelegate {

    @Autowired
    private UserService service;

    @Override @CanRootUsers @CanWriteUsers @CanReadUsers
    public ResponseEntity<UserResponse> getFindById(Integer userId) {
        return new ResponseEntity<>(service.findById(Long.valueOf(userId)),
                                    HttpStatus.OK);
    }

    @Override @CanRootUsers @CanWriteUsers @CanReadUsers
    public ResponseEntity<UserResponse> getFindByName(String userName) {
        return new ResponseEntity<>(service.findByUserName(userName),
                HttpStatus.OK);
    }

    @Override @CanRootUsers @CanWriteUsers @CanReadUsers
    public ResponseEntity<UserResponse> getFindByEmail(String userEmail) {
        return new ResponseEntity<>(service.findByEmail(userEmail),
                HttpStatus.OK);
    }

    @Override @CanRootUsers @CanWriteUsers @CanReadUsers
    public ResponseEntity<List<UserResponse>> getFindAll() {
        return new ResponseEntity<>(service.findAll(),
                HttpStatus.OK);
    }

    @Override @CanRootUsers @CanWriteUsers @CanReadUsers
    public ResponseEntity<UserResponsePageable> getFindByPageable(Long pageNumber, Long pageSize,
                                                                  String pageSortField, String pageSortOrder) {
        return new ResponseEntity<>(service.findByPageable(pageNumber,
                                                        pageSize,
                                                        pageSortField,
                                                        pageSortOrder),
                                                        HttpStatus.OK);
    }







    @Override @CanRootUsers @CanWriteUsers @CanReadUsers
    public ResponseEntity<UserResponsePageable> getFindByFilters(Long pageNumber, Long pageSize,
                                                                 String pageSortField, String pageSortOrder,
                                                                 String userName, String userEmail,
                                                                 String userType, String userStatus) {
        return new ResponseEntity<>(service.findByFilters(pageNumber,
                pageSize,
                pageSortField,
                pageSortOrder,
                userName,
                userEmail,
                userType,
                userStatus),
                HttpStatus.OK);
    }





}
