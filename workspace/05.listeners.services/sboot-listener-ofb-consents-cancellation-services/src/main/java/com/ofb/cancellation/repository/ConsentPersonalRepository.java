package com.ofb.cancellation.repository;

import com.ofb.cancellation.entity.ConsentPersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsentPersonalRepository extends JpaRepository<ConsentPersonalData, String>,
                                                        JpaSpecificationExecutor<ConsentPersonalData> {

}
