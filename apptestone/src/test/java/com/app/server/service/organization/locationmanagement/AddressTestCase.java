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
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
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
        State state = new State();
        state.setStateName("iSKOTaQaiM08UKTxAe7ldHjNLCITUNo8mbjMbN46yZ7uwwuVSD");
        state.setStateCapitalLongitude(11);
        state.setStateCodeChar2("keV56V4Q5WTu5fvG8fASYkS9RWbyfKsd");
        state.setStateFlag("Ku1hG2pI5LJ0LVd8d5mEyASpyRJ0lS3SFLDgf87tQf4Tekhqvx");
        state.setStateDescription("6I5Eb5srPeGIwFdNbHFnJeswJFMduPigAek9EmDer4fYPV3L4C");
        state.setStateCapitalLatitude(7);
        state.setStateCodeChar3("upHWRtR4OB5uFayNrGSfjLdlnPZxblcq");
        Country country = new Country();
        country.setCurrencyCode("ogg");
        country.setCurrencyName("5sxLla1ypUO2goqwnQpF9yVK1Kl71XygiV9dh87PvLq710Ewy2");
        country.setCapitalLongitude(7);
        country.setCurrencySymbol("jmrMLDomhLSlL95SyQnIylhpUNEfacP0");
        country.setCapitalLatitude(8);
        country.setCountryFlag("5OUl05281cB8hLe99HHcuNtD3XUUQUrL3utnRlUqTiffixCY5C");
        country.setIsoNumeric(174);
        country.setCountryCode2("6sL");
        country.setCapital("Q4MSLtHOPSY4biF8dYIQ8YJWyeIqr17b");
        country.setCountryCode1("XZY");
        country.setCountryName("UCbmQxlHxFCL2WJkQaZpL532FUKecducKpOCsDl5lU6qJn25sI");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("JCB8tK5WUEOaLrSmpTfZznM1nOnkrMDhLKhDzXn2vfg9EsQjgk");
        state.setStateCapitalLongitude(5);
        state.setStateCodeChar2("ewHoWpCxdpZWca8KYErNHhviWSYfFVEA");
        state.setStateFlag("UuvQDPeT2EB7BRYGG9nvjfpSNTuZAq3knaVLgrimpnjqtnGpRN");
        state.setStateDescription("SlMPyKrd99dozMaLLu9ueYJH6D0kQCE8Vlpiy8QOjQEPd1oa0C");
        state.setStateCapitalLatitude(7);
        state.setStateCodeChar3("6Wq00NMjGov7EVAYHO8pkDt3P8nEX4uh");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("a7dWxW2JKhecfsNJEB7LlW4x6knAglO805kVHZYcrIqDZfeU5H");
        state.setStateCode(1);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityDescription("VdkCqC0yG6ZEQbXOMmr2h9Gr663rMe1c88LtTlkX8m7kC3OYWk");
        city.setCityCode(3);
        city.setCityLongitude(11);
        city.setCityName("ohgtdrG1VDbmkJ6wbr9EacwYDJiPLait8s2dUIDpJPXV3VJFPD");
        city.setCityDescription("AlTI08eHVOGyfvMmgYBmY69yvcOh2zmf3f5TET0DqwV7TZSBu6");
        city.setCityCode(1);
        city.setCityLongitude(3);
        city.setCityName("K7KcAUqXDQYBLU7lI4HoPP7ps4MVRdYLPXTz8LIIdKwADqkDCS");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("Sv6WF7QTpnIgIXqgbb7O3Ehf3snkPBj2tUpjUjvwoKkPFhRxYQ");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("GRt5xMSx7K0dPEZ1omBFtx1X6s6yolDQ");
        city.setCityLatitude(2);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("U0jwF3TvW5IE4ge9IH7FjXLPJ05MnAY6U68t3uMlqxYl2oVwgc");
        addresstype.setAddressType("9ydDto1K21h4r1WjajMeavkJ7AepZLHYx8tNsZI4KSXSMppu0h");
        addresstype.setAddressTypeDesc("XhkQso93uFQKt6w873iMGYC2wIj1Co3zGNM9EFjmFA2TDas2ur");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Address address = new Address();
        address.setAddressLabel("IOJ5xAHb0Lm");
        address.setAddress3("CIINoHQh387kgMfSr6d12aIlMQTHIZwpOQQcAUmuGfC84MN4Ky");
        address.setAddress1("9Yr5vmgCU2nzWyVbAuy1ncRW1tdIxkZg7gJyNboC25kJBcSSJy");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("euShkStjELMcStcwJI8AyioWa7kHJv8wQ2y3mRxUO6AAokCRQy");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("Uebu8q");
        address.setAddress2("qyYUpFOTDX3kh2b6zOQVZkSHVPAmBws6T2jOvU8hxk3OoGSVb8");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setLongitude("DGGV6mxphr8M24wZblVko1MheIGFtoGCGf8ZURPe88foyXVejy");
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
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddressLabel("rNrdG8AQKvi");
            address.setAddress3("igH4hGpeLkqSVzJEw2WaBJXSMxH2rQSNIeM8jaKJnGoubgBVSk");
            address.setAddress1("tBVyIdwxGArpeXpJtXIgjw5cqwNNjoczDKBgWdocyi3k47ytVN");
            address.setVersionId(1);
            address.setLatitude("EZUKeX3q1H2DQ1RpPMrsX2doiOzxkuGRZizguHRdIaatDoC8Ip");
            address.setZipcode("41I5b3");
            address.setAddress2("oYqFfmoQPFjXqaWtwPfdjKGaYY6RmjJo2EdUgfbN2LhZY29fs1");
            address.setLongitude("EZ0GLvpowCcsjjZRG5LYFd4dcNHPJXE6aYxM4BBGKn2Cnn9Lsq");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
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
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "BM7GgWDUQpjK"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "vrnkSCBnYMTc8iW34TBZtx8xZRGIopO8PyBAc468JvlMInPFKWcN3CpKa"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "7zZoC4GGPqQxlvOYus2mCCKzPvgO7Vww8NRp1pPFE7M0gKPB1vrjOzvab"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "cV9F9DSybgjaNc8unukVh9xiHQCS3ivkEnU8PuuQeyd10hFExYxUpQTpg"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "FZn4CNA"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "5WDAMhvNdbv3C6SeYLNNi1HFhjWHzYBTjTI29gCguGJixezD2TZIz2Rpg16wIcZRr"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "sSio3hvgZ6mon0mSAY4QiC48BLcrOwDmcg04ZoZZYGXqeUomD6mGE5GeH9X2noTXJ"));
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
