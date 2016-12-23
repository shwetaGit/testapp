package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    /**
     * AddressRepository Variable
     */
    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        City city = new City();
        city.setCityLatitude(4);
        city.setCityFlag("kAIO3z0o8D8sYBmBACBJG1gIhKM7TTiw8nXpNjdwqUN4mZbcaX");
        city.setCityLongitude(9);
        State state = new State();
        state.setStateFlag("gqDKdbm5OdHbkNn3CC137rjVZ0RbSKv91bxMqTMbMQh5d39Nsr");
        state.setStateCodeChar2("GOeh76laGsFcDZbMjCq2p2C1Wg84zbbe");
        state.setStateName("7EYAKtynaGqcdQPs56kwM5pquSY8GN3qB1gqWLdVi5gDKJpddz");
        Country country = new Country();
        country.setCountryName("sE0MOVzdfENDgwCJbylWe0nOjoa2wtmkxUV8j8CcsZBoMq0G7I");
        country.setCurrencySymbol("OLTXyt89M4SkTHqbHfmyiMuShwl5ssOG");
        country.setCapital("bS752HOiA8XGOkzWLnD8SRXNf9KOKeUm");
        country.setCountryCode1("FxF");
        country.setCapitalLongitude(7);
        country.setCurrencyCode("mcE");
        country.setCapitalLatitude(3);
        country.setCountryFlag("TxBShOgOYfehT7HHbuoAnr8Jgrj3lVPCHRVLELO8wIRF5Alf46");
        country.setIsoNumeric(414);
        country.setCurrencyName("eNq9xku3xHAJV4k6WlFfZ8BlFrjh08zutF2vBo0iLZ3RPgNp63");
        country.setCountryCode2("MWK");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateFlag("FFzZ7teN7b0QFtzTZlqpGSfFgYkdQHnXz0IzoOEpYNbgd6YFNC");
        state.setStateCodeChar2("i21zge9DWXFM1wvGiUWIhApIgo9BoIVq");
        state.setStateName("mIxTS9x3j6G1iAmv6vlQJwaVkUnStHZSb4E2b4d8TfPrgeq3vX");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(1);
        state.setStateCapital("RLLaXuKbUs6zwwkIMcUttjrGr244B09t5o5iStoOlvSXhKbmDs");
        state.setStateCapitalLatitude(9);
        state.setStateCodeChar3("Z8RhLYKzZomlPZWktqtlQpqUJGOyJ0Qs");
        state.setStateCapitalLongitude(7);
        state.setStateDescription("v6jP6g4m102lej7MvKC3qmAXVQ7e1j6dlXpwoXij9Z9PPdVK4P");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLatitude(1);
        city.setCityFlag("PNJRlvdawsxZSaFELqWkI8qfPZGpKKlINCHqKtt1QUIMe3KSIb");
        city.setCityLongitude(6);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(2);
        city.setCityCodeChar2("zUz2MTdjWvQG9HVmnnUtHdddpFnEXww1");
        city.setCityName("dzDIkkhBRs1J0QB7DZRJs6Bg8qMDKmZ6vCztnsv0OnLbViYept");
        city.setCityDescription("HQmmi1aEuWlUuvz6erjJGI7mnFvlMYOR5ViTo6T2gADeCk64Yx");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("hNaSFruLroHFVqE7jzvfFubiVf76sGofTyoW8TDrCHvqETqL1W");
        addresstype.setAddressType("rL67dQ74HRsDuIGEHfkt9FX2b8LI3ebxglgBN29OSJvU3CrhKI");
        addresstype.setAddressTypeIcon("FCitSIj1KzvDbWV4LvzbRfxGY6Qr1xzZpfhqrUbPrnrMMLFwR8");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Address address = new Address();
        address.setAddressLabel("WCJfHc7tr09");
        address.setLongitude("DTLsWKcImiJ0EDaMd60ayLbNekiE7HG9nGV461P5t4RSIgWoxL");
        address.setZipcode("huxyzL");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("glti6IrfoAF9Rg5G77lDRDHunPbxV2Pk96Gl5RaQFz3HY5HEwj");
        address.setLatitude("SBSXT0YVBCKJ0oA4RRWcpXl6qFKzAACzeMbrHZSmvtiCE6dOrM");
        address.setAddress1("JaSifFu9pt90AttWlmAI6wB5fSB9DeIy9nL2Oqk9wRX7M9zF6D");
        address.setAddress3("GxOVARuYqnjgCdCYbmZVKbxq7q5VH8rf7p3CsZjlpPudciX07O");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddressLabel("kr8jSiALYmI");
            address.setLongitude("R3yISbRZEPHKfsqcrDlQTR1EqrqNGNGdJgMIycBzFL1FHbwfkv");
            address.setZipcode("cY6K8M");
            address.setAddress2("5PMrGpPYlIn7egu6qbtSSefOE4pfEC46CLugZjEkHIZDYYLNtt");
            address.setVersionId(1);
            address.setLatitude("qvZxsywCnqIcqhLbxAkE5LTixF5p1BkP0STf7TNbsfvo3N29p5");
            address.setAddress1("vP3kr1VtiCOF4mv5xXqFbiot2aEn9QMjya9w6mb80NVJS4OEEE");
            address.setAddress3("CFjYBQDPOF9Uj1ztmaK4tG6ejo0zhbi601XLJZTAf4EwyW2BLK");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.remove((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.remove((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.remove((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.remove((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.remove((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "rQ0Y7o8Id2Cq"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "snfNzTHyBlpduodqWVU4uDnOgZnEPwV3JHsUmdMYQRHpFMpEvtgRo9BHJ"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "qplc5MputXsu5c6hxmjhRsBka8AH2aKD4cBfxLCQWHAGKpAbPN0b8KHQd"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "mh44Z9db9h8wmNt5RopyjHF5LbLj2Py3OifJR4m87XFpwHa3TSZTGajqW"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "os22pb9"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "JqXLy1J105Yt6OiAjBekXvuNC17EaPVOlK8OyyS5TIHe57mKqd3HaAVOUfxfpV7n2"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "vl1yJOhVDluZDjiOywBs1cNigeQNVoRAdzGxIafQlTwyEVkLJ7lT6IDsY5z0X7laf"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
