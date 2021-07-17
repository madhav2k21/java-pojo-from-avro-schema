package com.techleads.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
@EnableKafka
@SpringBootApplication
public class JavaPojoFromAvroSchemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaPojoFromAvroSchemaApplication.class, args);
	}


}
