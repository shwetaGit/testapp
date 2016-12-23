package com.app.server.repository.organization.locationmanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.organization.locationmanagement.Address;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for Address Transaction table", complexity = Complexity.MEDIUM)
public class AddressRepositoryImpl extends CommonUtilRepositoryImpl implements AddressRepository<Address> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    private final String PAGE_WISE_FIND_ALL = "SELECT address.addressId as addressId,address.addressTypeId as addressTypeId,addresstype.addressType as addresstypeAddressType,address.addressLabel as addressLabel,address.address1 as address1,address.address2 as address2,address.address3 as address3,address.countryId as countryId,country.countryName as countryCountryName,address.stateId as stateId,state.stateName as stateStateName,address.cityId as cityId,city.cityName as cityCityName,city.cityCodeChar2 as cityCityCodeChar2,city.cityCode as cityCityCode,address.zipcode as zipcode,address.latitude as latitude,address.longitude as longitude FROM Address address LEFT JOIN AddressType addresstype ON address.addressTypeId=addresstype.addressTypeId LEFT JOIN Country country ON address.countryId=country.countryId LEFT JOIN State state ON address.stateId=state.stateId LEFT JOIN City city ON address.cityId=city.cityId WHERE address.activeStatus=1";

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Address> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Address> query = emanager.createNamedQuery("Address.findAll").getResultList();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <Address> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        String countquery = "SELECT COUNT(*)  FROM (" + PAGE_WISE_FIND_ALL + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <Address>
     * @return List<Address>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Address> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNativeQuery(PAGE_WISE_FIND_ALL, "AddressResultSetMapping");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<Address> listOfAddress = query.getResultList();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfAddress.size());
        return listOfAddress;
    }

    /**
     * Saves the new  <Address> object.
     * @return Address
     * @Params Object of Address
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Address save(Address entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ORGLM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Address> object.
     * @return java.util.List<Address>
     * @Params list of Address
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Address> save(List<Address> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Address address : entity) {
            emanager.persist(address);
        }
        Log.out.println("ORGLM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Address> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Address address = emanager.find(com.app.shared.organization.locationmanagement.Address.class, id);
        address.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(address);
        Log.out.println("ORGLM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <Address> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Address address = emanager.find(com.app.shared.organization.locationmanagement.Address.class, id);
        emanager.remove(address);
        Log.out.println("ORGLM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "remove", "Record Deleted");
    }

    /**
     * Updates the <Address> object.
     * @Params Object of Address
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Address entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGLM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Address> object.
     * @Params list of Address
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Address> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (Address address : entity) {
            emanager.merge(address);
        }
        Log.out.println("ORGLM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
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
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "search", "Total Records Fetched = " + list.size());
        return list;
    }

    /**
     * Return list of Address objects by filtering on refernce key <addressTypeId>
     * @return List<Address>
     * @Params addressTypeId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Address> findByAddressTypeId(String addressTypeId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Address.findByAddressTypeId");
        query.setParameter("addressTypeId", addressTypeId);
        java.util.List<com.app.shared.organization.locationmanagement.Address> listOfAddress = query.getResultList();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "findByAddressTypeId", "Total Records Fetched = " + listOfAddress.size());
        return listOfAddress;
    }

    /**
     * Return list of Address objects by filtering on refernce key <countryId>
     * @return List<Address>
     * @Params countryId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Address> findByCountryId(String countryId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Address.findByCountryId");
        query.setParameter("countryId", countryId);
        java.util.List<com.app.shared.organization.locationmanagement.Address> listOfAddress = query.getResultList();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "findByCountryId", "Total Records Fetched = " + listOfAddress.size());
        return listOfAddress;
    }

    /**
     * Return list of Address objects by filtering on refernce key <stateId>
     * @return List<Address>
     * @Params stateId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Address> findByStateId(String stateId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Address.findByStateId");
        query.setParameter("stateId", stateId);
        java.util.List<com.app.shared.organization.locationmanagement.Address> listOfAddress = query.getResultList();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "findByStateId", "Total Records Fetched = " + listOfAddress.size());
        return listOfAddress;
    }

    /**
     * Return list of Address objects by filtering on refernce key <cityId>
     * @return List<Address>
     * @Params cityId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Address> findByCityId(String cityId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Address.findByCityId");
        query.setParameter("cityId", cityId);
        java.util.List<com.app.shared.organization.locationmanagement.Address> listOfAddress = query.getResultList();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "findByCityId", "Total Records Fetched = " + listOfAddress.size());
        return listOfAddress;
    }

    /**
     * Return Address object by filtering on refernce key <addressId>
     * @return Address
     * @Params addressId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Address findById(String addressId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Address.findById");
        query.setParameter("addressId", addressId);
        Address listOfAddress = (Address) query.getSingleResult();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressRepositoryImpl", "findById", "Total Records Fetched = " + listOfAddress);
        return listOfAddress;
    }
}
