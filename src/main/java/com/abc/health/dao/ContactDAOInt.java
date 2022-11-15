package com.abc.health.dao;

import java.util.List;

import com.abc.health.dto.ContactDTO;


public interface ContactDAOInt {

	public long add(ContactDTO dto);
	
	public List<ContactDTO> list();
	
	public List<ContactDTO>list(int pageNo,int pageSize);
	
	public List<ContactDTO> search(ContactDTO dto);
	
	public List<ContactDTO> search(ContactDTO dto,int pageNo,int pageSize);
}
