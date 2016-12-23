package com.app.server.repository.appbasicsetup.userrolemanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
import java.util.Map;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for AppMenus Master table Entity", complexity = Complexity.LOW)
public class AppMenusRepositoryImpl extends CommonUtilRepositoryImpl implements AppMenusRepository<AppMenus> {

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
    public List<AppMenus> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<AppMenus> query = emanager.createNamedQuery("AppMenus.findAll").getResultList();
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppMenusRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <AppMenus> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query jpqlQuery = emanager.createNamedQuery("AppMenus.findAll");
        DatabaseQuery databaseQuery = ((EJBQueryImpl) jpqlQuery).getDatabaseQuery();
        String countquery = "SELECT COUNT(*)  FROM (" + databaseQuery.getSQLString() + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        query.setParameter(1, true);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppMenusRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <AppMenus>
     * @return List<AppMenus>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<AppMenus> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("AppMenus.findAll");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<AppMenus> listOfAppMenus = query.getResultList();
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppMenusRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfAppMenus.size());
        return listOfAppMenus;
    }

    /**
     * Saves the new  <AppMenus> object.
     * @return AppMenus
     * @Params Object of AppMenus
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public AppMenus save(AppMenus entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ABSRM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppMenusRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <AppMenus> object.
     * @return java.util.List<AppMenus>
     * @Params list of AppMenus
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<AppMenus> save(List<AppMenus> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (AppMenus appMenus : entity) {
            emanager.persist(appMenus);
        }
        Log.out.println("ABSRM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppMenusRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <AppMenus> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        AppMenus appMenus = emanager.find(com.app.shared.appbasicsetup.userrolemanagement.AppMenus.class, id);
        appMenus.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(appMenus);
        Log.out.println("ABSRM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppMenusRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <AppMenus> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        AppMenus appMenus = emanager.find(com.app.shared.appbasicsetup.userrolemanagement.AppMenus.class, id);
        emanager.remove(appMenus);
        Log.out.println("ABSRM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppMenusRepositoryImpl", "remove", "Record Deleted");
    }

    /**
     * Updates the <AppMenus> object.
     * @Params Object of AppMenus
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(AppMenus entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ABSRM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppMenusRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <AppMenus> object.
     * @Params list of AppMenus
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<AppMenus> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (AppMenus appMenus : entity) {
            emanager.merge(appMenus);
        }
        Log.out.println("ABSRM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppMenusRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Returns list of objects.
     * @Params findername,Map of fields,Map of fieldMetadata
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Object> search(String finderName, Map<String, Object> fields, Map<String, String> fieldMetaData) throws Exception {
        EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery(finderName);
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        Map<String, String> metaData = new java.util.HashMap<String, String>();
        metaData = fieldMetaData;
        String inputStr = "01-01-1850 00:00:00 UTC";
        java.util.Date date = setFormattedDate(inputStr);
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        for (Map.Entry<String, String> entry : metaData.entrySet()) {
            boolean matched = false;
            for (Map.Entry<String, Object> entry1 : fields.entrySet()) {
                if (entry.getKey() == entry1.getKey()) {
                    if (entry.getValue().equalsIgnoreCase("integer") || entry.getValue().equalsIgnoreCase("double") || entry.getValue().equalsIgnoreCase("float") || entry.getValue().equalsIgnoreCase("long")) {
                        map.put("min" + entry1.getKey(), entry1.getValue());
                        map.put("max" + entry1.getKey(), entry1.getValue());
                    } else if (entry.getValue().equalsIgnoreCase("String")) {
                        map.put(entry1.getKey(), "%" + entry1.getValue() + "%");
                    } else if (entry.getValue().equalsIgnoreCase("Date") || entry.getValue().equalsIgnoreCase("DateTime")) {
                        java.util.Date dateValue = setFormattedDate(entry1.getValue().toString());
                        map.put(entry1.getKey(), dateValue);
                    } else if (entry.getValue().equalsIgnoreCase("TimeStamp")) {
                        java.util.Date dateValue = setFormattedDate(entry1.getValue().toString());
                        map.put(entry1.getKey(), new java.sql.Timestamp(dateValue.getTime()));
                    } else {
                        map.put(entry1.getKey(), entry1.getValue());
                    }
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                if (entry.getValue().equalsIgnoreCase("String")) {
                    map.put(entry.getKey(), "%");
                } else if (entry.getValue().equalsIgnoreCase("integer")) {
                    map.put("min" + entry.getKey(), Integer.MIN_VALUE);
                    map.put("max" + entry.getKey(), Integer.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("double")) {
                    map.put("min" + entry.getKey(), java.lang.Double.MIN_VALUE);
                    map.put("max" + entry.getKey(), java.lang.Double.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("long")) {
                    map.put("min" + entry.getKey(), java.lang.Long.MIN_VALUE);
                    map.put("max" + entry.getKey(), java.lang.Long.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("float")) {
                    map.put("min" + entry.getKey(), java.lang.Float.MIN_VALUE);
                    map.put("max" + entry.getKey(), java.lang.Float.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("Date") || entry.getValue().equalsIgnoreCase("DATETIME")) {
                    map.put(entry.getKey(), date);
                } else if (entry.getValue().equalsIgnoreCase("TINYINT")) {
                    map.put(entry.getKey(), 1);
                } else if (entry.getValue().equalsIgnoreCase("timestamp")) {
                    map.put(entry.getKey(), timestamp);
                } else if (entry.getValue().equalsIgnoreCase("integer_userAccesCode")) {
                    map.put(entry.getKey(), runtimeLogInfoHelper.getUserAccessCode());
                }
            }
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List<Object> list = query.getResultList();
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppMenusRepositoryImpl", "search", "Total Records Fetched = " + list.size());
        return list;
    }

    /**
     * Return AppMenus object by filtering on refernce key <menuId>
     * @return AppMenus
     * @Params menuId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public AppMenus findById(String menuId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("AppMenus.findById");
        query.setParameter("menuId", menuId);
        AppMenus listOfAppMenus = (AppMenus) query.getSingleResult();
        Log.out.println("ABSRM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppMenusRepositoryImpl", "findById", "Total Records Fetched = " + listOfAppMenus);
        return listOfAppMenus;
    }
}
