package com.tican.open.finance.template_accounts_model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * O campo deve classificar a transação em um dos tipos descritos.  O transmissor deve classificar as transações disponíveis associando-a a um dos itens do Enum listado neste campo.  A opção OUTROS só deve ser utilizada para os casos em que de fato a transação compartilhada não possa ser classificada como um dos itens deste Enum.  Por exemplo no caso de recebimento de pensão alimentícia. 
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public enum EnumTransactionTypes {
  
  TED("TED"),
  
  DOC("DOC"),
  
  PIX("PIX"),
  
  TRANSFERENCIA_MESMA_INSTITUICAO("TRANSFERENCIA_MESMA_INSTITUICAO"),
  
  BOLETO("BOLETO"),
  
  CONVENIO_ARRECADACAO("CONVENIO_ARRECADACAO"),
  
  PACOTE_TARIFA_SERVICOS("PACOTE_TARIFA_SERVICOS"),
  
  TARIFA_SERVICOS_AVULSOS("TARIFA_SERVICOS_AVULSOS"),
  
  FOLHA_PAGAMENTO("FOLHA_PAGAMENTO"),
  
  DEPOSITO("DEPOSITO"),
  
  SAQUE("SAQUE"),
  
  CARTAO("CARTAO"),
  
  ENCARGOS_JUROS_CHEQUE_ESPECIAL("ENCARGOS_JUROS_CHEQUE_ESPECIAL"),
  
  RENDIMENTO_APLIC_FINANCEIRA("RENDIMENTO_APLIC_FINANCEIRA"),
  
  PORTABILIDADE_SALARIO("PORTABILIDADE_SALARIO"),
  
  RESGATE_APLIC_FINANCEIRA("RESGATE_APLIC_FINANCEIRA"),
  
  OPERACAO_CREDITO("OPERACAO_CREDITO"),
  
  OUTROS("OUTROS");

  private String value;

  EnumTransactionTypes(String value) {
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
  public static EnumTransactionTypes fromValue(String value) {
    for (EnumTransactionTypes b : EnumTransactionTypes.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

