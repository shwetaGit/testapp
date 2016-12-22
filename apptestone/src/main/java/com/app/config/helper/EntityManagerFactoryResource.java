package com.app.config.helper;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;

@Component()
@Scope(value = "prototype")
public final class EntityManagerFactoryResource implements ResourceFactoryManagerHelper {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EntityManager getResource() {
		return entityManager;
	}

	@Override
	public EntityManager getResource(final HashMap<String, Object> resourceAttributes) {
		for (Entry<String, Object> resource : resourceAttributes.entrySet()) {
			entityManager.setProperty(resource.getKey(), resource.getValue());
		}
		return entityManager;
	}

	@Override
	public EntityManager setResourceAttributes(final HashMap<String, Object> resourceAttributes) {
		for (Entry<String, Object> resource : resourceAttributes.entrySet()) {
			entityManager.setProperty(resource.getKey(), resource.getValue());
		}
		return entityManager;
	}

	
	public void destroy() throws Exception {
		closeResource();
	}

	@Override
	public void closeResource() {
		try {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		} catch (Exception ignored) {
			ignored.printStackTrace();
		}
	}

	@Override
	public void beginTransaction() {
		getResource().getTransaction().begin();
	}

	@Override
	public void rollbackTransaction() {
		getResource().getTransaction().rollback();
	}

	@Override
	public void commitTransaction() {
		getResource().getTransaction().commit();
	}

}
