package com.ofb.consents.repository.data;

import com.ofb.consents.entity.ConsentPermissionsAuthorised;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsentPermissionsAuthorisedRepository extends JpaRepository<ConsentPermissionsAuthorised, Long>,
                                                        JpaSpecificationExecutor<ConsentPermissionsAuthorised> {

}
