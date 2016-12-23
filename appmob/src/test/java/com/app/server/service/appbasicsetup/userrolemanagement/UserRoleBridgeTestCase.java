package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.UserRoleBridgeRepository;
import com.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge;
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
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
import com.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
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
public class UserRoleBridgeTestCase extends EntityTestCriteria {

    /**
     * UserRoleBridgeRepository Variable
     */
    @Autowired
    private UserRoleBridgeRepository<UserRoleBridge> userrolebridgeRepository;

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

    private UserRoleBridge createUserRoleBridge(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleHelp("LwCA5KVIHnp48LiMh8qfkPsOewQn65y5KGoXGdcrqz4Q3BooJJ");
        roles.setRoleName("cjknqfbXv7YiprHfm8cb2Svz9IW8veClkflWCWQlyal2fTdn8a");
        roles.setRoleDescription("zIEKZr3KHsLKcZkfWU3H9IhVtRwDAdAd0MoyLjbz3PjTxcvT41");
        roles.setRoleIcon("DR6xM8gN7oGWVgBEAzLcmMAEefd6HRVwjQx8UD2sJnAgzhq7bG");
        Roles RolesTest = new Roles();
        if (isSave) {
            RolesTest = rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        }
        UserDetail userdetail = new UserDetail();
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setAuthExpiryDate(new java.sql.Timestamp(1482410099827l));
        userdetail.setUserAccessCode(3432);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1482410099827l));
        userdetail.setAuthAlgo("Da72Iqe2fwiA03OBfpOtEGxqS4yGnWE6eGCKljzlXJClfJbnVh");
        userdetail.setIsLocked(1);
        userdetail.setChangeAuthNextLogin(1);
        userdetail.setAllowMultipleLogin(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainIcon("nlfuOqFnUcav5tTyywrWwOZcGVK0YSZ5HFylcQTKESb5pgYuai");
        useraccessdomain.setDomainHelp("YiEWy70PwOnjtyHRCPFLqQjmeGKMOukIQsFRYcj8BHfkk0vlwr");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("NOGvamtSK9f3Uta9DE33a782OAmtFFvaOctggSnj0vdJfSwG4j");
        useraccessdomain.setDomainName("NOsjfVDlbPjAHlW8jWH6DQLckYyoGKO6hbIunYAhCcIhp0VDNv");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("CCwOtZ9joTu0GFYrVShHaO1jlo4r0aPtxeQv6HiE43rZKKuHh5");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelName("FmEwCGAl8uCEfZHvZDu89gfffiAxtRgwOnFD9J7UGBlt02QfjX");
        useraccesslevel.setLevelHelp("dAyPgXuIEYy8iedRt9WmSCWFwmbrFW5yE6lBnESETW1R1a2qmU");
        useraccesslevel.setLevelIcon("ZZWPH6d3hiSY6HFAZOaUJntOUwMWhF5zJAKnY4wexAaJpQFM2t");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setAuthExpiryDate(new java.sql.Timestamp(1482410099864l));
        userdetail.setUserAccessCode(43640);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1482410099864l));
        userdetail.setAuthAlgo("raRh5uXRo3X2gJMvJ3lQ5UIV3mBAZ3eytHcQl2WAZKSCg2RLKi");
        userdetail.setIsLocked(1);
        userdetail.setChangeAuthNextLogin(1);
        userdetail.setAllowMultipleLogin(1);
        userdetail.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setSessionTimeout(877);
        userdetail.setGenTempOneTimeAuth(1);
        userdetail.setIsDeleted(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("gT3bQqsezZyQG967utFErYZP2wzffTGVhmYHg8Z5zPlaLOcdgW");
        Question question = new Question();
        question.setLevelid(5);
        question.setQuestionDetails("uOw76qv8Vv0uozR8HyqNsBx2YirypndhNgTn3cIjNTOZMv9rcf");
        question.setQuestion("BojDqIW0kUjc5XqhwiEMLDbThfNVZgNEaDCGwpczJQfqxSjoxs");
        question.setQuestionIcon("fVWyt1p1Ozpe0RwUEWsItMqzs26gqwVlZ5IX0DZT1WZEqOfxwl");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("nTOeZKKT6r5WDceqK5fYvtP04ei9y4LUvyxq1LJ32Wfxqz8U5W");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setUserDetail(userdetail);
        listOfPassRecovery.add(passrecovery);
        userdetail.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setUserDetail(userdetail);
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1482410100894l));
        userdata.setOneTimePasswordExpiry(6);
        userdata.setOneTimePassword("xNacBqEZNxUE7puzME6Rdj3atMZuUohc");
        userdata.setPassword("kCv5KCKZTXDFJa8n7Ic2LbvmVsdhcVxKJJzOrvputE5PE3UDAa");
        userdata.setLast5Passwords("ViqIH6epQGVrFq5tFtkpwRiEKZgC53PL8QJ0MhgQqNsSgO3Sdo");
        userdetail.setUserData(userdata);
        UserDetail UserDetailTest = new UserDetail();
        if (isSave) {
            UserDetailTest = userdetailRepository.save(userdetail);
            map.put("UserDetailPrimaryKey", userdetail._getPrimarykey());
        }
        UserRoleBridge userrolebridge = new UserRoleBridge();
        userrolebridge.setRoleId((java.lang.String) RolesTest._getPrimarykey()); /* ******Adding refrenced table data */
        userrolebridge.setUserId((java.lang.String) UserDetailTest._getPrimarykey());
        userrolebridge.setEntityValidator(entityValidator);
        return userrolebridge;
    }

    @Test
    public void test1Save() {
        try {
            UserRoleBridge userrolebridge = createUserRoleBridge(true);
            userrolebridge.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            userrolebridge.isValid();
            userrolebridgeRepository.save(userrolebridge);
            map.put("UserRoleBridgePrimaryKey", userrolebridge._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private RolesRepository<Roles> rolesRepository;

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
            Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            UserRoleBridge userrolebridge = userrolebridgeRepository.findById((java.lang.String) map.get("UserRoleBridgePrimaryKey"));
            userrolebridge.setVersionId(1);
            userrolebridge.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            userrolebridgeRepository.update(userrolebridge);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByroleId() {
        try {
            java.util.List<UserRoleBridge> listofroleId = userrolebridgeRepository.findByRoleId((java.lang.String) map.get("RolesPrimaryKey"));
            if (listofroleId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            userrolebridgeRepository.findById((java.lang.String) map.get("UserRoleBridgePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByuserId() {
        try {
            java.util.List<UserRoleBridge> listofuserId = userrolebridgeRepository.findByUserId((java.lang.String) map.get("UserDetailPrimaryKey"));
            if (listofuserId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            userrolebridgeRepository.remove((java.lang.String) map.get("UserRoleBridgePrimaryKey")); /* Deleting refrenced data */
            userdetailRepository.remove((java.lang.String) map.get("UserDetailPrimaryKey")); /* Deleting refrenced data */
            questionRepository.remove((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.remove((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.remove((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            rolesRepository.remove((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserRoleBridge(EntityTestCriteria contraints, UserRoleBridge userrolebridge) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            userrolebridgeRepository.save(userrolebridge);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
    }
}
