package com.app.server.repository.appinsight.alarms;
import com.app.shared.appinsight.alarms.ArtLogConfig;

import com.app.shared.appinsight.alarms.ArtLogSeverity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;

@Repository
@Transactional
public class ArtAlarmLoggerRepositoryImpl implements ArtAlarmLoggerRepository {

	@Autowired
	ResourceFactoryManagerHelper entity;

	@Override
	public void updateLoggerConfig(ArtLogConfig awsLogConfig) {
		try {
			EntityManager entityManager = entity.getResource();
			entityManager.merge(awsLogConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mergeSeverity(ArtLogSeverity awsLogSeverity) {
		try {
			EntityManager entityManager = entity.getResource();
			entityManager.merge(awsLogSeverity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArtLogSeverity getBySeverityId(int severityId) {
		try {
			EntityManager entityManager = entity.getResource();
			String JPQL = "SELECT severity FROM ArtLogSeverity severity WHERE severity.severityId =:severityid";
			Query query = entityManager.createQuery(JPQL);
			query.setParameter("severityid", severityId);
			@SuppressWarnings("unchecked")
			List<ArtLogSeverity> awsLogSeverity = query.getResultList();
			return awsLogSeverity.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void persistSeverity(ArtLogSeverity awsLogSeverity) {
		try {
			EntityManager entityManager = entity.getResource();
			entityManager.persist(entityManager.merge(awsLogSeverity));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveLoggerConfig(ArtLogConfig awsLogConfig) {
		try {
			EntityManager entityManager = entity.getResource();
			entityManager.persist(entityManager.merge(awsLogConfig));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ArtLogConfig> findAll() {
		try {
			EntityManager entityManager = entity.getResource();
			String JPQL = "SELECT awsLog FROM ArtLogConfig awsLog";
			Query query = entityManager.createQuery(JPQL);
			@SuppressWarnings("unchecked")
			List<ArtLogConfig> awsLoggerConfig = query.getResultList();
			return awsLoggerConfig;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ArtLogSeverity> getBySeverity(Integer severity) {
		try {
			EntityManager entityManager = entity.getResource();
			String JPQL = "SELECT severity FROM ArtLogSeverity severity WHERE severity.severity =:severity";
			Query query = entityManager.createQuery(JPQL);
			query.setParameter("severity", severity);
			@SuppressWarnings("unchecked")
			List<ArtLogSeverity> awsLogSeverity = query.getResultList();
			return awsLogSeverity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
