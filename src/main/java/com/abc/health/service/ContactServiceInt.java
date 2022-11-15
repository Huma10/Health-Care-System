package com.abc.health.service;

import java.util.List;

import com.abc.health.dto.ContactDTO;
import com.abc.health.exception.DuplicateRecordException;

public interface ContactServiceInt {

	public long add(ContactDTO dto) throws DuplicateRecordException;

	public List<ContactDTO> list();

	public List<ContactDTO> list(int pageNo, int pageSize);

	public List<ContactDTO> search(ContactDTO dto);

	public List<ContactDTO> search(ContactDTO dto, int pageNo, int pageSize);
}
