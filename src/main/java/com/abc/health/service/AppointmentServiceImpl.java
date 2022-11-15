package com.abc.health.service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.health.dao.AppointmentDAOInt;
import com.abc.health.dto.AppointmentDTO;
import com.abc.health.exception.DuplicateRecordException;
@Service
public class AppointmentServiceImpl implements AppointmentServiceInt {

	private static Logger log = Logger.getLogger(AppointmentServiceImpl.class.getName());


	@Autowired
	private AppointmentDAOInt dao;

	@Override
	@Transactional
	public long add(AppointmentDTO dto) throws DuplicateRecordException {
		log.info("AppointmentServiceImpl Add method start");
		long pk = dao.add(dto);
		log.info("AppointmentServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(AppointmentDTO dto) {
		// TODO Auto-generated method stub
		dao.delete(dto);
	}

	@Override
	public AppointmentDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("AppointmentServiceImpl findBypk method start");
		AppointmentDTO dto = dao.findBypk(pk);
		log.info("AppointmentServiceImpl findBypk method end");
		return dto;
	}

	@Override
	public AppointmentDTO findByLogin(String login) {
		// TODO Auto-generated method stub
		AppointmentDTO dto=dao.findByLogin(login);
		return dto;
	}

	@Override
	@Transactional
	public void update(AppointmentDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
			dao.update(dto);
	}

	@Override
	public List<AppointmentDTO> list() {
		// TODO Auto-generated method stub
		log.info("AppointmentServiceImpl list method start");
		List<AppointmentDTO> list = dao.list();
		log.info("AppointmentServiceImpl list method end");
		return list;
	}

	@Override
	public List<AppointmentDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AppointmentServiceImpl list method start");
		List<AppointmentDTO> list = dao.list(pageNo, pageSize);
		log.info("AppointmentServiceImpl list method end");
		return list;
	}

	@Override
	public List<AppointmentDTO> search(AppointmentDTO dto) {
		// TODO Auto-generated method stub
		log.info("AppointmentServiceImpl search method start");
		List<AppointmentDTO> list = dao.search(dto);
		log.info("AppointmentServiceImpl search method end");
		return list;
	}

	@Override
	public List<AppointmentDTO> search(AppointmentDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AppointmentServiceImpl search method start");
		List<AppointmentDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("AppointmentServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public void cancelAppointment(AppointmentDTO dto) {
		// TODO Auto-generated method stub
		dao.cancelAppointment(dto);
	}
}
