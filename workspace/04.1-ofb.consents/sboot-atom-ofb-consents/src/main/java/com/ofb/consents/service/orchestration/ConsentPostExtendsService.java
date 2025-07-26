package com.ofb.consents.service.orchestration;

import com.google.gson.Gson;
import com.ofb.consents.client.authentication.resources.handler.AppAuthenticationResourcesApi;
import com.ofb.consents.enums.ConsentResponseEnum;
import com.ofb.consents.exception.ConsentBadRequestException;
import com.ofb.consents.exception.ConsentInternalErrorException;
import com.ofb.consents.exception.ConsentUnprocessedEntityException;
import com.ofb.consents.model.ConsentPersonalModel;
import com.ofb.consents.repository.data.ConsentPersonalRepository;
import com.ofb.consents.repository.views.ConsentPermissionsAuthorisedlViewRepository;
import com.ofb.consents.repository.views.ConsentPersonalViewRepository;
import com.ofb.consents.server.consents.resources.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service @Slf4j
public class ConsentPostExtendsService {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private ConsentPersonalViewRepository consentsRepositoryView;

    @Autowired
    private ConsentPersonalRepository consentPersonalRepository;

    @Autowired
    private ConsentPermissionsAuthorisedlViewRepository permissionsAuthorised;

    @Value("${app.paths.clients.authentication-server}")
    private String PATH_AUTHENTICATION_SERVER;

    @Autowired private AppAuthenticationResourcesApi authenticationResourcesApi;

    public ResponseConsentReadExtensions consentsPostConsentsConsentIdExtends(String consentId) {

        List<ResponseConsentReadExtensionsDataInner> listResponseExtentions = new ArrayList<>();
        Page<ConsentPersonalModel> consentRequestList;
        List<ResponseErrorErrorsInner> listError = new ArrayList<>();

        ConsentPersonalModel consentRequested;

        try {
            consentRequested = consentsRepositoryView.findById(consentId).get();
        } catch (NoSuchElementException e) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent GET EXTENSIONS request error")
                    .code(ConsentResponseEnum.CodeEnum.CONSENT_NOT_FOUND.getValue())
                    .detail("The informed consentId does not exist")
                    .build());
            throw new ConsentBadRequestException(new Gson().toJson(listError));
        } catch (Exception e) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent GET EXTENSIONS request error")
                    .code(ConsentResponseEnum.CodeEnum.INTERNAL_ERROR.getValue())
                    .detail("An internal error occurred. Message Error: [" + e.getMessage() + "]")
                    .build());
            throw new ConsentInternalErrorException(new Gson().toJson(listError));
        }

        if (!consentRequested.getStatus().equals("AUTHORISED")) {
            listError.add(new ResponseErrorErrorsInner().toBuilder()
                    .title("Consent GET EXTENSIONS request error")
                    .code(ConsentResponseEnum.CodeEnum.BAD_REQUEST.getValue())
                    .detail("Consent cannot be extensions for ExpirationDateTime. Actual Status is [" + consentRequested.getStatus() + "].")
                    .build());
            throw new ConsentUnprocessedEntityException(new Gson().toJson(listError));
        }

        ResponseConsentReadExtensions responseConsentReadData = ResponseConsentReadExtensions.builder()
                .data(listResponseExtentions)
                .meta(null)
                .links(null)
                .build();

        return responseConsentReadData;
    }

}
