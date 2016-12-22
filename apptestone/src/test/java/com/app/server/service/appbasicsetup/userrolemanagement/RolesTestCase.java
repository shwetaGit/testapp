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
        roles.setRoleName("oiuW30prF9O63JYDRVvgOaKKi4VFb33tlz9Qs8oB9JKaepszr5");
        roles.setRoleDescription("Pigrr3Hzz6s15Z9NsT8ZNRuoijYY8lNGaaGnmrRwRQX696Akym");
        roles.setRoleHelp("LPonsE938r9WDIuv3HskIHlFDGGRCQ9i3Nbs5HRpqXF2hTRUie");
        roles.setRoleIcon("9QXy0Gt8i38sffndRMYBaiEPal9aGoEBovOAmm3SQ4POV2Iage");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsExecute(true);
        AppMenus appmenus = new AppMenus();
        appmenus.setAppId("Fh8c5BgC6vq5RO1WH9S4DalY0V3VXTQ0CA2Px7Zm7HRp4wwJzW");
        appmenus.setMenuCommands("pk6bnMoSjiDBHuMioGi6W917shIZ5MdbsO6DBqCJLAqdp1LE4Q");
        appmenus.setMenuAction("IVhtwbiKB8rhA8aHPj8sFJAIMSPA7erHsHWpOIKFu34OdC5C9X");
        appmenus.setAutoSave(true);
        appmenus.setRefObjectId("ePLZyzYRtzbtowxtflNT9k2VnlrHyfbcJcGHmLhrhg7DNEsloa");
        appmenus.setMenuDisplay(true);
        appmenus.setUiType("aom");
        appmenus.setMenuIcon("pHXmv70g0QoJ1NFRgAtjCj5ytZ7R3Y3TGOa7q4W8nRoWC0h0oK");
        appmenus.setMenuAccessRights(6);
        appmenus.setMenuHead(true);
        appmenus.setMenuTreeId("tnlYVod9kitoABWinFEabTOu9J2Kdlb9cWus133DqqJz7Foi5S");
        appmenus.setMenuLabel("wFn7fMoaHFTT7KO8X51VI1sjIO9SD8rQmLwtncohrv1qLGGhUr");
        appmenus.setAppType(1);
        appmenus.setStartDate(new java.sql.Timestamp(1482410112869l));
        appmenus.setExpiryDate(new java.sql.Timestamp(1482410112869l));
        appmenus.setGoLiveDate(new java.sql.Timestamp(1482410112869l));
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
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
            roles.setRoleName("ddCwoNxgvxqBMhEOoINeWQ830G914vgn71aDnBhiJMtQlnXt53");
            roles.setRoleDescription("VPpkgtjBWYJgBBrEzoh0fNMmwbzQYpblBc7vgFIDz7GCqffC9Q");
            roles.setRoleHelp("tch7Xw3B9SzfklqdsEXzG5iDkaUijwQwsSuE4NFomcQTI4vzjw");
            roles.setVersionId(1);
            roles.setRoleIcon("QVLX8XxSt4xlxsJeO9EiZbZOstImlLMAYmwkpvJQp54n75frro");
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "9sYD2OwXLBan5h3ThnlLzEgkoBMcNBG87RSle0Ya1bYkjlZOZleHqKB12CX5iLK7uAzgrMMDjnEDe9rBKrehg9rg0bchzoLj5AH6fpXJX9paBbhSOzXdxBGGTRg3dyLvqbmWxnXcbc3qRztnrUqKM8EhxBYjTJrESFUuRg2SXzlANvabNRW4uSiDleiK0ANLDnctVWpD8JvD5aqWmptJelwmWeUdiXlVLvAYOl5NcZfTfWPskbZkgLiXs25xR6WzD"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "YXLTYKHcKlXyLbiBLIgZzL9F3SmAbbiNx6VpFqSoqjONXcefuAFPtkFXD1P9MMl2G1dvS2ggAfaqCqTYr7o1JrFijZBp75vaYpoWBsXcHsSRfcbFXwSzvgkozmSL4HOvZMongYr4pp6FPyAy7yceh85Hnp9V06EbfJqaI5W9dBbDWo2mLtc3W1Y02jLP1I1NzFXn30DLd4xqf8RJEzPnCUpmTw0O7kkFIPFNLVOffMIeRjUKDYSwJXgNIGBwGT8gA"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "cfiy4Vv2MMUOkS9fUXkbUZswY7tY5YpC824imFDTNyYLnZFVXnHoyzkKDkEkUas5j"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "8eFLog0jNZF59LKOhFR2fNTlFWuTuWybC46QjiTaWFosqTkOGRs6xsoJ9rOUyegVpxfzI8QPP3088FyJGIBUkpYi3QywjA4fJqcijvM1ERlOKTnXRTc7dAi2HbF3NmY8D2fYkhkGgBVpWBlXy7dxZZYVwZTxQ2NdU0sUFFeftGpb1zuasJX5smFk1f9jBYEydZ48xUAFpp1K3vJfePk5ijOWy2MImDm7YgncX8BS4fX1ccSsn8ilnwNr287M3cIXJ"));
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
