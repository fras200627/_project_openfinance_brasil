package com.tican.open.finance.template_resources_handler;

import com.tican.open.finance.template_resources_model.ResponseErrorWithAbleAdditionalProperties;
import com.tican.open.finance.template_resources_model.ResponseResourceList;
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
 * A delegate to be called by the {@link ResourcesApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-07T13:29:04.924183300-03:00[America/Sao_Paulo]", comments = "Generator version: 7.12.0")
public interface ResourcesApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /resources : Obtém a lista de recursos consentidos pelo cliente.
     * Método para obter a lista de recursos mantidos pelo cliente na instituição transmissora e para as quais ele tenha fornecido consentimento.
     *
     * @param authorization Cabeçalho HTTP padrão. Permite que as credenciais sejam fornecidas dependendo do tipo de recurso solicitado (required)
     * @param xFapiInteractionId Um UUID [RFC4122](https://tools.ietf.org/html/rfc4122) usado como um ID de correlação entre request e response. Campo de geração e envio obrigatório pela receptora (client) e o seu valor deve ser “espelhado” pela transmissora (server) no cabeçalho de resposta. Caso não seja recebido ou se for recebido um valor inválido, a transmissora deve gerar um x-fapi-interaction-id e retorná-lo na resposta com o HTTP Status Code 400. A receptora deve acatar o valor recebido da transmissora. (required)
     * @param xFapiAuthDate Data em que o usuário logou pela última vez com o receptor. Representada de acordo com a [RFC7231](https://tools.ietf.org/html/rfc7231).Exemplo: Sun, 10 Sep 2017 19:43:31 UTC (optional)
     * @param xFapiCustomerIpAddress O endereço IP do usuário se estiver atualmente logado com o receptor. (optional)
     * @param xCustomerUserAgent Indica o user-agent que o usuário utiliza. (optional)
     * @param page Número da página que está sendo requisitada (o valor da primeira página é 1). (optional, default to 1)
     * @param pageSize Quantidade total de registros por páginas.  A transmissora deve considerar entrada como 25, caso seja informado algum valor menor pela receptora.  Enquanto houver mais que 25 registros a enviar, a transmissora deve considerar o mínimo por página como 25.  Somente a última página retornada (ou primeira, no caso de página única) pode conter menos de 25 registros.  Mais informações, acesse Especificações de APIs &gt; Padrões &gt; Paginação.  (optional, default to 25)
     * @return Dados de status dos recursos obtidos com sucesso. (status code 200)
     *         or Requisição foi recebida. (status code 202)
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
     * @see ResourcesApi#resourcesGetResources
     */
    default ResponseEntity<ResponseResourceList> resourcesGetResources(String authorization,
        UUID xFapiInteractionId,
        String xFapiAuthDate,
        String xFapiCustomerIpAddress,
        String xCustomerUserAgent,
        Integer page,
        Integer pageSize) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"data\" : [ { \"resourceId\" : \"25cac914-d8ae-6789-b215-650a6215820d\", \"type\" : \"ACCOUNT\", \"status\" : \"AVAILABLE\" }, { \"resourceId\" : \"25cac914-d8ae-6789-b215-650a6215820d\", \"type\" : \"ACCOUNT\", \"status\" : \"AVAILABLE\" } ], \"meta\" : { \"totalRecords\" : 1, \"requestDateTime\" : \"2021-05-21T08:30:00Z\", \"totalPages\" : 1 }, \"links\" : { \"next\" : \"https://api.banco.com.br/open-banking/api/v1/resource\", \"last\" : \"https://api.banco.com.br/open-banking/api/v1/resource\", \"prev\" : \"https://api.banco.com.br/open-banking/api/v1/resource\", \"self\" : \"https://api.banco.com.br/open-banking/api/v1/resource\", \"first\" : \"https://api.banco.com.br/open-banking/api/v1/resource\" } }";
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
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
