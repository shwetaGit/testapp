package com.app.server.repository.core;
import java.util.List;
import java.util.Map;

public interface CommonUtilRepository {

	List<Object> search(String repositoryName, Map<String, Object> fields, Map<String, String> fieldMetaData) throws Exception;

}
