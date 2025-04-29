package com.spatiallaser.backend.service;

import org.springframework.stereotype.Service;

import com.spatiallaser.backend.entity.writing.AuditLog;
import com.spatiallaser.backend.repository.writing.AuditLogRepository;

@Service
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void logActivity(String message) {

        AuditLog auditLog = new AuditLog();
        auditLog.setMessage(message);
        auditLogRepository.save(auditLog);
    }
}
