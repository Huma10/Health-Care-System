package com.abc.health.dao;

import java.util.List;

import com.abc.health.dto.AppointmentDTO;



public interface AppointmentDAOInt {
	
	public Long add(AppointmentDTO dto);

	public void delete(AppointmentDTO dto);
	
	public AppointmentDTO findBypk(long pk);
	
	public AppointmentDTO findByLogin(String login);
	
	public void update(AppointmentDTO dto);
	
	public void cancelAppointment(AppointmentDTO dto);
	
	public List<AppointmentDTO> list();
	
	public List<AppointmentDTO>list(int pageNo,int pageSize);
	
	public List<AppointmentDTO> search(AppointmentDTO dto);
	
	public List<AppointmentDTO> search(AppointmentDTO dto,int pageNo,int pageSize);
}
