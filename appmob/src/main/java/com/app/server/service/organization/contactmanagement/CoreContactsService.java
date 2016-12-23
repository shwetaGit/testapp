package com.app.server.service.organization.contactmanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.organization.contactmanagement.CoreContacts;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for CoreContacts Transaction table", complexity = Complexity.MEDIUM)
public abstract class CoreContactsService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> findPageWiseData(Integer page, Integer start, Integer limit) throws Exception;

    abstract HttpEntity<ResponseBean> save(CoreContacts entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<CoreContacts> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(CoreContacts entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<CoreContacts> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findByTitleId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByNativeLanguageCode(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByGenderId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findByTimeZoneId(FindByBean findByBean) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
