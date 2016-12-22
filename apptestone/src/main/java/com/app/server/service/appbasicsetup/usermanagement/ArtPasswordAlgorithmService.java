package com.app.server.service.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.ArtPasswordAlgorithm;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpEntity;
import com.athena.server.pluggable.utils.bean.FindByBean;
import com.athena.server.pluggable.utils.bean.ResponseBean;

public abstract class ArtPasswordAlgorithmService {

	public HttpEntity<ResponseBean> findAll() throws Exception {
		return null;
	}

	public HttpEntity<ResponseBean> save(ArtPasswordAlgorithm entity) throws Exception {
		return null;
	}

	public HttpEntity<ResponseBean> save(List<ArtPasswordAlgorithm> entity, boolean isArray) throws Exception {
		return null;
	}

	public HttpEntity<ResponseBean> delete(String id) throws Exception {
		return null;
	}

	public HttpEntity<ResponseBean> update(ArtPasswordAlgorithm entity) throws Exception {
		return null;
	}

	public HttpEntity<ResponseBean> update(List<ArtPasswordAlgorithm> entity, boolean isArray) throws Exception {
		return null;
	}

	public HttpEntity<ResponseBean> search(Map<String, Object> fieldData) throws Exception {
		return null;
	}

	public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
		return null;
	}
}
