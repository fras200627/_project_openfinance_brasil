package com.ofb.customers.service;

import com.google.gson.Gson;
import com.ofb.customers.model.PersonalDataModel;
import com.ofb.customers.repository.PersonalDataRepository;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.BadRequestException;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service @Slf4j
public class CustomerGetPersonalDataService {

    @Autowired private HttpServletRequest httpServletRequest;
    @Autowired private PersonalDataRepository personalDataRepository;

    private Gson gson = new Gson();
    private List<ResponseErrorsInnerTemplate>   listResponseErrors;

    public PersonalDataModel getPersonalData(String customerDocument,
                                              String personalId) {

        PersonalDataModel personalData = null;
        listResponseErrors = new ArrayList<>();
        String bearerToken = httpServletRequest.getHeader("Authorization");

        if ((customerDocument == null || customerDocument.trim().length() == 0) && (personalId == null || personalId.trim().length() == 0)) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("CustomerDocument OR PersonalID needs to be entered.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        try {
            if (customerDocument != null && customerDocument.trim().length() != 0) {
                personalData = personalDataRepository.findByDocument(customerDocument.trim());
            } else
            if (personalId != null && personalId.trim().length() != 0) {
                personalData = personalDataRepository.findById(personalId.trim()).get();
            }
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

        if (personalData == null) {
            listResponseErrors.add(new ResponseErrorsInnerTemplate().toBuilder()
                    .title("Get Customer request error")
                    .code(ResponseOFBCodesEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("An error occurred in request: Customer not exists.")
                    .build());
            throw new BadRequestException(gson.toJson(listResponseErrors));
        }

        return personalData;
    }

}
