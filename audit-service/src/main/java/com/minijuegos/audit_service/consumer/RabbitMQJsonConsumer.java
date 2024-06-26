package com.minijuegos.audit_service.consumer;

import com.minijuegos.audit_service.persistence.model.AuditingData;
import com.minijuegos.audit_service.services.AuditingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

// @Service
public class RabbitMQJsonConsumer {

//     private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
//
//     private final AuditingServiceImpl auditingMngm;
//
//     @Autowired
//     public RabbitMQJsonConsumer(AuditingServiceImpl auditingMngm) {
//         this.auditingMngm = auditingMngm;
//     }
//
//     @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
//     public void consumJsonMessage(@Payload String auditingData) {
//
//         LOGGER.info(String.format("Received message -> %s", auditingData));
//
//         String[] data = auditingData.split(",");
//
//         AuditingData auditingData1 = new AuditingData();
//         auditingData1.setCreatedBy(data[1]);
//         auditingData1.setCreatedDate(LocalDateTime.parse(data[2]));
//         auditingData1.setTypeRequest(data[3]);
//
//         auditingMngm.saveAudit(auditingData1);
//
//     }

}
