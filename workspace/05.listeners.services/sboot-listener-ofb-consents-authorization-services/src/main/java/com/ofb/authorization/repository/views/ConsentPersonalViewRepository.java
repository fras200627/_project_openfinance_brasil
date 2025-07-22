package com.ofb.authorization.repository.views;

import com.ofb.authorization.model.ConsentPersonalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsentPersonalViewRepository extends JpaRepository<ConsentPersonalModel, String>,
                                                        JpaSpecificationExecutor<ConsentPersonalModel> {

    @Query("SELECT a FROM ConsentPersonalView a WHERE a.cpfNumber = :document and a.status in ('AWAITING_AUTHORISATION', 'AUTHORISED')")
    public List<ConsentPersonalModel> findAllConsentsEnabledByDocumentIdentification(@Param("document")  String document);
}
