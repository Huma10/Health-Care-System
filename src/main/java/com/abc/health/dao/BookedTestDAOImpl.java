package com.abc.health.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abc.health.dto.BookedTestDTO;
@Repository
public class BookedTestDAOImpl implements BookedTestDAOInt {

	@Autowired
	private EntityManager entityManager;
	@Override
	public Long add(BookedTestDTO dto) {
		// TODO Auto-generated method stub
		//log.info("AppointmentDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long)session.save(dto);
		//log.info("AppointmentDAOImpl Add method ends");
		return pk;
	}

}
