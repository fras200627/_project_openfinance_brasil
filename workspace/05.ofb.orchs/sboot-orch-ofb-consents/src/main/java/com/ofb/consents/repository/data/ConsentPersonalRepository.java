package com.ofb.consents.repository.data;

import com.ofb.consents.entity.ConsentPersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsentPersonalRepository extends JpaRepository<ConsentPersonalData, String>,
                                                        JpaSpecificationExecutor<ConsentPersonalData> {

}
