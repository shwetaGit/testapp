package com.app.server.repository.appbasicsetup.aaa;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.appbasicsetup.aaa.PublicApi;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for PublicApi Master table Entity", complexity = Complexity.LOW)
public class PublicApiRepositoryImpl extends CommonUtilRepositoryImpl implements PublicApiRepository<PublicApi> {

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
    public List<PublicApi> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<PublicApi> query = emanager.createNamedQuery("PublicApi.findAll").getResultList();
        Log.out.println("ABSAA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <PublicApi> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query jpqlQuery = emanager.createNamedQuery("PublicApi.findAll");
        DatabaseQuery databaseQuery = ((EJBQueryImpl) jpqlQuery).getDatabaseQuery();
        String countquery = "SELECT COUNT(*)  FROM (" + databaseQuery.getSQLString() + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        query.setParameter(1, true);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("ABSAA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <PublicApi>
     * @return List<PublicApi>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<PublicApi> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("PublicApi.findAll");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<PublicApi> listOfPublicApi = query.getResultList();
        Log.out.println("ABSAA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfPublicApi.size());
        return listOfPublicApi;
    }

    /**
     * Saves the new  <PublicApi> object.
     * @return PublicApi
     * @Params Object of PublicApi
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public PublicApi save(PublicApi entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ABSAA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <PublicApi> object.
     * @return java.util.List<PublicApi>
     * @Params list of PublicApi
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<PublicApi> save(List<PublicApi> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (PublicApi publicApi : entity) {
            emanager.persist(publicApi);
        }
        Log.out.println("ABSAA322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <PublicApi> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        PublicApi publicApi = emanager.find(com.app.shared.appbasicsetup.aaa.PublicApi.class, id);
        publicApi.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(publicApi);
        Log.out.println("ABSAA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <PublicApi> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        PublicApi publicApi = emanager.find(com.app.shared.appbasicsetup.aaa.PublicApi.class, id);
        emanager.remove(publicApi);
        Log.out.println("ABSAA328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "remove", "Record Deleted");
    }

    /**
     * Updates the <PublicApi> object.
     * @Params Object of PublicApi
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(PublicApi entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ABSAA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <PublicApi> object.
     * @Params list of PublicApi
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<PublicApi> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (PublicApi publicApi : entity) {
            emanager.merge(publicApi);
        }
        Log.out.println("ABSAA321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return PublicApi object by filtering on refernce key <apiId>
     * @return PublicApi
     * @Params apiId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public PublicApi findById(String apiId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("PublicApi.findById");
        query.setParameter("apiId", apiId);
        PublicApi listOfPublicApi = (PublicApi) query.getSingleResult();
        Log.out.println("ABSAA324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PublicApiRepositoryImpl", "findById", "Total Records Fetched = " + listOfPublicApi);
        return listOfPublicApi;
    }
}
