package com.ofb.resources.repository;

import com.ofb.resources.model.ResourcesConfirmedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourcesConfirmedRepository extends JpaRepository<ResourcesConfirmedModel, String>,
                                                        JpaSpecificationExecutor<ResourcesConfirmedModel> {

    @Query("SELECT a FROM ConsentPersonalDataResourcesConfirmed a WHERE a.consentId = :consentId")
    public List<ResourcesConfirmedModel> findAllResourcesConfirmedByConsentId(@Param("consentId")  String consentId);

}
