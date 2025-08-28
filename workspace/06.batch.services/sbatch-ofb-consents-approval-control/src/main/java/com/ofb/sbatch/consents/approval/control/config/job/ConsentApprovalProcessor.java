package com.ofb.sbatch.consents.approval.control.config.job;

import com.ofb.sbatch.consents.approval.control.entity.ConsentPersonalData;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ConsentApprovalProcessor implements ItemProcessor<ConsentPersonalData, ConsentPersonalData> {

    @Value("${app.consents.approval.expires-in-minutes}")
    private Long CONSENTS_APPROVAL_EXPIRES_IN_MINUTES;

    @Override
    public ConsentPersonalData process(ConsentPersonalData item) throws Exception {
        Timestamp timestampNow = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")).minusMinutes(CONSENTS_APPROVAL_EXPIRES_IN_MINUTES));
        if (item.getAwaitingAuthStart().before(timestampNow)) {
            item.setStatus("REJECTED");
            item.setStatusUpdateDatetime(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            item.setConsentStatusId(82L);
            ///
            item.setAwaitingAuthEnd(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            item.setAwaitingAuthAdditionalInfo("consents rejected - approval time expired");
            //
            item.setRejectedStartDatetime(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            item.setRejectedEndDatetime(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            ///
            item.setRejectedCode("CONSENT_EXPIRED");
            item.setRejectedBy("ControlBatchProcessor");
            item.setRejectedReason("CONSENT_EXPIRED");
            item.setRejectedAdditionalInfo("consents rejected - approval time expired");
            ///
            item.setModifyAt(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
            item.setUserCode("batch ofb");
            return item;
        } else {
            return null;
        }
    }
}
