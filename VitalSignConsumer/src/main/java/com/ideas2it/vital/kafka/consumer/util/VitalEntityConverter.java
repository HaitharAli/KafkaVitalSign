package com.ideas2it.vital.kafka.consumer.util;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.ideas2it.vital.kafka.consumer.dto.VitalEntity;
import com.ideas2it.vital.sign.dto.VitalDto;

public class VitalEntityConverter implements Converter<VitalDto, VitalEntity> {

	@Override
	public VitalEntity convert(MappingContext<VitalDto, VitalEntity> context) {

		VitalDto vitalInputDto = context.getSource();
		VitalEntity entity = context.getDestination();

		if (entity == null) {
			entity = new VitalEntity();
		}

		if (vitalInputDto != null) {
			entity.setId(vitalInputDto.getVitalId());
			entity.setBloodPressure(vitalInputDto.getBloodPressure());
			entity.setBloodSugar(vitalInputDto.getBloodSugar());
			entity.setHeight(vitalInputDto.getHeight());
			entity.setPulse(vitalInputDto.getPulse());
			entity.setRespirations(vitalInputDto.getRespirations());
			entity.setRtInrRatio(vitalInputDto.getRtInrRatio());
			entity.setSpo2(vitalInputDto.getSpo2());
			entity.setTemperature(vitalInputDto.getTemperature());
			entity.setUserId(vitalInputDto.getUserId());
			entity.setPatientId(vitalInputDto.getPatientId());
		}

		return entity;
	}

}
