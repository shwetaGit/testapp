package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    /**
     * StateRepository Variable
     */
    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCountryName("NElx23qwNGa8TJPVwNfH0AQVsndy4K0FNXUJOd3fiNGrDwLYFC");
        country.setCurrencySymbol("bMVZTinooUCSa7nccRZxhpfda3Un0AaO");
        country.setCapital("vBdK27tXd44jaafi5AsTPdh2ZjFSRv7y");
        country.setCountryCode1("lr7");
        country.setCapitalLongitude(10);
        country.setCurrencyCode("asa");
        country.setCapitalLatitude(3);
        country.setCountryFlag("Sa2tyWFcwkgiXCqSPAsf83YzdmdR66HfGjw5Hvu3wV2MGuHi6u");
        country.setIsoNumeric(832);
        country.setCurrencyName("P49wSUmsYAcNUjBBKoWGAu7wFtz2HCd8fR33m51EoYzCp21XcJ");
        country.setCountryCode2("KXw");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateFlag("w1SedutizBV7j84NM5DUpXOnlAjJSNkITzzYhSbf2JUGyzHn2A");
        state.setStateCodeChar2("NRrnEPjuswcfqwtHVICowtirHjJaL1Bj");
        state.setStateName("M8TsuCegNZErMd3Q60fIugKmvufJIzfA933aE1ozz8aWbTM9RU");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCode(2);
        state.setStateCapital("pAScxkHLQjcdAl8VKTHekin10skKypXYgb6VHfxqOmZ9c7jVjp");
        state.setStateCapitalLatitude(4);
        state.setStateCodeChar3("wLF07o8FeOuJFVDK8w3ducNAbHpWOuyR");
        state.setStateCapitalLongitude(1);
        state.setStateDescription("UXUDtlFtLYSCwjq3WRotSBD4SnaJurdPdpmW1nTRBIU88Bak5o");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateFlag("qjXWberxRgQM9v3WbiQVRWv8ThXk9Jjtql8BomG8eLOROZWsaL");
            state.setStateCodeChar2("JGFzAJPyGtt8VE5XsOPD1lQF7K6giB8T");
            state.setStateName("0HTQzeUpzaGAT17XsHZHpWxJbxR6oZ0b6DKrsJa5KW4Onihlg3");
            state.setStateCode(1);
            state.setStateCapital("CwyIeJVhvWQi9SExgh5FdL76vZ7xIQXQnR2YXm26tYS7VC6utn");
            state.setStateCapitalLatitude(10);
            state.setStateCodeChar3("yValqfvAdCpHosyT8qNjBu98T5XkBI7x");
            state.setVersionId(1);
            state.setStateCapitalLongitude(6);
            state.setStateDescription("stPSJ2J6EhBMd8fWSJ5kRSx1nq09i6yP7ijkAsL9D2i1jlU4eF");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.remove((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.remove((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "BrqvhEBt3FH4miFCNHd8zYQGhPRe8wU8IV05Ld1RROXFLmlBejG5M3dHUR4gHRHOTj6ryuZgZT1f63l9TSnmY4S0t4EYWbNWzxXl5wKNYnfJpCFeA0C43Yb1QyfLaGqKnOYZSFUkMeSfqUJBK4dk5zXRFrtawJ1HJvW6WXhd8MrPeB4Q6hSXAgUYFsb2PeynwSDbyBloxCT45BBlpmf8aYxy4afhHRL26zrVcAkfJpSz6lTCgrv5FES1xLnqHxlUn"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "zxRxer4BKlFc72k3C4hSPv3V9CDFtgsdT"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "3OW3xb5FpFT2GJv7Yixi3sTlV3jz02iZL"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "vPtaiHoWWJ4XRVhqHmfcoLIrUqNiDXbpvE5whFCrfJnoAWV7QhMgN6HInUCc18glzI07ILEuy7EucH81iIxXLW2FhooC20zdicsF5Uq4rhgQKgKDyUGqKhZHxfPBDMIrXK1JiCHJH3Vqus5SdxrBuTn2of2RQFMi4nWDjgcJh7PsxcXPGtCACb7RT8OkGecnefiBIDQM8RjuSvKjuaekx1Zxtfhf05WJ1az4aWmZi9FCZoI0UUD1F5DLTQHCQVLN6"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "9Jm1crhUWbhhTMA1uDLVQDDkHZr53b19GSK8NYDZqqn7rjYsOl2B9l7FabMYDs1xlj6HwQDSDFidINKwh9FraFmZlwdlUSbKcBW3QN7x4Cj7auwcAxJrHySzY5jYmz3eD"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "kSWk9eBEZDzRTa2CDXQbvV00LfJ2RtkFS6ArIUsDxGJ7DlqlBNKgSDyTHRmqfHzputXdrBBKaN3Hv4N05x0g3sahUzwCQsKzjWhfpZj1wd9TpiD1mrAgayyarD05Asoe1"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 19));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 22));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
