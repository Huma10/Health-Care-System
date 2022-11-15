package com.abc.health.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "h_contact")
@Setter
@Getter
public class ContactDTO extends BaseDTO {

	@Column(name = "login", length = 255)
	private String login;
	@Column(name = "message", length = 555)
	private String message;
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
