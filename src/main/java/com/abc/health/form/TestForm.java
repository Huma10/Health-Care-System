package com.abc.health.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.abc.health.dto.BaseDTO;
import com.abc.health.dto.TestDTO;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class TestForm extends BaseForm {

	
	private Long centerId;
	@NotEmpty(message = "Test Name is required")
	private String testName;
	
	
	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		TestDTO bean = new TestDTO();
		bean.setId(id);
		bean.setTestName(testName);
		bean.setCenterId(centerId);
		bean.setCreatedBy(createdBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedBy(modifiedBy);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		TestDTO bean = (TestDTO)bDto;
		id = bean.getId();
		testName = bean.getTestName();
		centerId = bean.getCenterId();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
	}

}
