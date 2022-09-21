package com.ideas2it.vital.sign.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.ideas2it.vital.sign.dto.VitalDto;
import com.ideas2it.vital.sign.util.VitalConstants;

@Configuration
public class KafkaConfiguration {

	@Bean
	public ProducerFactory<String, VitalDto> producerFactory() {

		Map<String, Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,VitalConstants.KAFKA_PORT );
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, VitalDto>(configs);

	}

	@Bean
	public KafkaTemplate<String, VitalDto> kafkaTemplate() {
		return new KafkaTemplate<String,VitalDto>(producerFactory());
	}
}
