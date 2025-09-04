package com.ofb.resources.repository;

import com.ofb.resources.model.ResourcesPermissionsAuthorisedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourcesPermissionsAuthorisedRepository extends JpaRepository<ResourcesPermissionsAuthorisedModel, String>,
                                                        JpaSpecificationExecutor<ResourcesPermissionsAuthorisedModel> {

    @Query("SELECT a FROM ResourcesPermissionsAuthorised a WHERE a.consentId = :consentId")
    public List<ResourcesPermissionsAuthorisedModel> findAllResourcesPermissionsByConsentId(@Param("consentId")  String consentId);

    @Query("SELECT a FROM ResourcesPermissionsAuthorised a WHERE a.consentId = :consentId AND a.resourceType = 'ACCOUNT'")
    public List<ResourcesPermissionsAuthorisedModel> findAllResourcesAccountsPermissionsByConsentId(@Param("consentId")  String consentId);

    @Query("SELECT a FROM ResourcesPermissionsAuthorised a WHERE a.consentId = :consentId AND a.resourceType = 'CUSTOMER'")
    public List<ResourcesPermissionsAuthorisedModel> findAllResourcesCustomerPermissionsByConsentId(@Param("consentId")  String consentId);

}
