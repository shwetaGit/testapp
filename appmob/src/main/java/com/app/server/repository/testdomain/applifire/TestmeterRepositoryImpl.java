package com.app.server.repository.testdomain.applifire;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.testdomain.applifire.Testmeter;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "Repository for Testmeter Master table Entity", complexity = Complexity.LOW)
public class TestmeterRepositoryImpl extends CommonUtilRepositoryImpl implements TestmeterRepository<Testmeter> {

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
    public List<Testmeter> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Testmeter> query = emanager.createNamedQuery("Testmeter.findAll").getResultList();
        Log.out.println("TDNTA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestmeterRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <Testmeter> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query jpqlQuery = emanager.createNamedQuery("Testmeter.findAll");
        DatabaseQuery databaseQuery = ((EJBQueryImpl) jpqlQuery).getDatabaseQuery();
        String countquery = "SELECT COUNT(*)  FROM (" + databaseQuery.getSQLString() + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        query.setParameter(1, true);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("TDNTA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestmeterRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <Testmeter>
     * @return List<Testmeter>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Testmeter> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Testmeter.findAll");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Testmeter> listOfTestmeter = query.getResultList();
        Log.out.println("TDNTA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestmeterRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfTestmeter.size());
        return listOfTestmeter;
    }

    /**
     * Saves the new  <Testmeter> object.
     * @return Testmeter
     * @Params Object of Testmeter
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Testmeter save(Testmeter entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("TDNTA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestmeterRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Testmeter> object.
     * @return java.util.List<Testmeter>
     * @Params list of Testmeter
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Testmeter> save(List<Testmeter> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Testmeter testmeter : entity) {
            emanager.persist(testmeter);
        }
        Log.out.println("TDNTA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestmeterRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Testmeter> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Testmeter testmeter = emanager.find(com.app.shared.testdomain.applifire.Testmeter.class, id);
        testmeter.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(testmeter);
        Log.out.println("TDNTA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestmeterRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <Testmeter> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Testmeter testmeter = emanager.find(com.app.shared.testdomain.applifire.Testmeter.class, id);
        emanager.remove(testmeter);
        Log.out.println("TDNTA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestmeterRepositoryImpl", "remove", "Record Deleted");
    }

    /**
     * Updates the <Testmeter> object.
     * @Params Object of Testmeter
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Testmeter entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("TDNTA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestmeterRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Testmeter> object.
     * @Params list of Testmeter
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Testmeter> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Testmeter testmeter : entity) {
            emanager.merge(testmeter);
        }
        Log.out.println("TDNTA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestmeterRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return Testmeter object by filtering on refernce key <metercode>
     * @return Testmeter
     * @Params metercode of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Testmeter findById(String metercode) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Testmeter.findById");
        query.setParameter("metercode", metercode);
        Testmeter listOfTestmeter = (Testmeter) query.getSingleResult();
        Log.out.println("TDNTA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestmeterRepositoryImpl", "findById", "Total Records Fetched = " + listOfTestmeter);
        return listOfTestmeter;
    }
}
