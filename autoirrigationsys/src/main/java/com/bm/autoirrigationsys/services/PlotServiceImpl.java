package com.bm.autoirrigationsys.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bm.autoirrigationsys.dto.PlotDetailsDTO;
import com.bm.autoirrigationsys.dto.PlotDto;
import com.bm.autoirrigationsys.exception.ResourceNotFoundException;
import com.bm.autoirrigationsys.model.PlotDetailsEB;
import com.bm.autoirrigationsys.model.PlotEB;
import com.bm.autoirrigationsys.repo.PlotDetailsRepository;
import com.bm.autoirrigationsys.repo.PlotRepository;

@Service("PlotService")
public class PlotServiceImpl implements PlotService  {

	@Autowired
	PlotRepository plotRepository;

	@Autowired
	PlotDetailsRepository plotDetailsRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public PlotDto savePlot(PlotDto plotDto) throws ResourceNotFoundException {

		PlotEB newPlotEB = modelMapper.map(plotDto, PlotEB.class);
		try {

			newPlotEB = plotRepository.save(newPlotEB);
			return modelMapper.map(newPlotEB, PlotDto.class);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Couldn't create a new Plot ");
		}

	}

	
	@Override
	public List<PlotDto> getAllPlots() throws ResourceNotFoundException {

		try {

			List<PlotEB> plotEBs = plotRepository.findAll();
			List<PlotDto> plotDtos = new ArrayList<>();

			for (PlotEB plotEB : plotEBs) {
				PlotDto plotDto = modelMapper.map(plotEB, PlotDto.class);
				plotDtos.add(plotDto);

			}

			return plotDtos;
		} catch (Exception e) {
			throw new ResourceNotFoundException("No Plots exist. ");
		}
	}

	@Override
	public PlotDto updatePlot(PlotDto plotDto, long id) throws ResourceNotFoundException {

		try {
			PlotEB existingPlot = plotRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Can't find Plot with id = " + id));

			existingPlot.setSize(plotDto.getSize());
			existingPlot.setCrop(plotDto.getCrop());
			existingPlot.setLocation(plotDto.getLocation());

			return modelMapper.map(plotRepository.save(existingPlot), PlotDto.class);
		} catch (Exception e) {

			throw new ResourceNotFoundException("Can't find Plot with id = " + id);

		}

	}

	@Override
	public PlotDto configurePlot(PlotDetailsDTO newDetailsDto, long id) throws ResourceNotFoundException {
		try {
			PlotDetailsEB newDetailsEB = modelMapper.map(newDetailsDto, PlotDetailsEB.class);

			PlotEB existingPlot = plotRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Plot not found"));

			if (existingPlot.getPlotDetails() == null) {
				newDetailsEB = plotDetailsRepository.save(newDetailsEB);

				existingPlot.setPlotDetails(newDetailsEB);
			} else {

				PlotDetailsEB existingDetails = plotDetailsRepository
						.findById(existingPlot.getPlotDetails().getDetailsId()).get();
				existingDetails.setWaterAmountLtr(newDetailsEB.getWaterAmountLtr());
				existingDetails.setIrrigationStartTime(newDetailsEB.getIrrigationStartTime());
				existingDetails.setIrrigationEndTime(newDetailsEB.getIrrigationEndTime());

				existingPlot.setPlotDetails(existingDetails);
			}
			existingPlot = plotRepository.save(existingPlot);
			return modelMapper.map(existingPlot, PlotDto.class);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Plot with :" + id + "not found ");
		}

	}

	

	@Override
	public PlotDto getPlotById(long id) {
		ModelMapper mapper = new ModelMapper();
		PlotDto plotDto = null;
		Optional<PlotEB> plotEB = plotRepository.findById(id);
		if (plotEB.isPresent())
			plotDto = mapper.map(plotEB.get(), PlotDto.class);
		return plotDto;

	}
	
	
}
