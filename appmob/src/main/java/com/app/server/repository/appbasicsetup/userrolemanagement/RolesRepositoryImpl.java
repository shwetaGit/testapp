package com.app.server.repository.appbasicsetup.userrolemanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for Roles Transaction table", complexity = Complexity.MEDIUM)
public class RolesRepositoryImpl extends CommonUtilRepositoryImpl implements RolesRepository<Roles> {

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
    public List<Roles> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Roles> query = emanager.createNamedQuery("Roles.findAll").getResultList();
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "RolesRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <Roles> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query jpqlQuery = emanager.createNamedQuery("Roles.findAll");
        DatabaseQuery databaseQuery = ((EJBQueryImpl) jpqlQuery).getDatabaseQuery();
        String countquery = "SELECT COUNT(*)  FROM (" + databaseQuery.getSQLString() + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        query.setParameter(1, true);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "RolesRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <Roles>
     * @return List<Roles>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Roles> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Roles.findAll");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Roles> listOfRoles = query.getResultList();
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "RolesRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfRoles.size());
        return listOfRoles;
    }

    /**
     * Saves the new  <Roles> object.
     * @return Roles
     * @Params Object of Roles
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Roles save(Roles entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ABSRM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "RolesRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Roles> object.
     * @return java.util.List<Roles>
     * @Params list of Roles
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Roles> save(List<Roles> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Roles roles : entity) {
            emanager.persist(roles);
        }
        Log.out.println("ABSRM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "RolesRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Roles> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Roles roles = emanager.find(com.app.shared.appbasicsetup.userrolemanagement.Roles.class, id);
        roles.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(roles);
        Log.out.println("ABSRM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "RolesRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <Roles> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Roles roles = emanager.find(com.app.shared.appbasicsetup.userrolemanagement.Roles.class, id);
        emanager.remove(roles);
        Log.out.println("ABSRM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "RolesRepositoryImpl", "remove", "Record Deleted");
    }

    @Override
    @Transactional
    public void deleteRoleMenuBridge(List<RoleMenuBridge> rolemenubridge) {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge _rolemenubridge : rolemenubridge) {
            com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge s = emanager.find(com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge.class, _rolemenubridge.getRoleMenuMapId());
            emanager.remove(s);
        }
    }

    /**
     * Updates the <Roles> object.
     * @Params Object of Roles
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Roles entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ABSRM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "RolesRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Roles> object.
     * @Params list of Roles
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Roles> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Roles roles : entity) {
            emanager.merge(roles);
        }
        Log.out.println("ABSRM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "RolesRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return Roles object by filtering on refernce key <roleId>
     * @return Roles
     * @Params roleId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Roles findById(String roleId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Roles.findById");
        query.setParameter("roleId", roleId);
        Roles listOfRoles = (Roles) query.getSingleResult();
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "RolesRepositoryImpl", "findById", "Total Records Fetched = " + listOfRoles);
        return listOfRoles;
    }
}
