package com.tican.oauth2.users.service;

import com.tican.lib.amqp.service.MessageService;
import com.tican.lib.commons.jpa.RequestFilterParams;
import com.tican.lib.commons.jpa.RequestFilterPredicatesEnum;
import com.tican.lib.commons.jpa.RequestFilterSpecification;
import com.tican.lib.handlers.exception.BadRequestExceptionHandler;
import com.tican.oauth2.users.api.server.model.UserResponse;
import com.tican.oauth2.users.api.server.model.UserResponsePageable;
import com.tican.oauth2.users.domain.UserPaginationSettings;
import com.tican.oauth2.users.domain.UserRecord;
import com.tican.oauth2.users.domain.UserRecordFilter;
import com.tican.oauth2.users.entity.UserEntity;
import com.tican.oauth2.users.mapper.UserBusinessMapper;
import com.tican.oauth2.users.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired private HttpServletRequest request;
    @Autowired private MessageService messageService;
    @Autowired private PageableService pageableService;
    
    @Autowired private 
    UserRepository repository;
    
    public UserResponse findById(Long id) {
        try {
            UserResponse result =  UserBusinessMapper.INSTANCE.entityToUserResponse(repository.findById(id).get());
            messageService.sendMessageAuditTemplate(request);
            return result;
        } catch (Exception ex) {
            throw new BadRequestExceptionHandler("User with id= [" + id + "] not found! Check and Try Again.");
        }
    }

    public UserResponse findByUserName(String userName) {
        try {
            UserResponse result =  UserBusinessMapper.INSTANCE.entityToUserResponse(repository.findByUserName(userName));
            messageService.sendMessageAuditTemplate(request);
            if (result == null) {
                throw new BadRequestExceptionHandler("User with Name= [" + userName + "] not found! Check and Try Again.");
            }
            return result;
        } catch (Exception ex) {
            throw new BadRequestExceptionHandler("User with Name= [" + userName + "] not found! Check and Try Again.");
        }
    }

    public UserResponse findByEmail(String eMail) {
        try {
            UserResponse result =  UserBusinessMapper.INSTANCE.entityToUserResponse(repository.findByEmail(eMail));
            messageService.sendMessageAuditTemplate(request);
            if (result == null) {
                throw new BadRequestExceptionHandler("User with eMail= [" + eMail + "] not found! Check and Try Again.");
            }
            return result;
        } catch (Exception ex) {
            throw new BadRequestExceptionHandler("User with eMail= [" + eMail + "] not found! Check and Try Again.");
        }
    }

    public List<UserResponse> findAll() {
        List<UserResponse> result =  UserBusinessMapper.INSTANCE.listEntityToListUserResponse(repository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        messageService.sendMessageAuditTemplate(request);
        return result;
    }

    public UserResponsePageable findByPageable(Long pageNumber, Long pageSize,
                                               String pageSortField, String pageSortOrder) {

        Pageable pageableOptions =  pageableService.buildPageable(pageNumber,
                                                                pageSize,
                                                                pageSortField,
                                                                pageSortOrder);

        Page<UserEntity> pageResult = repository.findAll(pageableOptions);

        UserResponsePageable result = pageableService.buildOAuth2ClientsPageable(pageableOptions, pageResult);
        messageService.sendMessageAuditTemplate(request);

        return result;
    }

    public UserResponsePageable findByFilters(Long pageNumber, Long pageSize,
                                              String pageSortField, String pageSortOrder,
                                              String userName, String userEmail,
                                              String userType, String userStatus) {

        UserPaginationSettings pageSettings = new UserPaginationSettings(
                pageNumber.intValue(),
                pageSize.intValue(),
                pageSortField,
                pageSortOrder);

        Pageable pageableOptions =  pageableService.buildPageable(pageNumber,
                pageSize,
                pageSortField,
                pageSortOrder);

        UserRecordFilter filter = new UserRecordFilter(pageSettings,
                                                    userName,
                                                    userEmail,
                                                    userType,
                                                    userStatus);

        Specification<UserEntity> filterSpecs = this.buildFilter(filter);

        if (filterSpecs == null) {
            throw new BadRequestExceptionHandler("No parameters have been defined for the filter. check and adjust!");
        }

        Page<UserEntity> pageResult = repository.findAll(filterSpecs, pageableOptions);
        UserResponsePageable result = pageableService.buildOAuth2ClientsPageable(pageableOptions, pageResult);

        messageService.sendMessageAuditTemplate(request);

        return result;
    }

    public Specification<UserEntity> buildFilter(UserRecordFilter filter) {

        Specification<UserEntity> specs = null;
        RequestFilterParams requestFilterParams = null;

        if (filter.name() != null
                && filter.name().trim().length() != 0) {

            requestFilterParams = new RequestFilterParams("name",
                    RequestFilterPredicatesEnum.LIKE,
                    filter.name().trim().toLowerCase(),
                    null);

            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }

        if (filter.email() != null
                && filter.email().trim().length() != 0) {

            requestFilterParams = new RequestFilterParams("email",
                    RequestFilterPredicatesEnum.LIKE,
                    filter.email().trim().toLowerCase(),
                    null);

            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }

        if (filter.type() != null
                && filter.type().trim().length() != 0) {

            requestFilterParams = new RequestFilterParams("type",
                    RequestFilterPredicatesEnum.LIKE,
                    filter.type().trim().toUpperCase(),
                    null);

            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }

        if (filter.status() != null
                && filter.status().trim().length() != 0) {

            requestFilterParams = new RequestFilterParams("status",
                    RequestFilterPredicatesEnum.LIKE,
                    filter.status().trim().toUpperCase(),
                    null);

            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }

        return specs;
    }

}
