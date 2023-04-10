package com.bm.autoirrigationsys.dto;



public class SlotDTO {

    private long slot_id;
    
    private String name;
    
    private String start_time;
    
    private String end_time;
    
    private Integer water_amount_ltr;
    
    private String irrigation_status;
       
	private Boolean sensor_notified;
        
    private Long plot_id;

	public Long getSlot_id() {
		return slot_id;
	}

	public void setSlot_id(Long slot_id) {
		this.slot_id = slot_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public Integer getWater_amount_ltr() {
		return water_amount_ltr;
	}

	public void setWater_amount_ltr(Integer water_amount_ltr) {
		this.water_amount_ltr = water_amount_ltr;
	}

	public String getIrrigation_status() {
		return irrigation_status;
	}

	public void setIrrigation_status(String irrigation_status) {
		this.irrigation_status = irrigation_status;
	}

	public Boolean getSensor_notified() {
		return sensor_notified;
	}

	public void setSensor_notified(Boolean sensor_notified) {
		this.sensor_notified = sensor_notified;
	}

	public Long getPlot_id() {
		return plot_id;
	}

	public void setPlot_id(Long plot_id) {
		this.plot_id = plot_id;
	}

    

    
}
