package com.abc.health.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abc.health.dto.ContactDTO;
@Repository
public class ContactDAOImpl implements ContactDAOInt {


	private static Logger log = Logger.getLogger(ContactDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(ContactDTO dto) {
		// TODO Auto-generated method stub
		log.info("ContactDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long)session.save(dto);
		log.info("ContactDAOImpl Add method ends");
		return pk;
	}




	@Override
	public List<ContactDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<ContactDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("ContactDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<ContactDTO> query = session.createQuery("from ContactDTO", ContactDTO.class);
		List<ContactDTO> list = query.getResultList();
		log.info("ContactDAOImpl List method End");
		return list;
	}

	@Override
	public List<ContactDTO> search(ContactDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<ContactDTO> search(ContactDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("ContactDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ContactDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("ContactDAOImpl Search method End");
		return criteria.list();
	}
}
