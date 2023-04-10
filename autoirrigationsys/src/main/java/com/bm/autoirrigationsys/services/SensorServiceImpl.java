package com.bm.autoirrigationsys.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bm.autoirrigationsys.dto.PlotDto;

import io.github.resilience4j.retry.annotation.Retry;

@Service("SensorService")
public class SensorServiceImpl implements SensorService {

	Logger logger = LoggerFactory.getLogger(SensorServiceImpl.class);

	private final RestTemplate restTemplate = new RestTemplate();
	
	@Value("${irrigate.sensor.base_url}")
	private String baseUrl;

	@Value("${server.port}")
	private String port;


	@Autowired
	private MessageService messageService;

	@Override
	@Retry(name = "notifySensor", fallbackMethod = "fallbackAfterRetry")
	public Boolean notifySensor(PlotDto plotDto) {

		String sensorUrl = baseUrl + ":" + port + "/sensor/notify";
		logger.info("Sensor Url " + sensorUrl);
		// send POST request
		ResponseEntity<PlotDto> response = this.restTemplate.postForEntity(sensorUrl, plotDto, PlotDto.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Sensor notified ok!");
			return true;
		} else {
			return false;
		}

	}
	public Boolean fallbackAfterRetry(PlotDto plot,Exception ex) {
		messageService.alertService();
		return true;

	}
}