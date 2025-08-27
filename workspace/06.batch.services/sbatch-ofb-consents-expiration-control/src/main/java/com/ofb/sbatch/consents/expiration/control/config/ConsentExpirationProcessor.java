package com.ofb.sbatch.consents.expiration.control.config;

import com.ofb.sbatch.consents.expiration.control.entity.ConsentPersonalData;
import org.springframework.batch.item.ItemProcessor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ConsentExpirationProcessor implements ItemProcessor<ConsentPersonalData, ConsentPersonalData> {

    @Override
    public ConsentPersonalData process(ConsentPersonalData item) throws Exception {
        Timestamp timestampNow = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")));
        if (item.getExpirationDatetime().before(timestampNow)) {
            item.setStatus("REJECTED");
            item.setStatusUpdateDatetime(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            item.setConsentStatusId(83L);
            ///
            item.setRejectedStartDatetime(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            item.setRejectedEndDatetime(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            ///
            item.setRejectedCode("CONSENT_EXPIRED");
            item.setRejectedBy("ControlBatchProcessor");
            item.setRejectedReason("CONSENT_EXPIRED");
            item.setRejectedAdditionalInfo("consent rejected - consent expiration date");
            ///
            item.setModifyAt(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            item.setUserCode("batch ofb");
            return item;
        } else {
            return null;
        }
    }
}
