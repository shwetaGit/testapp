package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    /**
     * UserAccessLevelRepository Variable
     */
    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("s1wpC0KtT7IkE581zTN2JRjVEnQtM5G2sW4MuqulPZGyzYtU6t");
        useraccesslevel.setLevelDescription("3sOuKJRKOWSzgGALpPp2rKcn42JjWoydDeV0aVswgViCEbL67H");
        useraccesslevel.setLevelIcon("DYpins092ruNClZ61sBMhD1EeWwFT46VyfpRFKozGGVqQVXKvC");
        useraccesslevel.setLevelName("04St8TpbRlR2lQWPe8jTneyJQcJK3ix7zWWUJq3mE9CQxhuhhQ");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelHelp("7gSyW6UFlKzD2s9OSch4WLrOqgso24VgqlQMEGjV6TqnPuSD1N");
            useraccesslevel.setLevelDescription("S3N0EOWbnRNXZPGLvrftQb9IcoulaiQvvpIDcKnXP7hZsT6yVl");
            useraccesslevel.setLevelIcon("ZtWhKc0VtydJMLuJ7dmvBKLnHYYShJsbwwB44tTXYObcZUu6Tb");
            useraccesslevel.setLevelName("o3QCGJnGBdgxwx5A9MI8zT3mcIIiK3QXdkJSrED7AEQ7OEfBJP");
            useraccesslevel.setUserAccessLevel(19684);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.remove((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 177968));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "o82tvuPrW74gpmPMG2BC4E94A6UISfFvgIjuhZZYyxFFgLbBj4QAOaWda0bjxcFq1YQaYBKABjkc9U4wabSl5lrPU0XOoZmQQztvAIoXQyqkYCKPyNicN6PQViKxbz29PTzkEKgx9jPgKqlC4mdXfXaMOuEDY07huP5QtT3iPwsJzbxvbRVmPQyqd1YnKiuCyGq4FU3PV3CeumBa7FtatSEwRqdiLOpYiAFHMjrYfDT1rGyHvAi0vtWN3jUdPmo2y"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "7eSIuCUq8adbCp4Ub95pj4g8vBLWoiBLY9eVoRAINS4FfIEpVsN2XB7r5ZBgd1jIMF8KCA2f1HRRPpVL6xd2CBfqLnCq67PM1WP1YHPaOaEUny4QOjCyw6dsJDR6Jlt2qgeWeiYqZ6UBksAl0m5TZ5VOw83i0BXhgf43WK9F8PZspYTFBu4O6eFjpnNgaHPWzAUSltkf2WVrPdYXpbXRIhpSgL9oTW53PAj2YzXy8CpBz5QUvEt3n6T2N0AZdwUJh"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "gmXOkP0TuK4FKhzFfxesqpV9N2rZazof00IUKFlkQocDvVJddqLBabPx91geuTmpveYOUYZYffIp5gqYpPCJ5KR5D3QHuqi4KrZBkqY0S90hkXRmkmO7E36lSjE6ehDV6J73z2kKOJghSjrzlQFxnwn9PrQVGTxH2zNucg35w96a5yCrl3BPzG4kzcEMy5EzOVQPv0x0v6QINFfeyX0YS7JjQPNGEAB8yNHYLDJtE1Bt1pFq9kK8VzPRsSX2tb5yf"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
