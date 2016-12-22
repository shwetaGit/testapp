package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.ArtPasswordAlgorithm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;

@Repository
public class ArtPasswordAlgorithmRepositoryImpl implements ArtPasswordAlgorithmRepository<ArtPasswordAlgorithm> {

	@Autowired
	private ResourceFactoryManagerHelper emfResource;

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	/**
	 * initialize the findAll with given configuration
	 * 
	 * @param :
	 * @returns ArtPasswordAlgorithm list
	 * @throws : Exception
	 */
	@Override
	@Transactional
	public List<ArtPasswordAlgorithm> findAll() throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		final java.util.List<ArtPasswordAlgorithm> query = emanager.createQuery("select u from ArtPasswordAlgorithm u").getResultList();
		return query;
	}

	/**
	 * initialize the save with given configuration
	 * 
	 * @param : entity
	 * @returns : ArtPasswordAlgorithm
	 * @throws : Exception
	 */
	@Override
	@Transactional
	public ArtPasswordAlgorithm save(final ArtPasswordAlgorithm entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		emanager.persist(entity);
		return entity;
	}

	/**
	 * initialize the save with given configuration
	 * 
	 * @param : entity
	 * @returns ArtPasswordAlgorithm
	 * @throws : Exception
	 */
	@Override
	@Transactional
	public List<ArtPasswordAlgorithm> save(final List<ArtPasswordAlgorithm> entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		for (ArtPasswordAlgorithm artPasswordAlgorithm :entity) {
			emanager.persist(artPasswordAlgorithm);
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
	@Transactional
	@Override
	public void delete(final String id) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		final ArtPasswordAlgorithm s = emanager.find(ArtPasswordAlgorithm.class, id);
		emanager.remove(s);
	}

	/**
	 * initialize the update with given configuration
	 * 
	 * @param : entity
	 * @returns void
	 * @throws : Exception
	 */
	@Override
	@Transactional
	public void update(final ArtPasswordAlgorithm entity) throws Exception {
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
	@Transactional
	public void update(final List<ArtPasswordAlgorithm> entity) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		for (ArtPasswordAlgorithm artPasswordAlgorithm : entity) {
			emanager.merge(artPasswordAlgorithm);
		}
	}

	/**
	 * initialize the findById with given configuration
	 * 
	 * @param : entity
	 * @returns void
	 * @throws : Exception
	 */
	@Override
	public ArtPasswordAlgorithm findById(final String id) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		final ArtPasswordAlgorithm artPasswordAlgorithm = emanager.find(ArtPasswordAlgorithm.class, id);
		return artPasswordAlgorithm;
	}

}
