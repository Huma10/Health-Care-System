package com.abc.health.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.abc.health.dto.BaseDTO;
import com.abc.health.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserRegistrationForm extends BaseForm {
	@NotEmpty(message = "Login is required")
	@Pattern(regexp="(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))*$",message = "Email id is invalid")
	private String login;
	@NotEmpty(message = "First Name is required")
	@Pattern(regexp = "(^[A-Za-z ]*)*$",message = "First Name is Invalid")
	private String firstName;
	@NotEmpty(message = "Last Name is required")
	@Pattern(regexp = "(^[A-Za-z ]*)*$",message = "Last Name is Invalid")
	private String lastName;
	@NotEmpty(message = "Gender is required")
	private String gender;
	@NotEmpty(message ="Mobile no. is required")
	@Pattern(regexp="(^[7-9][0-9]{9})*$",message = "Mobile No is Invalid")
	private String mobileNo;
	@NotEmpty(message = "Password is required")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,20}$", message = "Incorrect Format")
	private String password;
	private Long roleId;
	private String roleName;

	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		UserDTO bean = new UserDTO();
		bean.setId(id);
		bean.setLogin(login);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setGender(gender);
		bean.setMobileNo(mobileNo);
		bean.setRoleId(2L);
		bean.setRoleName("USER");
		bean.setPassword(password);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		UserDTO bean = (UserDTO)bDto;
		id = bean.getId();
		login = bean.getLogin();
		firstName = bean.getFirstName();
		lastName = bean.getLastName();
		gender = bean.getGender();
		mobileNo = bean.getMobileNo();
		password = bean.getPassword();
		roleId = bean.getRoleId();
		roleName = bean.getRoleName();
		createdBy = bean.getCreatedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedBy = bean.getModifiedBy();
		modifiedDateTime = bean.getModifiedDatetime();
	}

}
