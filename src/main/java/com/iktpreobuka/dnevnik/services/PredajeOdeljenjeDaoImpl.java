package com.iktpreobuka.dnevnik.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.dnevnik.entities.PredajeOdeljenjeEntity;

@Service
public class PredajeOdeljenjeDaoImpl implements PredajeOdeljenjeDao {
	
	@PersistenceContext
    EntityManager em;
	
	@Override
	public List<PredajeOdeljenjeEntity> findPredajeOdeljenjeByOdeljenje(Integer id) {
		String sql = "select d " +
				"from DrziNastavuEntity d " +
				"left join fetch d.odeljenje o " +
				"where o.id= :id  ";
		
		Query query = em.createQuery(sql);
        query.setParameter("id", id);
        
        List<PredajeOdeljenjeEntity> result = new ArrayList<PredajeOdeljenjeEntity>();
        result = query.getResultList();
        return result;
	}
	
	@Override
	public List<PredajeOdeljenjeEntity> findPredajeOdeljenjeByPredaje(Integer id) {
		String sql = "select d " +
				"from DrziNastavuEntity d " +
				"left join fetch d.predaje p " +
				"where p.id= :id  ";
		
		Query query = em.createQuery(sql);
        query.setParameter("id", id);
        
        List<PredajeOdeljenjeEntity> result = new ArrayList<PredajeOdeljenjeEntity>();
        result = query.getResultList();
        return result;
	}

	
	/*@Override
	public List<DrziNastavuEntity> findDrziNastavuByPredajeOdeljnje(Integer predajeId, Integer odeljenjeId) {
		String sql = "select d " +
				"from DrziNastavuEntity d " +
				"left join fetch d.predaje p left join fetch p.odeljenje o " +
				"where p.predajeId= :predajeId and o.odeljenjeId= :odeljenjeId  ";
		
		Query query = em.createQuery(sql);
        query.setParameter("id", predajeId);
        query.setParameter("id", odeljenjeId);
        
        List<DrziNastavuEntity> result = new ArrayList<DrziNastavuEntity>();
        result = query.getResultList();
        return result;
	}*/
}
