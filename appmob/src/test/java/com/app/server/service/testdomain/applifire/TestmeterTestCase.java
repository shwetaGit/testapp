package com.app.server.service.testdomain.applifire;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.testdomain.applifire.TestmeterRepository;
import com.app.shared.testdomain.applifire.Testmeter;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class TestmeterTestCase extends EntityTestCriteria {

    /**
     * TestmeterRepository Variable
     */
    @Autowired
    private TestmeterRepository<Testmeter> testmeterRepository;

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

    private Testmeter createTestmeter(Boolean isSave) throws Exception {
        Testmeter testmeter = new Testmeter();
        testmeter.setPrice(-7082864154377746432l);
        testmeter.setMetertype("9cKNzwjQw1O7FKlbjAJ7bBAaxS4e1esWquUKkk0xSUbiFtk0Na");
        testmeter.setVendor("9LEXFkQDisuUdOH6W36dgjhR2l8GJGZY3lQQ8f4sF85xGriT3b");
        testmeter.setMeterArea("6PN5KyP89xDXpUuTon0Udk7AT3CC0FC2hKLkxE2pdlqy48KVt8");
        testmeter.setEntityValidator(entityValidator);
        return testmeter;
    }

    @Test
    public void test1Save() {
        try {
            Testmeter testmeter = createTestmeter(true);
            testmeter.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            testmeter.isValid();
            testmeterRepository.save(testmeter);
            map.put("TestmeterPrimaryKey", testmeter._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("TestmeterPrimaryKey"));
            Testmeter testmeter = testmeterRepository.findById((java.lang.String) map.get("TestmeterPrimaryKey"));
            testmeter.setPrice(-1970062493490023424l);
            testmeter.setVersionId(1);
            testmeter.setMetertype("EVUEH2dXIyrZiSPXGXuPkQqD7oIKRjWl8bEd1GSiwU9SmD9boN");
            testmeter.setVendor("pBtvLfzyJhhpccX82UFTnMhvysLLWTSXUKfiKVYATtMzSRwZNK");
            testmeter.setMeterArea("BwPZM8mnUIlDxUJifMrOiL0213GKHY1sefcr1IBhloRFudEyfN");
            testmeter.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            testmeterRepository.update(testmeter);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("TestmeterPrimaryKey"));
            testmeterRepository.findById((java.lang.String) map.get("TestmeterPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("TestmeterPrimaryKey"));
            testmeterRepository.remove((java.lang.String) map.get("TestmeterPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateTestmeter(EntityTestCriteria contraints, Testmeter testmeter) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            testmeter.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            testmeter.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            testmeter.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            testmeterRepository.save(testmeter);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "areaname", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "meterArea", "eRKiihtHNghjHuzrNqnyZd0JqVNnwB1UsmC2U68DegjpLN5WzaqAry2Dvv9k54eReRMUab4lP0TY91c1IQLppeTl5vP2Hw43JTEtsh9vo8045cy0a4VDTF0uVCuQ9T5QiDsWgHwscr9mKXZKitzp9ARgCcaCnz22sNc8CDzmNULzArm3GsmDf58YlIPFJjPk2QIaPLWk84gze0Ojjt86AQSEjf98LdKaqX0t3g4pKPKYPYQcgv6kmjTeg5Dbn1oNz"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "metertype", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "metertype", "UjkMJ3pTNnPuyiC9JIQTYdF393TDepN05vEW49LqPsmiWopx7p6LuuTVU1gT83mcpcSdFgk1eVxzkUJ1HyN3OaRyeqn6LXw0dxAFtnwQcm3WFlmuEhW3tm5b3Df25lkgFZvdGOZnpQahIPmDzxkimZN4CCjrEFWfcIzZqVhUOGRiZ4BFurc1xcjlusCZenDgfXJvSr4eUtcIYlZTtgto6qzezUrkkjz2IT7P1okjgvMPG0QD5m4xyAJdj59N8sKKp"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "vendor", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "vendor", "HsZFyTGSfrChNtSH4lLtPRtrYOsK3C6bXpoCw9Iy0aKTkyNchbwDYMUMJZzniy3lmhWcRI0nYIX3qiKyHsExpw7g1EoTzOEX1JHC8jrGkKDaToex55HX645CGsXyw9yqM2JlTcN1XfZw7aTC4gnXSwPFLVXXxsajkJwx9SzOwcdPHHhOa6mdpa4BjWCrj71kkt3G0DSV3qvq5EBsA3VhlhWwg1iK5Gy1PT8N14pa46tHiDUQzcslZfI1rN5ORHH9L"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 7, "price", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "price", 9223372036854775807l));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Testmeter testmeter = createTestmeter(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = testmeter.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(testmeter, null);
                        validateTestmeter(contraints, testmeter);
                        failureCount++;
                        break;
                    case 2:
                        testmeter.setMeterArea(contraints.getNegativeValue().toString());
                        validateTestmeter(contraints, testmeter);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(testmeter, null);
                        validateTestmeter(contraints, testmeter);
                        failureCount++;
                        break;
                    case 4:
                        testmeter.setMetertype(contraints.getNegativeValue().toString());
                        validateTestmeter(contraints, testmeter);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(testmeter, null);
                        validateTestmeter(contraints, testmeter);
                        failureCount++;
                        break;
                    case 6:
                        testmeter.setVendor(contraints.getNegativeValue().toString());
                        validateTestmeter(contraints, testmeter);
                        failureCount++;
                        break;
                    case 7:
                        field.setAccessible(true);
                        field.set(testmeter, null);
                        validateTestmeter(contraints, testmeter);
                        failureCount++;
                        break;
                    case 8:
                        testmeter.setPrice(Long.valueOf(contraints.getNegativeValue().toString()));
                        validateTestmeter(contraints, testmeter);
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
