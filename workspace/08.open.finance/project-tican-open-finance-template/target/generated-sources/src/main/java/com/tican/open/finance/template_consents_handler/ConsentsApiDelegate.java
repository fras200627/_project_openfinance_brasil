package com.tican.open.finance.template_consents_handler;

import com.tican.open.finance.template_consents_model.ConsentsPostConsents529Response;
import com.tican.open.finance.template_consents_model.CreateConsent;
import com.tican.open.finance.template_consents_model.CreateConsentExtensions;
import com.tican.open.finance.template_consents_model.Model422ResponseErrorCreateConsent;
import com.tican.open.finance.template_consents_model.ResponseConsent;
import com.tican.open.finance.template_consents_model.ResponseConsentExtensions;
import com.tican.open.finance.template_consents_model.ResponseConsentRead;
import com.tican.open.finance.template_consents_model.ResponseConsentReadExtensions;
import com.tican.open.finance.template_consents_model.ResponseError;
import com.tican.open.finance.template_consents_model.ResponseErrorUnprocessableEntity;
import com.tican.open.finance.template_consents_model.ResponseErrorUnprocessableEntityDelete;
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
 * A delegate to be called by the {@link ConsentsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:10.962348700-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public interface ConsentsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /consents/{consentId} : Deletar / Revogar o consentimento identificado por consentId.
     * Método para deletar / revogar o consentimento identificado por consentId.
     *
     * @param consentId O consentId é o identificador único do consentimento e deverá ser um URN - Uniform Resource Name.   Um URN, conforme definido na [RFC8141](https://tools.ietf.org/html/rfc8141) é um Uniform Resource  Identifier - URI - que é atribuído sob o URI scheme \&quot;urn\&quot; e um namespace URN específico, com a intenção de que o URN  seja um identificador de recurso persistente e independente da localização.   Considerando a string urn:bancoex:C1DD33123 como exemplo para consentId temos: - o namespace(urn) - o identificador associado ao namespace da instituição transnmissora (bancoex) - o identificador específico dentro do namespace (C1DD33123). Informações mais detalhadas sobre a construção de namespaces devem ser consultadas na [RFC8141](https://tools.ietf.org/html/rfc8141).  (required)
     * @param authorization Cabeçalho HTTP padrão. Permite que as credenciais sejam fornecidas dependendo do tipo de recurso solicitado (required)
     * @param xFapiInteractionId Um UUID RFC4122 usado como um ID de correlação entre request e response. Campo de geração e envio obrigatório pela receptora (client) e o seu valor deve ser “espelhado” pela transmissora (server) no cabeçalho de resposta. Caso não seja recebido ou se for recebido um valor inválido, a transmissora deve gerar um x-fapi-interaction-id e retorná-lo na resposta com o HTTP Status Code 400. A receptora deve acatar o valor recebido da transmissora. (required)
     * @param xFapiAuthDate Data em que o usuário logou pela última vez com o receptor. Representada de acordo com a [RFC7231](https://tools.ietf.org/html/rfc7231).Exemplo: Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress O endereço IP do usuário se estiver atualmente logado com o receptor. (optional)
     * @param xCustomerUserAgent Indica o user-agent que o usuário utiliza. (optional)
     * @return Consentimento revogado com sucesso. (status code 204)
     *         or A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL. (status code 400)
     *         or Cabeçalho de autenticação ausente/inválido ou token inválido (status code 401)
     *         or O token tem escopo incorreto ou uma política de segurança foi violada (status code 403)
     *         or O recurso solicitado não existe ou não foi implementado (status code 404)
     *         or O consumidor tentou acessar o recurso com um método não suportado (status code 405)
     *         or A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8 (status code 406)
     *         or A sintaxe da requisição esta correta, mas não foi possível processar as instruções presentes. (status code 422)
     *         or A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido (status code 429)
     *         or Ocorreu um erro no gateway da API ou no microsserviço (status code 500)
     *         or GATEWAY TIMEOUT - A requisição não foi atendida dentro do tempo limite estabelecido (status code 504)
     *         or O site está sobrecarregado e a operação foi recusada, pois foi atingido o limite máximo de TPS global, neste momento. (status code 529)
     *         or Erro inesperado. (status code 200)
     * @see ConsentsApi#consentsDeleteConsentsConsentId
     */
    default ResponseEntity<Void> consentsDeleteConsentsConsentId(String consentId,
        String authorization,
        UUID xFapiInteractionId,
        String xFapiAuthDate,
        String xFapiCustomerIpAddress,
        String xCustomerUserAgent) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
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
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"CONSENTIMENTO_EM_STATUS_REJEITADO\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"CONSENTIMENTO_EM_STATUS_REJEITADO\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"CONSENTIMENTO_EM_STATUS_REJEITADO\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"CONSENTIMENTO_EM_STATUS_REJEITADO\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"CONSENTIMENTO_EM_STATUS_REJEITADO\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
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
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /consents/{consentId} : Obter detalhes do consentimento identificado por consentId.
     * Método para obter detalhes do consentimento identificado por consentId.
     *
     * @param consentId O consentId é o identificador único do consentimento e deverá ser um URN - Uniform Resource Name.   Um URN, conforme definido na [RFC8141](https://tools.ietf.org/html/rfc8141) é um Uniform Resource  Identifier - URI - que é atribuído sob o URI scheme \&quot;urn\&quot; e um namespace URN específico, com a intenção de que o URN  seja um identificador de recurso persistente e independente da localização.   Considerando a string urn:bancoex:C1DD33123 como exemplo para consentId temos: - o namespace(urn) - o identificador associado ao namespace da instituição transnmissora (bancoex) - o identificador específico dentro do namespace (C1DD33123). Informações mais detalhadas sobre a construção de namespaces devem ser consultadas na [RFC8141](https://tools.ietf.org/html/rfc8141).  (required)
     * @param authorization Cabeçalho HTTP padrão. Permite que as credenciais sejam fornecidas dependendo do tipo de recurso solicitado (required)
     * @param xFapiInteractionId Um UUID RFC4122 usado como um ID de correlação entre request e response. Campo de geração e envio obrigatório pela receptora (client) e o seu valor deve ser “espelhado” pela transmissora (server) no cabeçalho de resposta. Caso não seja recebido ou se for recebido um valor inválido, a transmissora deve gerar um x-fapi-interaction-id e retorná-lo na resposta com o HTTP Status Code 400. A receptora deve acatar o valor recebido da transmissora. (required)
     * @param xFapiAuthDate Data em que o usuário logou pela última vez com o receptor. Representada de acordo com a [RFC7231](https://tools.ietf.org/html/rfc7231).Exemplo: Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress O endereço IP do usuário se estiver atualmente logado com o receptor. (optional)
     * @param xCustomerUserAgent Indica o user-agent que o usuário utiliza. (optional)
     * @return Consentimento consultado com sucesso. (status code 200)
     *         or A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL. (status code 400)
     *         or Cabeçalho de autenticação ausente/inválido ou token inválido (status code 401)
     *         or O token tem escopo incorreto ou uma política de segurança foi violada (status code 403)
     *         or O recurso solicitado não existe ou não foi implementado (status code 404)
     *         or O consumidor tentou acessar o recurso com um método não suportado (status code 405)
     *         or A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8 (status code 406)
     *         or A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido (status code 429)
     *         or Ocorreu um erro no gateway da API ou no microsserviço (status code 500)
     *         or GATEWAY TIMEOUT - A requisição não foi atendida dentro do tempo limite estabelecido (status code 504)
     *         or O site está sobrecarregado e a operação foi recusada, pois foi atingido o limite máximo de TPS global, neste momento. (status code 529)
     *         or Erro inesperado. (status code 200)
     * @see ConsentsApi#consentsGetConsentsConsentId
     */
    default ResponseEntity<ResponseConsentRead> consentsGetConsentsConsentId(String consentId,
        String authorization,
        UUID xFapiInteractionId,
        String xFapiAuthDate,
        String xFapiCustomerIpAddress,
        String xCustomerUserAgent) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"data\" : { \"consentId\" : \"urn:bancoex:C1DD33123\", \"expirationDateTime\" : \"2021-05-21T08:30:00Z\", \"permissions\" : [ \"ACCOUNTS_READ\", \"ACCOUNTS_OVERDRAFT_LIMITS_READ\", \"RESOURCES_READ\" ], \"statusUpdateDateTime\" : \"2021-05-21T08:30:00Z\", \"rejection\" : { \"reason\" : { \"additionalInformation\" : \"Tempo de confirmação da múltipla alçada excedido.\", \"code\" : \"CONSENT_EXPIRED\" }, \"rejectedBy\" : \"USER\" }, \"creationDateTime\" : \"2021-05-21T08:30:00Z\", \"status\" : \"AWAITING_AUTHORISATION\" }, \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"links\" : { \"self\" : \"https://api.banco.com.br/open-banking/api/v1/resource\" } }";
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
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /consents/{consentId}/extensions : Obter detalhes de extensões feitas no consentimento identificado por consentId.
     * Método para obter histórico de extensões de consentimento identificado por consentId.  IMPORTANTE: A lista do payload de resposta deve ser entregue em ordem decrescente pela data de requisição (&#x60;data[].requestDateTime&#x60;).  Dessa forma, o primeiro item da lista apresentará a mesma data de expiração do consentimento vigente, pois foi a última renovação feita. 
     *
     * @param consentId O consentId é o identificador único do consentimento e deverá ser um URN - Uniform Resource Name.   Um URN, conforme definido na [RFC8141](https://tools.ietf.org/html/rfc8141) é um Uniform Resource  Identifier - URI - que é atribuído sob o URI scheme \&quot;urn\&quot; e um namespace URN específico, com a intenção de que o URN  seja um identificador de recurso persistente e independente da localização.   Considerando a string urn:bancoex:C1DD33123 como exemplo para consentId temos: - o namespace(urn) - o identificador associado ao namespace da instituição transnmissora (bancoex) - o identificador específico dentro do namespace (C1DD33123). Informações mais detalhadas sobre a construção de namespaces devem ser consultadas na [RFC8141](https://tools.ietf.org/html/rfc8141).  (required)
     * @param authorization Cabeçalho HTTP padrão. Permite que as credenciais sejam fornecidas dependendo do tipo de recurso solicitado (required)
     * @param xFapiInteractionId Um UUID RFC4122 usado como um ID de correlação entre request e response. Campo de geração e envio obrigatório pela receptora (client) e o seu valor deve ser “espelhado” pela transmissora (server) no cabeçalho de resposta. Caso não seja recebido ou se for recebido um valor inválido, a transmissora deve gerar um x-fapi-interaction-id e retorná-lo na resposta com o HTTP Status Code 400. A receptora deve acatar o valor recebido da transmissora. (required)
     * @param xFapiAuthDate Data em que o usuário logou pela última vez com o receptor. Representada de acordo com a [RFC7231](https://tools.ietf.org/html/rfc7231).Exemplo: Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress O endereço IP do usuário se estiver atualmente logado com o receptor. (optional)
     * @param xCustomerUserAgent Indica o user-agent que o usuário utiliza. (optional)
     * @param page Número da página que está sendo requisitada (o valor da primeira página é 1). (optional, default to 1)
     * @param pageSize Quantidade total de registros por páginas. A transmissora deve considerar entrada como 25, caso seja informado algum valor menor pela receptora. Enquanto houver mais que 25 registros a enviar, a transmissora deve considerar o mínimo por página como 25. Somente a última página retornada (ou primeira, no caso de página única) pode conter menos de 25 registros. Mais informações, acesse Especificações de APIs &gt; Padrões &gt; Paginação. (optional, default to 25)
     * @return Renovações de consentimento consultado com sucesso. (status code 200)
     *         or A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL. (status code 400)
     *         or Cabeçalho de autenticação ausente/inválido ou token inválido (status code 401)
     *         or O token tem escopo incorreto ou uma política de segurança foi violada (status code 403)
     *         or O recurso solicitado não existe ou não foi implementado (status code 404)
     *         or O consumidor tentou acessar o recurso com um método não suportado (status code 405)
     *         or A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8 (status code 406)
     *         or A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido (status code 429)
     *         or Ocorreu um erro no gateway da API ou no microsserviço (status code 500)
     *         or GATEWAY TIMEOUT - A requisição não foi atendida dentro do tempo limite estabelecido (status code 504)
     *         or O site está sobrecarregado e a operação foi recusada, pois foi atingido o limite máximo de TPS global, neste momento. (status code 529)
     *         or Erro inesperado. (status code 200)
     * @see ConsentsApi#consentsGetConsentsConsentIdExtensions
     */
    default ResponseEntity<ResponseConsentReadExtensions> consentsGetConsentsConsentIdExtensions(String consentId,
        String authorization,
        UUID xFapiInteractionId,
        String xFapiAuthDate,
        String xFapiCustomerIpAddress,
        String xCustomerUserAgent,
        Integer page,
        Integer pageSize) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"data\" : [ { \"xCustomerUserAgent\" : \"Mozilla/5.0 (iPhone14,6; U; CPU iPhone OS 15_4 like Mac OS X)\", \"expirationDateTime\" : \"2021-05-21T08:30:00Z\", \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"loggedUser\" : { \"document\" : { \"identification\" : \"11111111111\", \"rel\" : \"CPF\" } }, \"previousExpirationDateTime\" : \"2023-10-18T18:30:00Z\", \"xFapiCustomerIpAddress\" : \"172.217.22.14\" }, { \"xCustomerUserAgent\" : \"Mozilla/5.0 (iPhone14,6; U; CPU iPhone OS 15_4 like Mac OS X)\", \"expirationDateTime\" : \"2021-05-21T08:30:00Z\", \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"loggedUser\" : { \"document\" : { \"identification\" : \"11111111111\", \"rel\" : \"CPF\" } }, \"previousExpirationDateTime\" : \"2023-10-18T18:30:00Z\", \"xFapiCustomerIpAddress\" : \"172.217.22.14\" } ], \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"links\" : { \"next\" : \"https://api.banco.com.br/open-banking/api/v1/resource\", \"last\" : \"https://api.banco.com.br/open-banking/api/v1/resource\", \"prev\" : \"https://api.banco.com.br/open-banking/api/v1/resource\", \"self\" : \"https://api.banco.com.br/open-banking/api/v1/resource\", \"first\" : \"https://api.banco.com.br/open-banking/api/v1/resource\" } }";
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
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /consents : Criar novo pedido de consentimento.
     * Método para a criação de um novo consentimento.
     *
     * @param authorization Cabeçalho HTTP padrão. Permite que as credenciais sejam fornecidas dependendo do tipo de recurso solicitado (required)
     * @param xFapiInteractionId Um UUID RFC4122 usado como um ID de correlação entre request e response. Campo de geração e envio obrigatório pela receptora (client) e o seu valor deve ser “espelhado” pela transmissora (server) no cabeçalho de resposta. Caso não seja recebido ou se for recebido um valor inválido, a transmissora deve gerar um x-fapi-interaction-id e retorná-lo na resposta com o HTTP Status Code 400. A receptora deve acatar o valor recebido da transmissora. (required)
     * @param createConsent Payload para criação do consentimento. (required)
     * @param xFapiAuthDate Data em que o usuário logou pela última vez com o receptor. Representada de acordo com a [RFC7231](https://tools.ietf.org/html/rfc7231).Exemplo: Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress O endereço IP do usuário se estiver atualmente logado com o receptor. (optional)
     * @param xCustomerUserAgent Indica o user-agent que o usuário utiliza. (optional)
     * @return Consentimento criado com sucesso. (status code 201)
     *         or A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL. (status code 400)
     *         or Cabeçalho de autenticação ausente/inválido ou token inválido (status code 401)
     *         or O token tem escopo incorreto ou uma política de segurança foi violada (status code 403)
     *         or O recurso solicitado não existe ou não foi implementado (status code 404)
     *         or O consumidor tentou acessar o recurso com um método não suportado (status code 405)
     *         or A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8 (status code 406)
     *         or O formato do payload não é um formato suportado. (status code 415)
     *         or A sintaxe da requisição esta correta, mas não foi possível processar as instruções presentes. (status code 422)
     *         or A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido (status code 429)
     *         or Ocorreu um erro no gateway da API ou no microsserviço (status code 500)
     *         or GATEWAY TIMEOUT - A requisição não foi atendida dentro do tempo limite estabelecido (status code 504)
     *         or O site está sobrecarregado e a operação foi recusada, pois foi atingido o limite máximo de TPS global, neste momento. (status code 529)
     *         or Erro inesperado. (status code 200)
     * @see ConsentsApi#consentsPostConsents
     */
    default ResponseEntity<ResponseConsent> consentsPostConsents(String authorization,
        UUID xFapiInteractionId,
        CreateConsent createConsent,
        String xFapiAuthDate,
        String xFapiCustomerIpAddress,
        String xCustomerUserAgent) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"data\" : { \"consentId\" : \"urn:bancoex:C1DD33123\", \"expirationDateTime\" : \"2021-05-21T08:30:00Z\", \"permissions\" : [ \"ACCOUNTS_READ\", \"ACCOUNTS_OVERDRAFT_LIMITS_READ\", \"RESOURCES_READ\" ], \"statusUpdateDateTime\" : \"2021-05-21T08:30:00Z\", \"creationDateTime\" : \"2021-05-21T08:30:00Z\", \"status\" : \"AWAITING_AUTHORISATION\" }, \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"links\" : { \"self\" : \"https://api.banco.com.br/open-banking/api/v1/resource\" } }";
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
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"SEM_PERMISSOES_FUNCIONAIS_RESTANTES\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"SEM_PERMISSOES_FUNCIONAIS_RESTANTES\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"SEM_PERMISSOES_FUNCIONAIS_RESTANTES\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"SEM_PERMISSOES_FUNCIONAIS_RESTANTES\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"SEM_PERMISSOES_FUNCIONAIS_RESTANTES\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
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
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /consents/{consentId}/extends : Renovar consentimento identificado por consentId.
     * Método utilizado para renovação de consentimento do cliente. O consentimento só pode ser renovado caso esteja ativo (status AUTHORISED) e tenha alçada simples de aprovação (não dependa de múltiplos aprovadores).  A alteração de data efetuada pela renovação deve ser refletida também na consulta do método _GET/consents/{consentId}_.
     *
     * @param consentId O consentId é o identificador único do consentimento e deverá ser um URN - Uniform Resource Name.   Um URN, conforme definido na [RFC8141](https://tools.ietf.org/html/rfc8141) é um Uniform Resource  Identifier - URI - que é atribuído sob o URI scheme \&quot;urn\&quot; e um namespace URN específico, com a intenção de que o URN  seja um identificador de recurso persistente e independente da localização.   Considerando a string urn:bancoex:C1DD33123 como exemplo para consentId temos: - o namespace(urn) - o identificador associado ao namespace da instituição transnmissora (bancoex) - o identificador específico dentro do namespace (C1DD33123). Informações mais detalhadas sobre a construção de namespaces devem ser consultadas na [RFC8141](https://tools.ietf.org/html/rfc8141).  (required)
     * @param authorization Cabeçalho HTTP padrão. Permite que as credenciais sejam fornecidas dependendo do tipo de recurso solicitado (required)
     * @param xFapiCustomerIpAddress O endereço IP do usuário se estiver atualmente logado com o receptor. (required)
     * @param xFapiInteractionId Um UUID RFC4122 usado como um ID de correlação entre request e response. Campo de geração e envio obrigatório pela receptora (client) e o seu valor deve ser “espelhado” pela transmissora (server) no cabeçalho de resposta. Caso não seja recebido ou se for recebido um valor inválido, a transmissora deve gerar um x-fapi-interaction-id e retorná-lo na resposta com o HTTP Status Code 400. A receptora deve acatar o valor recebido da transmissora. (required)
     * @param xCustomerUserAgent Indica o user-agent que o usuário utiliza. (required)
     * @param createConsentExtensions Payload para renovação do consentimento. (required)
     * @param xFapiAuthDate Data em que o usuário logou pela última vez com o receptor. Representada de acordo com a [RFC7231](https://tools.ietf.org/html/rfc7231).Exemplo: Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @return Renovação do consentimento finalizada com sucesso. (status code 201)
     *         or A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL. (status code 400)
     *         or Cabeçalho de autenticação ausente/inválido ou token inválido (status code 401)
     *         or O token tem escopo incorreto ou uma política de segurança foi violada (status code 403)
     *         or O recurso solicitado não existe ou não foi implementado (status code 404)
     *         or O consumidor tentou acessar o recurso com um método não suportado (status code 405)
     *         or A solicitação continha um cabeçalho Accept diferente dos tipos de mídia permitidos ou um conjunto de caracteres diferente de UTF-8 (status code 406)
     *         or O formato do payload não é um formato suportado. (status code 415)
     *         or A sintaxe da requisição está correta, mas não foi possível processar as instruções presentes. (status code 422)
     *         or A operação foi recusada, pois muitas solicitações foram feitas dentro de um determinado período ou o limite global de requisições concorrentes foi atingido (status code 429)
     *         or Ocorreu um erro no gateway da API ou no microsserviço (status code 500)
     *         or GATEWAY TIMEOUT - A requisição não foi atendida dentro do tempo limite estabelecido (status code 504)
     *         or O site está sobrecarregado e a operação foi recusada, pois foi atingido o limite máximo de TPS global, neste momento. (status code 529)
     *         or Erro inesperado. (status code 200)
     * @see ConsentsApi#consentsPostConsentsConsentIdExtends
     */
    default ResponseEntity<ResponseConsentExtensions> consentsPostConsentsConsentIdExtends(String consentId,
        String authorization,
        String xFapiCustomerIpAddress,
        UUID xFapiInteractionId,
        String xCustomerUserAgent,
        CreateConsentExtensions createConsentExtensions,
        String xFapiAuthDate) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"data\" : { \"consentId\" : \"urn:bancoex:C1DD33123\", \"expirationDateTime\" : \"2021-05-21T08:30:00Z\", \"permissions\" : [ \"ACCOUNTS_READ\", \"ACCOUNTS_OVERDRAFT_LIMITS_READ\", \"RESOURCES_READ\" ], \"statusUpdateDateTime\" : \"2021-05-21T08:30:00Z\", \"creationDateTime\" : \"2021-05-21T08:30:00Z\", \"status\" : \"AWAITING_AUTHORISATION\" }, \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"links\" : { \"self\" : \"https://api.banco.com.br/open-banking/api/v1/resource\" } }";
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
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"DEPENDE_MULTIPLA_ALCADA\", \"detail\" : \"O consentimento informado não pode ser renovado sem redirecionamento porque depende de múltipla alçada para aprovação.\", \"title\" : \"Necessário aprovação de múltipla alçada.\" }, { \"code\" : \"DEPENDE_MULTIPLA_ALCADA\", \"detail\" : \"O consentimento informado não pode ser renovado sem redirecionamento porque depende de múltipla alçada para aprovação.\", \"title\" : \"Necessário aprovação de múltipla alçada.\" }, { \"code\" : \"DEPENDE_MULTIPLA_ALCADA\", \"detail\" : \"O consentimento informado não pode ser renovado sem redirecionamento porque depende de múltipla alçada para aprovação.\", \"title\" : \"Necessário aprovação de múltipla alçada.\" } ] }";
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
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"meta\" : { \"requestDateTime\" : \"2021-05-21T08:30:00Z\" }, \"errors\" : [ { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" }, { \"code\" : \"code\", \"detail\" : \"detail\", \"title\" : \"title\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
