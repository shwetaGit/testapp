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
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
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
        Gender gender = new Gender();
        gender.setGender("lH0CP9NKaESPTcD800hOkFQXd8jHGln0VnmtvCxIx5cpHknBqV");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageDescription("aWEHgHDPdlOavTDHwwwQP6h8XcLjVsKYQlgXm4TdGOLvaBergP");
        language.setLanguageIcon("LlK6kQZcfh0jksTvid3527r4h1ZbblxajYz2PXf4bkCdYtJQDA");
        language.setAlpha2("ON");
        language.setAlpha3("rFR");
        language.setLanguageType("8isCAeMXRoJVpovh6z0LyqYqbsY4bJJl");
        language.setAlpha4parentid(1);
        language.setLanguage("mXL1pzxubd6pKFnHbaM9lGr9Rgxx8GwRzPauZs6tAPNNIzHa9S");
        language.setAlpha4("ylQj");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCities("yUQk84a3QuC1AD9d8oySmF0xUkIvdoKSqXFsrNEvEVX2TP2Jff");
        timezone.setGmtLabel("zH9BKrovQxvKYTV0NUK4dm1Xr6N0BR50C2daprs4VYVfXRmoV9");
        timezone.setTimeZoneLabel("1VBFEG59VQ8gRfgUFGZ4DXeQP9Dm8slDOk04CgxbiKZW63ovfP");
        timezone.setUtcdifference(9);
        timezone.setCountry("AjMlmSk3IvBryD8Qs6xc1F0K5UpPwV9AyqZyyk67hFo54hWbNQ");
        Title title = new Title();
        title.setTitles("faA1ZAw92fwGbjVm5mOrhTJ8VlKvanzU9s7PGjT0PwKKeXLCL6");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1482410080023l));
        corecontacts.setNativeFirstName("3Q04dJ1Tkqg03r1S8teLtKA6KuzXartbkwYf5jzWqZfRbnPenW");
        corecontacts.setNativeLastName("yeb3Dg4e9VzEFuAw0Y3XG5WgM46lweTYV3W7hstRQZZzy4evjv");
        corecontacts.setNativeMiddleName("iMw7Utt5A6XSJuS2BDxBGWSyKPGUgwtiNUBsvQQ2TECVyNN1bs");
        corecontacts.setEmailId("TA3lLeNmndGFQreuEprWrdHD8bYNiDXeWiDyZrVW0EEwnNQZDe");
        corecontacts.setNativeTitle("YFd0zNhn9UQzz0M0YCtx74QMPzM5W7M0MhHVllaPE5fPcUUH3A");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setPhoneNumber("iSyOg3iPRllKgvtyzlxl");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("L4kmFtKIAHQreRSkI5lN3wJ5QobmFbnt4s4ywa3uNQCx1HZEbz");
        corecontacts.setMiddleName("6IGEnDPDeAteh7sYJN8f0Q5KXXGCyKxgIAJLG5g7L5kGehhcD2");
        corecontacts.setFirstName("3vxHBdYNcwV48EmocTobqgLpYjjs7pQjZnhbCHzeTNxVoBS3rf");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1482410080445l));
        corecontacts.setAge(87);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("L4jKBJwMixj");
        address.setLongitude("xdy5B3l5UAkAitUuFCm0WBai2eig4m18kxwshwwFbKfDHNGqw3");
        address.setZipcode("1qvLId");
        City city = new City();
        city.setCityLatitude(4);
        city.setCityFlag("f7uSx6U5g5XdHKYxD8JCFChpZlNpDoNUEtxi2ePXoaLMfHLYZJ");
        city.setCityLongitude(2);
        State state = new State();
        state.setStateFlag("j4IgRe9JQZurT0q8XALCsQCYhaaCPW0zk9SmTG1D4hZ5HsqWrp");
        state.setStateCodeChar2("UrlgSeL9q3Muws97gdB2uvG8ufffOztZ");
        state.setStateName("Ljl0FYGmA5VpYiNC5lLScWdegcQeth88tqSnyMBhSVaJJvqsrJ");
        Country country = new Country();
        country.setCountryName("JWbXURpJ27XQwLYncvUabVdCd9ZfSm29jAo294HAePChcqDTkc");
        country.setCurrencySymbol("qxY0Pp6dP0ys0AX6zgzM81CSjOML0WOC");
        country.setCapital("VCwAde6z8rAB6f4iyd8td8kXf7dWhzWi");
        country.setCountryCode1("FPu");
        country.setCapitalLongitude(9);
        country.setCurrencyCode("cgc");
        country.setCapitalLatitude(7);
        country.setCountryFlag("aA3SNSivP9a7GL4YDUFpxWamzEfuqMpxJUWULyH3b5Lcx0KYmS");
        country.setIsoNumeric(262);
        country.setCurrencyName("60jJUuBiuFRPWwcxPthSpZwKjcXMJvi3Lcvi623hKtL87sTdyX");
        country.setCountryCode2("y6z");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateFlag("rM3NiMc8t1EELc4tYmTf0HlTauTa9zYtnZk6fcqpK75FKhWkhe");
        state.setStateCodeChar2("TOz2JpjA6Am9lFgPEUriq7qb77NXwdPw");
        state.setStateName("qRRJGjE8JP6D6cQTg8gG4OtUDTRT9vO0cVJJF39e14CnPEqewW");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(1);
        state.setStateCapital("g0NwguWb79tIDp4LlaDly4SjF94lkqHI1MCENU4wwZHtN3X96V");
        state.setStateCapitalLatitude(1);
        state.setStateCodeChar3("TQOQoBoGwDpNNoReLDTHXsbffS9nCwX9");
        state.setStateCapitalLongitude(8);
        state.setStateDescription("cj64wpsDgII7OUJQGRc7KP6M2RaHpvY1UhmoRsVIYuLQeYPHpi");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLatitude(4);
        city.setCityFlag("8sJWn3DNGUDweTl68YgePpFRsn4q1qXsW4a0mmShQjv5B2KxNY");
        city.setCityLongitude(5);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(1);
        city.setCityCodeChar2("qyRjp5tScd1lWGVCbpAj8gC0laIgvvIO");
        city.setCityName("DYJoWxcvnrYEEh4iTrS2ih2pC7m6eT1hEkeIgi9Zd5wCd5d2O4");
        city.setCityDescription("4TgiEpnr4iLW5BjMWEA8vCSFwgFjAdGwsVD35IOKE2TfUfFDBq");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("5tf9Wvx78uA7GzhpdkDwbDn8YkPHVN8erCNbImGOXtL2RuQTHr");
        addresstype.setAddressType("sqbi35xMIzAJ9nfjqdYHIrkCGINDfTKDY8eNm82OnwTObCtr2e");
        addresstype.setAddressTypeIcon("56HhT6ILGWtAzkKeJEx1ntdPhklIBvW8bfEK4bLGqtD2OrpmGD");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddressLabel("8ls3jKyH7qD");
        address.setLongitude("8BMsFoMoXp15H45IXpZhjbpZwN867G6TywL0AHwJtTeCp5hvkU");
        address.setZipcode("xf4ri7");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("HQMK3bY3tSxfZAfZHzHCrpWxphApkpLZ5vcAq3oJyiEkwuMVAR");
        address.setLatitude("cS4lZmpFm5SlzgrsdVnBGv8CrTOlCKXsOXBqLUT854GjlVShhr");
        address.setAddress1("z86CQmyi5io6PtFH9LVp03BXe428IvIckypKxZgTdfyT7zyIuI");
        address.setAddress3("wRSBXiQHDeEsDARApRuRdZ2S9xU3ZVlitSXxb7EQ10LEMSqdXi");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
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
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

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
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1482410081583l));
            corecontacts.setNativeFirstName("D3dEqRhXrzEiud4c7dfIdSFsYjuCKLzPJS6sSdvT6hE9aTR8Fc");
            corecontacts.setNativeLastName("JlMl6v7fMEcespvcMQhIoEZdJH1GX47CVdYpDakte3V5urEKkc");
            corecontacts.setNativeMiddleName("VfxUsQxhX5P2NOChBpapuWH8IIgCjLpkBwhnJYW6aFeBEieq3h");
            corecontacts.setVersionId(1);
            corecontacts.setEmailId("eKyXSGllKd1UdLxkkAfKSKS5LFyxa0BNJiEs9WylaQlLHh7Z4M");
            corecontacts.setNativeTitle("0ZYkbCvz537B3jttszV7tzcwPU4qLHhBl1PeEglxQcF6xftMr4");
            corecontacts.setPhoneNumber("HmbT8bKCjjjeu4hSRLmb");
            corecontacts.setLastName("GieEp2ifjPl7haM7IlimtUeBMxS9DPFqHMLTCb8TPaVAMQKWve");
            corecontacts.setMiddleName("jGQub1cT3JT7o9jdt5OJ1QyuA6T3BYMwVxJAieUEV8CN5CLx4D");
            corecontacts.setFirstName("uDlzu7KZ7i93jZU2ehT3rRnOn6f64QnO9SeXV5q04EEOwfNNIB");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1482410082057l));
            corecontacts.setAge(52);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
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
            languageRepository.remove((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.remove((java.lang.String) map.get("GenderPrimaryKey"));
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "qoMsqmwKa5Ai5L8jeXv8EwCne2nik0U2iLPxpvgUTLBfaCLezMTHy3rGc9Dk24ATn3G2XwE9B2mv2PaE8yMjsOuxG9Eh8UDdi2DJyjrm8wxSNCl1XHluOv7BgzQjcmQxnDCM56oKWRdqs09YymU8buoAZyq6RuYciIyfhC5TmwKVmeKPPSjhT9mbhuMeFHpM4D6ikswp2JGy9LSHcBSRf6Arxb0q2MaFq6alNbwv8SZTxMKBEJ4no5pbIKyap2e0U"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "3xsU7QxGZ3Heb9QdegUv5g1K77TB9X2HDGpQa7P4nr9rwUSFtDYbvTpn6528zOCFjOnjdmY0Fg3ND8xULmySkZOSkqxnr0AvETo5qdWcz8YkLXMWqbsv061BIlnFgTZFmRld6warOLTchX6MJqxjzz8Cw8TNaYq2QYkBIOiFi1kym1niMXnRNpdpsW3sbKiThXAk8Zh8lUxCK2JUVKLofIR6o1l9PBJMAChG7DF16L2tTLBkX39Gc5lvtvhKqsER0"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "r7RdcVn2sPQSh8WIAvwP5oJysNW0QtJ0RWsPp8eBUaJIofvsEj9vfGsdajckqxNDsj9bvNl7ZvbyfSIyBVIkB4gO4ZrnYBZ0k7JsDh2HN4kwVaP1aP0Kg87aFEgFpE4C0MOhJNazVXSiQ5mvVF3frB9aT3Lwypm5awFmIyfrKlJxexV0mlKOGPxg1Kz2FqWqiGLehc6K7THe8VLXdJcEUnL8iCD7v6apnf0Lej4RfdlhyNqJerMwskz2HYMFihRyr"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "FbTgObqHSnoGnzsGpFYCTPJEW9vRAyUmBt5oVN1w2gaiKEeQD08frVcDR1GTG9I62"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "zAHW1eZFpwW5N9y6kw4r97oRy2dOk6SmkjVoEOxCxjGL1Y66Vq9kASA6vpUsouiS3aodcmZhZrwlYiMkaMHJQSW2fL0lFDmekzC44AoVdWuknXA2s39T9xzdMOLUlpltEX9weEbiqdFXENPh79objZJFWUEuQKHXc588ZV5YprpazMjfwrt6GLX6giTXAt4Tn8KET2idtaiIGV5k8u421eiWUW2z73Bjs0FYBZYb1OPXIbZgSUbiQgiHTd1Hsq2lh"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "V8Ducqw5lfGJ23zjk7cv71B6uZ6XODJXQfyA3uVazehePc5YUtxWmRQnJR9gfLNvaBrjXE4DWvpr8y2Acmn1L6a7nat54MXqbFailpYTTzk4dloFUwjFh5HmOYSYKqTpHy0RF2TjX7vMbhpMAI5WZWIwISMINKhThP4QtInJsLEN2gNemXvT7CVSn7GTR0ZwsgjLMR7QsGIn5pIIT583LfJ1TpnF5SqpELhw38RdVJQGqF5rcVkfR7zlO5mAWPiva"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "Z9xwg0NTihCtmljUq5pSH1T7yLuovCbrGjyxtlFUmshdm6PNR9gWNu09gdLJQgmTA8MGW2xjGKnnZDW9k02JBt1zKL3I6ZiM1sD6otCgauTHv478XLcJtiFZJc3NLdMdS49Y4EKOVi7NNvbP3qNIKpHNvE6MI1Nv4E7oABdeWgxKWskZkrDKgKB83hHwe40OKeRISwvsZXWG3OdvsDzQxTAkckghXsOXSVMUrjAvj9NSsjNyNfKOgoJGz102VjfDH"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 229));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "ECRHabSx8qwyavOEyWODzXl6ytIkvouA8m6C1ET2TwQENgRYYZpqB9LLfBvUyyEXIfDoWS4aoJZ86ApKaYjpx9NUE6lIokKr2b3x10cuoUrOcYhCpKkdJm9G2CWnTph5APut7L9LevHR93SDu9G5YvSrb99QtVzZNo8iID2H1Tsp2GhGD4ZVTlIJ1WRJyz9M086h1XsbajQQ5YYJKnp3sJURDNqK3xSzqziJadvwqnPkCJh52j2rg8J20c2HJD4ov"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "4t32dSCYdTtruYlyOBFpy"));
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
