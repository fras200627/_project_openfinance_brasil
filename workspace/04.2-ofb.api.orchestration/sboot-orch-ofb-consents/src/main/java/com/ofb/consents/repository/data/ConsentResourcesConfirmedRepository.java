package com.ofb.consents.repository.data;

import com.ofb.consents.entity.ConsentResourcesConfirmed;
import com.ofb.consents.model.ConsentsResourcesAuthorisedAccountsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsentResourcesConfirmedRepository extends JpaRepository<ConsentResourcesConfirmed, Long>,
                                                        JpaSpecificationExecutor<ConsentResourcesConfirmed> {

    @Query("SELECT a FROM ConsentResourcesConfirmed a WHERE a.consentId = :consentId")
    public List<ConsentResourcesConfirmed> findAllResourcesConfirmedByConsentId(@Param("consentId")  String consentId);

}
