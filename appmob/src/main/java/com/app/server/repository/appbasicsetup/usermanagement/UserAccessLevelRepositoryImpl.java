package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import javax.persistence.EntityManager;
import org.eclipse.persistence.internal.jpa.EJBQueryImpl;
import org.eclipse.persistence.queries.DatabaseQuery;
import java.lang.Override;
import java.util.List;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for UserAccessLevel Master table Entity", complexity = Complexity.LOW)
public class UserAccessLevelRepositoryImpl extends CommonUtilRepositoryImpl implements UserAccessLevelRepository<UserAccessLevel> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<UserAccessLevel> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<UserAccessLevel> query = emanager.createNamedQuery("UserAccessLevel.findAll").getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessLevelRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <UserAccessLevel> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query jpqlQuery = emanager.createNamedQuery("UserAccessLevel.findAll");
        DatabaseQuery databaseQuery = ((EJBQueryImpl) jpqlQuery).getDatabaseQuery();
        String countquery = "SELECT COUNT(*)  FROM (" + databaseQuery.getSQLString() + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        query.setParameter(1, true);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessLevelRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <UserAccessLevel>
     * @return List<UserAccessLevel>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<UserAccessLevel> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("UserAccessLevel.findAll");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<UserAccessLevel> listOfUserAccessLevel = query.getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessLevelRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfUserAccessLevel.size());
        return listOfUserAccessLevel;
    }

    /**
     * Saves the new  <UserAccessLevel> object.
     * @return UserAccessLevel
     * @Params Object of UserAccessLevel
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public UserAccessLevel save(UserAccessLevel entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ABSUM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessLevelRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <UserAccessLevel> object.
     * @return java.util.List<UserAccessLevel>
     * @Params list of UserAccessLevel
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<UserAccessLevel> save(List<UserAccessLevel> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (UserAccessLevel userAccessLevel : entity) {
            emanager.persist(userAccessLevel);
        }
        Log.out.println("ABSUM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessLevelRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <UserAccessLevel> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        UserAccessLevel userAccessLevel = emanager.find(com.app.shared.appbasicsetup.usermanagement.UserAccessLevel.class, id);
        userAccessLevel.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(userAccessLevel);
        Log.out.println("ABSUM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessLevelRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <UserAccessLevel> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        UserAccessLevel userAccessLevel = emanager.find(com.app.shared.appbasicsetup.usermanagement.UserAccessLevel.class, id);
        emanager.remove(userAccessLevel);
        Log.out.println("ABSUM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessLevelRepositoryImpl", "remove", "Record Deleted");
    }

    /**
     * Updates the <UserAccessLevel> object.
     * @Params Object of UserAccessLevel
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(UserAccessLevel entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ABSUM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessLevelRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <UserAccessLevel> object.
     * @Params list of UserAccessLevel
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<UserAccessLevel> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (UserAccessLevel userAccessLevel : entity) {
            emanager.merge(userAccessLevel);
        }
        Log.out.println("ABSUM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessLevelRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return UserAccessLevel object by filtering on refernce key <userAccessLevelId>
     * @return UserAccessLevel
     * @Params userAccessLevelId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public UserAccessLevel findById(String userAccessLevelId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("UserAccessLevel.findById");
        query.setParameter("userAccessLevelId", userAccessLevelId);
        UserAccessLevel listOfUserAccessLevel = (UserAccessLevel) query.getSingleResult();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserAccessLevelRepositoryImpl", "findById", "Total Records Fetched = " + listOfUserAccessLevel);
        return listOfUserAccessLevel;
    }
}
