package com.bm.autoirrigationsys.services;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bm.autoirrigationsys.dto.MessageDTO;
import com.bm.autoirrigationsys.model.MessageEB;
import com.bm.autoirrigationsys.repo.MessageRepository;

@Service("MessageService")
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private ModelMapper modelMapper;
	Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

	private final RestTemplate restTemplate = new RestTemplate();

	@Value("${irrigate.sensor.base_url}")
	private String baseUrl;

	@Value("${server.port}")
	private String port;

	@Override
	public MessageDTO saveMessage(MessageDTO messageDTO) {

		MessageEB messageEB = modelMapper.map(messageDTO, MessageEB.class);
		return modelMapper.map(messageRepository.save(messageEB), MessageDTO.class);
	}
	
@Override
	public Boolean alertService() {
	
		// make a call to external alert service through sms
	
		MessageEB message = new MessageEB();
		message.setMessage("Hey! we were unable to reach the sensor. Please check it out.");
		
		String sensorUrl = baseUrl + ":" + port + "/alerts/send";
		logger.info("Sensor Url " + sensorUrl);
		
		// send POST request
		restTemplate.postForEntity(sensorUrl, modelMapper.map(message, MessageDTO.class), MessageDTO.class);

		return true;
	}

}
