package com.abc.health.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.health.dao.BookedTestDAOInt;
import com.abc.health.dto.BookedTestDTO;
@Service
public class BookedTestServiceImpl implements BookedTestServiceInt {

	@Autowired
	private BookedTestDAOInt bookedTestDAO;
	@Override
	public Long add(BookedTestDTO dto) {
		// TODO Auto-generated method stub
		Long pk = bookedTestDAO.add(dto);
		return pk;
	}

}
