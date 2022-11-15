package com.abc.health.service;

import java.util.List;

import com.abc.health.dto.TestDTO;
import com.abc.health.exception.DuplicateRecordException;

public interface TestServiceInt {

	public long add(TestDTO dto) throws DuplicateRecordException;
	
	public void delete(TestDTO dto);
	
	public TestDTO findBypk(long pk);

	public void update(TestDTO dto) throws DuplicateRecordException;

	public List<TestDTO> list();

	public List<TestDTO> list(int pageNo, int pageSize);

	public List<TestDTO> search(TestDTO dto);

	public List<TestDTO> search(TestDTO dto, int pageNo, int pageSize);
	
	public List<TestDTO> listByCenterId(Long centerId);
}
