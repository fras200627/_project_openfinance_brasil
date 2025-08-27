package com.ofb.sbatch.consents.approval.control.repository;

import com.ofb.sbatch.consents.approval.control.entity.ConsentPersonalData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ConsentPersonalRepository extends JpaRepository<ConsentPersonalData, String>,
                                                        JpaSpecificationExecutor<ConsentPersonalData> {

    @Query("SELECT a FROM ConsentPersonalData a WHERE a.status = 'REJECTED'")
    Page<ConsentPersonalData> findAllRejected(Pageable pageable);

    @Query("SELECT a FROM ConsentPersonalData a WHERE a.status = 'AWAITING_AUTHORISATION'")
    Page<ConsentPersonalData> findAllConsentsApprovalExpired(Pageable pageable);

    Page<ConsentPersonalData> findByStatus(String status, Pageable pageable);

}
