package com.abc.health.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.abc.health.dto.BaseDTO;
import com.abc.health.dto.CenterDTO;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class CenterForm extends BaseForm{

	@NotEmpty(message = "Center Name is required")
	private String centerName;
	@NotEmpty(message = "Center Name is required")
	private String centerAddress;
	@NotEmpty(message = "Center Name is required")
	@Pattern(regexp="(^[7-9][0-9]{9})*$",message = "Center No is Invalid")
	private String centerNo;
	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		CenterDTO bean = new CenterDTO();
		bean.setId(id);
		bean.setCenterName(centerName);
		bean.setCenterNo(centerNo);
		bean.setCenterAddress(centerAddress);
		bean.setCreatedBy(createdBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedBy(modifiedBy);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		CenterDTO bean = (CenterDTO)bDto;
		id = bean.getId();
		centerName = bean.getCenterName();
		centerAddress = bean.getCenterAddress();
		centerNo = bean.getCenterNo();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
	}

}
