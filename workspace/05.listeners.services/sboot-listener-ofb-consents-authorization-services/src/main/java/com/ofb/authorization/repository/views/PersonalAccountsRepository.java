package com.ofb.authorization.repository.views;

import com.ofb.authorization.model.PersonalAccountsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonalAccountsRepository extends JpaRepository<PersonalAccountsModel, String>,
                                                        JpaSpecificationExecutor<PersonalAccountsModel> {

    @Query("SELECT a FROM PersonalAccountView a WHERE a.personalid = :personalid")
    public List<PersonalAccountsModel> findAllAccountsByPersonalId(@Param("personalid")  String personalid);
}
