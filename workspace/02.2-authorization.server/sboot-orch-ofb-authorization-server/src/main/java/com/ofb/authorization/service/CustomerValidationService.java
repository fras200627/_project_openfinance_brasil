package com.ofb.authorization.service;

import com.google.gson.Gson;
import com.ofb.authorization.client.consents.model.*;
import com.ofb.authorization.model.ResponseValidateConsentModel;
import com.ofb.lib.handlers.enums.ResponseOFBCodesEnum;
import com.ofb.lib.handlers.exception.ofb.InternalErrorException;
import com.ofb.lib.handlers.exception.ofb.UnprocessedEntityException;
import com.ofb.lib.handlers.exception.template.ResponseErrorsInnerTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Passo 04: Verificação do Client (customer.document)
 *     Customer existe na base de customers ?
 *     Customer está ativo e desbloqueado?
 */
@Service @Slf4j
public class CustomerValidationService {

    // Use API CUstomers

}
