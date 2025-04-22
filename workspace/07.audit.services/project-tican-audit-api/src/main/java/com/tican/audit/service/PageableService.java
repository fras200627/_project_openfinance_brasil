package com.tican.audit.service;

import com.tican.audit.api.server.model.PageableDetailsTemplate;
import com.tican.audit.api.server.model.TicketRecordPageable;
import com.tican.audit.entity.AuditEntity;
import com.tican.audit.mapper.TicketMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PageableService {

    public TicketRecordPageable buildTicketRecordPageable(Pageable pageable, Page<AuditEntity> pageResult) {

        PageableDetailsTemplate pageableDetails = this.buildPageableDetailsTemplate(pageable, pageResult);

        TicketRecordPageable result = new TicketRecordPageable().toBuilder()
                .content(TicketMapper.INSTANCE.listAuditEntityToListTicketResponse(pageResult.getContent()))
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

    public PageableDetailsTemplate buildPageableDetailsTemplate(Pageable pageable, Page<AuditEntity> pageResult) {

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
