package com.minijuegos.login_service.published;

import com.minijuegos.login_service.persistence.model.AuditingData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

    private final RestTemplate restTemplate;
    private final String auditingServiceUrl;

    public RestClient(RestTemplate restTemplate, @Value("${auditing.service.url}") String auditingServiceUrl) {
        this.restTemplate = restTemplate;
        this.auditingServiceUrl = auditingServiceUrl;
    }

    public void sendAudit(AuditingData audit) {
        restTemplate.postForObject(auditingServiceUrl, audit, AuditingData.class);
    }

}
