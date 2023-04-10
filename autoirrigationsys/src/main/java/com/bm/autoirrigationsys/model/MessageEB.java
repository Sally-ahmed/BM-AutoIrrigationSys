package com.bm.autoirrigationsys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class MessageEB {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "message_id")
	private long messageId;

	@Column(name = "message")
	private String message;


}
