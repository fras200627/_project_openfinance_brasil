package com.tican.oauth2.users.controller;

import com.tican.oauth2.users.api.server.handler.DomainApiDelegate;
import com.tican.oauth2.users.api.server.model.Code202Template;
import com.tican.oauth2.users.api.server.model.UserCreateRecord;
import com.tican.oauth2.users.api.server.model.UserResponse;
import com.tican.oauth2.users.api.server.model.UserUpdateRecord;
import com.tican.oauth2.users.service.UserService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
@SecurityScheme(type = SecuritySchemeType.HTTP,
                name = "bearerAuth",
                scheme = "bearer",
                bearerFormat = "JWT",
                in = SecuritySchemeIn.HEADER)
public class DomainApiControllerImpl implements DomainApiDelegate {

    @Autowired private
    HttpServletRequest request;

    @Autowired private
    UserService service;

    @Override
    public ResponseEntity<UserResponse> postCreateNewUser(UserCreateRecord userCreateRecord) {
        UserResponse result = service.save(userCreateRecord);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();

        return ResponseEntity
                .created(uriBuilder.path(request.getServletPath() + "/findById/{id}").buildAndExpand(result.getId()).toUri())
                .body(result);
    }

    @Override
    public ResponseEntity<UserResponse> putUpdateUser(UserUpdateRecord userUpdateRecord) {
        UserResponse result = service.replace(userUpdateRecord);

        return ResponseEntity
                .accepted()
                .body(result);
    }

    @Override
    public ResponseEntity<Code202Template> putChangeUserPassord(Long id, String passwordOld, String passwordNew) {
        String result = service.changePassword(id, passwordOld, passwordNew);

        return ResponseEntity
                .accepted()
                .body(new Code202Template.Builder()
                        .code(202)
                        .message(result)
                        .build()
                );
    }

    @Override
    public ResponseEntity<Code202Template> putChangeUserStatus(Long id, String status) {
        String result = service.changeStatus(id, status);

        return ResponseEntity
                .accepted()
                .body(new Code202Template.Builder()
                        .code(202)
                        .message(result)
                        .build()
                );
    }

    @Override
    public ResponseEntity<Code202Template> deleteUserLogical(Long id, String reason) {
        String result = service.deleteLogical(id, reason);

        return ResponseEntity
                .accepted()
                .body(new Code202Template.Builder()
                        .code(202)
                        .message(result)
                        .build()
                );
    }

    @Override
    public ResponseEntity<Code202Template> deleteUserPhysical(Long id, String reason) {
        String result = service.deletePhysical(id, reason);

        return ResponseEntity
                .accepted()
                .body(new Code202Template.Builder()
                        .code(202)
                        .message(result)
                        .build()
                );
    }

}
