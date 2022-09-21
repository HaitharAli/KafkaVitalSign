package com.ideas2it.vital.sign.entity.listener;

import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.ideas2it.vital.sign.dto.VitalDto;
import com.ideas2it.vital.sign.entity.VitalEntity;
import com.ideas2it.vital.sign.util.VitalConstants;

public class MyEntityListener {

	@Autowired
	KafkaTemplate<String, VitalDto> kafkaTemplate;

	@AnnotationForAudit(name = "INSERT")
	@PostPersist
	private void postCreate(VitalEntity myEntity) {
		System.out.println(myEntity);
		System.out.println("entering postCreate annotated");
		VitalDto vitalDto = setVitalDto(myEntity);
		kafkaTemplate.send(VitalConstants.KAFKA_TOPIC_NAME, vitalDto);
	}

	@AnnotationForAudit(name = "UPDATE")
	@PreUpdate
	private void preUpdate(VitalEntity myEntity) {
		System.out.println(myEntity);
		System.out.println("entering preUpdate annotated");
		VitalDto vitalDto = setVitalDto(myEntity);
		kafkaTemplate.send(VitalConstants.KAFKA_TOPIC_NAME, vitalDto);
	}

	private VitalDto setVitalDto(VitalEntity myEntity) {
		VitalDto vitalDto = new VitalDto();
		vitalDto.setVitalId(myEntity.getId());
		vitalDto.setBloodPressure(myEntity.getBloodPressure());
		vitalDto.setBloodSugar(myEntity.getBloodSugar());
		vitalDto.setHeight(myEntity.getHeight());
		vitalDto.setPulse(myEntity.getPulse());
		vitalDto.setRespirations(myEntity.getRespirations());
		vitalDto.setRtInrRatio(myEntity.getRtInrRatio());
		vitalDto.setSpo2(myEntity.getSpo2());
		vitalDto.setTemperature(myEntity.getTemperature());
		vitalDto.setUserId(myEntity.getUserId());
		vitalDto.setPatientId(myEntity.getPatientId());
		return vitalDto;
	}
}
