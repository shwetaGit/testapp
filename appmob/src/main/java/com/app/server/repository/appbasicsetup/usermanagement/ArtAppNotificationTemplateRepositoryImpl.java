package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.ArtAppNotificationTemplate;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;

@Repository
public class ArtAppNotificationTemplateRepositoryImpl implements ArtAppNotificationTemplateRepository {

	@Autowired
	private ResourceFactoryManagerHelper emfResource;

	@Override
	public ArtAppNotificationTemplate findById(String templateId) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		Query query = emanager.createQuery("SELECT e FROM ArtAppNotificationTemplate e WHERE e.templateId=:templateId AND e.activeStatus=:activeStatus");
		query.setParameter("templateId", templateId);
		query.setParameter("activeStatus", true);
		ArtAppNotificationTemplate entity = (ArtAppNotificationTemplate) query.getSingleResult();
		return entity;
	}
}
