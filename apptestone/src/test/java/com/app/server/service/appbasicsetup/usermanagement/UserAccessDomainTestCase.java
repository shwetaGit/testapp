package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    /**
     * UserAccessDomainRepository Variable
     */
    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("QJTlWCT7z0mVDwnUhuMrK5wfAKklBYBOvSfpTviCpLqM1UwySV");
        useraccessdomain.setDomainIcon("ZIepTR4ca8smsqYJd82dTUwB9o2q3vY4dukOEyND6hPnnQveM8");
        useraccessdomain.setDomainHelp("6tA4IdFXiUI6HlkvoTujTLnQ2GEcSa4XoQf6NL22g8M678jh1S");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("7YiDCnhCFPAMrphJMbNZ4WCOGFf9T6N9KUokpAAN1eDgmsUWAD");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainName("p3dp8x1lHhHvhrdx2zCyZ6dBN6ZgMaxuROx8Ekwca6hbTFAGpr");
            useraccessdomain.setDomainIcon("FXMUBLXpqMLx9UbcNC6lAnBFWoBKBUseVAG0MgR3hMoobPQgJT");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainHelp("sAy0oAOL7EdvjxKhjGDi1dceSaa61Dgn2yjTkyZXva0O4zT5ux");
            useraccessdomain.setUserAccessDomain(71156);
            useraccessdomain.setDomainDescription("asZ9zrzPmEMhOqKYSLTgbwo3hQGtKtJMq5zQjj5GqFrzqkJUkP");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.remove((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 185894));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "iTval1J1mWYtQLCpRSKjAVobDGVnux1wTQxGc3rgxleVBSkqvINlhJ3Tx5QVTC22vfljzh2kyLR7tprSnMQ52uxGA6YvqdcDtzHXC3CLvVyiUgHz1d5ELZMlriNw7GtypsNqzLUdBsH7cfdPCWZvV9aqiFKByQfAIzI2DbbZ7CvQ8EdrlGeIGe41OOHjEozER7qGIP0obMOHoXf6OGq5kUwV64qRVMTqFWBfjFESCKoD826Kwt9xcDp2oLx5TiMyU"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "f9nKX9oFHwz6S4jd6zwsXuDn3993wF11aAGjv4hGbVhtskBWmWqUX2doJAUznvOrZ6x2BrJcFNFkqAwScQ0Py7iRh1Rt9kJgBBcLnsCAohDkjxa6qytHKcxFTl0E73u3v2NJX11SxNKh5uSeVWx4PIBzSVNi3wWFG5D3JQHnraRxHpzNdAa0v2Aji2XBeTWKzULrgIwoXuiI7X3Gr6vIdt0yCsQGY85LHy3tWjxtQKfliNG3x6EOVQGb40crBOVZn"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "wOFdwlT44escWTos9kfQsYeWFMEq6Vzw3AEP7rdRGgv78pwcRA7lcMHcdRdRh0iTlleFEQft6ydLLRtJGPFS5gKN6vhU8AcTW9XchjGO8qaChKOBryEK8ggZJPghs1cTxMD2kSzJb0GRQlhY5uEoudRhKayHEQWPDnHaxxPwPmpdzoSgSsGTCmVczYHmYILgK8g94POpQJHxPgwy4ZWnGUq6Axh1jPzxIdS5TdPwV2Iermk0nGu5W9iBkXsW4kJq6"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
