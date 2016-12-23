package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.ArtDocumentTemplate;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;

@Repository
public class ArtDocumentTemplateRepositoryImpl implements ArtDocumentTemplateRepository {

	@Autowired
	private ResourceFactoryManagerHelper emfResource;

	/**
	 * initialize the findById with given configuration
	 * @param : docTempId
	 * @returns : ArtDocumentTemplate
	 * @throws : Exception
	 */
	@Override
	public ArtDocumentTemplate findById(final String docTempId) throws Exception {
		final javax.persistence.EntityManager emanager = emfResource.getResource();
		final Query query = emanager.createQuery("SELECT e FROM ArtDocumentTemplate e WHERE e.docTempId=:docTempId AND e.activeStatus=:activeStatus");
		query.setParameter("docTempId", docTempId);
		query.setParameter("activeStatus", true);
		final ArtDocumentTemplate entity = (ArtDocumentTemplate) query.getSingleResult();
		return entity;
	}
}
