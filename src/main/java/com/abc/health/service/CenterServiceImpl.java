package com.abc.health.service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.health.dao.CenterDAOInt;
import com.abc.health.dto.CenterDTO;
import com.abc.health.dto.UserDTO;
import com.abc.health.exception.DuplicateRecordException;

@Service
public class CenterServiceImpl implements CenterServiceInt {

	private static Logger log = Logger.getLogger(CenterServiceImpl.class.getName());

	@Autowired
	private CenterDAOInt dao;

	@Override
	@Transactional
	public long add(CenterDTO dto) throws DuplicateRecordException {
		log.info("CenterServiceImpl Add method start");
		long pk = dao.add(dto);
		log.info("CenterServiceImpl Add method end");
		return pk;
	}

	

	@Override
	public List<CenterDTO> list() {
		// TODO Auto-generated method stub
		log.info("CenterServiceImpl list method start");
		List<CenterDTO> list = dao.list();
		log.info("CenterServiceImpl list method end");
		return list;
	}

	@Override
	public List<CenterDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("CenterServiceImpl list method start");
		List<CenterDTO> list = dao.list(pageNo, pageSize);
		log.info("CenterServiceImpl list method end");
		return list;
	}

	@Override
	public List<CenterDTO> search(CenterDTO dto) {
		// TODO Auto-generated method stub
		log.info("CenterServiceImpl search method start");
		List<CenterDTO> list = dao.search(dto);
		log.info("CenterServiceImpl search method end");
		return list;
	}

	@Override
	public List<CenterDTO> search(CenterDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("CenterServiceImpl search method start");
		List<CenterDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("CenterServiceImpl search method end");
		return list;
	}



	@Override
	@Transactional
	public void delete(CenterDTO dto) {
		// TODO Auto-generated method stub
		log.info("CenterServiceImpl delete method start");
		dao.delete(dto);
		log.info("CenterServiceImpl delete method end");
	}



	@Override
	public CenterDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("CenterServiceImpl findBypk method start");
		CenterDTO dto = dao.findBypk(pk);
		log.info("CenterServiceImpl findBypk method end");
		return dto;
	}



	@Override
	@Transactional
	public void update(CenterDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		dao.update(dto);
	}
	
	
}
