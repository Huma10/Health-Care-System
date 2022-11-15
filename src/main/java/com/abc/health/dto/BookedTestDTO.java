package com.abc.health.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "h_bookedtest")
@Setter
@Getter
public class BookedTestDTO extends BaseDTO {

	@Column(name = "appointment_id", length = 255)
	private Long appointmentId;
	
	@ManyToOne
	@JoinColumn(name = "appointmentid")
	private AppointmentDTO appointment;
	
	@Column(name = "test_id")
	private Long testId;
	
	@ManyToOne
	@JoinColumn(name = "testid")
	private TestDTO bookedTest;
	
	
	
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
