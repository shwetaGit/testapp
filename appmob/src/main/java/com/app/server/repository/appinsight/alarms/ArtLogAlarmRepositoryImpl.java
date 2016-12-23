package com.app.server.repository.appinsight.alarms;
import com.app.shared.appinsight.alarms.ArtLogAlarm;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;

@Repository
@Transactional
public class ArtLogAlarmRepositoryImpl implements ArtLogAlarmRepository {

	@Autowired
	ResourceFactoryManagerHelper entity;

	@Override
	@Transactional
	public void save(ArtLogAlarm artLogAlarm) {
		try {
			EntityManager entityManager = entity.getResource();
			entityManager.persist(artLogAlarm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArtLogAlarm findByAlarmType(int alarmType) {
		try {
			EntityManager entityManager = entity.getResource();
			String JPQL = "SELECT artLogAlarm FROM ArtLogAlarm artLogAlarm WHERE artLogAlarm.alarmType =:alarmType";
			Query query = entityManager.createQuery(JPQL);
			query.setParameter("alarmType", alarmType);
			ArtLogAlarm artLogAlarm = (ArtLogAlarm) query.getSingleResult();
			return artLogAlarm;
		} catch (NoResultException nr) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	@Override
	public void update(ArtLogAlarm artLogAlarm) {
		try {
			EntityManager entityManager = entity.getResource();
			entityManager.merge(artLogAlarm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getAlarmDataByType(int alarmType) {
		try {
			ArtLogAlarm artLogAlarm = findByAlarmType(alarmType);
			if (artLogAlarm != null)
				return artLogAlarm.getAlarmData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getVersionNumber() {
		ArtLogAlarm artLogAlarm = findByAlarmType(2);
		if (artLogAlarm != null) {
			return artLogAlarm.getAlarmVersion();
		}
		return 0;
	}

}
