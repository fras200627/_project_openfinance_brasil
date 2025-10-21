package com.ofb.consents.service;

import com.ofb.consents.client.consents.handler.ConsentsApi;
import com.ofb.consents.client.consents.model.ResponseAccessTokenRead;
import com.ofb.consents.client.consents.model.ResponseAccessTokenReadData;
import com.ofb.consents.server.model.ResponseConsentRead;
import com.ofb.consents.server.model.ResponseConsentReadData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@Slf4j
public class ConsentGetAccessTokenService {

    @Value("${app.paths.clients.ofb-consents}")
    private String OFB_CONSENTS_API_URL;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public ResponseAccessTokenRead consentsGetAccessTokenConsentId(ResponseConsentRead responseConsentRead ) {

        ResponseAccessTokenRead responseAccessTokenRead = new ResponseAccessTokenRead();

        if (responseConsentRead.getData().getStatus().equals(ResponseConsentReadData.StatusEnum.AUTHORISED)) {
            ConsentsApi consentsApi = new ConsentsApi();
            consentsApi.getApiClient().setBasePath(OFB_CONSENTS_API_URL);
            consentsApi.getApiClient().setBearerToken(request.getHeader("Authorization").toString().replace("Bearer ", ""));
            consentsApi.getApiClient().addDefaultHeader("x-fapi-interaction-id", request.getHeader("x-fapi-interaction-id").toString());

            try {
                responseAccessTokenRead = consentsApi.consentsGetAccessTokenConsentId(responseConsentRead.getData().getConsentId(),
                                                                        UUID.fromString(request.getHeader("x-fapi-interaction-id").toString()));
                if (responseAccessTokenRead.getData().getStatus().equals(ResponseAccessTokenReadData.StatusEnum.AUTHORISED)
                        ||
                        responseAccessTokenRead.getData().getAccessToken() != null) {
                    response.addHeader("access-token", responseAccessTokenRead.getData().getAccessToken());
                }
            } catch (Exception e) {
                log.info("consent Id does not a access token generated");
            }
        }

        return responseAccessTokenRead;

    }
}
