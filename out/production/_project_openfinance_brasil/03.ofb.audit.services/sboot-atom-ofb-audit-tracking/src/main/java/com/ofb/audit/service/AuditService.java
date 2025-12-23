package com.ofb.audit.service;

import com.ofb.audit.mapper.AuditTrackingMapper;
import com.ofb.audit.repository.AuditRepository;
import com.ofb.audit.server.model.AuditResponse;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class AuditService {

    @Autowired private
    AuditRepository repository;

    public AuditResponse findById(String id) {
        try {
            AuditResponse result = AuditTrackingMapper.INSTANCE.entityToResponse(repository.findById(Long.valueOf(id)).get());
            return result;
        } catch (Exception ex) {
            throw new InternalErrorException("Audit id= [" + id + "] not found! Check and Try Again.");
        }
    }

    public List<AuditResponse> findAll() {
        List<AuditResponse> result = AuditTrackingMapper.INSTANCE.listEntityToListResponse(repository.findAll(Sort.by(Sort.Direction.ASC, "id")));
        return result;
    }

}
