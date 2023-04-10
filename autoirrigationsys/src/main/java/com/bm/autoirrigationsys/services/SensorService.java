package com.bm.autoirrigationsys.services;

import com.bm.autoirrigationsys.dto.PlotDto;

public interface SensorService {

	Boolean notifySensor(PlotDto plotDto);

}
