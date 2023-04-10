package com.bm.autoirrigationsys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlotDetailsDTO {
	
	
	private long detailsId;
		
	private Integer waterAmountLtr;
	
	private String irrigationStartTime;
	
	private String irrigationEndTime;
	
	private Boolean sensorNotified;

}
