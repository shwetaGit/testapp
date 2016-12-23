package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.City;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import org.springframework.web.context.request.RequestContextHolder;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.junit.Assert;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    /**
     * CityRepository Variable
     */
    @Autowired
    private CityRepository<City> cityRepository;

    /**
     * RuntimeLogInfoHelper Variable
     */
    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * EntityValidator Variable
     */
    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    /**
     * RandomValueGenerator Variable
     */
    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    /**
     * List of EntityCriteria for negative Testing
     */
    private static List<EntityTestCriteria> entityConstraint;

    /**
     *  Variable to calculate health status
     */
    @Autowired
    private ArtMethodCallStack methodCallStack;

    /**
     * MockHttpSession Variable
     */
    protected MockHttpSession session;

    /**
     * MockHttpServletRequest Variable
     */
    protected MockHttpServletRequest request;

    /**
     * MockHttpServletResponse Variable
     */
    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        final MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            final String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void setUpAfterClass() throws Exception {
        map.clear();
        map = null;
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).requestCompleted();
        RequestContextHolder.resetRequestAttributes();
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private City createCity(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateFlag("uN7QU2X5VLEiASbCxVDlwqYZB2vXKZmrLBUXf1qQimQLEQVQv1");
        state.setStateCodeChar2("HEFQl6qxsBDA3TUvdBmZKPGwKLbWZNFr");
        state.setStateName("gW6mEb8EhbhRDUlwO86rrEVZfCOpRWMLg8HvUz9bSBWeucSvmQ");
        Country country = new Country();
        country.setCountryName("7cK9IGzSKaMPwf1cZjl2a6iirXDKMe9UfIz0deK0LFYE7ZDZTE");
        country.setCurrencySymbol("U3J5kq13x6HSmPiVCKqVFUb7ytFJj235");
        country.setCapital("LtfcXWXGiI6FnwrOKRCCvlJ0Fspw73bq");
        country.setCountryCode1("3Li");
        country.setCapitalLongitude(6);
        country.setCurrencyCode("GaA");
        country.setCapitalLatitude(6);
        country.setCountryFlag("cjTVO9SVaLfcWtyAzrcWgvPwJYbuBIq3qq90VPsbN9WOvgXZtS");
        country.setIsoNumeric(477);
        country.setCurrencyName("5U0ZCSSbaeURBit45Ca35G90pBrGX2VUJIoPmsgAilhA7RSZJp");
        country.setCountryCode2("5EP");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateFlag("187J6r5SR289jxnFL5fQ40Dx8ul5UUZGN4YCbayevCKCE4QQzN");
        state.setStateCodeChar2("EqBPLD5R5OrGW8ZtaRyyflBuiUPwYUcj");
        state.setStateName("b4u4AkRCSpfeDsaY5WO7UJBb7EYAvlCaB2cHEBUa13LOHvkiWv");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(2);
        state.setStateCapital("uMnejm1oEe3RY5QDmxo2K5mjK7vIgDJYCqm7Ho1JiPLarQJ2oV");
        state.setStateCapitalLatitude(5);
        state.setStateCodeChar3("t4OTf8z262nJnZxQbG6irCaj8DgNiLFl");
        state.setStateCapitalLongitude(9);
        state.setStateDescription("S0l9XYZ6613rNmHcEM5GI2MC3KrG8jXvUlvexI7bRXjecMguT3");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityLatitude(3);
        city.setCityFlag("2Zo1JlOZf6qQ5TQ402APiCZirrPzK76dKFAxV9H8Nvnh0V6ZGt");
        city.setCityLongitude(9);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        city.setCityCode(2);
        city.setCityCodeChar2("YAjm2poI1dFbMnh0V5VZvF2ixHFyJfa0");
        city.setCityName("HJTBJPMqfFiRt0zvEPWlXDllO79ISN9YbanXG2nL5oBJloDF15");
        city.setCityDescription("Vh9vp8rSH2fOGYVAu1vuLM9u9Gm2SrihsKcBUAwzgTh5zPPCcd");
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityLatitude(1);
            city.setCityFlag("MMqjm6l4gW0WDBVij6h0nr77tssJSwwNi8jZ5ve2d6dX1mf1G8");
            city.setCityLongitude(10);
            city.setCityCode(2);
            city.setCityCodeChar2("yTjeKs1v0ZVKKFs66xg8XlQeVHrjexwB");
            city.setCityName("OA89wa3F7Kh91Fo2SYI0vEy0RYPXwKjXrFDvLpGi029UvQeomx");
            city.setCityDescription("cbhs7bp7b7oxdA8rjsmB2WFeSmN2YjerSDcnbJyx9bfgtYJp6N");
            city.setVersionId(1);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.remove((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.remove((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.remove((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "jlCT5YjCrIFHMyIrn6pz6The1bl1SK9ZJJ6darhcSdMygvx25NUlWi9tbogq1W285J43Fpmn0OHniZa7VEqdeSicd5yIkYOBopjgRbeWEDyKUe3LPtJeGAzWCrp0d66ncVeCYqXTbVNsYoWWo6FwY1ksNeEyiSeXHspR4mGJ8mf7IUM3a0DtOkzkzOi0VOsfYIYV7QkfmOxpGo8rU5c5IQaSkHBe66REyz4G7hGnmX51PDLSELY3gBpJ7hhqgJe5Z"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "Tfzk8XivAq3uYvAXaB10sIPjXFpuJxczY"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 4));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "QzCQ5lSp4dNaOM5ZCdyd2s9VVhq3vIjPlbZLkcoZLDNi7mIo3IUOA4dxNaXQhFOK6BfiMkL0eZLstwDowg8F031ns51T5IVZgFzaWGWoRqv7peJ0n920YQQZdSSW9KAcb"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "pombWVwhVcqbY9P6kH6HBRzeI7mOy8vUquDsfpEU4u7hq0gJJpDQsvQfXwW9dt14v2ktEYRObddQsVxWg10oqL6tHqb8n8nvrC4qdnmgqpQYC5DfOOWlydpZpFwveHF3i"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 20));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 14));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            Assert.fail();
        }
    }
}
