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
        useraccessdomain.setDomainIcon("3TtDjVLMYSJjZsLO7HHLMpKMQBiP5Dk10TkrhkIAU3sUDvNvc9");
        useraccessdomain.setDomainHelp("9GAZNxftot02CuA9XLHy3GrMWewXQODMs1EaiatjiTlath7yV6");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainDescription("bOV9FWf9cYVSSbvTtgsRl8kG11r8g9inMPS5OVgNsWuDbpPZNa");
        useraccessdomain.setDomainName("ucDsgmANdD0imCjftpNGQXWRk0d6rP3gpTtPYHGSMJY0zDiVP6");
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
            useraccessdomain.setDomainIcon("SN3AEhbkJkqHb6ENQUPi8Ck3GzAJmqJAn9AYv61psgIqlq3CpA");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainHelp("9RSNxzsWHO8e2CjK33HKgWY12RfYZdaK8lmfxC0NGu1x1epXIa");
            useraccessdomain.setUserAccessDomain(99087);
            useraccessdomain.setDomainDescription("MvklAauUpwTpy362wCFJtXxGnDvQcwbu77yqA4itq8C2EqdUbK");
            useraccessdomain.setDomainName("eob8EnyOO26yjv4HSKprJheiTgZiCyNtgleX0rIIDemzw3nVpS");
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 101181));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "CZIiWQrISvZGiwzTjGgSMjp4h4CNioqUjLdIUminJmk3hPChJmO0UeJXrCHveoo3yaHEyI5mrxPoSt5BbSfeKasadiRvO4QSzrOe7h5Do1mHN50KJLslFhYecpzwbCCLMujElLx6xI4c95Z7Yi91v4ejRMLxPGHnfmc6OFwCpxRVemlcDuxSS1Jt863k7M4G0U4S01iIkiJbGpELikPLkEcfDsZb0FHg0TKWWCU8PQ8HQqxDUI1tU2ELTvXk2yPRC"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "T0LwizwdR0LY5Bl443o7lFKaG3Alx8bcU898rgyFkEB4SZVjdpilsk3K2Xiwudwp3z43LAqV6w6mxxZfv1P0DSQN3M6WtI99a4pvRxoikcudKw0pUG0UQ4IBGGqWu79RzXgysyTz9gZTTCfGpoqf3pn6RT5dwPAyMrDTGgwYO4EMdn94UFNvupraySkSkM8q35a4dEFISkSxZ8MxAJfLJL3tGhD6MpzSjDR7KZkCOCbEpm0Mwx1aQo4bztEL70zjq"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "B0zOHX4XIV3E0fYdioDXFIP2nkWS9eARljLsXKDwcxDf2xtkYv89MGDj1wz8gXSf4nuxMvhEogXZrwfv9oNuzmRIQANI3XYzOV9Km3Yd5yZHOmNaL5AZ8sHvClefZDDFyvrQ1S16tQs2xagLdPKh5wd3dTCE5h3w6J8TQKotaa0lZ7VnAVfbgfZM9olil0XO2hjy0p6A1whqzMnoQcmIckqY4K9i9dfOBnknjIV1lvPQhVRKdSSJXDdnb7UPmLE5S"));
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
