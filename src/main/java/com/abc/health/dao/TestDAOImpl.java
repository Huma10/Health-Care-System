package com.abc.health.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abc.health.dto.TestDTO;


@Repository
public class TestDAOImpl implements TestDAOInt{

	private static Logger log = Logger.getLogger(TestDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(TestDTO dto) {
		// TODO Auto-generated method stub
		log.info("TestDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long)session.save(dto);
		log.info("TestDAOImpl Add method ends");
		return pk;
	}




	@Override
	public List<TestDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<TestDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("TestDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<TestDTO> query = session.createQuery("from TestDTO", TestDTO.class);
		List<TestDTO> list = query.getResultList();
		log.info("TestDAOImpl List method End");
		return list;
	}

	@Override
	public List<TestDTO> search(TestDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<TestDTO> search(TestDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("TestDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TestDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getTestName() != null && dto.getTestName().length() > 0) {
				criteria.add(Restrictions.like("testName", dto.getTestName() + "%"));
			}
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("TestDAOImpl Search method End");
		return criteria.list();
	}
	
	@Override
	@Transactional
	public void delete(TestDTO dto) {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));

	}




	@Override
	public TestDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("TestDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		TestDTO dto = (TestDTO) session.get(TestDTO.class, pk);
		log.info("TestDAOImpl FindByPk method End");
		return dto;
	}




	@Override
	public void update(TestDTO dto) {
		// TODO Auto-generated method stub
		log.info("TestDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("TestDAOImpl update method End");
	}
	
	
	@Override
	public List<TestDTO> listByCenterId(Long centerId) {
		// TODO Auto-generated method stub
		log.info("TestDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<TestDTO> query = session.createQuery("from TestDTO where centerId ='"+centerId+"'", TestDTO.class);
		List<TestDTO> list = query.getResultList();
		log.info("TestDAOImpl List method End");
		return list;
	}
}
