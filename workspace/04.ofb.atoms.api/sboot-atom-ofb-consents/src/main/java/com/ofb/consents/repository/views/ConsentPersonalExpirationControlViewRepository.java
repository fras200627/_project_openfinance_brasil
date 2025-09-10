package com.ofb.consents.repository.views;

import com.ofb.consents.model.ConsentPersonalExpirationControlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsentPersonalExpirationControlViewRepository extends JpaRepository<ConsentPersonalExpirationControlModel, String>,
                                                        JpaSpecificationExecutor<ConsentPersonalExpirationControlModel> {

}
