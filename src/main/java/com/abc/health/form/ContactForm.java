package com.abc.health.form;

import javax.validation.constraints.NotEmpty;

import com.abc.health.dto.BaseDTO;
import com.abc.health.dto.ContactDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactForm extends BaseForm {
	@NotEmpty(message = "Login is required")
	private String login;
	@NotEmpty(message = "Message is required")
	private String message;

	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub

		ContactDTO bean = new ContactDTO();
		bean.setLogin(login);
		bean.setMessage(message);
		bean.setCreatedBy(createdBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedBy(modifiedBy);
		bean.setModifiedDatetime(modifiedDateTime);

		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub

		ContactDTO bean = (ContactDTO) getDTO();
		login = bean.getLogin();
		message = bean.getMessage();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
	}

}
