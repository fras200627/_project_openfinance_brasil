package com.ofb.authorization.repository.data;

import com.ofb.authorization.entity.ConsentResourcesPermissionsAuthorised;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsentResourcesPermissionsConfirmedRepository extends JpaRepository<ConsentResourcesPermissionsAuthorised, Long>,
                                                        JpaSpecificationExecutor<ConsentResourcesPermissionsAuthorised> {

}
