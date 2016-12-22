package com.app.server.repository.appinsight.alarms;
import com.app.shared.appinsight.alarms.ArtDomain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;

@Repository
@Transactional
public class ArtDomainRepositoryImpl implements ArtDomainRepository {

	@Autowired
	ResourceFactoryManagerHelper entity;

	@Override
	public List<ArtDomain> findByArtBoundedContext(final String boundedContextId) {

		try {
			final EntityManager entityManager = entity.getResource();
			final String JPQL = "SELECT artdomain FROM ArtDomain artdomain WHERE artdomain.artBoundedContext.boundedContextId =:boundedContextId";
			Query query = entityManager.createQuery(JPQL);
			query.setParameter("boundedContextId", boundedContextId);
			return query.getResultList();
		} catch (NoResultException nr) {
			nr.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArtDomain findById(final String id) {

		try {
			final EntityManager entityManager = entity.getResource();
			final String JPQL = "SELECT artDomain FROM ArtDomain artDomain WHERE artDomain.domainId =:domainId";
			Query query = entityManager.createQuery(JPQL);
			query.setParameter("domainId", id);
			return (ArtDomain) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(final ArtDomain artDomain) {
		try {
			final EntityManager entityManager = entity.getResource();
			entityManager.merge(artDomain);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(final ArtDomain artDomain) {
		try {
			final EntityManager entityManager = entity.getResource();
			entityManager.persist(entityManager.merge(artDomain));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ArtDomain> findAll() {
		try {
			final EntityManager entityManager = entity.getResource();
			final String JPQL = "SELECT artDomain FROM ArtDomain artDomain";
			Query query = entityManager.createQuery(JPQL);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArtDomain findByPrefix(String prefix) {
		try {
			final EntityManager entityManager = entity.getResource();
			final String JPQL = "SELECT artDomain FROM ArtDomain artDomain WHERE artDomain.alarmPrefix =:alarmPrefix";
			Query query = entityManager.createQuery(JPQL);
			query.setParameter("alarmPrefix", prefix);
			return (ArtDomain) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
