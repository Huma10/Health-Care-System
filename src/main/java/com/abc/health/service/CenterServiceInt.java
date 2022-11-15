package com.abc.health.service;

import java.util.List;

import com.abc.health.dto.CenterDTO;
import com.abc.health.exception.DuplicateRecordException;

public interface CenterServiceInt {

	public long add(CenterDTO dto) throws DuplicateRecordException;
	
	public void delete(CenterDTO dto);
	
	public CenterDTO findBypk(long pk);

	public void update(CenterDTO dto) throws DuplicateRecordException;

	public List<CenterDTO> list();

	public List<CenterDTO> list(int pageNo, int pageSize);

	public List<CenterDTO> search(CenterDTO dto);

	public List<CenterDTO> search(CenterDTO dto, int pageNo, int pageSize);
}
