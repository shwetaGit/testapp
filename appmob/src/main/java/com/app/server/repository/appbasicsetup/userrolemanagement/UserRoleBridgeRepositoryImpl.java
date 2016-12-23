package com.app.server.repository.appbasicsetup.userrolemanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for UserRoleBridge Transaction table", complexity = Complexity.MEDIUM)
public class UserRoleBridgeRepositoryImpl extends CommonUtilRepositoryImpl implements UserRoleBridgeRepository<UserRoleBridge> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    private final String PAGE_WISE_FIND_ALL = "SELECT userrolebridge.roleUserMapId as roleUserMapId,userrolebridge.roleId as roleId,roles.RoleName as rolesRoleName,userrolebridge.userId as userId,userdetail.authAlgo as userdetailAuthAlgo FROM UserRoleBridge userrolebridge LEFT JOIN Roles roles ON userrolebridge.roleId=roles.roleId LEFT JOIN UserDetail userdetail ON userrolebridge.userId=userdetail.userId WHERE userrolebridge.activeStatus=1";

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<UserRoleBridge> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<UserRoleBridge> query = emanager.createNamedQuery("UserRoleBridge.findAll").getResultList();
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserRoleBridgeRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <UserRoleBridge> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        String countquery = "SELECT COUNT(*)  FROM (" + PAGE_WISE_FIND_ALL + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserRoleBridgeRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <UserRoleBridge>
     * @return List<UserRoleBridge>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<UserRoleBridge> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNativeQuery(PAGE_WISE_FIND_ALL, "UserRoleBridgeResultSetMapping");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<UserRoleBridge> listOfUserRoleBridge = query.getResultList();
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserRoleBridgeRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfUserRoleBridge.size());
        return listOfUserRoleBridge;
    }

    /**
     * Saves the new  <UserRoleBridge> object.
     * @return UserRoleBridge
     * @Params Object of UserRoleBridge
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public UserRoleBridge save(UserRoleBridge entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ABSRM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserRoleBridgeRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <UserRoleBridge> object.
     * @return java.util.List<UserRoleBridge>
     * @Params list of UserRoleBridge
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<UserRoleBridge> save(List<UserRoleBridge> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (UserRoleBridge userRoleBridge : entity) {
            emanager.persist(userRoleBridge);
        }
        Log.out.println("ABSRM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserRoleBridgeRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <UserRoleBridge> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        UserRoleBridge userRoleBridge = emanager.find(com.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge.class, id);
        userRoleBridge.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(userRoleBridge);
        Log.out.println("ABSRM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserRoleBridgeRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <UserRoleBridge> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        UserRoleBridge userRoleBridge = emanager.find(com.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge.class, id);
        emanager.remove(userRoleBridge);
        Log.out.println("ABSRM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserRoleBridgeRepositoryImpl", "remove", "Record Deleted");
    }

    /**
     * Updates the <UserRoleBridge> object.
     * @Params Object of UserRoleBridge
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(UserRoleBridge entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ABSRM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserRoleBridgeRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <UserRoleBridge> object.
     * @Params list of UserRoleBridge
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<UserRoleBridge> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (UserRoleBridge userRoleBridge : entity) {
            emanager.merge(userRoleBridge);
        }
        Log.out.println("ABSRM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserRoleBridgeRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of UserRoleBridge objects by filtering on refernce key <roleId>
     * @return List<UserRoleBridge>
     * @Params roleId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<UserRoleBridge> findByRoleId(String roleId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("UserRoleBridge.findByRoleId");
        query.setParameter("roleId", roleId);
        java.util.List<com.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge> listOfUserRoleBridge = query.getResultList();
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserRoleBridgeRepositoryImpl", "findByRoleId", "Total Records Fetched = " + listOfUserRoleBridge.size());
        return listOfUserRoleBridge;
    }

    /**
     * Return list of UserRoleBridge objects by filtering on refernce key <userId>
     * @return List<UserRoleBridge>
     * @Params userId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<UserRoleBridge> findByUserId(String userId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("UserRoleBridge.findByUserId");
        query.setParameter("userId", userId);
        java.util.List<com.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge> listOfUserRoleBridge = query.getResultList();
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserRoleBridgeRepositoryImpl", "findByUserId", "Total Records Fetched = " + listOfUserRoleBridge.size());
        return listOfUserRoleBridge;
    }

    /**
     * Return UserRoleBridge object by filtering on refernce key <roleUserMapId>
     * @return UserRoleBridge
     * @Params roleUserMapId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public UserRoleBridge findById(String roleUserMapId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("UserRoleBridge.findById");
        query.setParameter("roleUserMapId", roleUserMapId);
        UserRoleBridge listOfUserRoleBridge = (UserRoleBridge) query.getSingleResult();
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "UserRoleBridgeRepositoryImpl", "findById", "Total Records Fetched = " + listOfUserRoleBridge);
        return listOfUserRoleBridge;
    }
}
