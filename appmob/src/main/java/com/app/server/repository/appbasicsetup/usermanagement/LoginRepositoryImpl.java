package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for Login Transaction table", complexity = Complexity.MEDIUM)
public class LoginRepositoryImpl extends CommonUtilRepositoryImpl implements LoginRepository<Login> {

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
    public List<Login> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Login> query = emanager.createNamedQuery("Login.findAll").getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <Login> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query jpqlQuery = emanager.createNamedQuery("Login.findAll");
        DatabaseQuery databaseQuery = ((EJBQueryImpl) jpqlQuery).getDatabaseQuery();
        String countquery = "SELECT COUNT(*)  FROM (" + databaseQuery.getSQLString() + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        query.setParameter(1, true);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <Login>
     * @return List<Login>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Login> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Login.findAll");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Login> listOfLogin = query.getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfLogin.size());
        return listOfLogin;
    }

    /**
     * Saves the new  <Login> object.
     * @return Login
     * @Params Object of Login
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Login save(Login entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ABSUM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Login> object.
     * @return java.util.List<Login>
     * @Params list of Login
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Login> save(List<Login> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Login login : entity) {
            emanager.persist(login);
        }
        Log.out.println("ABSUM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Login> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Login login = emanager.find(com.app.shared.appbasicsetup.usermanagement.Login.class, id);
        login.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(login);
        Log.out.println("ABSUM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <Login> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Login login = emanager.find(com.app.shared.appbasicsetup.usermanagement.Login.class, id);
        emanager.remove(login);
        Log.out.println("ABSUM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "remove", "Record Deleted");
    }

    /**
     * Updates the <Login> object.
     * @Params Object of Login
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Login entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ABSUM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Login> object.
     * @Params list of Login
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Login> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Login login : entity) {
            emanager.merge(login);
        }
        Log.out.println("ABSUM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
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
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "search", "Total Records Fetched = " + list.size());
        return list;
    }

    /**
     * Return list of Login objects by filtering on refernce key <contactId>
     * @return List<Login>
     * @Params contactId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Login> findByContactId(String contactId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Login.findByContactId");
        query.setParameter("contactId", contactId);
        java.util.List<com.app.shared.appbasicsetup.usermanagement.Login> listOfLogin = query.getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "findByContactId", "Total Records Fetched = " + listOfLogin.size());
        return listOfLogin;
    }

    /**
     * Return list of Login objects by filtering on refernce key <userId>
     * @return List<Login>
     * @Params userId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Login> findByUserId(String userId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Login.findByUserId");
        query.setParameter("userId", userId);
        java.util.List<com.app.shared.appbasicsetup.usermanagement.Login> listOfLogin = query.getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "findByUserId", "Total Records Fetched = " + listOfLogin.size());
        return listOfLogin;
    }

    /**
     * Return Login object by filtering on refernce key <loginPk>
     * @return Login
     * @Params loginPk of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Login findById(String loginPk) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Login.findById");
        query.setParameter("loginPk", loginPk);
        Login listOfLogin = (Login) query.getSingleResult();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "findById", "Total Records Fetched = " + listOfLogin);
        return listOfLogin;
    }

    /**
     * Return list of Login object by filtering on refernce key <null>
     * @return List<Login>
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Login> FindUnMappedUser() throws Exception, Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Login.FindUnMappedUser");
        List<Login> listOfLogin = query.getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "FindUnMappedUser", "Total Records Fetched = " + listOfLogin.size());
        return listOfLogin;
    }

    /**
     * Return list of Login object by filtering on refernce key <null>
     * @return List<Login>
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Login> FindMappedUser() throws Exception, Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Login.FindMappedUser");
        List<Login> listOfLogin = query.getResultList();
        Log.out.println("ABSUM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "LoginRepositoryImpl", "FindMappedUser", "Total Records Fetched = " + listOfLogin.size());
        return listOfLogin;
    }
}
