package com.ideas2it.vital.kafka.consumer.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ideas2it.vital.sign.dto.PatientDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "vital")
public class VitalEntity {

	@Id
	private Long id;
	private Long pulse;
	private Long bloodPressure;
	private double temperature;
	private Long spo2;
	private Long respirations;
	private Long bloodSugar;
	private double height;
	private double weight;
	private double rtInrRatio;
	private Long patientId;
	private String userId;
	private PatientDto patientDto;

}
