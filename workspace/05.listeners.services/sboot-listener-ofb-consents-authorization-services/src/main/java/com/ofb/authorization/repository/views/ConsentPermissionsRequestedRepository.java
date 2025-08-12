package com.ofb.authorization.repository.views;

import com.ofb.authorization.model.ConsentPermissionRequestedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsentPermissionsRequestedRepository extends JpaRepository<ConsentPermissionRequestedModel, Long>,
                                                        JpaSpecificationExecutor<ConsentPermissionRequestedModel> {

    @Query("SELECT a FROM ConsentPermissionRequestedView a WHERE a.consentid = :consentId")
    public List<ConsentPermissionRequestedModel> findAllConsentsPermissionsRequestedByConsentId(@Param("consentId")  String consentId);

}
