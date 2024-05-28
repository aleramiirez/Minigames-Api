package com.minijuegos.audit_service.services;


import com.minijuegos.audit_service.persistence.model.AuditingData;

public interface AuditingServiceI {

    public AuditingData saveAudit(AuditingData auditingData);

}
