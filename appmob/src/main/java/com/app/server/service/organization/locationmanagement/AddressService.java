package com.app.server.service.organization.locationmanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.organization.locationmanagement.Address;
import java.util.List;
import java.util.Map;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for Address Transaction table", complexity = Complexity.MEDIUM)
public abstract class AddressService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> findPageWiseData(Integer page, Integer start, Integer limit) throws Exception;

    abstract HttpEntity<ResponseBean> save(Address entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<Address> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(Address entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<Address> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> search(Map<String, Object> fieldData) throws Exception;

    abstract HttpEntity<ResponseBean> findByAddressTypeId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByCountryId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByStateId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByCityId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
