package com.abc.health.dao;

import java.util.List;

import com.abc.health.dto.TestDTO;

public interface TestDAOInt {

	public long add(TestDTO dto);
	
	public void delete(TestDTO dto);
	
	public TestDTO findBypk(long pk);
	
	public void update(TestDTO dto);
	
	public List<TestDTO> list();
	
	public List<TestDTO>list(int pageNo,int pageSize);
	
	public List<TestDTO> search(TestDTO dto);
	
	public List<TestDTO> search(TestDTO dto,int pageNo,int pageSize);

	List<TestDTO> listByCenterId(Long centerId);
}
