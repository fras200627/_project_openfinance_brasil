package com.ofb.authorization.repository.data;

import com.ofb.authorization.entity.ConsentResourcesAuthorised;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsentResourcesConfirmedRepository extends JpaRepository<ConsentResourcesAuthorised, Long>,
                                                        JpaSpecificationExecutor<ConsentResourcesAuthorised> {

    @Query("SELECT a FROM ConsentResourcesConfirmed a WHERE a.consentId = :consentId")
    public List<ConsentResourcesAuthorised> findAllResourcesConfirmedByConsentId(@Param("consentId")  String consentId);

}
