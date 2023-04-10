package com.bm.autoirrigationsys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bm.autoirrigationsys.dto.MessageDTO;
import com.bm.autoirrigationsys.services.MessageService;


@RestController
@RequestMapping("/alerts")
public class AlertsController {

	@Autowired
	private MessageService messageService;

	

	@PostMapping("/send")
	public String sendAlert(@RequestBody MessageDTO message) {
		messageService.saveMessage(message);

		return "Alert Sent ok";

	}

}
