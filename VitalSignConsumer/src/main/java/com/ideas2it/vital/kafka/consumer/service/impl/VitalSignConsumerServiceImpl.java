package com.ideas2it.vital.kafka.consumer.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.vital.kafka.consumer.dto.VitalEntity;
import com.ideas2it.vital.kafka.consumer.repository.VitalSignConsumerRepository;
import com.ideas2it.vital.kafka.consumer.service.VitalSignConsumerService;
import com.ideas2it.vital.kafka.consumer.util.VitalEntityConverter;
import com.ideas2it.vital.sign.dto.VitalDto;

@Service
public class VitalSignConsumerServiceImpl implements VitalSignConsumerService {

	@Autowired
	private VitalSignConsumerRepository consumerRepository;

	@Autowired
	private ModelMapper modelMapper;

	public VitalEntity convertToVitalEntity(VitalDto vitalDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addConverter(new VitalEntityConverter());
		return modelMapper.map(vitalDto, VitalEntity.class);
	}

	@Override
	public int addVitalToMongo(VitalDto vitalDto) {
		System.out.println("Haithar inside add");
		VitalEntity entity = convertToVitalEntity(vitalDto);
		VitalEntity SaveVitalEntity = consumerRepository.save(entity);
		return SaveVitalEntity.getId().intValue();
	}

}
