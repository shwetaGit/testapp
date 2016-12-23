package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;

import com.app.config.annotation.SourceCodeAuthorClass;

import com.app.server.repository.core.CommonUtilRepositoryImpl;

import com.app.shared.appbasicsetup.usermanagement.SMSNotification;

import org.springframework.stereotype.Repository;

import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;

import org.springframework.beans.factory.annotation.Autowired;

import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;

import java.lang.Override;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Repository
@SourceCodeAuthorClass(createdBy = "", updatedBy = "", versionNumber = "1", comments = "Repository for SMSNotification Master table Entity", complexity = Complexity.LOW)
public class SMSNotificationRepositoryImpl extends CommonUtilRepositoryImpl implements SMSNotificationRepository<SMSNotification> {

	@Autowired
	private ResourceFactoryManagerHelper emfResource;

	private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	/**
	 * Method for fetching list of entities
	 * 
	 * @throws java.lang.Exception
	 */
	@Override
	@Transactional
	public List<SMSNotification> findAll() throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		java.util.List<SMSNotification> query = emanager.createQuery("select u from SMSNotification u where u.activeStatus=1").getResultList();
		Log.out.println("ABSUM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SMSNotificationRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
		return query;
	}

	/**
	 * Saves the new <SMSNotification> object.
	 * 
	 * @return SMSNotification
	 * @Params Object of SMSNotification
	 * @throws java.lang.Exception
	 */
	@Override
	@Transactional
	public SMSNotification save(SMSNotification entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		emanager.persist(entity);
		Log.out.println("ABSUM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SMSNotificationRepositoryImpl", "save", entity);
		return entity;
	}

	/**
	 * Saves the list of new <SMSNotification> object.
	 * 
	 * @return java.util.List<SMSNotification>
	 * @Params list of SMSNotification
	 * @throws java.lang.Exception
	 */
	@Override
	@Transactional
	public List<SMSNotification> save(List<SMSNotification> entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		for (SMSNotification smsNotification : entity) {
			emanager.persist(smsNotification);
		}
		Log.out.println("ABSUM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SMSNotificationRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
		return entity;
	}

	/**
	 * Deletes the <SMSNotification> object.
	 * 
	 * @Params String id
	 * @throws java.lang.Exception
	 */
	@Transactional
	@Override
	public void delete(String id) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		SMSNotification smsNotification = emanager.find(SMSNotification.class, id);
		emanager.remove(smsNotification);
		Log.out.println("ABSUM328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SMSNotificationRepositoryImpl", "delete", "Record Deleted");
	}

	/**
	 * Updates the <SMSNotification> object.
	 * 
	 * @Params Object of SMSNotification
	 * @throws java.lang.Exception
	 */
	@Override
	@Transactional
	public void update(SMSNotification entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		emanager.merge(entity);
		Log.out.println("ABSUM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SMSNotificationRepositoryImpl", "update", entity);
	}

	/**
	 * Updates the list of <SMSNotification> object.
	 * 
	 * @Params list of SMSNotification
	 * @throws java.lang.Exception
	 */
	@Override
	@Transactional
	public void update(List<SMSNotification> entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		for (SMSNotification smsNotification : entity) {
			emanager.merge(smsNotification);
		}
		Log.out.println("ABSUM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SMSNotificationRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
	}

	/**
	 * Return SMSNotification object by filtering on refernce key
	 * <notificationId>
	 * 
	 * @return SMSNotification
	 * @Params notificationId of type String
	 * @throws java.lang.Exception
	 */
	@Transactional
	public SMSNotification findById(String notificationId) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		javax.persistence.Query query = emanager.createNamedQuery("SMSNotification.findById");
		query.setParameter("notificationId", notificationId);
		SMSNotification listOfSMSNotification = (SMSNotification) query.getSingleResult();
		Log.out.println("ABSUM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SMSNotificationRepositoryImpl", "findById", "Total Records Fetched = "
				+ listOfSMSNotification);
		return listOfSMSNotification;
	}
}
