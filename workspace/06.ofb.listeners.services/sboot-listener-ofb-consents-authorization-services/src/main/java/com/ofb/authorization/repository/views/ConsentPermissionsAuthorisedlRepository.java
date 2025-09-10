package com.ofb.authorization.repository.views;

import com.ofb.authorization.model.ConsentPermissionAuthorisedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsentPermissionsAuthorisedlRepository extends JpaRepository<ConsentPermissionAuthorisedModel, Long>,
                                                        JpaSpecificationExecutor<ConsentPermissionAuthorisedModel> {

    @Query("SELECT a FROM ConsentPermissionAuthorisedView a WHERE a.consentid = :consentId")
    public List<ConsentPermissionAuthorisedModel> findAllConsentsPermissionsAuthorisedByConsentId(@Param("consentId")  String consentId);

    @Query("SELECT a FROM ConsentPermissionAuthorisedView a WHERE a.consentid = :consentId AND a.resourcetype = 'CUSTOMER'")
    public List<ConsentPermissionAuthorisedModel> findAllConsentsCustomersPermissionsAuthorisedByConsentId(@Param("consentId")  String consentId);

    @Query("SELECT a FROM ConsentPermissionAuthorisedView a WHERE a.consentid = :consentId AND a.resourcetype = 'ACCOUNT'")
    public List<ConsentPermissionAuthorisedModel> findAllConsentsAccountsPermissionsAuthorisedByConsentId(@Param("consentId")  String consentId);

    @Query("SELECT a.personalid FROM ConsentPermissionAuthorisedView a WHERE a.consentid = :consentId AND a.resourcetype = 'CUSTOMER' GROUP BY a.personalid")
    public List<String> findPersonalIdCustomerPermissionsAuthorisedByConsentId(@Param("consentId")  String consentId);

    @Query("SELECT a.personalid FROM ConsentPermissionAuthorisedView a WHERE a.consentid = :consentId AND a.resourcetype = 'ACCOUNT' GROUP BY a.personalid")
    public List<String> findPersonalIdByAccountPermissionsAuthorisedByConsentId(@Param("consentId")  String consentId);

}
