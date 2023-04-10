package com.bm.autoirrigationsys.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bm.autoirrigationsys.dto.PlotDto;

@RestController
@RequestMapping("/sensor")
public class SensorController {


	@PostMapping("/notify")
	public ResponseEntity<String> notifySensor(@RequestBody PlotDto plot) {
		// Dummy implementation of sensor endpoint

		return new ResponseEntity<>("", HttpStatus.OK);
	}

	

}
