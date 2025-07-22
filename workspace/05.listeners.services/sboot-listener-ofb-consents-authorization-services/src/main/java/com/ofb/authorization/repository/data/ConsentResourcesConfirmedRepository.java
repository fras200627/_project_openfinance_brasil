package com.ofb.authorization.repository.data;

import com.ofb.authorization.entity.ConsentResourcesConfirmed;
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
