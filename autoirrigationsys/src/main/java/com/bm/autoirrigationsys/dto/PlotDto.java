package com.bm.autoirrigationsys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlotDto {
	
	
	private long plotId;
	
	private Double size;
	
	private String crop;
	
	private String location;

	private PlotDetailsDTO plotDetails;

}
