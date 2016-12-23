package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.ArtPasswordPolicy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;

@Repository
public class ArtPasswordPolicyRepositoryImpl implements ArtPasswordPolicyRepository<ArtPasswordPolicy> {

	@Autowired
	private ResourceFactoryManagerHelper emfResource;

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	/**
	 * initialize the findAll with given configuration
	 * 
	 * @param :
	 * @returns ArtPasswordPolicy
	 * @throws : Exception
	 */
	@Override
	public List<ArtPasswordPolicy> findAll() throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		final java.util.List<ArtPasswordPolicy> query = emanager.createQuery("select u from ArtPasswordPolicy u").getResultList();
		return query;
	}

	/**
	 * initialize the save with given configuration
	 * 
	 * @param : entity
	 * @returns ArtPasswordPolicy
	 * @throws : Exception
	 */
	@Override
	public ArtPasswordPolicy save(final ArtPasswordPolicy entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		emanager.persist(entity);
		return entity;
	}

	/**
	 * initialize the save with given configuration
	 * 
	 * @param : entity
	 * @returns ArtPasswordPolicy list
	 * @throws : Exception
	 */
	@Override
	public List<ArtPasswordPolicy> save(final List<ArtPasswordPolicy> entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		for (ArtPasswordPolicy artPasswordPolicy : entity) {
			emanager.persist(artPasswordPolicy);
		}
		return entity;
	}

	/**
	 * initialize the delete with given configuration
	 * 
	 * @param : id
	 * @returns void
	 * @throws : Exception
	 */
	@Override
	public void delete(final String id) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		final ArtPasswordPolicy artPasswordPolicy = emanager.find(ArtPasswordPolicy.class, id);
		emanager.remove(artPasswordPolicy);
	}

	/**
	 * initialize the update with given configuration
	 * 
	 * @param : entity
	 * @returns void
	 * @throws : Exception
	 */
	@Override
	public void update(final ArtPasswordPolicy entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		emanager.merge(entity);
	}

	/**
	 * initialize the update with given configuration
	 * 
	 * @param : entity
	 * @returns void
	 * @throws : Exception
	 */
	@Override
	public void update(final List<ArtPasswordPolicy> entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		for (ArtPasswordPolicy artPasswordPolicy : entity) {
			emanager.merge(artPasswordPolicy);
		}
	}

	/**
	 * initialize the findById with given configuration
	 * 
	 * @param : id
	 * @returns ArtPasswordPolicy
	 * @throws : Exception
	 */
	@Override
	public ArtPasswordPolicy findById(final String id) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		final ArtPasswordPolicy artPasswordPolicy = emanager.find(ArtPasswordPolicy.class, id);
		return artPasswordPolicy;

	}

}
