package com.app.server.service.organization.locationmanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.organization.locationmanagement.Language;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for Language Master table Entity", complexity = Complexity.LOW)
public abstract class LanguageService {

    abstract HttpEntity<ResponseBean> findAll() throws Exception;

    abstract HttpEntity<ResponseBean> findPageWiseData(Integer page, Integer start, Integer limit) throws Exception;

    abstract HttpEntity<ResponseBean> save(Language entity) throws Exception;

    abstract HttpEntity<ResponseBean> save(List<Language> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> delete(String id) throws Exception;

    abstract HttpEntity<ResponseBean> update(Language entity) throws Exception;

    abstract HttpEntity<ResponseBean> update(List<Language> entity, boolean isArray) throws Exception;

    abstract HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception;
}
