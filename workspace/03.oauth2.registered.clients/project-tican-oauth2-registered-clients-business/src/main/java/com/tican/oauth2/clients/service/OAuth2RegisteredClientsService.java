package com.tican.oauth2.clients.service;

import com.tican.lib.amqp.service.MessageService;
import com.tican.lib.commons.jpa.RequestFilterParams;
import com.tican.lib.commons.jpa.RequestFilterPredicatesEnum;
import com.tican.lib.commons.jpa.RequestFilterSpecification;
import com.tican.oauth2.clients.api.server.model.OAuth2ClientResponse;
import com.tican.oauth2.clients.api.server.model.OAuth2ClientsPageable;
import com.tican.oauth2.clients.domain.OAuth2RegisteredClientsPaginationSettings;
import com.tican.oauth2.clients.domain.OAuth2RegisteredClientsRecordFilter;
import com.tican.oauth2.clients.entity.OAuth2RegisteredClientsEntity;
import com.tican.oauth2.clients.mapper.OAuth2RegisteredClientMapper;
import com.tican.oauth2.clients.repository.OAuth2RegisteredClientsRepository;
import com.tican.lib.handlers.exception.BadRequestExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service @Slf4j
public class OAuth2RegisteredClientsService {

    @Autowired private HttpServletRequest request;
    @Autowired private MessageService messageService;
    @Autowired private OAuth2RegisteredClientsRepository repository;
    @Autowired private PageableService pageableService;

    public OAuth2ClientResponse findById(String id) {
        try {
            OAuth2ClientResponse result = OAuth2RegisteredClientMapper.INSTANCE.entityToResponse(repository.findById(id).get());
            messageService.sendMessageAuditTemplate(request);
            return result;
        } catch (Exception ex) {
            throw new BadRequestExceptionHandler("Registered id= [" + id + "] not found! Check and Try Again.");
        }
    }

    public OAuth2ClientResponse findByClientId(String client_id) {
        try {
            OAuth2ClientResponse result = OAuth2RegisteredClientMapper.INSTANCE.entityToResponse(repository.findByClientId(client_id));
            messageService.sendMessageAuditTemplate(request);
            return result;
        } catch (Exception ex) {
            throw new BadRequestExceptionHandler("Client Id= [" + client_id + "] not found! Check and Try Again.");
        }
    }

    public OAuth2ClientResponse findByClientName(String client_name) {
        try {
            OAuth2ClientResponse result = OAuth2RegisteredClientMapper.INSTANCE.entityToResponse(repository.findByClientName(client_name));
            messageService.sendMessageAuditTemplate(request);
            return result;
        } catch (Exception ex) {
            throw new BadRequestExceptionHandler("Client Name= [" + client_name + "] not found! Check and Try Again.");
        }
    }
    
    public List<OAuth2ClientResponse> findAll() {
        List<OAuth2ClientResponse> result = OAuth2RegisteredClientMapper.INSTANCE.listEntityToListResponse(repository.findAll(Sort.by(Sort.Direction.ASC, "id")));
        messageService.sendMessageAuditTemplate(request);
        return result;
    }

    public OAuth2ClientsPageable findByPageable(Long pageNumber, Long pageSize,
                                                String pageSortField, String pageSortOrder)  {

        Pageable pageableOptions =  pageableService.buildPageable(pageNumber,
                                                                pageSize,
                                                                pageSortField,
                                                                pageSortOrder);
        Page<OAuth2RegisteredClientsEntity> pageResult = repository.findAll(pageableOptions);
        OAuth2ClientsPageable result = pageableService.buildOAuth2ClientsPageable(pageableOptions, pageResult);
        messageService.sendMessageAuditTemplate(request);

        return result;
    }

    public OAuth2ClientsPageable findByFilters(Long pageNumber,
                                               Long pageSize,
                                               String pageSortField,
                                               String pageSortOrder,
                                               String registeredId,
                                               String clientId,
                                               String clientName,
                                               String securityScope) {

        OAuth2RegisteredClientsPaginationSettings pageSettings = new OAuth2RegisteredClientsPaginationSettings(
                                                                            pageNumber.intValue(),
                                                                            pageSize.intValue(),
                                                                            pageSortField,
                                                                            pageSortOrder);
        Pageable pageableOptions =  pageableService.buildPageable(pageNumber,
                                                                pageSize,
                                                                pageSortField,
                                                                pageSortOrder);
        OAuth2RegisteredClientsRecordFilter filter = new OAuth2RegisteredClientsRecordFilter(pageSettings,
                                                                                            registeredId,
                                                                                            clientId,
                                                                                            clientName,
                                                                                            securityScope);
        Specification<OAuth2RegisteredClientsEntity> filterSpecs = this.buildFilter(filter);

        if (filterSpecs == null) {
            throw new BadRequestExceptionHandler("No parameters have been defined for the filters. check and adjust!");
        }

        Page<OAuth2RegisteredClientsEntity> pageResult = repository.findAll(filterSpecs, pageableOptions);
        OAuth2ClientsPageable result = pageableService.buildOAuth2ClientsPageable(pageableOptions, pageResult);

        messageService.sendMessageAuditTemplate(request);

        return result;
    }

    public Specification<OAuth2RegisteredClientsEntity> buildFilter(OAuth2RegisteredClientsRecordFilter filter) {
        Specification<OAuth2RegisteredClientsEntity> specs = null;
        RequestFilterParams requestFilterParams = null;

        if (filter.id() != null && filter.id().trim().length() != 0) {
            requestFilterParams = new RequestFilterParams("id",
                                                        RequestFilterPredicatesEnum.LIKE,
                                                        filter.id().trim().toUpperCase(),
                                                        null);
            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }

        if (filter.clientId() != null && filter.clientId().trim().length() != 0) {
            requestFilterParams = new RequestFilterParams("clientId",
                                                        RequestFilterPredicatesEnum.LIKE,
                                                        filter.clientId().trim().toUpperCase(),
                                                        null);
            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }

        if (filter.clientName() != null && filter.clientName().trim().length() != 0) {
            requestFilterParams = new RequestFilterParams("clientName",
                                                        RequestFilterPredicatesEnum.LIKE,
                                                        filter.clientName().trim().toUpperCase(),
                                                        null);
            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }

        if (filter.scopes() != null && filter.scopes().trim().length() != 0) {
            requestFilterParams = new RequestFilterParams("scopes",
                    RequestFilterPredicatesEnum.LIKE,
                    filter.scopes().trim().toLowerCase(),
                    null);
            specs = Specification.where(specs).and(new RequestFilterSpecification<>(requestFilterParams));
        }

        return specs;
    }

}
