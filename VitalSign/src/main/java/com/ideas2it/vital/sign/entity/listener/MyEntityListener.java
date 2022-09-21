package com.ideas2it.vital.sign.entity.listener;

import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.ideas2it.vital.sign.entity.VitalEntity;
import com.ideas2it.vital.sign.util.VitalConstants;

public class MyEntityListener {
	
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	@AnnotationForAudit(name = "INSERT")
	@PostPersist
	private void postCreate(VitalEntity myEntity) {
		System.out.println(myEntity);
		System.out.println("entering postCreate annotated");
		kafkaTemplate.send(VitalConstants.KAFKA_TOPIC_NAME,myEntity);
	}

	@AnnotationForAudit(name = "UPDATE")
	@PreUpdate
	private void preUpdate(VitalEntity myEntity) {
		System.out.println(myEntity);
		System.out.println("entering preUpdate annotated");
		kafkaTemplate.send(VitalConstants.KAFKA_TOPIC_NAME,myEntity);
	}
}
