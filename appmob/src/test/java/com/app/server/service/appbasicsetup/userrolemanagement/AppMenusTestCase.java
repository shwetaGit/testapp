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
        appmenus.setMenuCommands("Oz2ogRE13PFQn0yEaM3GjvLTB8AUIlogwHrl6894HmMO21z1WS");
        appmenus.setMenuTreeId("VtX8ohubeD6P0SFzPl1VhXpEbh8o3uPSyz56wkpVucEX4MvyyB");
        appmenus.setAutoSave(true);
        appmenus.setMenuAction("IuGq7XLz2biYq7SRCHpkLv2SFYAhMTVHzK4ZQWn9OEyNKbAHyF");
        appmenus.setAppId("41uA0GT3aFUQZDhYaakijJeemt3M72vy9jAyPwqQNtrke28OeT");
        appmenus.setRefObjectId("gzRtCwETsektNV8RylEoe8hhMWLq8yA8RcU46KsNenppyjorOW");
        appmenus.setStartDate(new java.sql.Timestamp(1482410098243l));
        appmenus.setMenuAccessRights(8);
        appmenus.setMenuIcon("47flc0RGjL3PdoACbQdeXVrX3hQuDESuUYQ7TnBR4KQv5DG5F8");
        appmenus.setExpiryDate(new java.sql.Timestamp(1482410098243l));
        appmenus.setMenuDisplay(true);
        appmenus.setMenuLabel("nsvxRjJGv4fCB9MjtxeqJfhh9vluzZX6gv6U3ELFGGuCAkpiGL");
        appmenus.setMenuHead(true);
        appmenus.setGoLiveDate(new java.sql.Timestamp(1482410098243l));
        appmenus.setUiType("3WQ");
        appmenus.setAppType(2);
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
            appmenus.setMenuCommands("Ch9tNV6Kjb8CelBbA84dj1IJA2kxEzAHMAflvlVooQBNByG8Xp");
            appmenus.setMenuTreeId("nidHaQdX0vkU6w1jema1UiKNVuC7jhwF1s6x4dPILCdIEIBRjB");
            appmenus.setMenuAction("tLeH6aedrMy4eyMQgxz1Ra5heKRCRpNS44RHLcpuXoxG4vM0wL");
            appmenus.setAppId("tlUX77ogM9WfvW5R7Z7kr95epdOu0BfllhcJlHr03U6XeL3mu0");
            appmenus.setVersionId(1);
            appmenus.setRefObjectId("qQ3A31h5UwIxBoAH80oeJbyHFHB7oPcJvgsZMP9uDIfXGHSwrI");
            appmenus.setStartDate(new java.sql.Timestamp(1482410098337l));
            appmenus.setMenuAccessRights(6);
            appmenus.setMenuIcon("moxx1ZJK1N3y7PfFOJ6DYUssaHBDmuzxyovmfBSkDrQpfbE1Xs");
            appmenus.setExpiryDate(new java.sql.Timestamp(1482410098352l));
            appmenus.setMenuLabel("biVc7GqY8yYc0Zj0xVMOaBMcjVfFnFKsSy22wiqp9687ViugWU");
            appmenus.setGoLiveDate(new java.sql.Timestamp(1482410098371l));
            appmenus.setUiType("lf2");
            appmenus.setAppType(1);
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "E8X11jyr4YSEZESx4fHHzj0heTxGHRv1kTZ03M4wMoUzG42SzPQVRvkxlkMyjB3z9i6DZCC4d6gBJNY2xj7uqyFwMxel7pTdI3AGBr6tnF5MEZVqg1u1IyAHyLCnHZQr2axer93DaJ6s1cX5UwfK5RnEuNyazVwDeTVh96yepNBkPkXjxkxtqZLnrOID67xqsIeX0CsJ1369k3bAUGcOd7M7Z57PNzD5AyHhQBSxjsozASfbJCTm2lAVBSaYo5LqR"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "oYWzCQGXxB460lpaeF2GKM6aSj4wfLeitD62BTgN9sGM9HAeB80JWPsIXxoMDt2KpYBTnI6hVKUMy2EbTqyDI9PcmWRBwK3enlWmhAL2aqZ5Cai7siV7kT0FSb77uBIMvtLI7QUOT1p1tqdGqMsXyAMnve02UWAhyGxfssli9lku8ZPfbE1d0NoHKyAOeta9IKulnhYgP39Vlgf16ciN5dGUvyG4zwXDA3mbObsH4Jb8FS6RTkZtmjMgMYl2jov4U"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "hur7AsmjFMBKxIN2MBcTPQs2ojYu2mUaLkHqeyLtTOPnwBYK53nsUpP2N6FAK4BdVlnh5pHhZxQCEuBoljearoVllxFQmMl8bUSQU5xl2QYlKuGH5Tp2RCdIPlq5y7qklkx5b0osUaFydbKZKWgIN8wQoyv5hQ1k58Hpngqnj1gXCY85ljh49kzIJMewEexIPnijLDahTFtwTkiqgkvyoCztrrZ5TgDMaB1BvVnyJhQP3TbxBVMpFGI2Y8BNgpD1Q"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "UxMaAbRtYwfDhKksMMtRX4NFaaEiAuibg2agkH86WlcZhtb5cEhr2abc5OFJ3tCnN"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "YMR7kKBHLDV6nzycYXqKbmh9N3kbpbWVvgoUKCZxbIYOtXDClM8VEFW30aCbQqDQBdIBUp1B3HmvrGfWheKHy6Tx5BxJwNC9xVTLjgVU15w6WdUl6bRoNIZLmOCftLTsQMfBTTuwcklEX2Ip7BV8bzgMaiKQciqbkeaLFSy7aZzaIrjo6cM1lgSPLzEdtJh9sZ5fcSBdDXRs2J7orc9IEx7cnM1DGX0KHXF6WlzbtOxY6PTLcFGvX9RltLWYgn4E8"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "KYBO"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "5v6rSFfP0j1TT6TRLxtJKQcoClptAoIjR58TJl8FuBy3wu5WWtMPzBsURtz4hpsGxNrrhPY54zQFAi3ft1hycOPv5XEzryCnaLKuSA5kuR1vRNwdNBvCf8TADaFLdKAWiVAaCiHmSjbXATB4L5V3meu8GiASV5cr1YLyFsdqrg8z5OIfhSqMv8t0FAVQA5pDmrFGTFChnCVJzbXxYaXTgdjIPetokrEoZmJW7GCr09iWAb96490kADsty0QI6tNYc"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 13));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 3));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "WpQekH1jUBEBM4NSXxObKBLF7mCqBdAEm32exuCdi7hkNTR2OADoA5dId8HT4PF4eyDguUSDHpUiDFqjc9rPYEMgnlxIMNMbTx178haoanRMjebTiGqjU4WT4EuryTO4zE9z0IDIrBQMW7VFYP0SVecq2WL6OctpXD3zTGmzehOSTmYfGLo5RbHk3dSL5BSLUh57xXBoE63kCEXOskB9IHEwyHF8YOFKP4Wr3nD7Tz5agQNDuU56L53XtsOBHLVfm"));
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
