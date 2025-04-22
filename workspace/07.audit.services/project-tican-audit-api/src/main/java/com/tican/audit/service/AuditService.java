package com.tican.audit.service;

import com.tican.audit.api.server.model.TicketRecordPageable;
import com.tican.audit.api.server.model.TicketResponse;
import com.tican.audit.mapper.TicketMapper;
import com.tican.audit.entity.AuditEntity;
import com.tican.audit.repository.AuditRepository;
import com.tican.lib.handlers.exception.BadRequestExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tican.lib.amqp.service.MessageService;
import com.tican.lib.amqp.model.MessageAuditTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service @Slf4j
public class AuditService {

    @Autowired private HttpServletRequest request;
    @Autowired private AuditRepository repository;
    @Autowired private MessageService messageService;
    @Autowired private PageableService pageableService;

    private AuditEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BadRequestExceptionHandler("Ticket number id= '" + id + "' not found!"));
    }

    public List<TicketResponse> findByTicketNumber(String ticketNumber) {
        List<AuditEntity> listResult = repository.findByTicketNumber(ticketNumber);
        List<TicketResponse> result = TicketMapper.INSTANCE.listAuditEntityToListTicketResponse(listResult);

        if (result == null || result.size() == 0) {
            throw new BadRequestExceptionHandler("TicketNUmber = [" + ticketNumber + "] not found! Check and Try Again.");
        }

        messageService.sendMessageAuditTemplate(request);

        return result;
    }

    public List<TicketResponse> findAllTicketRecord(String pageSortField,
                                                    String pageSortOrder) {
        Pageable pageableOptions =  pageableService.buildPageable(1L, 1L,
                                                              pageSortField,
                                                              pageSortOrder);

        List<AuditEntity> listResult = repository.findAll(pageableOptions.getSort());
        List<TicketResponse> result = TicketMapper.INSTANCE.listAuditEntityToListTicketResponse(listResult);
        messageService.sendMessageAuditTemplate(request);

        return result;
    }

    public TicketRecordPageable findTicketRecordByPageable(Long   pageNumber,
                                                           Long   pageSize,
                                                           String pageSortField,
                                                           String pageSortOrder)  {
        Pageable pageableOptions =  pageableService.buildPageable(pageNumber,
                                                             pageSize,
                                                             pageSortField,
                                                             pageSortOrder);
        Page<AuditEntity> pageResult = repository.findAll(pageableOptions);
        TicketRecordPageable result = pageableService.buildTicketRecordPageable(pageableOptions, pageResult);
        messageService.sendMessageAuditTemplate(request);

        return result;
    }

    // Services for Listener of MessageQueue Rabbit
    // ----------------------------------------------------------------------------------------------------------
    public void saveMessageQueue(MessageAuditTemplate messageAuditTemplate) {
        try {
            repository.saveAndFlush(TicketMapper.INSTANCE.messageAuditTemplateToAuditEntity(messageAuditTemplate));
            log.info("[" + System.getProperty("App.Module.Name") + "]\n\t" +
                    "[Ticket Message Queue: {" +
                    "ticket: " + messageAuditTemplate.getTicket() + " saved sucessfully" +
                    "}]"
            );
        } catch (Exception e) {
            log.error("[" + System.getProperty("App.Module.Name") + "]\n\t" +
                    "[Ticket Message Queue: {" +
                    "ticket: " + messageAuditTemplate.getTicket() + " error in write." +
                    "}]" + "]\n\t" +
                    "[ERROR: " + e.getMessage() + "] " +
                    "Error details [" + e.getMessage() + "]");
        }
    }

}
