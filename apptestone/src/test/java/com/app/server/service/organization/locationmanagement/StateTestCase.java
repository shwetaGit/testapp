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
        country.setCurrencyCode("ybr");
        country.setCurrencyName("PdV5YtfvmOxS2sUA0MXKtzoOZwnmhLCtWTLorvLeufQr1gnZP1");
        country.setCapitalLongitude(2);
        country.setCurrencySymbol("PXGYoO76oTy0iFM6LWg8oBzlTl5ICYnu");
        country.setCapitalLatitude(4);
        country.setCountryFlag("EAPB2hBtb5tX6fysAZTmFqCkGpxlnm3hbVCfFkQaWg0J6G3ouh");
        country.setIsoNumeric(399);
        country.setCountryCode2("ZeK");
        country.setCapital("PL1kh9wIudlAIIlcAxXGgYLFiSeXxtds");
        country.setCountryCode1("yNm");
        country.setCountryName("HVDpEV6UKA0VjKTbYbJQny04a51W79YcSBSaWmA4HSuGYvJdIl");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("Y1b5Xa8vPHldYThYiY3SbIliZ5eMCkfPu3eHQk6n2cUUBJtuL6");
        state.setStateCapitalLongitude(10);
        state.setStateCodeChar2("2mbQbelTL7yrcSmzQOFIdEt2oS98PCLl");
        state.setStateFlag("HtnR0ST9AaxflfHASYIY3TCeFlGoqZZHykSBgz3yx1ODWeaTQh");
        state.setStateDescription("wWTJy1hpSbJAbCPpcCYHpGYU1rePPzqc3cC68ukoaQttSFCWXM");
        state.setStateCapitalLatitude(2);
        state.setStateCodeChar3("qO64Gd0EZneKP6fGCTpkpZLXHSv68wDb");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCapital("6pvD0wnjHqVMOitUU1AV21pLQkwjYz2vvDAlFGdqBe0x6CEdcD");
        state.setStateCode(1);
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
            state.setStateName("CX3zFB2R4NxFndIAQRauqfXSu8y28Np7WnU1LwoRaGwv7lJDb8");
            state.setStateCapitalLongitude(5);
            state.setStateCodeChar2("CmwLDDNVI3W4fZ6V4ZkMGSgSGbrkEq2F");
            state.setStateFlag("FHi4lG8svgi8mRSGIXZsgzjr1chcQZ4q6znZ1SCUcXuFEdxBVT");
            state.setStateDescription("y7mKsAKWbIruNYTGvGBAbNG5R7h0Ym2XPei8QDxBGID3G6ThQs");
            state.setStateCapitalLatitude(8);
            state.setStateCodeChar3("uYDLIpX13VZ0oPSpRspIv7zsKmvU0Edd");
            state.setStateCapital("9dWrKfkpIEgGwce4cNfJH36PBCR7xsAvymSo42tzbPzAHIqNlZ");
            state.setVersionId(1);
            state.setStateCode(2);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "NVERHOeCtKdJGVSgP5LafUbPSNZHI5ljYnyEjmH6FvHDD7b4wuLMmR6C7cN7WomrRWtdQU0AE7QSNrnny6uFoItnv7D0uyaTi4s5cZ9v2TdbjprZbAPiWUntKZult8ObTgcJ2gtZzH1ejWjHD4IzsI8oLhOqAweEpPsL11Q9Cq7CqQkgY1CTWTHRZziDBIjuEmb4LQVouPUVVVeui86w4VYaS6SMqYbZhDzEAARmwnfBIy7DVcpQqcLzdZT5SVA4p"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "qWLRqkY4b3h5f5cM5L5B8PUcBlYuueQOr"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "5xi25o6Zyb2nKndbu5Qtk5Ugv20wpt3gg"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "Z1x58BtTTgWGhxbbxY0DNikg70b51YOQRTq47W0mX4hlfLRnH8GkGDrz3ePP8qAJQvKwmkdAFYR01bFJnQTrZmEBAxMF74kwB9p5TJjTJwvnSet8nK1E51PcIgL8J47FLLX5LdsAjKxZeqYmxfQVVKAYuhZYA2B7fmghpXluGzxyZVXdSXOb0F8UioN3edNsX8NYAx7q6OSAaJJkaYU4IZWSiWJwEymyYuIAlOKIZzyaWg0GTVYhoAXAiObNKdpCy"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "IpaPBvAU2vUxY7uVpeYtBQxQLZTc3TJdOHVQ2lfKOC3YcrYqOj1LVHRx6dP84A8glkt9Cd4nGDKIFGBMCxL94HBtMUB9uAAdGK1TP1KsAeBokHx8LdE9aXxkVTRxRaPPT"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "VDLA96ZILqzLhSyqdlKSv0u2F9PWzlT2QqKY4cWXE7Gkf3a0xjgZ52F7s16se5Mmu6e9AUIxAqFqVY2JFqtV40yAwxZE8GltSwonh1LaunJXhDMpXjdpEnFr3NERn5OiM"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 19));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 12));
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
