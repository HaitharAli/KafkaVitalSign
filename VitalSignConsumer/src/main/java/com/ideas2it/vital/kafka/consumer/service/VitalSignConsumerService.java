package com.ideas2it.vital.kafka.consumer.service;

import com.ideas2it.vital.sign.dto.VitalDto;

public interface VitalSignConsumerService {
	
	public int addVitalToMongo(VitalDto vitalDto);

}
