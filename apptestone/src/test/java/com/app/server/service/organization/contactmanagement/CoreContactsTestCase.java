package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
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
public class CoreContactsTestCase extends EntityTestCriteria {

    /**
     * CoreContactsRepository Variable
     */
    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Language language = new Language();
        language.setLanguage("59tL8fsMnA3amqsobHQyXy60FTpCcwfd4iDAaXsPkPqXkSvyKv");
        language.setAlpha4("iknf");
        language.setLanguageDescription("c49DnpZddTwOfIuh2VY4dAV3Jkqcm2gt0nex97RLsJ7CkiH1OZ");
        language.setAlpha2("Pf");
        language.setLanguageType("4bXiWSXkUF8c243sDx4IoLFM0Wb8ix5N");
        language.setLanguageIcon("OTYRp7aBMN6F4LEc4PVIabVnEo0EISepzGlwqumWVGxyhUFELU");
        language.setAlpha3("Cxt");
        language.setAlpha4parentid(4);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("weEDU5Jfqdwqi3L3x7IFSZZKfnVHC5x1Kfrey4WyR4u3LE7I0U");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("vXwPoIt1OQWOPxjNNAixBIb9NS8Rrq9QHjHjfiREbI8aB9USYS");
        timezone.setCountry("gH5jjWljdGZneFkJwfMMR6KirXM5dMBd6gHoNwpfjJ2Sr3OpEq");
        timezone.setUtcdifference(10);
        timezone.setCities("ApeP5uN0dIrRO50eLFiLZxUTZWK5HboMK0iIs2WHGe7OzRavan");
        timezone.setTimeZoneLabel("T0twU2f1M9QONL3yQOj8TkCtaWl8Rt91jd0V8f04C0RTwfODbi");
        Title title = new Title();
        title.setTitles("PyZnEMDhmY7pmzKHjWslfyd7tcO48CNQwT0atOWQKX8DEou7av");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1482410095308l));
        corecontacts.setFirstName("dohO2bYV9hNSvRqpm8ANt4lBlLpVnjDJbXFs5YqFJgCA6LP3p9");
        corecontacts.setLastName("NGmDI9TvKFIuhimnY5lVSSo0KUjF6M4RfeyiLWocjIbJWk7luf");
        corecontacts.setNativeLastName("y27plexAkCj4qXcVyhFe9FLIu1Pibnno1RZszm2Uzhpa3N4Zcg");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("iHlYz5BbMvjyZE321a8D5dASE1tifLkqGXeHyXUBuVLrAJYjrf");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setEmailId("nyRcwowNSmpbXBObGJHorWYM6siCvrZXMmXDfUr1ah0dl8SR5k");
        corecontacts.setMiddleName("9IEDtlFnSM9P1PukRmpYP1t8dC1uCIlN2JtskCELh5nm0ePMtq");
        corecontacts.setAge(93);
        corecontacts.setNativeMiddleName("DABZXnuCu6120CcOsg0qkLPBoOeNdi1xTw9l5wFr2NK1WButfX");
        corecontacts.setPhoneNumber("16Nf40DrI2iXzBlrt2Jy");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1482410095866l));
        corecontacts.setNativeTitle("mgu14298BWVCPYoJWPqCRlbOlV3KKegDSg14vpKn2b5K16cE5H");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("4JM0JbHsyw7");
        address.setAddress3("51mvw6TcVCV6fwBGv8imsBz67u4j9VPnQhgchnFpiPiARvjqrq");
        address.setAddress1("78TqoIpMJC47oX5csxVtgr2KonMbZO4MwSQkl2hiCoA4wXV8Kt");
        State state = new State();
        state.setStateName("ZYv0Xunny3wP2xKNRrG2PtDJs5TWoA2WdFcGgMn5hf6q98KBMJ");
        state.setStateCapitalLongitude(6);
        state.setStateCodeChar2("M1npb4CqrXpQ23W4QZrEhQuZeTAxlBlY");
        state.setStateFlag("JR5uRmbEK33t3kdQRacmtpey2B82sbFj2xzG78bX4aOHj1GPWz");
        state.setStateDescription("5ngFwK9zLZhNPSwgvP6OvTT9rGKSsVO4VkZ81IzZmuZxSk7juz");
        state.setStateCapitalLatitude(11);
        state.setStateCodeChar3("s2i5fsVxM7S3lIGrH6n1yRfaKPqa16OH");
        Country country = new Country();
        country.setCurrencyCode("Mq1");
        country.setCurrencyName("Upgbk1h5JnN9Dg8B3WqRXUvV8TwIkQSe1cUAtiiaHPCCZAH9Lg");
        country.setCapitalLongitude(4);
        country.setCurrencySymbol("7Df8wMyOoM6OXORnsTPqBtP6Oee0MSLm");
        country.setCapitalLatitude(3);
        country.setCountryFlag("Cnty6pcDQCE8Fs81v35dGwjJgzn9W2JXPGWJnDKhtzLie1q2Wd");
        country.setIsoNumeric(337);
        country.setCountryCode2("21u");
        country.setCapital("CMf7O1NOtOWJtBl14ignZlqCgRiFbd6k");
        country.setCountryCode1("pGI");
        country.setCountryName("A0owEN2chcHgCanlGjNCkop6SRSGFxwL7Rlrsx1jfsMQHR7Lbp");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("HU9eUAG3ULxmqYtC3vXTUpgQQ3MGd0BK3abCITAO1XoaAxyC6b");
        state.setStateCapitalLongitude(7);
        state.setStateCodeChar2("cwBhfugCFiJnpUBKsc9S2sr3V1oczzNy");
        state.setStateFlag("bfRKmdvb8TN6W31WNfH538E6190liouSPtYj0NM6eMnVoObUCz");
        state.setStateDescription("gvdSQFZ4lespBpaY43dqWYu74pbaOa58mEp5hKGsKWOghbvjzd");
        state.setStateCapitalLatitude(4);
        state.setStateCodeChar3("T3QRikDVI2QyM7fNdR4CtMTdgIaX3nvk");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("YbHTYzeNA3MaBG2Ke8eEBAZsiPhTTajkVNSncJ2xDDUlb3oJEO");
        state.setStateCode(2);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityDescription("N2LRrZbXOTZXIfRhwpc5MIL6lprMe12I28iC19z33JzcRYaYmz");
        city.setCityCode(2);
        city.setCityLongitude(3);
        city.setCityName("h80t4zHmULlAUgDbJAivU6jUShrzv3XRdSX0Un3tLL5GOXorKF");
        city.setCityDescription("fN29QDbWSjTojIXcz6Z8cL0KybX1CEUImM587DzVtadAdJeLxZ");
        city.setCityCode(1);
        city.setCityLongitude(11);
        city.setCityName("Jn6wjNhWBkfx2UoomllVOrLgzDphEVkpDUARqHIaAPGGSKclxU");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("y6AJV3zBoPP3p6qBUJ3pYpBjvwrNRZwjnUMYYBgJnxKeOGGF2b");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("eLLkGoTmKT4yAZHMrbN7HLMAcUsLhwBy");
        city.setCityLatitude(5);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("kvKgSgB0vQ0bsgQnJWVfsbqj1OC6NsJswo1fpzRFYTcQG3nkDA");
        addresstype.setAddressType("VhvJ8DwNhDJuj2tg1pNDRSUL86sQIOaMCzxs8lkPTcKlgrDzeI");
        addresstype.setAddressTypeDesc("jpJawbUyXvvFi21z2yy5kZ1R0AWOWpxKV3b6ywXdRNaKt0P8EB");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddressLabel("sqQgvZqMiYG");
        address.setAddress3("7v99F6r1Pb7qOHEhn3S59sQeYaS9GzhTiNhfoQmPNjepferCSD");
        address.setAddress1("d6KNaNNcUtfoxZVkU7T0mdFGdSQoaxStTtXPFGHhbhgw1vfuJL");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("N2wkOvA2glRn4hVfxTbecoe1hIUaw3CkARx6bTJjKefpNv1Q95");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("oOdahu");
        address.setAddress2("11Y0UPvYAkJLruZ3iclM07Q6E0EL6YoI8ZZhK01q9MzPPySH7c");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setLongitude("MUknzTjV7DkGdPkp9I95GS2dYHsjztXXNXNcufpKYrcNf3FLuo");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

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
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1482410096892l));
            corecontacts.setFirstName("cC7A14BO6928GmoZcuAyUPx16e7abg48n8N7mww9bbeD4hJpC7");
            corecontacts.setLastName("7xxB1OqdX2uPJrAin36cIDZc8qKW0Mhjky29FzKZiBH3wIdmKD");
            corecontacts.setNativeLastName("NtUaU05wloJhno41R3HP3pgWRM0PqnWKhs4beaI0D5kNzggs3j");
            corecontacts.setNativeFirstName("iwbVr04BrmvxtsStLbKDQWp304Y7PaLYjlNTa4udfJIdyfilVW");
            corecontacts.setVersionId(1);
            corecontacts.setEmailId("iDXsoCJwdSNzu5nJYXyLSAd0FBZ0XjCX3zoOCmvvPcY0GK8ZcN");
            corecontacts.setMiddleName("xRWzjHGYj4H6d9IQDkNBzwjj4WXjXVW1KmU0ORe2U21B1xmRVW");
            corecontacts.setAge(56);
            corecontacts.setNativeMiddleName("KCBG5SJu4qWdWpgMqYqB46zS5LFPovtvGRQo8OgrPlitlr1mF6");
            corecontacts.setPhoneNumber("2OaHBLIdbXO8woDqGSSY");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1482410097422l));
            corecontacts.setNativeTitle("lfnR7HQkURlQrD4MvweL6hKoqZdFCQ3IGF2gw40BN3KGNTeHX9");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.remove((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.remove((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.remove((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.remove((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.remove((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            titleRepository.remove((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.remove((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            genderRepository.remove((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            languageRepository.remove((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "wLQT6ZLjEDZaHnO1fRTkiJo8MTYhPPs1cbr3Qsys1dPpJf44mjGOHSJERO7ldZYNwi3eZ0P5mFuyVldyy5Ah0PuzrLsY3EIsUCxWyKzaZ99xbdmlLtKRMEsyQoCUm4WmMHUevhmKHZOdiuJKNHeLqTxj8XmqlLx2HEmTfCtYgH4xJa7JiRWeAXj0WiddeM5vmq8lFc4KsjWv124sObqEl2MAtNsnI8Q2pxRaXssQXQb9mCEeLDSsYRkNZfzURF2yM"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "faZu5TQ7j0c7N5sUB09jlUvBLj6clLaxNEBBWerd2SfFHj82vVjr9cKwjTXchNwmIXGKWQ6GKiAS2dKDbeqHf2szC4Z68HVaCMfCtLjm2GPbjztXHTZVFgBiGPxj3NhvOH5AYxgjUxjRQVdCzc74GW45bS0L4TsOr26oZvPHTOjOn7TI2SkFr438GqimIME5x9CZNmN0J0nxf1Fs4Z8SjvqGxuLnh5AQva84YiZJK9oljernKcyGLtBaVViVSC7Ux"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "cTuRUTfio541oovqc5Mto3VQEstNYBKWx5tsA1gdDizMxQYVh2rpARBc4K6jVBlo4yZDv4it4H9qZWQNErduVfpDysQHFbYOUJafvlhq1Z2CcgzqVeAZOsQXW0IKrduSTL1CtQ7mLhztCW6kS8EVVa2CVb2xLMoqoteK8TRXyqVg8e7jaqC0qtTIqSPnKAbelXFY4adllWTT2mYLGj2VNuliD96Vzqs28Ph1585CG7jfs3iGSBAKoBuk3aroFSJCk"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "oHRAwogagH9V8FS9ElGcv7VFwkTfYkigDgMnugIWz7GMAbZiZ2PLNmrKEthD0FwC8"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "9xOQL2Ixmk1YJvEd4o00w8BJKbssgyPfQYFH2lDByjXaJygEKeyltFNfUlK5zXIUvF4flZw5XKuqpHDY2quFzc5KmC5O0L0nLHfSsZkwAjidm17k6Rz2BeJlbOrkwf6gzweawxSXYcsBEwxVlGJery0taWf5d59GId0zPZKmK1QUdvMdDYdnX0P3VqpkNcPQrap7PjZ3FiXL8o1PQmPLVWJ5sPcuHupL2TndpaeFN0Y6R9Ry87qenYPHfsBuRuwVq"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "bvFbfgnNvF18ycWGnGGm8hfeMrpfdJSYUAhNtGDzrwuEOnNJT2G1Mw4ZrvEqHfIIQkFL9JiIn8F9jpI6h1yEe7DMZRk2Qqene84UlcEXhvKNkv8ZWF6tsB1uJk83qHXojG9lzJrSgdFuxux14kdMsHrrWM1w38ajKHCBJ5DDCWfpwYgHIajZxnj98n1jfFH1AxkISxMAEoXaX2sJaNW1WsnNdJvIUcaZLPlrdNCbIPSFBqHsme9dzFP4cZpMKlHF6"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "GHxKcRjNIBjAARVnxPoZrun83bdlL46ha18CIlv8TfgxoxcpadKQ1buYPC986Fnn3t0fLWYQSIaQE4wEEWFj5bOOVBe0n7exaw1VNDMSZXSBNgob7g3EY0MTjWFvJtAioCfF6weNcuavZfKxEZbOvmUBg5wuCRugN5VjFMr2QRCeBsvzq3CBzx996dxzzdoCcqCueiceh3tzU4mEL6ZZfPcdJe9A3M99mK0EoOhQr09byw6U3P2eSYig1QiON9mEf"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 197));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "lETYSKy2rWKGopEb4XGPtpvK43HiPqMo7RZ3x5wCdsh9OA8MzuJYk9DIpcEla3azFKMMoWNPQW3KLyV2bnt42JdlLDtXgH8fnVEnh232C16SuXxuPziu0NWY6ug2Yk6GaoQDn0oRHM4aEM7ZFTMo6731qnrvtZsWGrattWmEkN17pTJn5lEDhdT0G9UVvmU0plnUphYFdjP7jZSr6vsT9I5AfSxqWtvEq2tZFnO0yHA5ngseW5DUX7uWrzh3Hju6c"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "IExWTC2fZC6eqCeXPHQiH"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
