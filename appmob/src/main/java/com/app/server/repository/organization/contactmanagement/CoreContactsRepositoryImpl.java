package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
import com.app.shared.organization.locationmanagement.Address;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for CoreContacts Transaction table", complexity = Complexity.MEDIUM)
public class CoreContactsRepositoryImpl extends CommonUtilRepositoryImpl implements CoreContactsRepository<CoreContacts> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    private final String PAGE_WISE_FIND_ALL = "SELECT corecontacts.contactId as contactId,corecontacts.titleId as titleId,title.titles as titleTitles,corecontacts.firstName as firstName,corecontacts.middleName as middleName,corecontacts.lastName as lastName,corecontacts.nativeLanguageCode as nativeLanguageCode,language.language as languageLanguage,corecontacts.nativeTitle as nativeTitle,corecontacts.nativeFirstName as nativeFirstName,corecontacts.nativeMiddleName as nativeMiddleName,corecontacts.nativeLastName as nativeLastName,corecontacts.genderId as genderId,gender.gender as genderGender,corecontacts.dateofbirth as dateofbirth,corecontacts.age as age,corecontacts.approximateDOB as approximateDOB,corecontacts.emailId as emailId,corecontacts.phoneNumber as phoneNumber,timezone.utcdifference as timezoneUtcdifference,timezone.timeZoneLabel as timezoneTimeZoneLabel FROM CoreContacts corecontacts LEFT JOIN Title title ON corecontacts.titleId=title.titleId LEFT JOIN Language language ON corecontacts.nativeLanguageCode=language.languageId LEFT JOIN Gender gender ON corecontacts.genderId=gender.genderId LEFT JOIN Timezone timezone ON corecontacts.timeZoneId=timezone.timeZoneId WHERE corecontacts.activeStatus=1";

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<CoreContacts> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<CoreContacts> query = emanager.createNamedQuery("CoreContacts.findAll").getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <CoreContacts> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        String countquery = "SELECT COUNT(*)  FROM (" + PAGE_WISE_FIND_ALL + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <CoreContacts>
     * @return List<CoreContacts>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<CoreContacts> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNativeQuery(PAGE_WISE_FIND_ALL, "CoreContactsResultSetMapping");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<CoreContacts> listOfCoreContacts = query.getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfCoreContacts.size());
        return listOfCoreContacts;
    }

    /**
     * Saves the new  <CoreContacts> object.
     * @return CoreContacts
     * @Params Object of CoreContacts
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public CoreContacts save(CoreContacts entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        java.util.List<com.app.shared.organization.locationmanagement.Address> address = new java.util.ArrayList<com.app.shared.organization.locationmanagement.Address>();
        for (java.util.Iterator iterator = entity.getAddress().iterator(); iterator.hasNext(); ) {
            com.app.shared.organization.locationmanagement.Address childEntity = (com.app.shared.organization.locationmanagement.Address) iterator.next();
            if (childEntity.getPrimaryKey() != null) {
                com.app.shared.organization.locationmanagement.Address ans = emanager.find(Address.class, childEntity.getPrimaryKey());
                address.add(ans);
            } else {
                address.add(childEntity);
            }
        }
        entity.setAddress(address);
        emanager.persist(entity);
        Log.out.println("ORGCM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <CoreContacts> object.
     * @return java.util.List<CoreContacts>
     * @Params list of CoreContacts
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<CoreContacts> save(List<CoreContacts> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (CoreContacts coreContacts : entity) {
            emanager.persist(coreContacts);
        }
        Log.out.println("ORGCM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <CoreContacts> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        CoreContacts coreContacts = emanager.find(com.app.shared.organization.contactmanagement.CoreContacts.class, id);
        coreContacts.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(coreContacts);
        Log.out.println("ORGCM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <CoreContacts> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        CoreContacts coreContacts = emanager.find(com.app.shared.organization.contactmanagement.CoreContacts.class, id);
        emanager.remove(coreContacts);
        Log.out.println("ORGCM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "remove", "Record Deleted");
    }

    @Override
    @Transactional
    public void deleteAddress(List<Address> address) {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (com.app.shared.organization.locationmanagement.Address _address : address) {
            com.app.shared.organization.locationmanagement.Address s = emanager.find(com.app.shared.organization.locationmanagement.Address.class, _address.getAddressId());
            emanager.remove(s);
        }
    }

    /**
     * Updates the <CoreContacts> object.
     * @Params Object of CoreContacts
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(CoreContacts entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGCM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <CoreContacts> object.
     * @Params list of CoreContacts
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<CoreContacts> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (CoreContacts coreContacts : entity) {
            emanager.merge(coreContacts);
        }
        Log.out.println("ORGCM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of CoreContacts objects by filtering on refernce key <titleId>
     * @return List<CoreContacts>
     * @Params titleId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<CoreContacts> findByTitleId(String titleId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("CoreContacts.findByTitleId");
        query.setParameter("titleId", titleId);
        java.util.List<com.app.shared.organization.contactmanagement.CoreContacts> listOfCoreContacts = query.getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "findByTitleId", "Total Records Fetched = " + listOfCoreContacts.size());
        return listOfCoreContacts;
    }

    /**
     * Return list of CoreContacts objects by filtering on refernce key <nativeLanguageCode>
     * @return List<CoreContacts>
     * @Params nativeLanguageCode of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<CoreContacts> findByNativeLanguageCode(String nativeLanguageCode) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("CoreContacts.findByNativeLanguageCode");
        query.setParameter("nativeLanguageCode", nativeLanguageCode);
        java.util.List<com.app.shared.organization.contactmanagement.CoreContacts> listOfCoreContacts = query.getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "findByNativeLanguageCode", "Total Records Fetched = " + listOfCoreContacts.size());
        return listOfCoreContacts;
    }

    /**
     * Return list of CoreContacts objects by filtering on refernce key <genderId>
     * @return List<CoreContacts>
     * @Params genderId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<CoreContacts> findByGenderId(String genderId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("CoreContacts.findByGenderId");
        query.setParameter("genderId", genderId);
        java.util.List<com.app.shared.organization.contactmanagement.CoreContacts> listOfCoreContacts = query.getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "findByGenderId", "Total Records Fetched = " + listOfCoreContacts.size());
        return listOfCoreContacts;
    }

    /**
     * Return list of CoreContacts objects by filtering on refernce key <timeZoneId>
     * @return List<CoreContacts>
     * @Params timeZoneId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<CoreContacts> findByTimeZoneId(String timeZoneId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("CoreContacts.findByTimeZoneId");
        query.setParameter("timeZoneId", timeZoneId);
        java.util.List<com.app.shared.organization.contactmanagement.CoreContacts> listOfCoreContacts = query.getResultList();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "findByTimeZoneId", "Total Records Fetched = " + listOfCoreContacts.size());
        return listOfCoreContacts;
    }

    /**
     * Return CoreContacts object by filtering on refernce key <contactId>
     * @return CoreContacts
     * @Params contactId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public CoreContacts findById(String contactId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("CoreContacts.findById");
        query.setParameter("contactId", contactId);
        CoreContacts listOfCoreContacts = (CoreContacts) query.getSingleResult();
        Log.out.println("ORGCM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CoreContactsRepositoryImpl", "findById", "Total Records Fetched = " + listOfCoreContacts);
        return listOfCoreContacts;
    }
}
