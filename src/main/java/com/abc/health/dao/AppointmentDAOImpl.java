package com.abc.health.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abc.health.dto.AppointmentDTO;

@Repository
public class AppointmentDAOImpl implements AppointmentDAOInt {

	private static Logger log = Logger.getLogger(AppointmentDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public Long add(AppointmentDTO dto) {
		// TODO Auto-generated method stub
		log.info("AppointmentDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long)session.save(dto);
		log.info("AppointmentDAOImpl Add method ends");
		return pk;
	}

	@Override
	public void delete(AppointmentDTO dto) {
		// TODO Auto-generated method stub
	entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));

	}

	@Override
	public AppointmentDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("AppointmentDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		AppointmentDTO dto = (AppointmentDTO) session.get(AppointmentDTO.class, pk);
		log.info("AppointmentDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public AppointmentDTO findByLogin(String login) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AppointmentDTO.class);
		criteria.add(Restrictions.eq("login", login));
		AppointmentDTO dto = (AppointmentDTO) criteria.uniqueResult();
		return dto;
	}

	@Override
	public void update(AppointmentDTO dto) {
		// TODO Auto-generated method stub
		log.info("AppointmentDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		System.out.println("status "+dto.getStatus());
		String hqlUpdate = "update AppointmentDTO a set a.status =:newStatus where a.id =:oldId";
		session.createQuery(hqlUpdate).setString("newStatus", dto.getStatus()).setLong("oldId", dto.getId()).executeUpdate();
		log.info("AppointmentDAOImpl update method End");
	}

	@Override
	public List<AppointmentDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<AppointmentDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AppointmentDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<AppointmentDTO> query = session.createQuery("from AppointmentDTO", AppointmentDTO.class);
		List<AppointmentDTO> list = query.getResultList();
		log.info("AppointmentDAOImpl List method End");
		return list;
	}

	@Override
	public List<AppointmentDTO> search(AppointmentDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<AppointmentDTO> search(AppointmentDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AppointmentDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AppointmentDTO.class);
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
		log.info("AppointmentDAOImpl Search method End");
		return criteria.list();
	}

	@Override
	public void cancelAppointment(AppointmentDTO dto) {
		// TODO Auto-generated method stub
		log.info("AppointmentDAOImpl cancelAppointment method Start");
		Session session = entityManager.unwrap(Session.class);
		System.out.println("status "+dto.getStatus());
		String hqlUpdate = "update AppointmentDTO a set a.status =:newStatus where a.id =:oldId";
		session.createQuery(hqlUpdate).setString("newStatus", dto.getStatus()).setLong("oldId", dto.getId()).executeUpdate();
		log.info("AppointmentDAOImpl cancelAppointment method End");
	}

	
}
