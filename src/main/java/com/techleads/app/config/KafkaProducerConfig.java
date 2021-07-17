package com.techleads.app.config;

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

import com.techleads.app.avro.MyMessages;
import com.techleads.app.common.KafkaConstants;
import com.techleads.app.model.MyMessage;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

@Configuration
public class KafkaProducerConfig {
	
	@Bean
	public ProducerFactory<String, MyMessages> producerFactory(){
		Map<String, Object> configProps=new HashMap<>();
		configProps.put(ProducerConfig.CLIENT_ID_CONFIG, KafkaConstants.APP_ID);
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,KafkaConstants.BOOTSTRAPSERVERS);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
		configProps.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, KafkaConstants.SCHEMAREGISTRYSERVERS);
		return new DefaultKafkaProducerFactory<>(configProps);
		
		
	}
	@Bean(name = "kafkaTemplate")
	public KafkaTemplate<String, MyMessages> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}

}
