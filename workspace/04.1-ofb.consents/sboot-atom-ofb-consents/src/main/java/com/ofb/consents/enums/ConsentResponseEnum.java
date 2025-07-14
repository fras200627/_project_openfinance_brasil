package com.ofb.consents.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ofb.consents.server.consents.resources.model.ResponseErrorUnprocessableEntityErrorsInner;

public class ConsentResponseEnum extends ResponseErrorUnprocessableEntityErrorsInner {

    public enum CodeEnum {
        /// from ResponseErrorUnprocessableEntityErrorsInner
        SEM_PERMISSOES_FUNCIONAIS_RESTANTES("SEM_PERMISSOES_FUNCIONAIS_RESTANTES"),
        INFORMACOES_PJ_NAO_INFORMADAS("INFORMACOES_PJ_NAO_INFORMADAS"),
        PERMISSOES_PJ_INCORRETAS("PERMISSOES_PJ_INCORRETAS"),
        PERMISSAO_PF_PJ_EM_CONJUNTO("PERMISSAO_PF_PJ_EM_CONJUNTO"),
        COMBINACAO_PERMISSOES_INCORRETA("COMBINACAO_PERMISSOES_INCORRETA"),
        DATA_EXPIRACAO_INVALIDA("DATA_EXPIRACAO_INVALIDA"),
        ERRO_NAO_MAPEADO("ERRO_NAO_MAPEADO"),
        /// Addiconal
        REGISTERED_CLIENT_NOT_EXISTS("REGISTERED_CLIENT_NOT_FOUND"),
        REGISTERED_CLIENT_IS_LOCKED("REGISTRED_CLIENT_IS_LOCKED"),
        REGISTERED_CLIENT_DOES_NOT_HAVE_READ_AUTHORIZATION("REGISTERED_CLIENT_DOES_NOT_HAVE_READ_AUTHORIZATION"),
        REGISTERED_CLIENT_DOES_NOT_HAVE_WRITE_AUTHORIZATION("REGISTERED_CLIENT_DOES_NOT_HAVE_WRITE_AUTHORIZATION"),
        REGISTERED_CLIENT_DOES_NOT_HAVE_READ_OR_WRITE_AUTHORIZATION("REGISTERED_CLIENT_DOES_NOT_HAVE_READ_OR_WRITE_AUTHORIZATION"),
        REGISTERED_CLIENT_SERVICE_UNAVAILABLE("REGISTERED_CLIENT_SERVICE_UNAVAILABLE"),
        LOGGED_USER_NOT_EXISTS("LOGGED_USER_NOT_EXISTS"),
        LOGGED_USER_SERVICE_UNAVAILABLE("LOGGED_USER_SERVICE_UNAVAILABLE"),
        ONE_CONSENT_FOR_LOGGED_USER_ALREADY_EXISTS("ONE_CONSENT_FOR_LOGGED_USER_ALREADY_EXISTS"),
        ERROR_CREATION_CONSENT_REQUIRED("ERROR_CREATION_CONSENT_REQUIRED"),
        ERROR_CREATION_CONSENT_PERMISSIONS_REQUIRED("ERROR_CREATION_CONSENT_PERMISSIONS_REQUIRED");

        private String value;

        CodeEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static ConsentResponseEnum.CodeEnum fromValue(String value) {
            for (ConsentResponseEnum.CodeEnum b : ConsentResponseEnum.CodeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private ConsentResponseEnum.CodeEnum code;

}
