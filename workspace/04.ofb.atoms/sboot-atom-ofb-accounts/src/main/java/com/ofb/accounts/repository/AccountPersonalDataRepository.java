package com.ofb.accounts.repository;

import com.ofb.accounts.model.AccountPersonalDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountPersonalDataRepository extends JpaRepository<AccountPersonalDataModel, String>,
                                                       JpaSpecificationExecutor<AccountPersonalDataModel> {

    @Query("SELECT a FROM AccountPersonalDataView a WHERE a.accountId = :accountId")
    AccountPersonalDataModel findAccountByAccountId(@Param("accountId") String accountId);

}
