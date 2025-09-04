package com.ofb.consents.repository.data;

import com.ofb.consents.entity.ConsentResourcesPermissionsConfirmed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsentResourcesPermissionsConfirmedRepository extends JpaRepository<ConsentResourcesPermissionsConfirmed, Long>,
                                                        JpaSpecificationExecutor<ConsentResourcesPermissionsConfirmed> {

}
