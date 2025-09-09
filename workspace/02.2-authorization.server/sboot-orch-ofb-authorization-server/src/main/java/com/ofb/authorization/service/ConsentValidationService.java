package com.ofb.authorization.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Passo 05: Verificação do Consentimento (ofb.consent.id)
 *     Consentimento existe na base de consentimentos?
 *     Consentimento está com status AUTHORISED?
 *     Consentimento expirou ?
 */
@Service @Slf4j
public class ConsentValidationService {

    public void consentValidate(Object objectData, Object referenceId) {
    }

}
