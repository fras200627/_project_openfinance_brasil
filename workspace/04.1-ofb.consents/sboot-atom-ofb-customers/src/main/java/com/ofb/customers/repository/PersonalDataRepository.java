package com.ofb.customers.repository;

import com.ofb.customers.model.PersonalDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonalDataRepository extends JpaRepository<PersonalDataModel, String>,
                                                       JpaSpecificationExecutor<PersonalDataModel> {

}
