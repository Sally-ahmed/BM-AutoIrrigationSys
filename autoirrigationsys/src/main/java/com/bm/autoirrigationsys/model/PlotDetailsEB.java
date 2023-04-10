package com.bm.autoirrigationsys.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class PlotDetailsEB {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "details_id", nullable = false)
	private long detailsId;

	@Column(name = "amount_of_water_ltr")
	private Integer waterAmountLtr;

	@Column(name = "irrigation_start_time")
	private String irrigationStartTime;

	@Column(name = "irrigation_end_time")
	private String irrigationEndTime;

	@Column(name = "sensor_notified" , columnDefinition = "boolean default false")
	private Boolean sensorNotified;

	
}
