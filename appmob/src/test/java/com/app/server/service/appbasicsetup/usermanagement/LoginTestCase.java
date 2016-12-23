package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.appbasicsetup.usermanagement.UserDetail;
import com.app.server.repository.appbasicsetup.usermanagement.UserDetailRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    /**
     * LoginRepository Variable
     */
    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        UserDetail userdetail = new UserDetail();
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setAuthExpiryDate(new java.sql.Timestamp(1482410089690l));
        userdetail.setUserAccessCode(10979);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1482410089690l));
        userdetail.setAuthAlgo("nY9xkhbXy8sQfeDgcAV7oTI3O1Uqz6ZFAgJMQ3N5DQGwXrBgIi");
        userdetail.setIsLocked(1);
        userdetail.setChangeAuthNextLogin(1);
        userdetail.setAllowMultipleLogin(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainIcon("kwCjToHrpe1TS1NU3nOCrwjAUsCxi5hSNekVfwDwRk954Sl5PU");
        useraccessdomain.setDomainHelp("F5RoOXrUWg0q3X8FkB1RyIxQ2JrY5DUJBZTnD5aLs8k5bYhgPj");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("jn0L191ZLkxWZiuARrs1u3hDyyfOkTIK1mPaOQhPmzdvkdV0HW");
        useraccessdomain.setDomainName("5yxiBtnRJmsDcusVDYxpNriiLx7Ja9MriG2yzk6vWug58lmCYM");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("13f0Fc7zUwrgGCeuNVqwcrFrQCV8TAo2au6DCwW4z5f2BHe6G3");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelName("ufVIt44nayJ4IUXq1tEQTzgeCxYnYPcY39D5SzV8bM5eqMuvI2");
        useraccesslevel.setLevelHelp("RRZ6n21HVWVQu6sb8DEio7f1ru1ikz216Qh71cjrZSXCvSJ4tG");
        useraccesslevel.setLevelIcon("VaCBwYgsP0LljyCd1C96N87a2HAtN92iUW1Bfn8aGKscCGfAF5");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setAuthExpiryDate(new java.sql.Timestamp(1482410089775l));
        userdetail.setUserAccessCode(55478);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1482410089775l));
        userdetail.setAuthAlgo("t1mUMyccNj7OQ3jqrmeKcscSiMxGL1enwFlAgLMFc9AV5HGrjC");
        userdetail.setIsLocked(1);
        userdetail.setChangeAuthNextLogin(1);
        userdetail.setAllowMultipleLogin(1);
        userdetail.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setSessionTimeout(2469);
        userdetail.setGenTempOneTimeAuth(1);
        userdetail.setIsDeleted(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("oA15Fgkr68Gx9k8WPW1OQwYUswfdLnt7tV9TEfKG4LxphFycuF");
        Question question = new Question();
        question.setLevelid(6);
        question.setQuestionDetails("Ao9KK4alQnt3l0gBQxnB4xj52k4S6MNeqcz21bbRNn7h0d8IHZ");
        question.setQuestion("rTFSBfmtaWy0naMfwLw2Q2E7qm7pGx9mXZzFK60lhux7o7VHlm");
        question.setQuestionIcon("akjwHboPjlveHEKKagzS0AnJHCiEGkv9A0UCXfmHvYCGMtBf1S");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("9tOy26yeyiqunk6ODrITj8ZQo519HUzBLG28rrLPeew2CNx9O1");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setUserDetail(userdetail);
        listOfPassRecovery.add(passrecovery);
        userdetail.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setUserDetail(userdetail);
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1482410090891l));
        userdata.setOneTimePasswordExpiry(10);
        userdata.setOneTimePassword("HfzIkPkHV5UDnqLNJT2Q3UknxmrzvkIO");
        userdata.setPassword("iPwCbjbadMVABu9BEl2Ytlqg0pOuuRRi8agWivoZj4NATMt5Jq");
        userdata.setLast5Passwords("aqoQ6XALtrUyGt4EIvbUcFbi3yaDj7l2dW2Y0Qtc3jl6LXFNSI");
        userdetail.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        Gender gender = new Gender();
        gender.setGender("wxuRqv2isteC7267ihictoK1n45aHiKlKhuST7BmVDkCIv0HW3");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageDescription("UFII5LA1i78DVeNNLgvkaBxWw8qv03bgPz3EzOUuvVcVgO42aZ");
        language.setLanguageIcon("VjosdFJlmWol3C5ITCPE8z9juuWIhmulK9aOd8bkd5almp7Wqz");
        language.setAlpha2("7l");
        language.setAlpha3("svI");
        language.setLanguageType("TZNTJK5GESKPj9s1EurCPuzQh9sBmFFf");
        language.setAlpha4parentid(5);
        language.setLanguage("VCLBzOLEAmYQWgIKBH8J3MMxPRo77Fcx2R9NMjHiW7QYOGjK5v");
        language.setAlpha4("35SC");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCities("qFh6f3rAQ5hA5ksrdGghoBVcYuij8vzQI0krEh7SxHBKnVJPqW");
        timezone.setGmtLabel("Z4X3ySxvpRnhqG83GjQk53hVsycagDnH2kdhYtGjwRjTDDr6Mz");
        timezone.setTimeZoneLabel("R9F78NihmP0hDgM0hPacQdfbmVB4ZaoiVwBV1iLe9oI6gYkXFh");
        timezone.setUtcdifference(10);
        timezone.setCountry("5AZNAGFDsCC0Mr1SWMN4u38tjrqEhFbnJpczHkH3izzFSzy712");
        Title title = new Title();
        title.setTitles("MZHX1tt9n6wflen7rREQ65Zvv0hp1xQfxow8iDPGMd0jNRs9Pf");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1482410091423l));
        corecontacts.setNativeFirstName("V8xhjaxai28E8yrZPftruCEY08MoKl8G57cEKY4ki6wbZOa3zU");
        corecontacts.setNativeLastName("zuIpHoOYtLjPLEOKEA3765wiIS7KGFn0sqIYVUEVcPsUmfRAnZ");
        corecontacts.setNativeMiddleName("bEFfinlKLPAdVxyXqa2J7Rh5FSxosnKgzy2kC5fpges99o4Wzw");
        corecontacts.setEmailId("rVNwzBNedwfStQfWjLQStx4CbhwzH4MxvMSeRUV8K3p5Rd46EP");
        corecontacts.setNativeTitle("KI7veMxtoZpUgwNR3RTCLvIYKAv8rbUiFfDynIO97in6vq0EaT");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setPhoneNumber("xQAUwPGEVmr2gPBxN6Bm");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("r5B6Y6wxlM0CMTewGlj5JAr5vZnXwLVrtCslXJLHfCb39IgZBa");
        corecontacts.setMiddleName("Bz9ta0bTTFZ4UqoRtcyRsipqfFckDiCwAvmWIVvR8YVxIoH9vy");
        corecontacts.setFirstName("d6RvOc9PbHHxs05ZdLzKVhAHfK6zvycNaJOsCLfbJ19ClCyFIo");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1482410091819l));
        corecontacts.setAge(89);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("WlnM8yaOsa1");
        address.setLongitude("ehmm30Da1PGdNlkh90vt6b9UUznaYz9L8LABpP17CwD9s8uwNF");
        address.setZipcode("d9Lg3v");
        City city = new City();
        city.setCityLatitude(7);
        city.setCityFlag("bl8RHa9ZiuTxo0OMZPUcnA6T9b57UcJ7vzE8DMiPH2qW3YadfM");
        city.setCityLongitude(8);
        State state = new State();
        state.setStateFlag("ESTu6V7MhYFapIHZ3w6FgemoPevtLBnDj2u6R0qCrBUeT0BI5Z");
        state.setStateCodeChar2("qXEMG6unHmmimIs6BTfQwJRJSFGigMRw");
        state.setStateName("TM4gyH34QLN5EwTti3MWGiPuD0PEgdWZo5jkZhRwwLRbahp0X7");
        Country country = new Country();
        country.setCountryName("3f7YX1MMci2461CqA62U4d3TJIqRQJUpGW1BHjZEW7zn3uuabP");
        country.setCurrencySymbol("mLD3wFy4l4uwEs21lkDe66AoTuUoplV8");
        country.setCapital("CSWOFnx0pnkh955IE6ZeAQh7it689MTP");
        country.setCountryCode1("lHV");
        country.setCapitalLongitude(5);
        country.setCurrencyCode("FCX");
        country.setCapitalLatitude(11);
        country.setCountryFlag("B2IQ641s2I1y11dXAdSj353TkfSnu3PoQpv4o4DkRbir3q00QO");
        country.setIsoNumeric(500);
        country.setCurrencyName("64uZC2rSe8DpnfUBm3cUsD9thEBo0TWJY8srAYh9m4Oh9P2onU");
        country.setCountryCode2("gmy");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateFlag("hRMJnOsUTCOmz7AS8IukpXClNCrcfxbrh7LYsGJVSZoYtfngcf");
        state.setStateCodeChar2("VtS56p1bDETWrj7BAOSHRTTBcY0JYLz6");
        state.setStateName("7k7HxtuMuvLK4U3TS2SAtu9DNiLjZLc6GujstWpfhQMWZWCqvk");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(2);
        state.setStateCapital("SqsprI1vzvk6lkMIyb727pTeQKuxXUcTflwfwPPeDPi6b9hgo7");
        state.setStateCapitalLatitude(10);
        state.setStateCodeChar3("hKvpvkSYx0nJs1DwqnO4ouSv0OHijFOp");
        state.setStateCapitalLongitude(6);
        state.setStateDescription("kQNAdfuEPmhsRVEx2YrEdQVCduGPOA64xYoXM0WGUUEzx2UMJK");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLatitude(7);
        city.setCityFlag("10KhuXWXQLruofm2LqNxE4lqGL8W3c79JxyoZSoJu2w5qlsNOh");
        city.setCityLongitude(2);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(2);
        city.setCityCodeChar2("vfQJvaUzQo2tIVZ17BPjCe8H98sX04qw");
        city.setCityName("osMW73n0s3SDcga5hMhd7vfDhofyio3eXlda0jy3ICwholwX2I");
        city.setCityDescription("sGqWZ3BYWUNCb7KGTXQv0Y3jKo4VVXHPiEkKqvFmphJXwEcQkG");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("nydhEFT8o1wOB2hPmMxpKdHIZLq67oUeWSLczbxrBe2Zn7nftv");
        addresstype.setAddressType("XLEEGawUeTq91oa0TcDm53ixZ9ENXhi16fP2l1PyUGlkqgWzhn");
        addresstype.setAddressTypeIcon("CGfxvtJTrh2E3eiJpop5IDZf1xkipwDvjEVdC1mmVEspwrLcos");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddressLabel("FE2VoFLAoZV");
        address.setLongitude("SGNdtgtVk49RwFN5hJqJwpoSMm7pDELEzfNWhIzVvfnspCCSUo");
        address.setZipcode("u8YTCf");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("dzmfmqEAKfAlNqeTNC4RumB5KudnJX448AgyhSPKWBh0WjVpBl");
        address.setLatitude("EkntaZKGowa9ip1FGrV9S875u7lkRcvVM8DXxzfGCaOsgexRhv");
        address.setAddress1("8hFgvtTfyRWbvB2bNeLXmTGO8oY2r8UD54it1rWb8mtLeQaS8i");
        address.setAddress3("1sXvuZdobW1xB0RaivJtn15QqaRmzyJfMSl5dIiQU06S2piRpu");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        userdetail.setUserId(null);
        login.setUserDetail(userdetail);
        login.setLoginId("Zk8nJJ7UHsiPl9RvhRlRFiZU504c9lD8jkkJJqLvZhT0Un75Mg");
        login.setServerAuthText("T8kRCZN8Y4sRegOw");
        login.setIsAuthenticated(true);
        login.setFailedLoginAttempts(7);
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setServerAuthImage("tCCD2AopmGc8ktT16FAfzjaBifydRUc9");
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserDetailPrimaryKey", login.getUserDetail()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserDetailRepository<UserDetail> userdetailRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setLoginId("22fB3fY6a63v7c1A35DqFPXSvSqdoHZ1PkrWCg5iRvGMCgZU79");
            login.setServerAuthText("moOaT1p8qZF1VB3t");
            login.setFailedLoginAttempts(7);
            login.setVersionId(1);
            login.setServerAuthImage("JEq2DwiXXW9hYGPJQ6yfjIa0IX3vpQ4A");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.remove((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.remove((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.remove((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.remove((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.remove((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            titleRepository.remove((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.remove((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.remove((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.remove((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            questionRepository.remove((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.remove((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.remove((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "bdO5G1ZXvTQx52mWGTMeaSNyCHNBdzFAKAVomdpNyNCA3EZ6jmiFnqGworVDOQel52vYXjfYT7ISrV9sFDgEr0AGgbWCinuNrjyIkw7X2pAklewsjwNOhMghkrSnWN44GvGVbIkS9tF0OQLKldXDgCZXvTkv3fauqwUo81KAy2RZqHSilgyvH8iCaKdZ48EbIcWrwy3bv"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "crcVwnocCQ7zvb0liTGZoFEou9zw5OTQB"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "jsS8Nocpf2FA4bGwN"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 13));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
