package com.ofb.customers.repository;

import com.ofb.customers.model.PersonalDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PersonalDataRepository extends JpaRepository<PersonalDataModel, String>,
                                                       JpaSpecificationExecutor<PersonalDataModel> {
    @Query("SELECT a FROM PersonalDataView a WHERE a.CPFNumber = :document")
    public PersonalDataModel findByDocument(String document);

}
