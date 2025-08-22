package com.ofb.consents.repository.views;

import com.ofb.consents.model.PersonalDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonalDataViewRepository extends JpaRepository<PersonalDataModel, String>,
                                                    JpaSpecificationExecutor<PersonalDataModel> {

}
