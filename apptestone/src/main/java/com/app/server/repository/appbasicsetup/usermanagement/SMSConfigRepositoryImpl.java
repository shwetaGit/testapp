package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;

import com.app.config.annotation.SourceCodeAuthorClass;

import com.app.server.repository.core.CommonUtilRepositoryImpl;

import com.app.shared.appbasicsetup.usermanagement.SMSConfig;

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
@SourceCodeAuthorClass(createdBy = "", updatedBy = "", versionNumber = "1", comments = "Repository for SMSConfig Master table Entity", complexity = Complexity.LOW)
public class SMSConfigRepositoryImpl extends CommonUtilRepositoryImpl implements SMSConfigRepository<SMSConfig> {

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
	public List<SMSConfig> findAll() throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		java.util.List<SMSConfig> query = emanager.createQuery("select u from SMSConfig u where u.activeStatus=1").getResultList();
		Log.out.println("ABSUM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SMSConfigRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
		return query;
	}

	/**
	 * Saves the new <SMSConfig> object.
	 * 
	 * @return SMSConfig
	 * @Params Object of SMSConfig
	 * @throws java.lang.Exception
	 */
	@Override
	@Transactional
	public SMSConfig save(SMSConfig entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		emanager.persist(entity);
		Log.out.println("ABSUM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SMSConfigRepositoryImpl", "save", entity);
		return entity;
	}

	/**
	 * Saves the list of new <SMSConfig> object.
	 * 
	 * @return java.util.List<SMSConfig>
	 * @Params list of SMSConfig
	 * @throws java.lang.Exception
	 */
	@Override
	@Transactional
	public List<SMSConfig> save(List<SMSConfig> entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		for (SMSConfig smsConfig : entity) {
			emanager.persist(smsConfig);
		}
		Log.out.println("ABSUM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SMSConfigRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
		return entity;
	}

	/**
	 * Deletes the <SMSConfig> object.
	 * 
	 * @Params String id
	 * @throws java.lang.Exception
	 */
	@Transactional
	@Override
	public void delete(String id) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		SMSConfig smsConfig = emanager.find(SMSConfig.class, id);
		emanager.remove(smsConfig);
		Log.out.println("ABSUM328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SMSConfigRepositoryImpl", "delete", "Record Deleted");
	}

	/**
	 * Updates the <SMSConfig> object.
	 * 
	 * @Params Object of SMSConfig
	 * @throws java.lang.Exception
	 */
	@Override
	@Transactional
	public void update(SMSConfig entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		emanager.merge(entity);
		Log.out.println("ABSUM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SMSConfigRepositoryImpl", "update", entity);
	}

	/**
	 * Updates the list of <SMSConfig> object.
	 * 
	 * @Params list of SMSConfig
	 * @throws java.lang.Exception
	 */
	@Override
	@Transactional
	public void update(List<SMSConfig> entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		for (SMSConfig smsConfig : entity) {
			emanager.merge(smsConfig);
		}
		Log.out.println("ABSUM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SMSConfigRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
	}

	/**
	 * Return SMSConfig object by filtering on refernce key <configId>
	 * 
	 * @return SMSConfig
	 * @Params configId of type String
	 * @throws java.lang.Exception
	 */
	@Transactional
	public SMSConfig findById(String configId) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		javax.persistence.Query query = emanager.createNamedQuery("SMSConfig.findById");
		query.setParameter("configId", configId);
		SMSConfig listOfSMSConfig = (SMSConfig) query.getSingleResult();
		Log.out.println("ABSUM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SMSConfigRepositoryImpl", "findById", "Total Records Fetched = " + listOfSMSConfig);
		return listOfSMSConfig;
	}
}
