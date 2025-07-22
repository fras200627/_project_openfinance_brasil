package com.ofb.authorization.repository.views;

import com.ofb.authorization.model.ConsentPermissionAuthorisedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsentPermissionsAuthorisedlViewRepository extends JpaRepository<ConsentPermissionAuthorisedModel, Long>,
                                                        JpaSpecificationExecutor<ConsentPermissionAuthorisedModel> {

    @Query("SELECT a FROM ConsentPermissionAuthorisedView a WHERE a.consentid = :consentId")
    public List<ConsentPermissionAuthorisedModel> findAllConsentsPermissionsAuthorisedByConsentId(@Param("consentId")  String consentId);

}
