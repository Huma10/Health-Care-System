package com.abc.health.service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.health.dao.CenterDAOInt;
import com.abc.health.dao.TestDAOInt;
import com.abc.health.dto.CenterDTO;
import com.abc.health.dto.TestDTO;
import com.abc.health.dto.UserDTO;
import com.abc.health.exception.DuplicateRecordException;

@Service
public class TestServiceImpl implements TestServiceInt {

	private static Logger log = Logger.getLogger(TestServiceImpl.class.getName());

	@Autowired
	private TestDAOInt dao;

	@Override
	@Transactional
	public long add(TestDTO dto) throws DuplicateRecordException {
		log.info("TestServiceImpl Add method start");
		long pk = dao.add(dto);
		log.info("TestServiceImpl Add method end");
		return pk;
	}

	

	@Override
	public List<TestDTO> list() {
		// TODO Auto-generated method stub
		log.info("TestServiceImpl list method start");
		List<TestDTO> list = dao.list();
		log.info("TestServiceImpl list method end");
		return list;
	}

	@Override
	public List<TestDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("TestServiceImpl list method start");
		List<TestDTO> list = dao.list(pageNo, pageSize);
		log.info("TestServiceImpl list method end");
		return list;
	}

	@Override
	public List<TestDTO> search(TestDTO dto) {
		// TODO Auto-generated method stub
		log.info("TestServiceImpl search method start");
		List<TestDTO> list = dao.search(dto);
		log.info("TestServiceImpl search method end");
		return list;
	}

	@Override
	public List<TestDTO> search(TestDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("TestServiceImpl search method start");
		List<TestDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("TestServiceImpl search method end");
		return list;
	}



	@Override
	@Transactional
	public void delete(TestDTO dto) {
		// TODO Auto-generated method stub
		log.info("TestServiceImpl delete method start");
		dao.delete(dto);
		log.info("TestServiceImpl delete method end");
	}



	@Override
	public TestDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("TestServiceImpl findBypk method start");
		TestDTO dto = dao.findBypk(pk);
		log.info("TestServiceImpl findBypk method end");
		return dto;
	}



	@Override
	@Transactional
	public void update(TestDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		dao.update(dto);
	}



	@Override
	public List<TestDTO> listByCenterId(Long centerId) {
		// TODO Auto-generated method stub
		log.info("TestServiceImpl listByCenterId method start");
		List<TestDTO> list = dao.listByCenterId(centerId);
		log.info("TestServiceImpl listByCenterId method end");
		return list;
	}
	
	
}
