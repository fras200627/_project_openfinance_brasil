package com.ofb.authorization.repository;

import com.ofb.authorization.model.ResourcesAuthorisedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourcesAuthorisedRepository extends JpaRepository<ResourcesAuthorisedModel, String>,
                                                        JpaSpecificationExecutor<ResourcesAuthorisedModel> {

    @Query("SELECT a FROM ResourcesAuthorised a WHERE a.consentId = :consentId AND a.resourceType = 'ACCOUNT'")
    public List<ResourcesAuthorisedModel> findAllResourcesAccountsByConsentId(@Param("consentId")  String consentId);

}
