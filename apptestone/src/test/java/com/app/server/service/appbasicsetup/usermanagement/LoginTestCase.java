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
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1482410105170l));
        corecontacts.setFirstName("3jTYzzO2lbksZuGvuvuAVaWtYoxmbY2JH2ky9FSXdP3Xxkc2Id");
        corecontacts.setLastName("ceSplMBfPajoNEiVkvWyIeobI6ZDzrMCY5hgJnvIHGpPYLNaXM");
        corecontacts.setNativeLastName("rsuYe52HCgi2wEmolpFFqKbvHempVQsZ4KIlVI9Cvwt5FLsX05");
        Language language = new Language();
        language.setLanguage("XDuxY7T28bsvEx1Wuz4nIIqtrzhCbqfewo3KVcePvucawjDoEN");
        language.setAlpha4("YeLZ");
        language.setLanguageDescription("mFbvOfwLS6iPL7g2zibyoAQx04wOOC72aHI07ksIWBYJzpNCxf");
        language.setAlpha2("PG");
        language.setLanguageType("AcXSk4y0tBXA86N9n0v4bnZNjrY2Pm8k");
        language.setLanguageIcon("D8Zm2au4SuyIiZO6M03JCQ6HUGFIzm59iZ55gy1P5KgitOexBr");
        language.setAlpha3("HPm");
        language.setAlpha4parentid(10);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("oyrV7zBh8rQa1j24qNjp42ABAq811J380vaitEclMTZh48bzdz");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("DLy8NcgG0YkWNC7WFs2q5tvUGm9wvec9OnqhA1EjNJornkmDgp");
        timezone.setCountry("zWPrpFeHqZoJkb4ThmZjTgh7oUJtF4CBUZgGSOVX386UeSPhTF");
        timezone.setUtcdifference(2);
        timezone.setCities("Q7n0zDSF04U80j8byCS8wcoe2yBecbV8irUBl7nFi0mcDcdB3n");
        timezone.setTimeZoneLabel("pAncZPmpjFFh4LF6ZmCGuI24u1mDtBhdlLn3WBq2Fcq1Mhh3ps");
        Title title = new Title();
        title.setTitles("Ifq2I8MquAwmmzc02rz9lTbmA2srEjbQPd5tDf1lG6IyxevDSK");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1482410105255l));
        corecontacts.setFirstName("srurevT0P9qWmzCvcgQabUPTuhRjx2OFvyjxEVUv8gL8O7gdwL");
        corecontacts.setLastName("H2SnemIe8uhfeoNvUpmJrowEum5PRVjPB2qOb7BzwgymwMk9h0");
        corecontacts.setNativeLastName("AAKSzlLicb6gUYHAIEDyHbnbRvZ5wrO9kaTzg8nFWlDNpa095z");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeFirstName("anwYgychu12U02Vw9oWaDm93kkD1UfNUDx2M7LMgIUxPumlBXe");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setEmailId("o0T4nwWp3JmPsRnL71180UtAaZHDCzEdcQ2zrb0loGMnObr30O");
        corecontacts.setMiddleName("QV41PBrKue2RW2deAWksfE0nZLL4QuZgLcQmDgNopQxfHWqpje");
        corecontacts.setAge(49);
        corecontacts.setNativeMiddleName("SWSoxiJGtnXsp0u5Cfv7F1veiizkheRw1AxyGt8p4sAPriJzWq");
        corecontacts.setPhoneNumber("n385CT1FihpbxyLgxPvh");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1482410105787l));
        corecontacts.setNativeTitle("oDzQcCoQEQxLsDH8yTskVFRBiK3zKenHsFkF6vlv5mJkPlh57m");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("4ZJj1ywQMID");
        address.setAddress3("1ROEI4FOj1smocaZyBFN8ywxJjoqqK5wqyJUJDhAErO2XwSd4d");
        address.setAddress1("6C0R9MXoC6Gs9ylDi1939lsYIrb0LYayOXSLeQLW0ZuSGDdyXk");
        State state = new State();
        state.setStateName("tIiU40EcC0TYQ5Vgb3COpf3CVg6LSaELMMp8WmMYS322PbZfB5");
        state.setStateCapitalLongitude(11);
        state.setStateCodeChar2("UfnclKCxxlSvYMFt2vnh9eBjlqL3Wbkc");
        state.setStateFlag("l3HFS5jDeqepW9OEBXxbK7TRCVZFA9R3wOV25DG5qXz84GfSoh");
        state.setStateDescription("FWla4JyCGx76ekLvueJXjufJh61xySAaSEoGKCCfVB4thiqgG3");
        state.setStateCapitalLatitude(3);
        state.setStateCodeChar3("ezqVyvJXgZF4kgMdxCBfQDltgkc2Afst");
        Country country = new Country();
        country.setCurrencyCode("ryc");
        country.setCurrencyName("foqt6IT82dJqbiicHkcoOFrKuKRBeTjVzP41L2WC1LcHS30kUu");
        country.setCapitalLongitude(7);
        country.setCurrencySymbol("CKxfGfMN9HjiGWbRRfICKULGSKAZXFdX");
        country.setCapitalLatitude(4);
        country.setCountryFlag("nRjyo4daBRkDT2cUf3xnDC012ncFPecHiEoDnZtb01wpCh46yG");
        country.setIsoNumeric(154);
        country.setCountryCode2("ygj");
        country.setCapital("CFVKUmrFedQ9QHxVG0SFofrmvH1zfsFF");
        country.setCountryCode1("NEY");
        country.setCountryName("AgKwF3dMpJCDhKV5fEMdJQELsKRLtrb6wiD2fx6j1LCY1r5uC8");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("gTnwQT3C1qbHIoNvSOfOhbsKPfaGgGWv8T4QhaCHEUecXlSovm");
        state.setStateCapitalLongitude(5);
        state.setStateCodeChar2("KdRz2wfIPT2bbfax7YXxCIpQrC72wLGF");
        state.setStateFlag("Fn0gWHDDsBkIu8EcIGVqMBBWKMZyAoi3rQv7A3pTrqPriZSVa4");
        state.setStateDescription("8yNObo8uADONcJkvbSkGsRRpgUSPGCJTKaglh8Tb0ZtinLn51I");
        state.setStateCapitalLatitude(3);
        state.setStateCodeChar3("Jrhfi57tdLGF8NGhMxRdJlw1LJajP6pU");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("MjBCFOq67GPwljKMqgZZiBLBCZNkdvNBnrFD3cnUdDNxCjxNsu");
        state.setStateCode(1);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityDescription("08uKccOozazlEZolE8tSSAMs3w3JBtY0Oro9IgJQdSOY2lIQBa");
        city.setCityCode(2);
        city.setCityLongitude(7);
        city.setCityName("fNaBSmdV6D4rizvNfav9EBpJzkl8HDDlmT8gRYVAC3G0wlsGEH");
        city.setCityDescription("ucEUm1ZDGJ2yORLnCWl81QpDPdL1KhaPoyso85G8mJw2Axyn2H");
        city.setCityCode(2);
        city.setCityLongitude(1);
        city.setCityName("WHqVhpWIz9UWXzDi8VVanKexBvJ5c1Syi3CSJCSSt8b8N98HBR");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("14N9yqc3pFQgmHEzj9xTMbQgTIZxB6eRWGpmh5hYajhNgbwtAt");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("aQ6iOB2cNEt2nqn6OylJvwVDmcwhU1au");
        city.setCityLatitude(5);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("4NR5ZaVI497AiOiYi3VUEhJ1Fqy8oA6VXYnEWtQI7Kh4rVJUUh");
        addresstype.setAddressType("E6v2T5ls5Gu3c3yzpUD9Ok2CmfWzVaFV6GZP7TX0s73apgxQQW");
        addresstype.setAddressTypeDesc("qdV7GhnHYz2Eog3oKC9R4cxlFR1gPWERRDn4NzxF1I7M6Y9WQ6");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddressLabel("lWsOL2C03Lw");
        address.setAddress3("NCz4W1GliQpp9rRUUgXVpfXZFfX2yMPNtukgnKmuSmHah5cLra");
        address.setAddress1("hqTuqmcvo0ZDT2TaHUKy81X6fDf3zCghraF8goyfU0mTIciaGp");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("fOycw8eB718UT5GtO76HVMSC95cg5TnJUuyE8did3ovBKEmKaH");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("5Jn97S");
        address.setAddress2("8TblmuHAUtBhhuE5B4wQJqqet19v00mZEaB3Jyl3lnNvIGBuQp");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("NCcDoxT1IYaVh1XJFgR2Gkl4PVyj5KJq3qCJhRxMAgyn9ZUoYO");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        UserDetail userdetail = new UserDetail();
        userdetail.setUserAccessCode(31951);
        userdetail.setAuthExpiryDate(new java.sql.Timestamp(1482410106932l));
        userdetail.setIsLocked(1);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1482410106932l));
        userdetail.setIsDeleted(1);
        userdetail.setAllowMultipleLogin(1);
        userdetail.setChangeAuthNextLogin(1);
        userdetail.setSessionTimeout(18);
        userdetail.setGenTempOneTimeAuth(1);
        userdetail.setMultiFactorAuthEnabled(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("KVckcJ1Pmpq1ysmak27eTYHEvxHePfGilOFgQsvGmGFACAmuRX");
        useraccessdomain.setDomainIcon("0PL16aixv7x9RpxkRV5lWeLgHI8xXCA74LpI331ncMRCVVnbyT");
        useraccessdomain.setDomainHelp("nQfM4QGMaEzqUIRfUdl37cHOAn9V4bNzfAAOymRPunB7fNZsM2");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("66srYdBBuh4N4BPAxxyVRmZg23OEHfnvUy6QO2OQRA2QtIfS2D");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("1tF58uWwZsLPDSEyVlMHrsymU89nIAbCHjosUuHm35Y8cVwIy9");
        useraccesslevel.setLevelDescription("HyUeiy5MHqYo7AS4Fy2y24AOs1K5ECBK4Os5ILG00WiJ4Df75P");
        useraccesslevel.setLevelIcon("MYLIN6aQiOEAfXNV8lpmo4pfZTDxiNDrA2PW5I7pK4bX1wlZRe");
        useraccesslevel.setLevelName("EBITTgyMsxH1pW287oXJitN8cE9kNL346LtPklKYcqyi7Vxkol");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        userdetail.setUserAccessCode(21829);
        userdetail.setAuthExpiryDate(new java.sql.Timestamp(1482410107020l));
        userdetail.setIsLocked(1);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1482410107020l));
        userdetail.setIsDeleted(1);
        userdetail.setAllowMultipleLogin(1);
        userdetail.setChangeAuthNextLogin(1);
        userdetail.setSessionTimeout(151);
        userdetail.setGenTempOneTimeAuth(1);
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setAuthAlgo("aWeQJ87Po4DlD3s1dw0PCwQ6Ae3AnUN0F5F9sa2g3a3yG9Cipt");
        userdetail.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestion("pMotfg6fHsie4hFuIf3tpPpA8L57Bwn4baVJmxCQTfZK0YcxrM");
        question.setQuestionDetails("X1xhPeO5zK9MbO0S0fVZuUwjybamY32LKqa5XwPbkCXScJPPBe");
        question.setLevelid(8);
        question.setQuestionIcon("TJPQYWUWyxaWqv0zltWBtXEYXetVHpoJJF8QLjN9MmVEkNPrp2");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        passrecovery.setAnswer("Yc50PfGBvzdH6daGor6Dvg361ub4Te11eHUWHFAJ1XvF9Dfv6y");
        passrecovery.setUserDetail(userdetail);
        listOfPassRecovery.add(passrecovery);
        userdetail.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1482410107931l));
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1482410108015l));
        userdata.setUserDetail(userdetail);
        userdata.setPassword("IzzOl7jRsvfAlwbloud0r9KWTyEIBeE1gybKW8iJzZBlPryueW");
        userdata.setOneTimePassword("50jCUHEmCoISHSB4c2nZ6Zx9n7PCAerX");
        userdata.setLast5Passwords("EY9WCY4UsswuJm5lXuAAj0BYnCCCWl2iRVH8z4VVVSSYE8l2cZ");
        userdata.setOneTimePasswordExpiry(8);
        userdetail.setUserData(userdata);
        Login login = new Login();
        login.setServerAuthText("eCM0Rrfx3bG0GEuY");
        login.setLoginId("0msg7b5WgkfFPjrRfRwK3wgLidhue1MWJesFSW699RTE95wrVa");
        login.setServerAuthImage("AMzUK2CfW9VqehI3IyM5H5EJG7xT5ZnL");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setIsAuthenticated(true);
        userdetail.setUserId(null);
        login.setUserDetail(userdetail);
        login.setFailedLoginAttempts(6);
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
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
            map.put("UserDetailPrimaryKey", login.getUserDetail()._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    @Autowired
    private UserDetailRepository<UserDetail> userdetailRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthText("DgQAfH1qKaOnZwvZ");
            login.setVersionId(1);
            login.setLoginId("bNMDtot2Ieom7ib7zFfBrlOiPLTsWG3e2tmE5ivqPMpMUS7ue2");
            login.setServerAuthImage("fgSYUyOcyjj5HJkiBrWMn3LConJ0sfoX");
            login.setFailedLoginAttempts(8);
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
            questionRepository.remove((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.remove((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.remove((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "hKofVyNl0bDS8bZQK1jDNOE5nE4t83xQKd3oDr4SkGkWqQ6xenYGVlRQfS9b1GZdOOh2WVU14w8M7aJD2RKyBB03TJ8NNKHOCAS3rmZrtfOpUR2Qva0G08rzNxV1EYgFDfEsET1xivhenEyATcUxIms3kfLYtRY3Ez5Q3Zjb8IGgDkDD3FXXRgfZOY77inP6sl3Gk9h8U"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "FpaTtjKfAy97excQBYTJKB3bIELzWduXZ"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "J8nf2Gv0TzUh3WEgc"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 17));
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
