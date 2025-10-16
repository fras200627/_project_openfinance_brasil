package com.ofb.accounts.service;

import com.google.gson.Gson;
import com.ofb.accounts.model.AccountPersonalDataModel;
import com.ofb.accounts.repository.AccountPersonalDataRepository;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class AccountsGetAccountDataByIdService {

    @Autowired
    private AccountPersonalDataRepository accountPersonalRepository;

    private Gson gson = new Gson();
    private List<ResponseErrorsInnerTemplate> listResponseErrors;

    public AccountPersonalDataModel getAccountDataById(String accountId) {

        AccountPersonalDataModel accountPersonalData = null;
        listResponseErrors = new ArrayList<>();

        try {
            accountPersonalData = accountPersonalRepository.findAccountByAccountId(accountId);
        } catch (NoSuchElementException e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("An error occurred in request: Customer not exists.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        } catch (Exception e) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new InternalErrorException(gson.toJson(listResponseErrors));
        }

        if (accountPersonalData == null) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("An error occurred in request: Customer not exists.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        return accountPersonalData;
    }
}
