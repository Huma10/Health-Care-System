package com.abc.health.service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.health.dao.UserDAOInt;
import com.abc.health.dto.UserDTO;
import com.abc.health.exception.DuplicateRecordException;

@Service
public class UserServiceImpl implements UserServiceInt {
	
	private static Logger log = Logger.getLogger(UserServiceImpl.class.getName());


	@Autowired
	private UserDAOInt dao;

	@Override
	@Transactional
	public long add(UserDTO dto) throws DuplicateRecordException {
		log.info("UserServiceImpl Add method start");
		UserDTO existdto = dao.findByLogin(dto.getLogin());
		if (existdto != null)
			throw new DuplicateRecordException("Login id Already Exist");
		long pk = dao.add(dto);
		log.info("UserServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(UserDTO dto) {
		// TODO Auto-generated method stub
		dao.delete(dto);
	}

	@Override
	public UserDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl findBypk method start");
		UserDTO dto = dao.findBypk(pk);
		log.info("UserServiceImpl findBypk method end");
		return dto;
	}

	@Override
	public UserDTO findByLogin(String login) {
		// TODO Auto-generated method stub
		UserDTO dto=dao.findByLogin(login);
		return dto;
	}

	@Override
	@Transactional
	public void update(UserDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
			dao.update(dto);
	}

	@Override
	public List<UserDTO> list() {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl list method start");
		List<UserDTO> list = dao.list();
		log.info("UserServiceImpl list method end");
		return list;
	}

	@Override
	public List<UserDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl list method start");
		List<UserDTO> list = dao.list(pageNo, pageSize);
		log.info("UserServiceImpl list method end");
		return list;
	}

	@Override
	public List<UserDTO> search(UserDTO dto) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl search method start");
		List<UserDTO> list = dao.search(dto);
		log.info("UserServiceImpl search method end");
		return list;
	}

	@Override
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl search method start");
		List<UserDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("UserServiceImpl search method end");
		return list;
	}

	@Override
	public UserDTO authentication(UserDTO dto) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl authentication method start");
		dto = dao.authentication(dto);
		log.info("UserServiceImpl authentication method end");
		return dto;
	}

	@Override
	@Transactional
	public boolean changePassword(Long id, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl  changePassword method start"); 
		UserDTO dtoExist =  findBypk(id); 
		if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {
				  dtoExist.setPassword(newPassword); 
				  dao.update(dtoExist);
				  log.info("UserServiceImpl  changePassword method end"); 
				  return true;
				  }else
				  {
				
		return false;}
	}

	@Override
	public boolean forgetPassword(String login) {
		// TODO Auto-generated method stub
		return false;
	}

	public Long countUser() {
		// TODO Auto-generated method stub
		return dao.countUser();
	}
	
	
}
