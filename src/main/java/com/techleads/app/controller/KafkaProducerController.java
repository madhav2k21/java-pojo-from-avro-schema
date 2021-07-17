package com.techleads.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techleads.app.model.MyMessage;
import com.techleads.app.service.ProducerService;

@RestController
public class KafkaProducerController {

	private ProducerService producerService;

	@Autowired
	public KafkaProducerController(ProducerService producerService) {
		this.producerService = producerService;
	}

	@PostMapping(value = "/messages", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String addMessages(@RequestBody List<MyMessage> myMessage) {

		return producerService.addMessagesToKafkaTopic(myMessage);
	}

}
