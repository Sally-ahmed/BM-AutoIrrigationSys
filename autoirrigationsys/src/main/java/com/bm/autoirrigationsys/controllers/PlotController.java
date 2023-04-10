package com.bm.autoirrigationsys.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bm.autoirrigationsys.dto.PlotDetailsDTO;
import com.bm.autoirrigationsys.dto.PlotDto;
import com.bm.autoirrigationsys.exception.ResourceNotFoundException;
import com.bm.autoirrigationsys.services.PlotServiceImpl;

@RestController
@RequestMapping("/plot")
public class PlotController {

	@Autowired
	PlotServiceImpl plotService;

	@PostMapping("/add")
	public ResponseEntity<PlotDto> addPlot(@RequestBody PlotDto newPlotDto) {

		PlotDto plotDto = plotService.savePlot(newPlotDto);

		return new ResponseEntity<>(plotDto, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<PlotDto>> getAllPlots() throws ResourceNotFoundException {

		List<PlotDto> plotDtos = plotService.getAllPlots();

		if (plotDtos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(plotDtos, HttpStatus.OK);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<PlotDto> updatePlot(@RequestBody PlotDto plot, @PathVariable long id)
			throws ResourceNotFoundException {

		PlotDto plotDto = null;
		if (plot != null && id != 0) {
			plotDto = plotService.updatePlot(plot, id);
			return new ResponseEntity<>(plotDto, HttpStatus.OK);

//			if (plotDto == null) {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
		}
		return new ResponseEntity<>(plotDto, HttpStatus.NOT_MODIFIED);

	}

	@PutMapping("/configure/{id}")
	public ResponseEntity<PlotDto> configurePlot(@RequestBody PlotDetailsDTO plotDetails, @PathVariable long id)
			throws ResourceNotFoundException {

		PlotDto plotDto = null;
		if (plotDetails != null && id != 0) {
			plotDto = plotService.configurePlot(plotDetails, id);
			return new ResponseEntity<>(plotDto, HttpStatus.OK);

//			if (plotDto == null) {
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//			}
		}

		return new ResponseEntity<>(plotDto, HttpStatus.NOT_MODIFIED);
	}

}
