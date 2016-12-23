package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    /**
     * RolesRepository Variable
     */
    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleHelp("Q9CNwDyQHNn8Ohc4nTJlgM8LVNcjBHHFM7sytW4Fm39HW5wQrv");
        roles.setRoleName("sF2i0BdVMNmSHdx2j9UemGyRIvVifDiMdhzRW9U0HWu45BCtRK");
        roles.setRoleDescription("vuGAHt5TbFMj89JoMgntfFyly3qKLfMAlA0aWmxotihQUIb6MV");
        roles.setRoleIcon("94uZQO9v1pvjqY5tJMlwGibTwAoMD0WR2Gil2pEHZYKiLcqBuT");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        rolemenubridge.setIsWrite(true);
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuCommands("F1rAdqd286eQt0wURNMLIgjCNOGMhvWUYtKzu09r2OkIRrH4OF");
        appmenus.setMenuTreeId("TWOxi49Q2EH7RPY7o4ClT2t1PXY9MXlrFXuUmh6SUI5zHnxNlM");
        appmenus.setAutoSave(true);
        appmenus.setMenuAction("izCRFAuert3t6iLevey92uzdqJBGsZ2ERrtN6DVH7RXJ4StiOd");
        appmenus.setAppId("PsOQF4mC6YlWZfiVJIJOCEIt9aXCqUHDuQ6bxLDc9X0mFUofpG");
        appmenus.setRefObjectId("AUXwfucGvF2DvpCBkWiTcZUi0VbZPnFR7hoo9tU6wzASWeQH9V");
        appmenus.setStartDate(new java.sql.Timestamp(1482410097282l));
        appmenus.setMenuAccessRights(11);
        appmenus.setMenuIcon("sq2fvbkCrJBbc4WpY2oxy7MtkCLrHQ8IUABxeqgf8nUoDrfMBF");
        appmenus.setExpiryDate(new java.sql.Timestamp(1482410097283l));
        appmenus.setMenuDisplay(true);
        appmenus.setMenuLabel("Wr49xAmQZlI55VwdOu5tKjhOxCrbUnqkmpwivFQo8E2i8oCmcV");
        appmenus.setMenuHead(true);
        appmenus.setGoLiveDate(new java.sql.Timestamp(1482410097283l));
        appmenus.setUiType("vg8");
        appmenus.setAppType(2);
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setIsRead(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleHelp("1hFC2iXKrvv2YcqrokZUQBAZu2tJop444bJMcON6oRufMYJJsX");
            roles.setRoleName("xja5BTVV8SRV0WLrqwDLrOMzJc1qb2KPPe5WFrtkRWYCLIGd1b");
            roles.setVersionId(1);
            roles.setRoleDescription("lTXa2ah16bUPCCT0e8WIOlh5W4cI1o5vibOaNT3pGNV2xO9dn0");
            roles.setRoleIcon("pF0vzOt0FQGYVi7o2ygoWuYISPTTyy1ATSqeB0mCcXuQ4z19dp");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.remove((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.remove((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "PAdzRPp9i1iUbhrlrNGCwhvzSACxkFhi0P6pdE8v0BiRfi8vU6N6F0EEgkmsbxFPFlACkS336Lztm8WyC6qcw8d7qCGuuED0HbSFpzXVeIvfzNKOlDoCEKYB0dMispCInzPQfwgaaKhWMg2u1LnLr26c0x496NA3CcG131OOtoKcsUM7uO8viMjEn5GeyFhNwci7k1DPFoVzwQKrmgnGY1bCxmJrLWEgUtm3KTdG6OtGBD76ldiwwfXevWnmUKlIt"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "zgkicambMNUrMVpxnEa0in6LTv6IkeCjmr21PQTySgDagIQzIjAUT2nzZ0OVCZVicmzRpO7nS4aWcywxsuTk2JmyGl3En0MaGHWsHAJViCgMV1zTrzl58S5OiaNqXfCxEJkVOwF20934IYH6wYtjx7APVyQnrGj63PUydxbmSmOqYbF31SgMzT6hnJrkBe1jramrb8GGyxH3yprxl5SJNSf6IDX8PtPFsraC3LdUeNEordaO8w4D7j4D5MEVWxd0H"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "JFnNnQyY4lo9srthfuP7xzZhZASNLhyyfIaE2FHqYk4pBSFqvYn0JauCMMYP8kyiP"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "tFO3TR5llaLIHu4HRCTsict31bX29E4YqOe6CcfYtlmWEpqdydh4MPgDruy4Eqmp610O52jxJEs7WZUYimhgWTgJB0F07ICZe43WglEzOFgjuX4VB8fSWHZRwSGagvetOffKX0AIc8ZZUZuff646PNKVusHL6VElrRYePA8Mzo6yQr8lQzuG3rNnJ1HPymSSEMNvnLNULBvWz28c2NoqD4YMqU2ji1GT9cj3PpVG3LMYvamLPH57Rs7zI7TGSky1q"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
