package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    /**
     * AppMenusRepository Variable
     */
    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setAppId("JBcTvdXyWF9gDGsWnfmCKNOnk8TBbPYyPL7B3myfcegvf7URaJ");
        appmenus.setMenuCommands("Z1RYgVHlT4W2RHwLqC3Ad66VVv2bJk0jTH4ajPajFsPZDBGCRt");
        appmenus.setMenuAction("i9Ui2aB83DcagM9hr1mMh7BYFqlkVqA5Z4enqaoCHLFl0b0Nxu");
        appmenus.setAutoSave(true);
        appmenus.setRefObjectId("OUxiDcryCk9WkRVDUFevYARSG0W1MkaoHTZvAGYtnc4RuNbCe9");
        appmenus.setMenuDisplay(true);
        appmenus.setUiType("vlF");
        appmenus.setMenuIcon("wtWOnOECLz6VBRMDsTvBCmOWMMsL1QTHWeSOgmpD4EGNGcuGlh");
        appmenus.setMenuAccessRights(1);
        appmenus.setMenuHead(true);
        appmenus.setMenuTreeId("xjklqWntrx1pA8clmwoB8FKBqTUZJYFpxlzPpNC7HmhJskCJFq");
        appmenus.setMenuLabel("yt7GyRAab2P2AXk0XgOAyWA1rsWLEfkj7RsdaagecKowPYBMCg");
        appmenus.setAppType(1);
        appmenus.setStartDate(new java.sql.Timestamp(1482410113705l));
        appmenus.setExpiryDate(new java.sql.Timestamp(1482410113705l));
        appmenus.setGoLiveDate(new java.sql.Timestamp(1482410113705l));
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setAppId("yYWiS6gprhCCHrwxLjp8GdbRKURYqFWu274E7u9iyRCZsFEfpG");
            appmenus.setMenuCommands("Qm0OHhcJDelbi2ndROFlmimnkLuhWAQfbjxTeBeISWrZ8tQlwt");
            appmenus.setMenuAction("BzZDEag5GHw2L2b5T5gbk5IDccgruc4MVHI1INIsq0e8Jaj0Of");
            appmenus.setVersionId(1);
            appmenus.setRefObjectId("tsu13twOh5biDHBktexEeJxCd8mMwbipHIrgezf0DVc4TZUuPI");
            appmenus.setUiType("WNS");
            appmenus.setMenuIcon("r1msr3MSyXxqPtr85D8hfaCwwGxmxQTmlJOtpMCtB4zITbBeco");
            appmenus.setMenuAccessRights(3);
            appmenus.setMenuTreeId("aB3Pgh3eEY34nBLlCjcp75rr44WwIXLoPNfOc6ZJ52OIFJgusL");
            appmenus.setMenuLabel("QcIlmgBvBFLB1enJfM6JzLZUYl4Qqz8GLVllpvmho7MdTyLTPC");
            appmenus.setAppType(2);
            appmenus.setStartDate(new java.sql.Timestamp(1482410113825l));
            appmenus.setExpiryDate(new java.sql.Timestamp(1482410113829l));
            appmenus.setGoLiveDate(new java.sql.Timestamp(1482410113833l));
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.remove((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "SnKIw1VuD5UCLo1nX1m2RIeinAkE9qrBljT9e9sCunwtYb04KGMRKPRntaNVDGvwSKfISK9WRwb18XGlbd5NoPXqVG0xpuRAGFOPj0twNZ6iQ3blxNOQuEtRDR0LHoztxoNS3hmxnRQqurAt07siqHNYUlZ10aLYnDKdueBg71yrc9uCm5IsUP4mp1ePrTxgTnWGpKEc6ibZ50BWGU5rlf1tqkJjZ5oGw99dUdKevwaERK2eVOQL0VU5cQH0qoMfe"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "GM3E7aNyZWUwDrLFQupVZyirgYY9ywZhZTapSEbTG8krVjmh9ktan0cFJHq7QJ9Myd07S4Fhv3Qe58K03ZRFOCyc1wmTyPIcmYaNwGaNEgMd6HfmLjW9oMea9S2YOPH1kt1bEgjLNfJm5cWXUHA73oBb8g7XvGBSwRByjeLVbZq58RkyLTVFMgKiq7h3jqyMRR3jpisABUnyZOkA6iF9fY4iee4jiLPnOEPF6MnRATNdwrRjkGA8S8CvTaKuAjB5d"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "3gzjWPJO0AreebqcM6P8xWTe2dOueT9RfqQ83jOSI67cPdEC2v8rHZ8Sx3SBaIIBYyC5qyi3jiChEottPwZ9FpadCWaF1J818DRqFt4rzip97j7WXPAf1UPpvNuKKqXQoPdlxuD7H7FqZhtRpUYb2ySNlCzeCgyrcu6xwA2oXwGa6lkHE5a1UhomyxSdHag60HURQ21CV0dtp1yfZJOroYL1pa7GycKs7zdRJ4EIaYqQsgMIvuISY1dZ2teXXieC9"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "CU1yrIb7A5OD18692fezYZeclu2ZTEmFLRrbhnhsztZvHfhKL3B9vUapyzqSBcHeu"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "kRXx8xLbgrud2vQ3m2ybpyJyVBVrIo54AUUP8eDO3yMrhtwb0hOGCbFRptgcKwSLuWeNhe8yFaX4qncIZelN7E3vuDR2N7DGqvZM0YYUq42TqHHAdcFXLM5rQN26pZHye68wbn8M4IghUseMc9ULZTOFhzXe9iDtcv5uHmUyHCnRCfJOkrFudWtDLowgC9uFBXpwaJTKmL7q77fv33KddugN7GfLAW9pEOoIXim89H0q1I4fn3Y04ZPJyf4Spl4R9"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "SqrR"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "RIpaySKdrqRGhpMnc3S29Pz8tPXMpRjGXHg5nMTmzaaRzLiXEcSdp4ggvRnwXsl7GszWyBnoYLjWSBsufMhmCp3Sqtbbh5h2i3jL5l9pZ0RXjNBo2sPKlhQsHkhpqjKBexdPVdjUpiQvD0fY4esVSk9qSOd9SrJ7JpFQenwCZAtzYb6TDVORhB3ngKIo5RsVK7IWAOEpnN4ZHG70MZ4gw6wQMzUVL3E6mRhC0Api503HPTNLYFY5GoXxMsHRU7qyL"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 14));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 3));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "YOqMCSAYMs98QokJNedAx92m7eWzHX5CybEaQrByijsU5t50YeC9fBXPKwseRIzh7matSFr7VXRtozHqnim920aNc7dmx2yDkxAz2y7JnmE5lbz3LtQGpjtYCUVfHxU7hFiikdaFZQVhU42GVNfWLgvu1vPd5qondGnQebIyP7futl9aq6dpgOZ1yXJhaBwSSkxE06cvsnbQ1rWedkc2IOjVCkJHFyplrLqOgNLcEJr51eh677SFWYH2u1wVMR2ho"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
