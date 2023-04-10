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
public class PlotEB {
		
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		@Id
		@Column(name = "plot_id")
		private long plotId;
		
		@Column(name = "size")
		private Double size;
		
		@NonNull
		@Column(name = "crop")
		private String crop;
		
		@NonNull
		@Column(name = "location")
		private String location;


		@OneToOne
		@JoinColumn(name = "details_id")
		private PlotDetailsEB plotDetails;

		public PlotEB() {}
		public PlotEB(long plotId, Double size, @NonNull String crop, @NonNull String location,
				PlotDetailsEB plotDetails) {
	
			this.plotId = plotId;
			this.size = size;
			this.crop = crop;
			this.location = location;
			this.plotDetails = plotDetails;
		}


		public PlotEB(long plotId, Double size, @NonNull String crop, @NonNull String location) {
			this.plotId = plotId;
			this.size = size;
			this.crop = crop;
			this.location = location;
		}


		

		
		
}
