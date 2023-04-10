package com.bm.autoirrigationsys.services;

import java.util.List;

import com.bm.autoirrigationsys.dto.PlotDetailsDTO;
import com.bm.autoirrigationsys.dto.PlotDto;
import com.bm.autoirrigationsys.exception.ResourceNotFoundException;

public interface PlotService {

	PlotDto savePlot(PlotDto plotDto) throws ResourceNotFoundException;

	List<PlotDto> getAllPlots() throws ResourceNotFoundException;

	PlotDto updatePlot(PlotDto plotDto, long id) throws ResourceNotFoundException;

	PlotDto configurePlot(PlotDetailsDTO newDetailsDto, long id) throws ResourceNotFoundException;

	PlotDto getPlotById(long id);

}
