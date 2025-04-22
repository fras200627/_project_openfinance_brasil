package com.tican.audit.mapper.audit;

import com.tican.audit.api.server.model.TicketResponse;
import com.tican.audit.entity.AuditEntity;
import com.tican.audit.mapper.TicketMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

class TicketMapperTest {

    private AuditEntity buildAuditEntity() {
        AuditEntity audit = new AuditEntity();
        audit.setTicket("776650703");
        audit.setPayload("ProductRecord[id=100000, product=Product Test 3, status=ACTIVE, " +
                "codes=ProductCodesRecord[cod_ggrem=012345678901236, registry_code=0123456789012, " +
                "ean1_code=01234567898836, ean2_code=01234567891234, ean3_code=01234567891234], " +
                "substance=SubstanceRecord[id=19, substance=, status=], presentation=string, " +
                "terapy_class=string, restriction=string, tag=string, " +
                "laboratory=ProductLaboratoryRecord[laboratory=string, laboratory_cnpj=stringstringstring], " +
                "type=string, prices=ProductPricesRecord[price_rule=string, " +
                "base_price=0, price_icms_12=0, price_icms_17=0, price_icms_18=0], " +
                "user_code=tican-admin-master, dt_insert=Tue Feb 04 09:18:04 BRT 2025, " +
                "dt_update=Tue Feb 04 09:18:04 BRT 2025]");
        audit.setRequestMethod("POST");
        audit.setRequestUri("/products");
        audit.setRequestTime("2025-03-20T09:40:22.070184600-03:00");
        audit.setRequestUserName("tican-admin-master");

        return audit;
    }

    private List<AuditEntity> buildListAuditEntity() {
        List<AuditEntity> entityList = new ArrayList<>();
        entityList.add(this.buildAuditEntity());
        entityList.add(this.buildAuditEntity());
        entityList.add(this.buildAuditEntity());
        return entityList;
    }

    @Test
    void getNewTicketNumberTest() {
        Long ticketNumber = TicketMapper.INSTANCE.getNewTicketNumber();
        Assertions.assertEquals(ticketNumber, ticketNumber);
    }

    @Test
    void getNewOffsetDateTimeTest() {
        OffsetDateTime offsetDateTime = TicketMapper.INSTANCE.getNewOffsetDateTime();
        Assertions.assertEquals(offsetDateTime, offsetDateTime);
    }

    @Test
    void getNewOffsetDateTimeFormatStringTest() {
        String offsetDateTime = TicketMapper.INSTANCE.getNewOffsetDateTimeFormatString();
        Assertions.assertEquals(offsetDateTime, offsetDateTime);
    }

    @Test
    void convertStringToOffSetDateTimeTest() {
        OffsetDateTime offsetDateTime = TicketMapper.INSTANCE.convertStringToOffSetDateTime("2025-03-20T09:40:22.070184600-03:00");
        Assertions.assertEquals(offsetDateTime, offsetDateTime);
    }

    @Test
    void convertStringToOffSetDateTime2Test() {
        OffsetDateTime offsetDateTime = TicketMapper.INSTANCE.convertStringToOffSetDateTime(this.buildAuditEntity());
        Assertions.assertEquals(offsetDateTime, offsetDateTime);
    }

    @Test
    void convertOffSetDateTimeToStringTest() {
        String offsetDateTime1 = TicketMapper.INSTANCE.convertOffSetDateTimeToString(OffsetDateTime.now());
        Assertions.assertEquals(offsetDateTime1, offsetDateTime1);

        TicketResponse ticketResponse = TicketMapper.INSTANCE.auditEntityToTicketResponse(this.buildAuditEntity());
        String offsetDateTime2 = TicketMapper.INSTANCE.convertOffSetDateTimeToString(ticketResponse);
        Assertions.assertEquals(offsetDateTime2, offsetDateTime2);
    }

    @Test
    void ticketResponseToAuditEntityTest() {
        TicketResponse ticketResponse = TicketMapper.INSTANCE.auditEntityToTicketResponse(this.buildAuditEntity());
        AuditEntity auditEntity = TicketMapper.INSTANCE.ticketResponseToAuditEntity(ticketResponse);
        Assertions.assertEquals(auditEntity, this.buildAuditEntity());
    }

    @Test
    void listAuditEntityToListTicketResponseTest() {
        List<TicketResponse> listTicketResponse = TicketMapper.INSTANCE.listAuditEntityToListTicketResponse(this.buildListAuditEntity());
        Assertions.assertEquals(listTicketResponse, listTicketResponse);
    }

    @Test
    void auditEntityToTicketRecordTest() {
        TicketResponse ticketResponse = TicketMapper.INSTANCE.auditEntityToTicketResponse(this.buildAuditEntity());
        Assertions.assertEquals(ticketResponse, ticketResponse);
    }


}