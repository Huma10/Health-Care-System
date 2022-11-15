package com.abc.health.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ManyToAny;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "h_test")
@Setter
@Getter
public class TestDTO extends BaseDTO{

	private String testName;
	@ManyToOne
	@JoinColumn(name = "centerid")
	private CenterDTO center;
	@Column(name = "center_id")
	private Long centerId;	
	@OneToMany(
			mappedBy = "bookedTest",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	@Cascade({org.hibernate.annotations.CascadeType.DELETE})
	private List<BookedTestDTO> listOfBookingTests  = new ArrayList<>();
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
