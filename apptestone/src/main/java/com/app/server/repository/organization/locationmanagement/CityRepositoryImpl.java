package com.app.server.repository.organization.locationmanagement;
import com.app.server.repository.core.CommonUtilRepositoryImpl;
import com.app.shared.organization.locationmanagement.City;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for City Master table Entity", complexity = Complexity.LOW)
public class CityRepositoryImpl extends CommonUtilRepositoryImpl implements CityRepository<City> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    private final String PAGE_WISE_FIND_ALL = "SELECT city.cityId as cityId,city.countryId as countryId,country.countryName as countryCountryName,city.stateId as stateId,state.stateName as stateStateName,city.cityName as cityName,city.cityCodeChar2 as cityCodeChar2,city.cityCode as cityCode,city.cityDescription as cityDescription,city.cityFlag as cityFlag,city.cityLatitude as cityLatitude,city.cityLongitude as cityLongitude FROM City city LEFT JOIN Country country ON city.countryId=country.countryId LEFT JOIN State state ON city.stateId=state.stateId WHERE city.activeStatus=1";

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<City> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<City> query = emanager.createNamedQuery("City.findAll").getResultList();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CityRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Retrive the total count of given named query for <City> object.
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public long getTotalPageCount() throws Exception {
        EntityManager emanager = emfResource.getResource();
        String countquery = "SELECT COUNT(*)  FROM (" + PAGE_WISE_FIND_ALL + ") totalSize";
        Query query = emanager.createNativeQuery(countquery);
        long pageCount = Long.parseLong(query.getSingleResult().toString());
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CityRepositoryImpl", "getTotalPageCount", "Total Records Size = " + pageCount);
        return pageCount;
    }

    /**
     * Returns the list of <City>
     * @return List<City>
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<City> findPageWiseData(Integer pageSize, Integer pageNo) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNativeQuery(PAGE_WISE_FIND_ALL, "CityResultSetMapping");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<City> listOfCity = query.getResultList();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CityRepositoryImpl", "findPageWiseData", "Total Records Fetched = " + listOfCity.size());
        return listOfCity;
    }

    /**
     * Saves the new  <City> object.
     * @return City
     * @Params Object of City
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public City save(City entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ORGLM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CityRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <City> object.
     * @return java.util.List<City>
     * @Params list of City
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<City> save(List<City> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (City city : entity) {
            emanager.persist(city);
        }
        Log.out.println("ORGLM322990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CityRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <City> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        City city = emanager.find(com.app.shared.organization.locationmanagement.City.class, id);
        city.setSystemInformation(RECORD_TYPE.DELETE);
        emanager.merge(city);
        Log.out.println("ORGLM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CityRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Deletes the <City> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void remove(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        City city = emanager.find(com.app.shared.organization.locationmanagement.City.class, id);
        emanager.remove(city);
        Log.out.println("ORGLM328990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CityRepositoryImpl", "remove", "Record Deleted");
    }

    /**
     * Updates the <City> object.
     * @Params Object of City
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(City entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGLM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CityRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <City> object.
     * @Params list of City
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<City> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (City city : entity) {
            emanager.merge(city);
        }
        Log.out.println("ORGLM321990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CityRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of City objects by filtering on refernce key <countryId>
     * @return List<City>
     * @Params countryId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<City> findByCountryId(String countryId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("City.findByCountryId");
        query.setParameter("countryId", countryId);
        java.util.List<com.app.shared.organization.locationmanagement.City> listOfCity = query.getResultList();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CityRepositoryImpl", "findByCountryId", "Total Records Fetched = " + listOfCity.size());
        return listOfCity;
    }

    /**
     * Return list of City objects by filtering on refernce key <stateId>
     * @return List<City>
     * @Params stateId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<City> findByStateId(String stateId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("City.findByStateId");
        query.setParameter("stateId", stateId);
        java.util.List<com.app.shared.organization.locationmanagement.City> listOfCity = query.getResultList();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CityRepositoryImpl", "findByStateId", "Total Records Fetched = " + listOfCity.size());
        return listOfCity;
    }

    /**
     * Return City object by filtering on refernce key <cityId>
     * @return City
     * @Params cityId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public City findById(String cityId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("City.findById");
        query.setParameter("cityId", cityId);
        City listOfCity = (City) query.getSingleResult();
        Log.out.println("ORGLM324990200", runtimeLogInfoHelper.getRequestHeaderBean(), "CityRepositoryImpl", "findById", "Total Records Fetched = " + listOfCity);
        return listOfCity;
    }
}
