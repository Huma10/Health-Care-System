package com.abc.health.service;

import java.util.List;

import com.abc.health.dto.AppointmentDTO;
import com.abc.health.exception.DuplicateRecordException;

public interface AppointmentServiceInt {
	
	public long add(AppointmentDTO dto) throws DuplicateRecordException;

	public void delete(AppointmentDTO dto);

	public AppointmentDTO findBypk(long pk);

	public AppointmentDTO findByLogin(String login);

	public void update(AppointmentDTO dto) throws DuplicateRecordException;

	public void cancelAppointment(AppointmentDTO dto);
	
	public List<AppointmentDTO> list();

	public List<AppointmentDTO> list(int pageNo, int pageSize);

	public List<AppointmentDTO> search(AppointmentDTO dto);

	public List<AppointmentDTO> search(AppointmentDTO dto, int pageNo, int pageSize);

}
