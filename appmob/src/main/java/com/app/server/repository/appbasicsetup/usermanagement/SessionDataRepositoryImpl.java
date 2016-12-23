package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.appbasicsetup.usermanagement.SessionData;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.server.interfaces.SessionDataInterface;
import java.lang.Override;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for SessionData Transaction table", complexity = Complexity.MEDIUM)
public class SessionDataRepositoryImpl extends CommonUtilRepositoryImpl implements SessionDataRepository<SessionData> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    @Transactional
    @Override
    public SessionDataInterface saveSessionData(SessionDataInterface sessiondata) throws Exception {
        sessiondata.setVersionId(1);
        sessiondata.setSystemInformation(1);
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(sessiondata);
        return (SessionDataInterface) sessiondata;
    }

    @Transactional
    @Override
    public void update(SessionDataInterface entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.app.shared.appbasicsetup.usermanagement.SessionData s = emanager.find(com.app.shared.appbasicsetup.usermanagement.SessionData.class, id);
        emanager.remove(s);
    }

    @Transactional
    @Override
    public List<SessionDataInterface> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<SessionDataInterface> query = emanager.createQuery("select u from SessionData u where u.systemInfo.activeStatus=1").getResultList();
        return query;
    }

    @Transactional
    @Override
    public List<SessionDataInterface> findByAppSessionId(String appSessionId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("SessionData.FindByAppSessionId");
        query.setParameter("appSessionId", appSessionId);
        java.util.List<SessionDataInterface> listOfSessionData = query.getResultList();
        return listOfSessionData;
    }

    @Transactional
    @Override
    public SessionDataInterface findBySessionKey(String appSessionId, String sessionKey) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("SessionData.FindBySessionKey");
        query.setParameter("sessionKey", sessionKey);
        query.setParameter("appSessionId", appSessionId);
        java.util.List<SessionDataInterface> listOfSessionData = query.getResultList();
        if (listOfSessionData != null) {
            if (listOfSessionData.size() > 0) {
                return listOfSessionData.get(0);
            }
        }
        return null;
    }

    @Transactional
    @Override
    public void deleteAll(String appSessionId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("SessionData.DeleteAllSessionData");
        query.setParameter("appSessionId", appSessionId);
        query.executeUpdate();
    }
}
