package com.ofb.consents.repository.data;

import com.ofb.consents.entity.ConsentPermissionsRequested;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsentPermissionsRequestedRepository extends JpaRepository<ConsentPermissionsRequested, Long>,
                                                        JpaSpecificationExecutor<ConsentPermissionsRequested> {

}
