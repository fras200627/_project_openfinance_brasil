package com.ofb.consents.repository.views;

import com.ofb.consents.model.ConsentPersonalExpirationControlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ConsentPersonalExpirationControlViewRepository extends JpaRepository<ConsentPersonalExpirationControlModel, String>,
                                                        JpaSpecificationExecutor<ConsentPersonalExpirationControlModel> {


    @Query("SELECT a FROM ConsentPersonalExpirationControlView a " +
            "WHERE " +
            "a.consentId = :consentId " +
            "AND " +
            "a.id = " +
            "(SELECT MAX(b.id) FROM ConsentPersonalExpirationControlView b WHERE b.consentId = :consentId)")
    public ConsentPersonalExpirationControlModel findRegistryOdMaxExpirationByConsentId(String consentId);

}
