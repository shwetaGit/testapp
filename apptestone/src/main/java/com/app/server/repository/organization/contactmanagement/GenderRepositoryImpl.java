package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.organization.contactmanagement.Gender;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for Gender Master table Entity", complexity = Complexity.LOW)
public class GenderRepositoryImpl extends CommonUtilRepositoryImpl implements GenderRepository<Gender> {

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
    public List<Gender> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Gender> query = emanager.createNamedQuery("Gender.findAll").getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <Gender> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query jpqlQuery = emanager.createNamedQuery("Gender.findAll");
        DatabaseQuery databaseQuery = ((EJBQueryImpl) jpqlQuery).getDatabaseQuery();
        String countquery = "SELECT COUNT(*)  FROM (" + databaseQuery.getSQLString() + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        query.setParameter(1, true);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <Gender>
     * @return List<Gender>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Gender> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Gender.findAll");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Gender> listOfGender = query.getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfGender.size());
        return listOfGender;
    }

    /**
     * Saves the new  <Gender> object.
     * @return Gender
     * @Params Object of Gender
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Gender save(Gender entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ORGCM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Gender> object.
     * @return java.util.List<Gender>
     * @Params list of Gender
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Gender> save(List<Gender> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Gender gender : entity) {
            emanager.persist(gender);
        }
        Log.out.println("ORGCM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Gender> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Gender gender = emanager.find(com.app.shared.organization.contactmanagement.Gender.class, id);
        gender.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(gender);
        Log.out.println("ORGCM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <Gender> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Gender gender = emanager.find(com.app.shared.organization.contactmanagement.Gender.class, id);
        emanager.remove(gender);
        Log.out.println("ORGCM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "remove", "Record Deleted");
    }

    /**
     * Updates the <Gender> object.
     * @Params Object of Gender
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Gender entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGCM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Gender> object.
     * @Params list of Gender
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Gender> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Gender gender : entity) {
            emanager.merge(gender);
        }
        Log.out.println("ORGCM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return Gender object by filtering on refernce key <genderId>
     * @return Gender
     * @Params genderId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Gender findById(String genderId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Gender.findById");
        query.setParameter("genderId", genderId);
        Gender listOfGender = (Gender) query.getSingleResult();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "GenderRepositoryImpl", "findById", "Total Records Fetched = " + listOfGender);
        return listOfGender;
    }
}
