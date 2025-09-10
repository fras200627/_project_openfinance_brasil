package com.ofb.consents.repository.views;

import com.ofb.consents.model.ConsentPermissionRequestedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsentPermissionsRequestedlViewRepository extends JpaRepository<ConsentPermissionRequestedModel, Long>,
                                                        JpaSpecificationExecutor<ConsentPermissionRequestedModel> {

    @Query("SELECT a FROM ConsentPermissionRequestedView a WHERE a.consentid = :consentId")
    public List<ConsentPermissionRequestedModel> findAllConsentsPermissionsRequestedByConsentId(@Param("consentId")  String consentId);

    @Query("SELECT a.permission FROM ConsentPermissionRequestedView a WHERE a.consentid = :consentId")
    public List<String> findAllConsentsPermissionsNamesRequestedByConsentId(@Param("consentId")  String consentId);

}
