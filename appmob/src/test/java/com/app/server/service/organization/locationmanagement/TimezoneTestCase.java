package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.locationmanagement.Timezone;
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
public class TimezoneTestCase extends EntityTestCriteria {

    /**
     * TimezoneRepository Variable
     */
    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

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

    private Timezone createTimezone(Boolean isSave) throws Exception {
        Timezone timezone = new Timezone();
        timezone.setCities("Ntq96q3H0JMBcvl1i9nkGoPZwwbHTkaRvsdBz8mlSFwZn5S4yP");
        timezone.setGmtLabel("Nz1CS2C6hVWSpeaVhPLVwwjxmc2fg78sXlrENCcuzfj99sdKzd");
        timezone.setTimeZoneLabel("uclN8NJLeQntn3KxUm8MBzxV35XrV3A6ERHxLMUbZ5ArLS9FgX");
        timezone.setUtcdifference(3);
        timezone.setCountry("8zQx93oRAlxvERDFOmjHKvMkMzRhodDd4rRCxeVuIuwDbRRLOA");
        timezone.setEntityValidator(entityValidator);
        return timezone;
    }

    @Test
    public void test1Save() {
        try {
            Timezone timezone = createTimezone(true);
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            timezone.isValid();
            timezoneRepository.save(timezone);
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            Timezone timezone = timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
            timezone.setCities("gCXxFGpcQk9ql30NaC1G7kr2suu0izD0PUKsUsTLX9VSS1yweZ");
            timezone.setVersionId(1);
            timezone.setGmtLabel("tTqU0kXi1luUyUUeFt65RXs0vpephOxdpRbfWmcKe9b1NGCO8s");
            timezone.setTimeZoneLabel("hW8KHKzoF4WOjgMjfOH4lgK8Ht9cURGPYCM5r5PB7mrN5pe020");
            timezone.setUtcdifference(6);
            timezone.setCountry("TJ600KCTfLTMRY3la9bDLuEMlm5MtfQStcYM4fmWPgyx6BOATG");
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            timezoneRepository.update(timezone);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.remove((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateTimezone(EntityTestCriteria contraints, Timezone timezone) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            timezone.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            timezoneRepository.save(timezone);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "utcdifference", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "utcdifference", 15));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "gmtLabel", "a4U9uWlHovKSLroOR8ibrLgoOq13N6PJ34luba5FE8Eq9bFGqC0nB04PUbSWa5NfGAwo015x7rhAFvmmYh7R9kKso6olyMlgJgf8PX2Vk7P60gJGSvUuFIrUjOGk9Bh3jYb6l57sti0Y8xx9TOt6IKWJFhcze8xLbEvcrTmCKATiUbbOKFTF7ta4d1sbzol20ppurCIuWbT4NgN0Srw8szFcLwVKwM19q9VyMVaCE3CxXrPuwWS1C2Zb6rB4UgO5g"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "timeZoneLabel", "b1dpmaU6PQQRfryZniyihWUGaBzVCvFNIyUIOUiu78hDzXHucsjILfuOLPQ2MBlU6ZgRWf7JGHkqorVUrX62sCVkyrPo3aydPmKGdWsrHxTMi4VyDfskY0cJlwaj2gBSMzhtwxoWf2CqeHOXGQG2hU6wF5C8zduWfQCkYoLjkycgzIO9W7oAgoZIxMPgM6j6cKJTO0DqX0veKf719xsOqumor7JvPwHXrs1gwIfqkrl2OGF5buk5LZipeth7lK6oc"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "country", "k5XzDTnwb3qXryciUuIPcUoIKN1foYQHTBVmL5amuesk2woiUH7ADD8k3BAvoTgbVlRHgt3Od10qIXOygib5rTYGyUSR2D8MwfNBnVdj2q9GovXObyDpsoIxJebU82UK3U8cTLPGjGdDKgca7PDkZz1m7tRmW4nBWiqiRpihcKBNocyu87GydDDlQjtyTfrJOnV9nHzapyFn9VAxFOwwUlhmxPa34K7dfeEzFNFW6W3PcHf9E1pYh6FWdRc9dxjRn"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "cities", "DTxxvrsNAsvQT8eivBtKEMtGXQVEY0Jfm17QjsG5M65njYSaHESZDgrQK4N6UClYp2Q2TlRJ9cVjLQtgHEnBMlSBOsTgAQBivo5JhR3i7hadY5y2PvBxUyMEfyJPAJVPFbr9t5nXvXQoV3Yazl9Mcjh0SUnljS4CrzldczF5dCWCgL5Lhu8EriMh9e2i2WMXocHpm8OYDwRVO7C4CQRFzWPhkWI3OcjB5NUAp8bAiLUmoE7NThnotCDuWQ6rOxAW3"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Timezone timezone = createTimezone(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = timezone.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(timezone, null);
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 2:
                        timezone.setUtcdifference(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 3:
                        timezone.setGmtLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 4:
                        timezone.setTimeZoneLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 5:
                        timezone.setCountry(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 6:
                        timezone.setCities(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
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
