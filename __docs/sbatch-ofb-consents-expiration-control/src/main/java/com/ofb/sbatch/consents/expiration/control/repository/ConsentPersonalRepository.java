package com.ofb.sbatch.consents.expiration.control.repository;

import com.ofb.sbatch.consents.expiration.control.entity.ConsentPersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsentPersonalRepository extends JpaRepository<ConsentPersonalData, String>,
                                                        JpaSpecificationExecutor<ConsentPersonalData> {

}
