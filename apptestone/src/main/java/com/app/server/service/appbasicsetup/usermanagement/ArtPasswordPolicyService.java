package com.app.server.service.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.ArtPasswordPolicy;

import java.util.List;

import org.springframework.http.HttpEntity;
import com.athena.server.pluggable.utils.bean.FindByBean;
import com.athena.server.pluggable.utils.bean.ResponseBean;

public abstract class ArtPasswordPolicyService {
	abstract HttpEntity<ResponseBean> findAll() throws Exception;

	abstract HttpEntity<ResponseBean> save(ArtPasswordPolicy entity) throws Exception;

	abstract HttpEntity<ResponseBean> save(List<ArtPasswordPolicy> entity, boolean isArray) throws Exception;

	abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

	abstract HttpEntity<ResponseBean> update(ArtPasswordPolicy entity) throws Exception;

	abstract HttpEntity<ResponseBean> update(List<ArtPasswordPolicy> entity, boolean isArray) throws Exception;

	abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;

}
