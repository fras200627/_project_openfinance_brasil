package com.tican.audit.mapper;

import com.tican.audit.api.server.model.TicketResponse;
import com.tican.audit.entity.AuditEntity;
import com.tican.lib.amqp.model.MessageAuditTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.time.*;
import java.util.List;

@Mapper
public interface TicketMapper {

    TicketMapper INSTANCE = Mappers.getMapper( TicketMapper.class );

    default long getNewTicketNumber() {
        int random = (int)(Math.random() * 999999999 + 1);
        return Integer.valueOf(random).longValue();
    }

    default OffsetDateTime getNewOffsetDateTime() {
        return OffsetDateTime.now();
    }

    default String getNewOffsetDateTimeFormatString() {
        return OffsetDateTime.now().toString();
    }

    default OffsetDateTime convertStringToOffSetDateTime(String source) {
        return OffsetDateTime.parse(source.subSequence(0, source.length()));
    }

    default OffsetDateTime convertStringToOffSetDateTime(AuditEntity source) {
        return OffsetDateTime.parse(source.getRequestTime().subSequence(0, source.getRequestTime().length()));
    }

    default String convertOffSetDateTimeToString(OffsetDateTime source) {
        return source.toString();
    }

    default String convertOffSetDateTimeToString(TicketResponse source) {
        return source.getRequestTime().toString();
    }

    @Mapping(target = "ticket",             source      = "source.ticket")
    @Mapping(target = "interactionId",      source      = "source.interactionId")
    @Mapping(target = "requestTime",        source      = "source.requestTime")
    @Mapping(target = "requestUri",         source      = "source.requestUri")
    @Mapping(target = "requestSource",      source      = "source.requestSource")
    @Mapping(target = "requestMethod",      source      = "source.requestMethod")
    @Mapping(target = "requestUserName",    source      = "source.requestUserName")
    @Mapping(target = "payload",            source      = "source.payload")
    TicketResponse auditEntityToTicketResponse(AuditEntity source);

    @Mapping(target = "ticket",             source      = "source.ticket")
    @Mapping(target = "interactionId",      source      = "source.interactionId")
    @Mapping(target = "requestTime",        source      = "source.requestTime")
    @Mapping(target = "requestUri",         source      = "source.requestUri")
    @Mapping(target = "requestSource",      source      = "source.requestSource")
    @Mapping(target = "requestMethod",      source      = "source.requestMethod")
    @Mapping(target = "requestUserName",    source      = "source.requestUserName")
    @Mapping(target = "payload",            source      = "source.payload")
    List<TicketResponse> listAuditEntityToListTicketResponse(List<AuditEntity> source);

    @Mapping(target = "ticket",             source      = "source.ticket")
    @Mapping(target = "interactionId",      source      = "source.interactionId")
    @Mapping(target = "requestTime",        source      = "source.requestTime")
    @Mapping(target = "requestUri",         source      = "source.requestUri")
    @Mapping(target = "requestSource",      source      = "source.requestSource")
    @Mapping(target = "requestMethod",      source      = "source.requestMethod")
    @Mapping(target = "requestUserName",    source      = "source.requestUserName")
    @Mapping(target = "payload",            source      = "source.payload")
    AuditEntity ticketResponseToAuditEntity(TicketResponse source);

    @Mapping(target = "ticket",             source      = "source.ticket")
    @Mapping(target = "interactionId",      source      = "source.interactionId")
    @Mapping(target = "requestTime",        source      = "source.requestTime")
    @Mapping(target = "requestUri",         source      = "source.requestUri")
    @Mapping(target = "requestSource",      source      = "source.requestSource")
    @Mapping(target = "requestMethod",      source      = "source.requestMethod")
    @Mapping(target = "requestUserName",    source      = "source.requestUserName")
    @Mapping(target = "payload",            source      = "source.payload")
    AuditEntity messageAuditTemplateToAuditEntity(MessageAuditTemplate source);

}
