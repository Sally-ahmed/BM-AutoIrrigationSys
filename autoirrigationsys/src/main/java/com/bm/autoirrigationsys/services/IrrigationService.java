package com.bm.autoirrigationsys.services;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bm.autoirrigationsys.dto.PlotDto;
import com.bm.autoirrigationsys.exception.ResourceNotFoundException;
import com.bm.autoirrigationsys.model.PlotDetailsEB;
import com.bm.autoirrigationsys.model.PlotEB;
import com.bm.autoirrigationsys.repo.PlotDetailsRepository;
import com.bm.autoirrigationsys.repo.PlotRepository;

@Component
public class IrrigationService {

	@Autowired
	PlotRepository plotRepository;

	@Autowired
	PlotDetailsRepository plotDetailsRepository;

	@Autowired
	SensorService sensorService;
	@Autowired
	ModelMapper modelMapper;

	@Value("${irrigate.sensor.base_url}")
	private String baseUrl;

	@Value("${server.port}")
	private String port;

	Logger logger = LoggerFactory.getLogger(IrrigationService.class);
	private final RestTemplate restTemplate = new RestTemplate();

	@Scheduled(cron = "0/60 * * * * *") // run job after every 60 seconds || 1 minute
	public void irrigateLandSensor() {
		LocalTime my_time = LocalTime.now();
		logger.info("Scheduler running at " + my_time);

		List<PlotEB> plots = plotRepository.findAll();

		for (PlotEB plot : plots) {
			if (plot.getPlotDetails() != null) {
				PlotDetailsEB details = plot.getPlotDetails();
				String irrigateStartTimeStr = details.getIrrigationStartTime().toString();

				LocalTime currentTime = LocalTime.now();
				
				LocalTime irrigateStartTime = LocalTime.parse(irrigateStartTimeStr, DateTimeFormatter.ISO_TIME);
				
				if (currentTime.getHour() == irrigateStartTime.getHour() && currentTime.getMinute() == irrigateStartTime.getMinute()) {
					// notify irrigate land sensor
					logger.info("About to notify sensor! ");

					Boolean notified = notifySensor(plot);
					if (notified) {
						// update sensor notified status
						PlotEB updatePlot = plotRepository.findById(plot.getPlotId()).get();
						details.setSensorNotified(true);
						updatePlot.setPlotDetails(details);
						plotRepository.save(updatePlot);
					}
				}

			}
		}

	}

	public Boolean notifySensor(PlotEB plot) throws ResourceNotFoundException {

		PlotDto plotDto = modelMapper.map(plot, PlotDto.class);

		// check response status code
		return sensorService.notifySensor(plotDto);

	}

}
