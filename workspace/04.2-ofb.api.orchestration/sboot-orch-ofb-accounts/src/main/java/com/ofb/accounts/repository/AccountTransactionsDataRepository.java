package com.ofb.accounts.repository;

import com.ofb.accounts.model.AccountTransactionDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountTransactionsDataRepository extends JpaRepository<AccountTransactionDataModel, String>,
                                                       JpaSpecificationExecutor<AccountTransactionDataModel> {

    @Query("SELECT a FROM AccountTransactionDataView a WHERE a.accountId = :accountId")
    List<AccountTransactionDataModel> findTransactionsByAccountId(@Param("accountId") String accountId);

}
