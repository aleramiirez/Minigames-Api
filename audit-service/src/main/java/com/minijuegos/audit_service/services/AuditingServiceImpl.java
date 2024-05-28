package com.minijuegos.audit_service.services;

import com.minijuegos.audit_service.persistence.model.AuditingData;
import com.minijuegos.audit_service.persistence.repository.AuditorRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditingServiceImpl implements AuditingServiceI {

    private final AuditorRepositoryI auditorRepo;

    @Autowired
    public AuditingServiceImpl(AuditorRepositoryI auditorRepo) {
        this.auditorRepo = auditorRepo;
    }

    @Override
    public AuditingData saveAudit(AuditingData audit) {
        return auditorRepo.save(audit);
    }


}
