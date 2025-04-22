package com.tican.oauth2.users.service;

import com.tican.lib.amqp.service.MessageService;
import com.tican.lib.handlers.exception.BadRequestExceptionHandler;
import com.tican.oauth2.users.api.server.model.UserCreateRecord;
import com.tican.oauth2.users.api.server.model.UserResponse;
import com.tican.oauth2.users.api.server.model.UserUpdateRecord;
import com.tican.oauth2.users.domain.*;
import com.tican.oauth2.users.entity.UserEntity;
import com.tican.oauth2.users.mapper.UserDomainMapper;
import com.tican.oauth2.users.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Service
public class UserService {

    @Autowired private
    HttpServletRequest request;
    
    @Autowired private 
    UserRepository repository;

    @Autowired private
    PasswordEncoder passwordEncoder;

    @Autowired private
    MessageService messageService;

    private UserEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BadRequestExceptionHandler("User id= '" + id + "' not found!"));
    }

    public UserResponse save(UserCreateRecord userRequest) {
        
        if (repository.findByUserName(0L, userRequest.getName()) != null) {
            throw new BadRequestExceptionHandler("Name='" + userRequest.getName() + "' already exists in other User. check and adjust!");
        }
        if (repository.findByEmail(0L, userRequest.geteMail()) != null) {
            throw new BadRequestExceptionHandler("Email='" + userRequest.geteMail() + "' already exists in other User. check and adjust!");
        }
       
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequest.getName().trim());
        userEntity.setCreatedAt(new Date());
        userEntity.setEmail(userRequest.geteMail().trim());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setStatus("LOCKED");
        userEntity.setType(userRequest.getType());

        UserResponse result = UserDomainMapper.INSTANCE.entityToUserResponse(repository.saveAndFlush(userEntity));

        messageService.sendMessageAuditTemplate(request);

        return result;
    }

    public UserResponse replace(UserUpdateRecord userRequest) {
        
        UserEntity userEntity = this.findById(userRequest.getId());

        if (userRequest.getName() != null) {
            if (repository.findByUserName(userRequest.getId(), userRequest.getName()) != null) {
                throw new BadRequestExceptionHandler("Name='" + userRequest.getName() + "' already exists in other User. check and adjust!");
            }
            userEntity.setName(userRequest.getName());
        }
        if (userRequest.geteMail() != null) {
            if (repository.findByEmail(userRequest.getId(), userRequest.geteMail()) != null) {
                throw new BadRequestExceptionHandler("Email='" + userRequest.geteMail() + "' already exists in other User. check and adjust!");
            }
            userEntity.setEmail(userRequest.geteMail());
        }
        if (userRequest.getStatus() != null) {
            userEntity.setStatus(userRequest.getStatus());
        }
        if (userRequest.getType() != null) {
            userEntity.setType(userRequest.getType());
        }

        UserResponse result = UserDomainMapper.INSTANCE.entityToUserResponse(repository.saveAndFlush(userEntity));

        messageService.sendMessageAuditTemplate(request);

        return result;
    }

    public String changeStatus(Long id, String status) {
        
        UserEntity userEntity = this.findById(id);
        userEntity.setStatus(status);

        repository.saveAndFlush(userEntity);

        messageService.sendMessageAuditTemplate(request);

        return "Change Status executed successfully!";
    }

    public String changePassword(Long id, String passwordOld, String passwordNew) {
        
        UserEntity userEntity = this.findById(id);

        if (!passwordEncoder.matches(passwordOld, userEntity.getPassword())) {
            throw new BadRequestExceptionHandler("The current password does not match. check and adjust!");
        }

        userEntity.setPassword(passwordEncoder.encode(passwordNew));
        repository.saveAndFlush(userEntity);

        messageService.sendMessageAuditTemplate(request);

        return "Change password executed successfully!";
    }

    public String deleteLogical(Long id, String reason) {
        
        UserEntity userEntity = this.findById(id);
        repository.saveAndFlush(userEntity);

        messageService.sendMessageAuditTemplate(request);

        return "Delete record id= [" + id + "] executed successfully!";
    }

    public String deletePhysical(Long id, String reason) {
        
        UserEntity userEntity = this.findById(id);
        repository.delete(userEntity);

        messageService.sendMessageAuditTemplate(request);

        return "Delete record id= [" + id + "] executed successfully!";
    }

}
