package com.ofb.consents.repository.views;

import com.ofb.consents.model.ConsentPermissionAuthorisedModel;
import com.ofb.consents.model.ConsentsResourcesAuthorisedAccountsModel;
import com.ofb.consents.model.ConsentsResourcesAuthorisedCustomersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsentResourcesAuthorisedlAccountsViewRepository extends JpaRepository<ConsentsResourcesAuthorisedAccountsModel, Long>,
                                                        JpaSpecificationExecutor<ConsentsResourcesAuthorisedAccountsModel> {

    @Query("SELECT a FROM ConsentsResourcesAuthorisedAccountsView a WHERE a.consentid = :consentId")
    public List<ConsentsResourcesAuthorisedAccountsModel> findAllConsentsResourcesAuthorisedAccountsByConsentId(@Param("consentId")  String consentId);

    @Query("""
           SELECT a.accountid 
           FROM ConsentsResourcesAuthorisedAccountsView a 
           WHERE a.consentid = :consentId
           GROUP BY
           a.cpfnumber,
           a.consentid,
           a.resourcetype,
           a.accountid
           """)
    public List<String> findAccountIdOfConsentResourceAuthorisedAccountsByConsentId(@Param("consentId")  String consentId);

    @Query("SELECT a FROM ConsentsResourcesAuthorisedAccountsView a WHERE a.consentid = :consentId and a.accountid = :accountId")
    public List<ConsentsResourcesAuthorisedAccountsModel> findAllConsentsResourcesAuthorisedAccountsByConsentIdAndAccountId(@Param("consentId")  String consentId,
                                                                                                        @Param("accountId")  String accountId);

}