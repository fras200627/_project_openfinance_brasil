package com.ofb.consents.repository.views;

import com.ofb.consents.model.ConsentsResourcesAuthorisedCustomersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsentResourcesAuthorisedlCustomersViewRepository extends JpaRepository<ConsentsResourcesAuthorisedCustomersModel, Long>,
                                                        JpaSpecificationExecutor<ConsentsResourcesAuthorisedCustomersModel> {

    @Query("SELECT a FROM ConsentsResourcesAuthorisedCustomersView a WHERE a.consentid = :consentId")
    public List<ConsentsResourcesAuthorisedCustomersModel> findAllConsentsResourcesAuthorisedCustomersByConsentId(@Param("consentId")  String consentId);

    @Query("""
           SELECT a.personalid 
           FROM ConsentsResourcesAuthorisedCustomersView a 
           WHERE a.consentid = :consentId
           GROUP BY
           a.cpfnumber,
           a.consentid,
           a.consentstatus,
           a.personalid,
           a.customer,
           a.resourcetype
           """)
    public String findPersonalIdOfConsentResourceAuthorisedCustomerByConsentId(@Param("consentId")  String consentId);
}
