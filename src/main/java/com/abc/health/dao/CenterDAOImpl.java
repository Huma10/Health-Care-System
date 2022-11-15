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

import com.abc.health.dto.CenterDTO;
import com.abc.health.dto.ContactDTO;
import com.abc.health.dto.UserDTO;

@Repository
public class CenterDAOImpl implements CenterDAOInt {

	private static Logger log = Logger.getLogger(CenterDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(CenterDTO dto) {
		// TODO Auto-generated method stub
		log.info("CenterDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long)session.save(dto);
		log.info("CenterDAOImpl Add method ends");
		return pk;
	}




	@Override
	public List<CenterDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<CenterDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("CenterDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<CenterDTO> query = session.createQuery("from CenterDTO", CenterDTO.class);
		List<CenterDTO> list = query.getResultList();
		log.info("CenterDAOImpl List method End");
		return list;
	}

	@Override
	public List<CenterDTO> search(CenterDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<CenterDTO> search(CenterDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("CenterDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CenterDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getCenterName() != null && dto.getCenterName().length() > 0) {
				criteria.add(Restrictions.like("centerName", dto.getCenterName() + "%"));
			}
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("CenterDAOImpl Search method End");
		System.out.println("lis "+criteria.list().size());
		return criteria.list();
	}
	
	@Override
	@Transactional
	public void delete(CenterDTO dto) {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));

	}




	@Override
	public CenterDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("CenterDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		CenterDTO dto = (CenterDTO) session.get(CenterDTO.class, pk);
		log.info("CenterDAOImpl FindByPk method End");
		return dto;
	}




	@Override
	public void update(CenterDTO dto) {
		// TODO Auto-generated method stub
		log.info("CenterDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("CenterDAOImpl update method End");
	}
}
