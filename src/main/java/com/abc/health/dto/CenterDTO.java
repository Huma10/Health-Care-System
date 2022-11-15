package com.abc.health.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "h_Center")
@Setter
@Getter
public class CenterDTO extends BaseDTO {

	@Column(name = "center_name", length = 255)
	private String centerName;
	@Column(name = "center_no", length = 255)
	private String centerNo;
	@Column(name = "center_address", length = 255)
	private String centerAddress;
	@OneToMany(
			mappedBy = "center",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	@Cascade({org.hibernate.annotations.CascadeType.DELETE})
	private List<TestDTO> listOfTests  = new ArrayList<>();
	
	@OneToMany(
			mappedBy = "center",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	@Cascade({org.hibernate.annotations.CascadeType.DELETE})
	private List<AppointmentDTO> listOfAppointments  = new ArrayList<>();
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
