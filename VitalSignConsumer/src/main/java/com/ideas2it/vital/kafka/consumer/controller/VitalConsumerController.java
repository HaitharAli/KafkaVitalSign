package com.ideas2it.vital.kafka.consumer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.vital.kafka.consumer.service.VitalSignConsumerService;
import com.ideas2it.vital.sign.dto.VitalDto;

@RestController
public class VitalConsumerController {
	
	@Autowired
	private VitalSignConsumerService consumerService;

	List<String> messList = new ArrayList<>();

	VitalDto vitalDtoFromTopic = null;

//	@GetMapping("/consumeStringMessage")
//	public List<String> consumeMsg() {
//		return messList;
//	}

	@GetMapping("/consumeVitalAudit")
	public VitalDto consumeVitalAudit() {
		
		return vitalDtoFromTopic;
	}

//	@KafkaListener(groupId = "sampleTopic-1", topics = "sampleTopic", containerFactory = "userConcurrentKafkaListenerContainerFactory")
//	public List<String> getMsgFromTopic(String data) {
//		messList.add(data);
//		return messList;
//	}

	@KafkaListener(groupId = "sampleTopic-1", topics = "sampleTopic", containerFactory = "userConcurrentKafkaListenerContainerFactory")
	public VitalDto getVitalFromTopic(VitalDto vitalDto) {
		System.out.println("inside getVitalcontroller");
		vitalDtoFromTopic = vitalDto;
		consumerService.addVitalToMongo(vitalDto);		
		return vitalDtoFromTopic;
	}

}
