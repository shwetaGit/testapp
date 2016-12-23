package com.app.server.repository.appbasicsetup.aaa;
import com.app.shared.appbasicsetup.aaa.ArtUserLastStatus;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;

@Repository
@Transactional
public class ArtUserStatusRepositoryImpl implements ArtUserStatusRepository {

	@Autowired
	private ResourceFactoryManagerHelper resourceFactoryManager;

	/**
	 * save function accept ArtUserLastStatus object and save initialize the
	 * save with given configuration
	 * 
	 * @param : userLastStatus
	 * @returns :
	 * @throws : Exception
	 */
	@Override
	public void save(final ArtUserLastStatus userLastStatus) throws Exception {
		if (userLastStatus == null) {
			throw new com.spartan.pluggable.exception.security.InvalidDataException();
		}

		try {
			final EntityManager entityManager = resourceFactoryManager.getResource();
			entityManager.persist(userLastStatus);
		} catch (Exception e) {
			throw new Exception("Database Query Failed in " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}

	/**
	 * update function accept ArtUserLastStatus object and update initialize the
	 * update with given configuration
	 * 
	 * @param : userLastStatus
	 * @returns:
	 * @throws : Exception
	 */
	@Override
	public void update(final ArtUserLastStatus userLastStatus) throws Exception {

		if (userLastStatus == null) {
			throw new com.spartan.pluggable.exception.security.InvalidDataException();
		}

		try {
			final EntityManager entityManager = resourceFactoryManager.getResource();
			entityManager.merge(userLastStatus);
		} catch (Exception e) {
			throw new Exception("Database Query Failed in " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}

	}

	/**
	 * delete function accept id and delete initialize the delete with given
	 * configuration
	 * 
	 * @param : id
	 * @returns :
	 * @throws : Exception
	 */
	@Override
	public void delete(final String id) throws Exception {
		if (id == null) {
			throw new com.spartan.pluggable.exception.security.InvalidDataException();
		}

		try {
			final EntityManager entityManager = resourceFactoryManager.getResource();
			final ArtUserLastStatus userLastStatus = entityManager.find(ArtUserLastStatus.class, id);
			if (userLastStatus != null) {
				entityManager.remove(userLastStatus);
			}
		} catch (Exception e) {
			throw new Exception("Database Query Failed in " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}

	/**
	 * findById function accept id and find user last status and return
	 * ArtUserLastStatus initialize the findById with given configuration
	 * 
	 * @param : id
	 * @returns : ArtUserLastStatus
	 * @throws : Exception
	 */
	@Override
	public ArtUserLastStatus findById(final String id) throws Exception {

		if (id == null) {
			throw new com.spartan.pluggable.exception.security.InvalidDataException();
		}

		try {
			final EntityManager entityManager = resourceFactoryManager.getResource();
			final ArtUserLastStatus userLastStatus = entityManager.find(ArtUserLastStatus.class, id);
			return userLastStatus;
		} catch (Exception e) {
			throw new Exception("Database Query Failed in " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}

	/**
	 * findByUserId function accept userId return list of ArtUserLastStatus
	 * object initialize the findByUserId with given configuration
	 * 
	 * @param : userId
	 * @returns ArtUserLastStatus list
	 * @throws : Exception
	 */
	@Override
	public List<ArtUserLastStatus> findByUserId(final String userId) throws Exception {

		if (userId == null) {
			throw new com.spartan.pluggable.exception.security.InvalidDataException();
		}

		try {
			final EntityManager entityManager = resourceFactoryManager.getResource();
			final String JPQL = "SELECT userLastStatus FROM ArtUserLastStatus userLastStatus where userLastStatus.userId = :userId ORDER BY userLastStatus.createdDate";
			Query query = entityManager.createQuery(JPQL);
			query.setParameter("userId", userId);
			final List<ArtUserLastStatus> result = query.getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Database Query Failed in " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}

	/**
	 * findByUserMenuId function accept userId and menuId and return the list of
	 * ArtUserLastStatus object list initialize the findByUserMenuId with given
	 * configuration
	 * 
	 * @param : userId, menuId
	 * @returns : ArtUserLastStatus list
	 * @throws : Exception
	 */
	@Override
	public List<ArtUserLastStatus> findByUserMenuId(final String userId, final String menuId) throws Exception {

		if (userId == null) {
			throw new com.spartan.pluggable.exception.security.InvalidDataException();
		}

		try {
			final EntityManager entityManager = resourceFactoryManager.getResource();
			final String jpql = "SELECT userLastStatus FROM ArtUserLastStatus userLastStatus where userLastStatus.userId = :userId and userLastStatus.menuId = :menuId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			query.setParameter("menuId", menuId);
			final List<ArtUserLastStatus> result = query.getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Database Query Failed in " + Thread.currentThread().getStackTrace()[1].getMethodName());
		}
	}
}
