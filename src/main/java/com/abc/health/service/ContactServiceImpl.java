package com.abc.health.service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.health.dao.ContactDAOInt;
import com.abc.health.dto.ContactDTO;
import com.abc.health.exception.DuplicateRecordException;

@Service
public class ContactServiceImpl implements ContactServiceInt {

	private static Logger log = Logger.getLogger(ContactServiceImpl.class.getName());

	@Autowired
	private ContactDAOInt dao;

	@Override
	@Transactional
	public long add(ContactDTO dto) throws DuplicateRecordException {
		log.info("ContactServiceImpl Add method start");
		long pk = dao.add(dto);
		log.info("ContactServiceImpl Add method end");
		return pk;
	}

	

	@Override
	public List<ContactDTO> list() {
		// TODO Auto-generated method stub
		log.info("ContactServiceImpl list method start");
		List<ContactDTO> list = dao.list();
		log.info("ContactServiceImpl list method end");
		return list;
	}

	@Override
	public List<ContactDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("ContactServiceImpl list method start");
		List<ContactDTO> list = dao.list(pageNo, pageSize);
		log.info("ContactServiceImpl list method end");
		return list;
	}

	@Override
	public List<ContactDTO> search(ContactDTO dto) {
		// TODO Auto-generated method stub
		log.info("ContactServiceImpl search method start");
		List<ContactDTO> list = dao.search(dto);
		log.info("ContactServiceImpl search method end");
		return list;
	}

	@Override
	public List<ContactDTO> search(ContactDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("ContactServiceImpl search method start");
		List<ContactDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("ContactServiceImpl search method end");
		return list;
	}

}
