package com.abc.health.form;

import java.util.Date;

import com.abc.health.dto.AppointmentDTO;
import com.abc.health.dto.BaseDTO;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class AppointmentForm extends BaseForm {

	private Long userId;
	
	private Long centerId;
	
	private Long testId;
	
	private Date dateOfAppointment;
	
	private String status;
	
	private String timeSlot;
	
	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		AppointmentDTO bean = new AppointmentDTO();
		bean.setId(id);
		bean.setUserId(userId);
		//bean.setTestId(testId);
		bean.setDateOfAppointnment(dateOfAppointment);
		bean.setStatus(status);
		bean.setCenterId(centerId);
		bean.setTimeSlot(timeSlot);
		bean.setCreatedBy(createdBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedBy(modifiedBy);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		AppointmentDTO bean = (AppointmentDTO)bDto;
		id = bean.getId();
		userId = bean.getUserId();
		//testId = bean.getTestId();
		centerId = bean.getCenterId();
		dateOfAppointment = bean.getDateOfAppointnment();
		status = bean.getStatus();
		timeSlot = bean.getTimeSlot();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
	}

}
