package com.app.server.repository.appinsight.alarms;
import com.app.shared.appinsight.alarms.ArtBoundedContext;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;

@Repository
@Transactional
@Scope(value = "prototype")
public class ArtBoundedContextRepositoryImpl implements ArtBoundedContextRepository {

	@Autowired
	ResourceFactoryManagerHelper entity;

	@Override
	@Transactional
	public void save(final ArtBoundedContext artBoundedContext) {
		try {
			final EntityManager entityManager = entity.getResource();
			entityManager.merge(artBoundedContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void update(final ArtBoundedContext artBoundedContext) {
		try {
			final EntityManager entityManager = entity.getResource();
			entityManager.merge(artBoundedContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public ArtBoundedContext findByBoundedContextPrefix(final String bcPrefix) {
		try {
			final EntityManager entityManager = entity.getResource();
			final String JPQL = "SELECT artBoundedContext FROM ArtBoundedContext artBoundedContext WHERE ArtBoundedContext.boundedContextPrefix =:boundedContextPrefix";
			Query query = entityManager.createQuery(JPQL);
			query.setParameter("boundedContextPrefix", bcPrefix);
			return (ArtBoundedContext) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArtBoundedContext> findAll() {
		try {
			final EntityManager entityManager = entity.getResource();
			return entityManager.createQuery("select u from ArtBoundedContext u").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArtBoundedContext findById(String boundedContextId) {
		try {
			final EntityManager entityManager = entity.getResource();
			final String JPQL = "SELECT artBoundedContext FROM ArtBoundedContext artBoundedContext WHERE ArtBoundedContext.boundedContextId =:boundedContextId";
			Query query = entityManager.createQuery(JPQL);
			query.setParameter("boundedContextId", boundedContextId);
			return (ArtBoundedContext) query.getSingleResult();
		} catch (NoResultException nr) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
