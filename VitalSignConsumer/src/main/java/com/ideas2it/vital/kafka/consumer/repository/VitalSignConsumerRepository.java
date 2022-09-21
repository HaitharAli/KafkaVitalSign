package com.ideas2it.vital.kafka.consumer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.vital.kafka.consumer.dto.VitalEntity;

@Repository
public interface VitalSignConsumerRepository extends MongoRepository<VitalEntity, Long> {

}
