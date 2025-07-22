package com.ofb.authorization.repository.data;

import com.ofb.authorization.entity.ConsentPermissionsAuthorised;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsentPermissionsAuthorisedRepository extends JpaRepository<ConsentPermissionsAuthorised, Long>,
                                                        JpaSpecificationExecutor<ConsentPermissionsAuthorised> {

}
