package com.ideas2it.vital.kafka.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.ideas2it.vital.sign.dto.VitalDto;

@Configuration
@EnableKafka
public class KafkaConsumerConfiguration {

	public ConsumerFactory<String, VitalDto> consumerFactory() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		configs.put(JsonDeserializer.VALUE_DEFAULT_TYPE,VitalDto.class);
		configs.put(JsonDeserializer.TRUSTED_PACKAGES, "com.ideas2it.vital.kafka.consumer.dto");
		configs.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "sampleTopic-1");

		return new DefaultKafkaConsumerFactory<>(configs,new StringDeserializer(),new JsonDeserializer<>(VitalDto.class));

	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, VitalDto> userConcurrentKafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, VitalDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;

	}

}
