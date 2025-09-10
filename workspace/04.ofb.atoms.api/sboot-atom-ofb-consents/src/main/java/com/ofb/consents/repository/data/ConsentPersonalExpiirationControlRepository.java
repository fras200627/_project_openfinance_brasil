package com.ofb.consents.repository.data;

import com.ofb.consents.entity.ConsentPersonalDataExpirationControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsentPersonalExpiirationControlRepository extends JpaRepository<ConsentPersonalDataExpirationControl, Long>,
                                                        JpaSpecificationExecutor<ConsentPersonalDataExpirationControl> {

}
