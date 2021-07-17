package com.techleads.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.techleads.app.avro.MyMessages;
import com.techleads.app.common.KafkaConstants;
import com.techleads.app.model.MyMessage;

@Service
public class ProducerService {

	static Logger logger = LoggerFactory.getLogger(ProducerService.class.getName());
	private KafkaTemplate<String, MyMessages> kafkaTemplate;
	@Autowired
	public ProducerService(KafkaTemplate<String, MyMessages> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public String addMessagesToKafkaTopic(List<MyMessage> mymessages) {

		try {
				for (MyMessage mymessage : mymessages) {
					try {
						MyMessages msg = MyMessages.newBuilder()
						.setMessage(mymessage.getMessage())
						.setMsgId(mymessage.getMsgId()).build();
						kafkaTemplate.send(KafkaConstants.TOPIC, msg);
						logger.info("***********Message Published to Kafka topic************");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Customer Record added to Kafka Queue successfully";
	}


}
