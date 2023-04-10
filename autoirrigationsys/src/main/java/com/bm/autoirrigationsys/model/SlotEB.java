package com.bm.autoirrigationsys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NonNull
public class SlotEB {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "slot_id")
    private long slotId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "start_time")
    private String startTime;
    
    @Column(name = "end_time")
    private String endTime;
    
    @Column(name = "water_amount_ltr")
    private Integer waterAmountLtr;
    
    @Column(name = "irrigation_status")
    private String irrigationStatus;
       
    @Column(name="sensor_notified" ,columnDefinition = "boolean default false")
	private Boolean sensorNotified;
        
    @OneToOne
    @JoinColumn(name = "plot_id")
    private PlotEB plot;

	


}
