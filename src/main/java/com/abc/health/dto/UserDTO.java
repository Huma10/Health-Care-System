package com.abc.health.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "h_user")
@Setter
@Getter
public class UserDTO extends BaseDTO {
	
	@Column(name = "login", length = 255)
	private String login;
	@Column(name = "first_name", length = 255)
	private String firstName;
	@Column(name = "last_name", length = 255)
	private String lastName;
	@Column(name = "gender", length = 255)
	private String gender;
	@Column(name = "mobile_no", length = 255)
	private String mobileNo;
	@Column(name = "password", length = 255)
	private String password;
	@Column(name = "role_id", length = 255)
	private Long roleId;
	@Column(name = "role_name", length = 255)
	private String roleName;
	
	@OneToMany(
			mappedBy = "user",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
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
