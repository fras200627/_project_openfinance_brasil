package com.ofb.clients.business.service;

import com.ofb.clients.business.entity.ClientsBusinessEntity;
import com.ofb.clients.business.mapper.ClientsBusinessMapper;
import com.ofb.clients.business.server.clients.model.OAuth2ClientResponse;
import com.ofb.clients.business.server.clients.model.OAuth2ClientsPageable;
import com.ofb.lib.amqp.service.MessageService;
import com.ofb.lib.commons.jpa.RequestFilterParams;
import com.ofb.lib.commons.jpa.RequestFilterPredicatesEnum;
import com.ofb.lib.commons.jpa.RequestFilterSpecification;
import com.ofb.clients.business.domain.ClientsBusinessPaginationSettings;
import com.ofb.clients.business.domain.ClientsBusinessRecordFilter;
import com.ofb.clients.business.repository.ClientsBusinessRepository;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
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
public class ClientsBusinessService {

    @Autowired private HttpServletRequest request;
    @Autowired private MessageService messageService;
    @Autowired private ClientsBusinessRepository repository;
    @Autowired private PageableService pageableService;

    public OAuth2ClientResponse findById(String id) {
        try {
            OAuth2ClientResponse result = ClientsBusinessMapper.INSTANCE.entityToResponse(repository.findById(id).get());
            //messageService.sendMessageAuditTemplate(request);
            return result;
        } catch (Exception ex) {
            throw new InternalErrorException("Registered id= [" + id + "] not found! Check and Try Again.");
        }
    }

    public OAuth2ClientResponse findByClientId(String client_id) {
        try {
            OAuth2ClientResponse result = ClientsBusinessMapper.INSTANCE.entityToResponse(repository.findByClientId(client_id));
            messageService.sendMessageAuditTemplate(request);
            return result;
        } catch (Exception ex) {
            throw new InternalErrorException("Client Id= [" + client_id + "] not found! Check and Try Again.");
        }
    }

    public OAuth2ClientResponse findByClientName(String client_name) {
        try {
            OAuth2ClientResponse result = ClientsBusinessMapper.INSTANCE.entityToResponse(repository.findByClientName(client_name));
            //messageService.sendMessageAuditTemplate(request);
            return result;
        } catch (Exception ex) {
            throw new InternalErrorException("Client Name= [" + client_name + "] not found! Check and Try Again.");
        }
    }

    public OAuth2ClientResponse findByClientDocument(String client_document) {
        try {
            OAuth2ClientResponse result = ClientsBusinessMapper.INSTANCE.entityToResponse(repository.findByClientDocument(client_document));
            //messageService.sendMessageAuditTemplate(request);
            return result;
        } catch (Exception ex) {
            throw new InternalErrorException("Client Name= [" + client_document + "] not found! Check and Try Again.");
        }
    }
    
    public List<OAuth2ClientResponse> findAll() {
        List<OAuth2ClientResponse> result = ClientsBusinessMapper.INSTANCE.listEntityToListResponse(repository.findAll(Sort.by(Sort.Direction.ASC, "id")));
        messageService.sendMessageAuditTemplate(request);
        return result;
    }

    public OAuth2ClientsPageable findByPageable(Long pageNumber, Long pageSize,
                                                String pageSortField, String pageSortOrder)  {

        Pageable pageableOptions =  pageableService.buildPageable(pageNumber,
                                                                pageSize,
                                                                pageSortField,
                                                                pageSortOrder);

        Page<ClientsBusinessEntity> pageResult = repository.findAll(pageableOptions);
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

        ClientsBusinessPaginationSettings pageSettings = new ClientsBusinessPaginationSettings(
                                                                            pageNumber.intValue(),
                                                                            pageSize.intValue(),
                                                                            pageSortField,
                                                                            pageSortOrder);
        Pageable pageableOptions =  pageableService.buildPageable(pageNumber,
                                                                pageSize,
                                                                pageSortField,
                                                                pageSortOrder);
        ClientsBusinessRecordFilter filter = new ClientsBusinessRecordFilter(pageSettings,
                                                                                            registeredId,
                                                                                            clientId,
                                                                                            clientName,
                                                                                            securityScope);
        Specification<ClientsBusinessEntity> filterSpecs = this.buildFilter(filter);

        if (filterSpecs == null) {
            throw new InternalErrorException("No parameters have been defined for the filters. check and adjust!");
        }

        Page<ClientsBusinessEntity> pageResult = repository.findAll(filterSpecs, pageableOptions);
        OAuth2ClientsPageable result = pageableService.buildOAuth2ClientsPageable(pageableOptions, pageResult);

        messageService.sendMessageAuditTemplate(request);

        return result;
    }

    public Specification<ClientsBusinessEntity> buildFilter(ClientsBusinessRecordFilter filter) {
        Specification<ClientsBusinessEntity> specs = null;
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
