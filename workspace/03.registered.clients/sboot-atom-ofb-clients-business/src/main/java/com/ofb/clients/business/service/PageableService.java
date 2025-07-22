package com.ofb.clients.business.service;

import com.ofb.clients.business.entity.ClientsBusinessEntity;
import com.ofb.clients.business.mapper.ClientsBusinessMapper;
import com.ofb.clients.business.server.clients.model.OAuth2ClientsPageable;
import com.ofb.clients.business.server.clients.model.PageableDetailsTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PageableService {

    public OAuth2ClientsPageable buildOAuth2ClientsPageable(Pageable pageable, Page<ClientsBusinessEntity> pageResult) {

        PageableDetailsTemplate pageableDetails = this.buildPageableDetailsTemplate(pageable, pageResult);

        OAuth2ClientsPageable result = new OAuth2ClientsPageable().toBuilder()
                .content(ClientsBusinessMapper.INSTANCE.listEntityToListResponse(pageResult.getContent()))
                .pageable(pageableDetails)
                .build();

        return result;
    }

    public Pageable buildPageable(Long   pageNumber,
                                  Long   pageSize,
                                  String pageSortField,
                                  String pageSortOrder)  {

        Pageable pageableResult = null;
        if (pageSortField != null) {
            pageableResult = PageRequest.of(Long.valueOf(pageNumber - 1).intValue(), Long.valueOf(pageSize).intValue())
                    .withSort(
                            //Fix a Sort Order
                            (pageSortOrder == null ? Sort.Direction.ASC
                                    : (pageSortOrder.toUpperCase().equals("ASC") ? Sort.Direction.ASC
                                    : Sort.Direction.DESC)),
                            //Fix a Field used
                            (pageSortField)
                    );
        }

        return pageableResult;
    }

    public PageableDetailsTemplate buildPageableDetailsTemplate(Pageable pageable, Page<ClientsBusinessEntity> pageResult) {

        int pageNumber = (pageResult.getPageable().getPageNumber() > pageResult.getTotalPages())
                ? pageResult.getTotalPages()
                : pageResult.getPageable().getPageNumber() + 1;

        PageableDetailsTemplate pageableDetails = null;

        if (pageResult.isEmpty()) {
            pageableDetails = new PageableDetailsTemplate().toBuilder()
                    .isEmptyResult(pageResult.isEmpty())
                    .pageNumberRequested(pageResult.getPageable().getPageNumber() + 1)
                    .pageSizeRequested(pageable.getPageSize())
                    .sortFieldRequested(pageable.getSort().toString())
                    .totalPagesExisting(pageResult.getTotalPages())
                    .totalElementsExisting(Long.valueOf(pageResult.getTotalElements()).intValue())
                    .build();
        } else {
            pageableDetails = new PageableDetailsTemplate().toBuilder()
                    .isEmptyResult(pageResult.isEmpty())
                    .pageNumberRequested(pageResult.getPageable().getPageNumber() + 1)
                    .pageSizeRequested(pageable.getPageSize())
                    .sortFieldRequested(pageable.getSort().toString())
                    .pageNumberResulting(pageNumber)
                    .isFirstPage(pageResult.isFirst())
                    .isLastPage(pageResult.isLast())
                    .previousPageNumber(pageResult.isFirst() == true ? pageNumber : pageNumber - 1)
                    .nextPageNumber(pageResult.isLast() == true ? pageNumber : pageNumber + 1)
                    .pageSizeResulting(pageResult.getContent().size())
                    .totalPagesExisting(pageResult.getTotalPages())
                    .totalElementsExisting(Long.valueOf(pageResult.getTotalElements()).intValue())
                    .build();
        }

        return pageableDetails;
    }

}
