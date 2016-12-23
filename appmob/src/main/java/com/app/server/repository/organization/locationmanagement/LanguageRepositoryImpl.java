package com.app.server.repository.organization.locationmanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.organization.locationmanagement.Language;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for Language Master table Entity", complexity = Complexity.LOW)
public class LanguageRepositoryImpl extends CommonUtilRepositoryImpl implements LanguageRepository<Language> {

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
    public List<Language> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Language> query = emanager.createNamedQuery("Language.findAll").getResultList();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LanguageRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <Language> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query jpqlQuery = emanager.createNamedQuery("Language.findAll");
        DatabaseQuery databaseQuery = ((EJBQueryImpl) jpqlQuery).getDatabaseQuery();
        String countquery = "SELECT COUNT(*)  FROM (" + databaseQuery.getSQLString() + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        query.setParameter(1, true);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LanguageRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <Language>
     * @return List<Language>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Language> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Language.findAll");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Language> listOfLanguage = query.getResultList();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LanguageRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfLanguage.size());
        return listOfLanguage;
    }

    /**
     * Saves the new  <Language> object.
     * @return Language
     * @Params Object of Language
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Language save(Language entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ORGLM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LanguageRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Language> object.
     * @return java.util.List<Language>
     * @Params list of Language
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Language> save(List<Language> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Language language : entity) {
            emanager.persist(language);
        }
        Log.out.println("ORGLM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LanguageRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Language> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Language language = emanager.find(com.app.shared.organization.locationmanagement.Language.class, id);
        language.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(language);
        Log.out.println("ORGLM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LanguageRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <Language> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Language language = emanager.find(com.app.shared.organization.locationmanagement.Language.class, id);
        emanager.remove(language);
        Log.out.println("ORGLM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LanguageRepositoryImpl", "remove", "Record Deleted");
    }

    /**
     * Updates the <Language> object.
     * @Params Object of Language
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Language entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGLM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LanguageRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Language> object.
     * @Params list of Language
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Language> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Language language : entity) {
            emanager.merge(language);
        }
        Log.out.println("ORGLM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LanguageRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return Language object by filtering on refernce key <languageId>
     * @return Language
     * @Params languageId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Language findById(String languageId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Language.findById");
        query.setParameter("languageId", languageId);
        Language listOfLanguage = (Language) query.getSingleResult();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LanguageRepositoryImpl", "findById", "Total Records Fetched = " + listOfLanguage);
        return listOfLanguage;
    }
}
