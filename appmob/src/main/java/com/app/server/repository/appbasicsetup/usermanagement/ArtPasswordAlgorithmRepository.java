package com.app.server.repository.appbasicsetup.usermanagement;
import java.util.List;

public interface ArtPasswordAlgorithmRepository<T> {

	List<T> findAll() throws Exception;

	T save(final T entity) throws Exception;

	List<T> save(final List<T> entity) throws Exception;

	void delete(final String id) throws Exception;

	void update(final T entity) throws Exception;

	void update(final List<T> entity) throws Exception;

	T findById(final String id) throws Exception;

}
