package com.ofb.resources.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.UUID;

import com.ofb.resources.client.resources.handler.ResourcesApi;
import com.ofb.resources.client.resources.model.*;

@Service
@Slf4j
public class ResourcesService {

    @Value("${app.paths.clients.ofb-resources}")
    private String OFB_PATH_RESOURCES;

    private Gson gson = new Gson();
    private String consentId;
    private ResponseResourceList responseResourceList;

    @Autowired
    private ResourcesApi resourcesApi;

    public ResponseResourceList getResourcesList(String accessToken,
                                                      UUID xFapiInteractionId,
                                                      String xFapiAuthDate,
                                                      String xFapiCustomerIpAddress,
                                                      String xCustomerUserAgent,
                                                      Integer page,
                                                      Integer pageSize) {

        resourcesApi.getApiClient().setBasePath(OFB_PATH_RESOURCES);
        resourcesApi.getApiClient().setBearerToken(accessToken.replace("Bearer ", ""));

        consentId            = "";
        responseResourceList = new ResponseResourceList();

        /// Authorize AccessToken
        try {
            responseResourceList = resourcesApi.resourcesGetResources(accessToken,
                                                                      xFapiInteractionId,
                                                                      xFapiAuthDate,
                                                                      xFapiCustomerIpAddress,
                                                                      xCustomerUserAgent,
                                                                      page,
                                                                      pageSize);
        } catch (Exception e) {
//            listResponseErrors.add(new ResultErrorsErrorsInner().toBuilder()
//                    .title("Get Authorization request error (in ResourcesAPI)")
//                    .code(ResponseOFBCodesEnum.CodeEnum.INTERNAL_ERROR.getValue())
//                    .detail(e.getMessage())
//                    .build());
//            return ResponseAuthorizationData.builder()
//                    .data(ResponseResultData.builder()
//                            .resultStatus(ResultStatus.builder()
//                                    .status(ResultStatus.StatusEnum.AUTHORIZATION_DENIED)
//                                    .build())
//                            .resultErrors(ResultErrors.builder()
//                                    .errors(listResponseErrors)
//                                    .build())
//                            .build()
//                    )
//                    .meta(Meta.builder()
//                            .requestDateTime(OffsetDateTime.now(ZoneId.of("UTC")).toString().substring(0, 19) + "Z")
//                            .build())
//                    .build();
        }

        return responseResourceList;
    }

}
