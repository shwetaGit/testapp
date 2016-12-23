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
        useraccesslevel.setLevelDescription("JLJqJUXYhUP0Btc2rwD5bQwp2u78bWVNzg8GnOEiGuZ59Pndx8");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelName("DmQCHfWEXdV9En67jmpFEcyQtSoX7uBHwpud709c6Q97he3GOL");
        useraccesslevel.setLevelHelp("5RiqDXaMv3wwMEEK8t7M62hqFkiiIIfTyAD360YRipx9zSbFg2");
        useraccesslevel.setLevelIcon("nLkdiHv6EI0looW2oePSGLnlIClqH6oMQb7rPZJbQkEOjj5e23");
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
            useraccesslevel.setLevelDescription("AOyM3hvawVIII1RIgDsnvHM87v04AK6N9VCByjHfrwJQ9P9Nq7");
            useraccesslevel.setUserAccessLevel(69338);
            useraccesslevel.setLevelName("TWrMjQK95YPruaHkffqlr1Fcf0rHll6YBs3kYqjqtOIhRUh7go");
            useraccesslevel.setLevelHelp("9HJVcxG6mrV0dzBfOPe8GjYo2qgpwBfgIFKsNCfdxyUCNfNIdw");
            useraccesslevel.setLevelIcon("6f0pSWdJHNSCDiEf9OH9nUqjED8bITHwlMQTJ8ZCd8ezShKrjc");
            useraccesslevel.setVersionId(1);
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 181598));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "Vr67tSD1hcrkJfbitktdvaSQzSlJYQZLpoB8jAzivNSuwGtjSfPpHo27f02MPGOhjBzZECSFJBuO3XMRZftadO0zX7oQOm6TWoSZGSJ48rmKjDXrN1vKVubtCMSGRf2ZDAUJKo8S2UeQXcRFrSRJv0V9lV6rSNBXhfrBq8QeXHcvr3wwZe6nMSKscZKn8ZiLZQvzKQAoi3i9rMHP7zUXxcQJfP5anCaD3OXdAacpnxi7cPBwKEunQcYOyMsJX4T1o"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "OxqNKLeA1lr6eRQAN9nL2l5h7Rv8pm0r3THukilzaTXqberNPic0YDx5XpOmrpiAaPY0uS2PleeiMi9hR6ctf8fNBhy6q66aEeyt9osLpcsa9YHwRNLVcdXNO7b863bKcmzqafesUNigqKuka4pgVsCs9UMsdalmvS9M5ICGDyJ8V0JrAJYqR38CkI8J86NzzTJZP9yGOpf7wW0XuuWAqVUyiaFHKTgEnKvN87JZOW2EC8P29BFiksdX9T9tbMkXN"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "saMcIAlxSYWESPIcZ4CdjcmD4XQUw89ZdoBD1QbgAco3Xb2baCKqzsPszgvk3m46YuTGUR3OngJgqYGzSXrMxrhsoNCp1Db7vGSp4zuddjyF0TxsVEp0gveAQlhLO4bAElAJ3LyXWhnM9UWk6lCi9LqhmEdVx5eM8ZXrlKDcNSfISoOKREI7YcuKysAkGYoLWA7qEgq8qK8r20RLwFRJXTeQSuFIRu662Lwjt64xTuOMFVat4MheVjWVO8IK225qU"));
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
