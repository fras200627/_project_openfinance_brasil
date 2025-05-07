package com.tican.open.finance.template_accounts_handler;

import org.springframework.format.annotation.DateTimeFormat;
import com.tican.open.finance.template_accounts_model.EnumAccountType;
import com.tican.open.finance.template_accounts_model.EnumCreditDebitIndicator;
import java.time.LocalDate;
import com.tican.open.finance.template_accounts_model.ResponseAccountBalances;
import com.tican.open.finance.template_accounts_model.ResponseAccountIdentification;
import com.tican.open.finance.template_accounts_model.ResponseAccountList;
import com.tican.open.finance.template_accounts_model.ResponseAccountOverdraftLimits;
import com.tican.open.finance.template_accounts_model.ResponseAccountTransactions;
import com.tican.open.finance.template_accounts_model.ResponseError;
import com.tican.open.finance.template_accounts_model.ResponseErrorMetaSingle;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link AccountsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:09.506997400-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public interface AccountsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /accounts : Obtém a lista de contas consentidas pelo cliente.
     * Método para obter a lista de contas depósito à vista, poupança e pagamento pré-pagas mantidas pelo cliente na instituição transmissora e para as quais ele tenha fornecido consentimento.
     *
     * @param authorization Cabeçalho HTTP padrão. Permite que as credenciais sejam fornecidas dependendo do tipo de recurso solicitado (required)
     * @param xFapiInteractionId Um UUID [RFC4122](https://tools.ietf.org/html/rfc4122) usado como um ID de correlação entre request e response. Campo de geração e envio obrigatório pela receptora (client) e o seu valor deve ser “espelhado” pela transmissora (server) no cabeçalho de resposta. Caso não seja recebido ou se for recebido um valor inválido, a transmissora deve gerar um x-fapi-interaction-id e retorná-lo na resposta com o HTTP Status Code 400. A receptora deve acatar o valor recebido da transmissora. (required)
     * @param xFapiAuthDate Data em que o usuário logou pela última vez com o receptor. Representada de acordo com a [RFC7231](https://tools.ietf.org/html/rfc7231).Exemplo: Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress O endereço IP do usuário se estiver atualmente logado com o receptor. (optional)
     * @param xCustomerUserAgent Indica o user-agent que o usuário utiliza. (optional)
     * @param page Número da página que está sendo requisitada (o valor da primeira página é 1). (optional, default to 1)
     * @param pageSize Quantidade total de registros por páginas. (optional, default to 25)
     * @param accountType Tipos de contas. Modalidades tradicionais previstas pela Resolução 4.753, não contemplando contas vinculadas, conta de domiciliados no exterior, contas em moedas estrangeiras e conta correspondente moeda eletrônica. Vide Enum. (optional)
     * @param paginationKey Identificador de rechamada, utilizado para evitar a contagem de chamadas ao endpoint durante a paginação. (optional)
     * @return Dados de identificação das contas obtidos com sucesso. (status code 200)
     *         or A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL. (status code 400)
     *         or Cabeçalho de autenticação ausente/inválido ou token inválido (status code 401)
     *         or O token tem escopo incorreto ou uma política de segurança foi violada (status code 403)
     *         or O recurso solicitado não existe ou não foi implementado (status code 404)
     *         or O consumidor tentou acessar o recurso com um método não suportado (status code 405)
     *         or A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8 (status code 406)
     *         or A sintaxe da requisição esta correta, mas não foi possível processar as instruções presentes. (status code 422)
     *         or Locked (status code 423)
     *         or A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido (status code 429)
     *         or Ocorreu um erro no gateway da API ou no microsserviço (status code 500)
     *         or GATEWAY TIMEOUT - A requisição não foi atendida dentro do tempo limite estabelecido (status code 504)
     *         or O site está sobrecarregado e a operação foi recusada, pois foi atingido o limite máximo de TPS global, neste momento. (status code 529)
     *         or Erro inesperado. (status code 200)
     * @see AccountsApi#accountsGetAccounts
     */
    default ResponseEntity<ResponseAccountList> accountsGetAccounts(String authorization,
        UUID xFapiInteractionId,
        String xFapiAuthDate,
        String xFapiCustomerIpAddress,
        String xCustomerUserAgent,
        Integer page,
        Integer pageSize,
        EnumAccountType accountType,
        String paginationKey) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"data\" : [ { \"branchCode\" : \"6272\", \"number\" : \"94088392\", \"accountId\" : \"92792126019929279212650822221989319252576\", \"brandName\" : \"Organização A\", \"companyCnpj\" : \"21128159000166\", \"type\" : \"CONTA_DEPOSITO_A_VISTA\", \"checkDigit\" : \"4\", \"compeCode\" : \"001\" }, { \"branchCode\" : \"6272\", \"number\" : \"94088392\", \"accountId\" : \"92792126019929279212650822221989319252576\", \"brandName\" : \"Organização A\", \"companyCnpj\" : \"21128159000166\", \"type\" : \"CONTA_DEPOSITO_A_VISTA\", \"checkDigit\" : \"4\", \"compeCode\" : \"001\" } ], \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"links\" : { \"next\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"last\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"prev\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"self\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"first\" : \"https://api.banco.com.br/open-banking/api/v2/resource\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /accounts/{accountId} : Obtém os dados de identificação da conta identificada por accountId.
     * Método para obter os dados de identificação da conta de depósito à vista, poupança ou pagamento pré-paga identificada por accountId mantida pelo cliente na instituição transmissora.
     *
     * @param authorization Cabeçalho HTTP padrão. Permite que as credenciais sejam fornecidas dependendo do tipo de recurso solicitado (required)
     * @param xFapiInteractionId Um UUID [RFC4122](https://tools.ietf.org/html/rfc4122) usado como um ID de correlação entre request e response. Campo de geração e envio obrigatório pela receptora (client) e o seu valor deve ser “espelhado” pela transmissora (server) no cabeçalho de resposta. Caso não seja recebido ou se for recebido um valor inválido, a transmissora deve gerar um x-fapi-interaction-id e retorná-lo na resposta com o HTTP Status Code 400. A receptora deve acatar o valor recebido da transmissora. (required)
     * @param accountId Identificador da conta de depósito à vista, de poupança ou de pagamento pré-paga. (required)
     * @param xFapiAuthDate Data em que o usuário logou pela última vez com o receptor. Representada de acordo com a [RFC7231](https://tools.ietf.org/html/rfc7231).Exemplo: Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress O endereço IP do usuário se estiver atualmente logado com o receptor. (optional)
     * @param xCustomerUserAgent Indica o user-agent que o usuário utiliza. (optional)
     * @return Dados de identificação da conta identificada por accountId obtidos com sucesso. (status code 200)
     *         or A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL. (status code 400)
     *         or Cabeçalho de autenticação ausente/inválido ou token inválido (status code 401)
     *         or O token tem escopo incorreto ou uma política de segurança foi violada (status code 403)
     *         or O recurso solicitado não existe ou não foi implementado (status code 404)
     *         or O consumidor tentou acessar o recurso com um método não suportado (status code 405)
     *         or A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8 (status code 406)
     *         or A sintaxe da requisição esta correta, mas não foi possível processar as instruções presentes. (status code 422)
     *         or Locked (status code 423)
     *         or A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido (status code 429)
     *         or Ocorreu um erro no gateway da API ou no microsserviço (status code 500)
     *         or GATEWAY TIMEOUT - A requisição não foi atendida dentro do tempo limite estabelecido (status code 504)
     *         or O site está sobrecarregado e a operação foi recusada, pois foi atingido o limite máximo de TPS global, neste momento. (status code 529)
     *         or Erro inesperado. (status code 200)
     * @see AccountsApi#accountsGetAccountsAccountId
     */
    default ResponseEntity<ResponseAccountIdentification> accountsGetAccountsAccountId(String authorization,
        UUID xFapiInteractionId,
        String accountId,
        String xFapiAuthDate,
        String xFapiCustomerIpAddress,
        String xCustomerUserAgent) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"data\" : { \"branchCode\" : \"6272\", \"number\" : \"24550245\", \"subtype\" : \"INDIVIDUAL\", \"currency\" : \"BRL\", \"checkDigit\" : \"4\", \"type\" : \"CONTA_DEPOSITO_A_VISTA\", \"compeCode\" : \"001\" }, \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"links\" : { \"next\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"last\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"prev\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"self\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"first\" : \"https://api.banco.com.br/open-banking/api/v2/resource\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /accounts/{accountId}/balances : Obtém os saldos da conta identificada por accountId.
     * Método para obter os saldos da conta de depósito à vista, poupança ou pagamento pré-paga identificada por accountId mantida pelo cliente na instituição transmissora.
     *
     * @param authorization Cabeçalho HTTP padrão. Permite que as credenciais sejam fornecidas dependendo do tipo de recurso solicitado (required)
     * @param xFapiInteractionId Um UUID [RFC4122](https://tools.ietf.org/html/rfc4122) usado como um ID de correlação entre request e response. Campo de geração e envio obrigatório pela receptora (client) e o seu valor deve ser “espelhado” pela transmissora (server) no cabeçalho de resposta. Caso não seja recebido ou se for recebido um valor inválido, a transmissora deve gerar um x-fapi-interaction-id e retorná-lo na resposta com o HTTP Status Code 400. A receptora deve acatar o valor recebido da transmissora. (required)
     * @param accountId Identificador da conta de depósito à vista, de poupança ou de pagamento pré-paga. (required)
     * @param xFapiAuthDate Data em que o usuário logou pela última vez com o receptor. Representada de acordo com a [RFC7231](https://tools.ietf.org/html/rfc7231).Exemplo: Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress O endereço IP do usuário se estiver atualmente logado com o receptor. (optional)
     * @param xCustomerUserAgent Indica o user-agent que o usuário utiliza. (optional)
     * @return Dados relativos aos saldos da conta identificada por accountId obtidos com sucesso. (status code 200)
     *         or A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL. (status code 400)
     *         or Cabeçalho de autenticação ausente/inválido ou token inválido (status code 401)
     *         or O token tem escopo incorreto ou uma política de segurança foi violada (status code 403)
     *         or O recurso solicitado não existe ou não foi implementado (status code 404)
     *         or O consumidor tentou acessar o recurso com um método não suportado (status code 405)
     *         or A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8 (status code 406)
     *         or A sintaxe da requisição esta correta, mas não foi possível processar as instruções presentes. (status code 422)
     *         or Locked (status code 423)
     *         or A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido (status code 429)
     *         or Ocorreu um erro no gateway da API ou no microsserviço (status code 500)
     *         or GATEWAY TIMEOUT - A requisição não foi atendida dentro do tempo limite estabelecido (status code 504)
     *         or O site está sobrecarregado e a operação foi recusada, pois foi atingido o limite máximo de TPS global, neste momento. (status code 529)
     *         or Erro inesperado. (status code 200)
     * @see AccountsApi#accountsGetAccountsAccountIdBalances
     */
    default ResponseEntity<ResponseAccountBalances> accountsGetAccountsAccountIdBalances(String authorization,
        UUID xFapiInteractionId,
        String accountId,
        String xFapiAuthDate,
        String xFapiCustomerIpAddress,
        String xCustomerUserAgent) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"data\" : { \"availableAmount\" : { \"amount\" : \"1000.0400\", \"currency\" : \"BRL\" }, \"automaticallyInvestedAmount\" : { \"amount\" : \"1000.0400\", \"currency\" : \"BRL\" }, \"updateDateTime\" : \"2021-05-21T08:30:00Z\", \"blockedAmount\" : { \"amount\" : \"1000.0400\", \"currency\" : \"BRL\" } }, \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"links\" : { \"next\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"last\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"prev\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"self\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"first\" : \"https://api.banco.com.br/open-banking/api/v2/resource\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /accounts/{accountId}/overdraft-limits : Obtém os limites da conta identificada por accountId.
     * Método para obter os limites da conta de depósito à vista, poupança ou pagamento pré-paga identificada por accountId mantida pelo cliente na instituição transmissora. Para as instituições financeiras transmissoras que possuam contas sem limites associados devem retornar HTTP Status 200 com o objeto “data” vazio, sem nenhum atributo interno.
     *
     * @param authorization Cabeçalho HTTP padrão. Permite que as credenciais sejam fornecidas dependendo do tipo de recurso solicitado (required)
     * @param xFapiInteractionId Um UUID [RFC4122](https://tools.ietf.org/html/rfc4122) usado como um ID de correlação entre request e response. Campo de geração e envio obrigatório pela receptora (client) e o seu valor deve ser “espelhado” pela transmissora (server) no cabeçalho de resposta. Caso não seja recebido ou se for recebido um valor inválido, a transmissora deve gerar um x-fapi-interaction-id e retorná-lo na resposta com o HTTP Status Code 400. A receptora deve acatar o valor recebido da transmissora. (required)
     * @param accountId Identificador da conta de depósito à vista, de poupança ou de pagamento pré-paga. (required)
     * @param xFapiAuthDate Data em que o usuário logou pela última vez com o receptor. Representada de acordo com a [RFC7231](https://tools.ietf.org/html/rfc7231).Exemplo: Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress O endereço IP do usuário se estiver atualmente logado com o receptor. (optional)
     * @param xCustomerUserAgent Indica o user-agent que o usuário utiliza. (optional)
     * @return Dados de limites da conta identificada por accountId obtidos com sucesso. (status code 200)
     *         or A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL. (status code 400)
     *         or Cabeçalho de autenticação ausente/inválido ou token inválido (status code 401)
     *         or O token tem escopo incorreto ou uma política de segurança foi violada (status code 403)
     *         or O recurso solicitado não existe ou não foi implementado (status code 404)
     *         or O consumidor tentou acessar o recurso com um método não suportado (status code 405)
     *         or A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8 (status code 406)
     *         or A sintaxe da requisição esta correta, mas não foi possível processar as instruções presentes. (status code 422)
     *         or Locked (status code 423)
     *         or A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido (status code 429)
     *         or Ocorreu um erro no gateway da API ou no microsserviço (status code 500)
     *         or GATEWAY TIMEOUT - A requisição não foi atendida dentro do tempo limite estabelecido (status code 504)
     *         or O site está sobrecarregado e a operação foi recusada, pois foi atingido o limite máximo de TPS global, neste momento. (status code 529)
     *         or Erro inesperado. (status code 200)
     * @see AccountsApi#accountsGetAccountsAccountIdOverdraftLimits
     */
    default ResponseEntity<ResponseAccountOverdraftLimits> accountsGetAccountsAccountIdOverdraftLimits(String authorization,
        UUID xFapiInteractionId,
        String accountId,
        String xFapiAuthDate,
        String xFapiCustomerIpAddress,
        String xCustomerUserAgent) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"data\" : { \"unarrangedOverdraftAmount\" : { \"amount\" : \"1000.0400\", \"currency\" : \"BRL\" }, \"overdraftUsedLimit\" : { \"amount\" : \"1000.0400\", \"currency\" : \"BRL\" }, \"overdraftContractedLimit\" : { \"amount\" : \"1000.0400\", \"currency\" : \"BRL\" } }, \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"links\" : { \"next\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"last\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"prev\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"self\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"first\" : \"https://api.banco.com.br/open-banking/api/v2/resource\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /accounts/{accountId}/transactions : Obtém a lista de transações da conta identificada por accountId.
     * Método para obter a lista de transações da conta de depósito à vista, poupança ou pagamento pré-paga identificada por accountId mantida pelo cliente na instituição transmissora. É permitida uma consulta máxima que se estenda em 12 meses no passado mais 12 meses no futuro.
     *
     * @param authorization Cabeçalho HTTP padrão. Permite que as credenciais sejam fornecidas dependendo do tipo de recurso solicitado (required)
     * @param xFapiInteractionId Um UUID [RFC4122](https://tools.ietf.org/html/rfc4122) usado como um ID de correlação entre request e response. Campo de geração e envio obrigatório pela receptora (client) e o seu valor deve ser “espelhado” pela transmissora (server) no cabeçalho de resposta. Caso não seja recebido ou se for recebido um valor inválido, a transmissora deve gerar um x-fapi-interaction-id e retorná-lo na resposta com o HTTP Status Code 400. A receptora deve acatar o valor recebido da transmissora. (required)
     * @param accountId Identificador da conta de depósito à vista, de poupança ou de pagamento pré-paga. (required)
     * @param xFapiAuthDate Data em que o usuário logou pela última vez com o receptor. Representada de acordo com a [RFC7231](https://tools.ietf.org/html/rfc7231).Exemplo: Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress O endereço IP do usuário se estiver atualmente logado com o receptor. (optional)
     * @param xCustomerUserAgent Indica o user-agent que o usuário utiliza. (optional)
     * @param page Número da página que está sendo requisitada (o valor da primeira página é 1). (optional, default to 1)
     * @param pageSize Quantidade total de registros por páginas. (optional, default to 25)
     * @param fromBookingDate Data inicial de filtragem. [Restrição] Deve obrigatoriamente ser enviado caso o campo toBookingDate seja informado. Caso não seja informado, deve ser assumido o dia atual. (optional)
     * @param toBookingDate Data final de filtragem. [Restrição] Deve obrigatoriamente ser enviado caso o campo fromBookingDate seja informado. Caso não seja informado, deve ser assumido o dia atual. (optional)
     * @param creditDebitIndicator Indicador do tipo de lançamento (optional)
     * @param paginationKey Identificador de rechamada, utilizado para evitar a contagem de chamadas ao endpoint durante a paginação. (optional)
     * @return Dados da lista de transações da conta identificada por accountId obtidos com sucesso. (status code 200)
     *         or A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL. (status code 400)
     *         or Cabeçalho de autenticação ausente/inválido ou token inválido (status code 401)
     *         or O token tem escopo incorreto ou uma política de segurança foi violada (status code 403)
     *         or O recurso solicitado não existe ou não foi implementado (status code 404)
     *         or O consumidor tentou acessar o recurso com um método não suportado (status code 405)
     *         or A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8 (status code 406)
     *         or A sintaxe da requisição esta correta, mas não foi possível processar as instruções presentes. (status code 422)
     *         or Locked (status code 423)
     *         or A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido (status code 429)
     *         or Ocorreu um erro no gateway da API ou no microsserviço (status code 500)
     *         or GATEWAY TIMEOUT - A requisição não foi atendida dentro do tempo limite estabelecido (status code 504)
     *         or O site está sobrecarregado e a operação foi recusada, pois foi atingido o limite máximo de TPS global, neste momento. (status code 529)
     *         or Erro inesperado. (status code 200)
     * @see AccountsApi#accountsGetAccountsAccountIdTransactions
     */
    default ResponseEntity<ResponseAccountTransactions> accountsGetAccountsAccountIdTransactions(String authorization,
        UUID xFapiInteractionId,
        String accountId,
        String xFapiAuthDate,
        String xFapiCustomerIpAddress,
        String xCustomerUserAgent,
        Integer page,
        Integer pageSize,
        LocalDate fromBookingDate,
        LocalDate toBookingDate,
        EnumCreditDebitIndicator creditDebitIndicator,
        String paginationKey) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"data\" : [ { \"partieBranchCode\" : \"6272\", \"transactionDateTime\" : \"2016-01-29T12:29:03.374Z\", \"partiePersonType\" : \"PESSOA_NATURAL\", \"creditDebitType\" : \"DEBITO\", \"transactionName\" : \"TRANSFCWAR5TXHCX5I9IDBHML8082N8NEO30M6LNNG7ANAYIJYRM00ZBZPU8\", \"type\" : \"PIX\", \"partieCheckDigit\" : \"4\", \"transactionId\" : \"TXpRMU9UQTROMWhZV2xSU1FUazJSMDl\", \"completedAuthorisedPaymentType\" : \"TRANSACAO_EFETIVADA\", \"partieCompeCode\" : \"001\", \"transactionAmount\" : { \"amount\" : \"1000.0400\", \"currency\" : \"BRL\" }, \"partieNumber\" : \"67890854360\", \"partieCnpjCpf\" : \"43908445778\" }, { \"partieBranchCode\" : \"6272\", \"transactionDateTime\" : \"2016-01-29T12:29:03.374Z\", \"partiePersonType\" : \"PESSOA_NATURAL\", \"creditDebitType\" : \"DEBITO\", \"transactionName\" : \"TRANSFCWAR5TXHCX5I9IDBHML8082N8NEO30M6LNNG7ANAYIJYRM00ZBZPU8\", \"type\" : \"PIX\", \"partieCheckDigit\" : \"4\", \"transactionId\" : \"TXpRMU9UQTROMWhZV2xSU1FUazJSMDl\", \"completedAuthorisedPaymentType\" : \"TRANSACAO_EFETIVADA\", \"partieCompeCode\" : \"001\", \"transactionAmount\" : { \"amount\" : \"1000.0400\", \"currency\" : \"BRL\" }, \"partieNumber\" : \"67890854360\", \"partieCnpjCpf\" : \"43908445778\" } ], \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"links\" : { \"next\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"prev\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"self\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"first\" : \"https://api.banco.com.br/open-banking/api/v2/resource\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /accounts/{accountId}/transactions-current : Obtém a lista de transações recentes (últimos 7 dias) da conta identificada por accountId.
     * Método para obter a lista de transações da conta de depósito à vista, poupança ou pagamento pré-paga identificada por accountId mantida pelo cliente na instituição transmissora. É permitida uma consulta máxima que se estenda em 7 dias no passado mais 12 meses no futuro.
     *
     * @param authorization Cabeçalho HTTP padrão. Permite que as credenciais sejam fornecidas dependendo do tipo de recurso solicitado (required)
     * @param xFapiInteractionId Um UUID [RFC4122](https://tools.ietf.org/html/rfc4122) usado como um ID de correlação entre request e response. Campo de geração e envio obrigatório pela receptora (client) e o seu valor deve ser “espelhado” pela transmissora (server) no cabeçalho de resposta. Caso não seja recebido ou se for recebido um valor inválido, a transmissora deve gerar um x-fapi-interaction-id e retorná-lo na resposta com o HTTP Status Code 400. A receptora deve acatar o valor recebido da transmissora. (required)
     * @param accountId Identificador da conta de depósito à vista, de poupança ou de pagamento pré-paga. (required)
     * @param xFapiAuthDate Data em que o usuário logou pela última vez com o receptor. Representada de acordo com a [RFC7231](https://tools.ietf.org/html/rfc7231).Exemplo: Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress O endereço IP do usuário se estiver atualmente logado com o receptor. (optional)
     * @param xCustomerUserAgent Indica o user-agent que o usuário utiliza. (optional)
     * @param page Número da página que está sendo requisitada (o valor da primeira página é 1). (optional, default to 1)
     * @param pageSize Quantidade total de registros por páginas. (optional, default to 25)
     * @param fromBookingDate Data inicial de filtragem. O período máximo utilizado no filtro é de 7 dias inclusive (D-6).    [Restrição] Deve obrigatoriamente ser enviado caso o campo toBookingDate seja informado.  Caso não seja informado, deve ser assumido o dia atual.  (optional)
     * @param toBookingDate Data final de filtragem. O período máximo utilizado no filtro é de 7 dias inclusive (D-6).    [Restrição] Deve obrigatoriamente ser enviado caso o campo fromBookingDate seja informado.  Caso não seja informado, deve ser assumido o dia atual.  (optional)
     * @param creditDebitIndicator Indicador do tipo de lançamento (optional)
     * @param paginationKey Identificador de rechamada, utilizado para evitar a contagem de chamadas ao endpoint durante a paginação. (optional)
     * @return Dados da lista de transações da conta identificada por accountId obtidos com sucesso. (status code 200)
     *         or A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL. (status code 400)
     *         or Cabeçalho de autenticação ausente/inválido ou token inválido (status code 401)
     *         or O token tem escopo incorreto ou uma política de segurança foi violada (status code 403)
     *         or O recurso solicitado não existe ou não foi implementado (status code 404)
     *         or O consumidor tentou acessar o recurso com um método não suportado (status code 405)
     *         or A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8 (status code 406)
     *         or A sintaxe da requisição esta correta, mas não foi possível processar as instruções presentes. (status code 422)
     *         or Locked (status code 423)
     *         or A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido (status code 429)
     *         or Ocorreu um erro no gateway da API ou no microsserviço (status code 500)
     *         or GATEWAY TIMEOUT - A requisição não foi atendida dentro do tempo limite estabelecido (status code 504)
     *         or O site está sobrecarregado e a operação foi recusada, pois foi atingido o limite máximo de TPS global, neste momento. (status code 529)
     *         or Erro inesperado. (status code 200)
     * @see AccountsApi#accountsGetAccountsAccountIdTransactionsCurrent
     */
    default ResponseEntity<ResponseAccountTransactions> accountsGetAccountsAccountIdTransactionsCurrent(String authorization,
        UUID xFapiInteractionId,
        String accountId,
        String xFapiAuthDate,
        String xFapiCustomerIpAddress,
        String xCustomerUserAgent,
        Integer page,
        Integer pageSize,
        LocalDate fromBookingDate,
        LocalDate toBookingDate,
        EnumCreditDebitIndicator creditDebitIndicator,
        String paginationKey) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"data\" : [ { \"partieBranchCode\" : \"6272\", \"transactionDateTime\" : \"2016-01-29T12:29:03.374Z\", \"partiePersonType\" : \"PESSOA_NATURAL\", \"creditDebitType\" : \"DEBITO\", \"transactionName\" : \"TRANSFCWAR5TXHCX5I9IDBHML8082N8NEO30M6LNNG7ANAYIJYRM00ZBZPU8\", \"type\" : \"PIX\", \"partieCheckDigit\" : \"4\", \"transactionId\" : \"TXpRMU9UQTROMWhZV2xSU1FUazJSMDl\", \"completedAuthorisedPaymentType\" : \"TRANSACAO_EFETIVADA\", \"partieCompeCode\" : \"001\", \"transactionAmount\" : { \"amount\" : \"1000.0400\", \"currency\" : \"BRL\" }, \"partieNumber\" : \"67890854360\", \"partieCnpjCpf\" : \"43908445778\" }, { \"partieBranchCode\" : \"6272\", \"transactionDateTime\" : \"2016-01-29T12:29:03.374Z\", \"partiePersonType\" : \"PESSOA_NATURAL\", \"creditDebitType\" : \"DEBITO\", \"transactionName\" : \"TRANSFCWAR5TXHCX5I9IDBHML8082N8NEO30M6LNNG7ANAYIJYRM00ZBZPU8\", \"type\" : \"PIX\", \"partieCheckDigit\" : \"4\", \"transactionId\" : \"TXpRMU9UQTROMWhZV2xSU1FUazJSMDl\", \"completedAuthorisedPaymentType\" : \"TRANSACAO_EFETIVADA\", \"partieCompeCode\" : \"001\", \"transactionAmount\" : { \"amount\" : \"1000.0400\", \"currency\" : \"BRL\" }, \"partieNumber\" : \"67890854360\", \"partieCnpjCpf\" : \"43908445778\" } ], \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"links\" : { \"next\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"prev\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"self\" : \"https://api.banco.com.br/open-banking/api/v2/resource\", \"first\" : \"https://api.banco.com.br/open-banking/api/v2/resource\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json; charset=utf-8"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json; charset=utf-8", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
