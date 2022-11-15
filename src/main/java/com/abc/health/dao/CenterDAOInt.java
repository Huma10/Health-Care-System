package com.abc.health.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.abc.health.dto.CenterDTO;
import com.abc.health.dto.UserDTO;

public interface CenterDAOInt {

	public long add(CenterDTO dto);
	
	public void delete(CenterDTO dto);
	
	public CenterDTO findBypk(long pk);
	
	public void update(CenterDTO dto);
	
	public List<CenterDTO> list();
	
	public List<CenterDTO>list(int pageNo,int pageSize);
	
	public List<CenterDTO> search(CenterDTO dto);
	
	public List<CenterDTO> search(CenterDTO dto,int pageNo,int pageSize);
}
