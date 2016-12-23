package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.AddressType;
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
public class AddressTypeTestCase extends EntityTestCriteria {

    /**
     * AddressTypeRepository Variable
     */
    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

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

    private AddressType createAddressType(Boolean isSave) throws Exception {
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("RyQgUqsWPFbF5yN4jj1J33juuQCFYftzQ6KKkqVT7JtTSthvPv");
        addresstype.setAddressType("nU3Ow5mZjTYhAsqeZvGlnlFbgIMzLqDgv9hFpOdwq7NK2E2gbl");
        addresstype.setAddressTypeIcon("MjUjriV2Zt5PqaJKPbMR6vSnHv1bzyckYtPmcorg8uLxqrWpPv");
        addresstype.setEntityValidator(entityValidator);
        return addresstype;
    }

    @Test
    public void test1Save() {
        try {
            AddressType addresstype = createAddressType(true);
            addresstype.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            addresstype.isValid();
            addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AddressTypePrimaryKey"));
            AddressType addresstype = addresstypeRepository.findById((java.lang.String) map.get("AddressTypePrimaryKey"));
            addresstype.setVersionId(1);
            addresstype.setAddressTypeDesc("mOKv9fc1D5yYgJ0gGqCRCwpOj1GS5RC7gV0CRPoJRyeEpMZfSG");
            addresstype.setAddressType("6HY0Jq7jSOuYNyMXNdenn4w2HATRuTEp4E0UUHocsFbGMDBuoQ");
            addresstype.setAddressTypeIcon("R7RbozYFCGJM2PjVxqlKVVvCu8G30s8GrmcuspLrOVhXJJqkX7");
            addresstype.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addresstypeRepository.update(addresstype);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AddressTypePrimaryKey"));
            addresstypeRepository.findById((java.lang.String) map.get("AddressTypePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AddressTypePrimaryKey"));
            addresstypeRepository.remove((java.lang.String) map.get("AddressTypePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAddressType(EntityTestCriteria contraints, AddressType addresstype) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            addresstype.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            addresstype.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            addresstype.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addresstypeRepository.save(addresstype);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "addressType", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "addressType", "G4MczkiO37XGU00A7Sh8L2hl4TV8wNQ8FQbyITmibPqLAYOc6CVGHHtzAlMCS6q5OaXsFyUM9KqEp8fIekgj7iqny4Mg7NKUuJxqFJU1dRkYf7Imjq1BCpzj0bn121Bb5"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "addressTypeDesc", "80FERZ7KrkF5jQ7CfLgqiUvNclL6A52SiIqeuzaQ3Z3DmhTHfOhBbHt3ab7iuG8EifrAwWF5I2zqEQm4XUCKCdmi4gWCTYJLy8neYeY9arPXNbeU4sd0DseanNQTHc68VW14CZ3MGENv961VSfxGf0RDyCwB7RRQC12fIJLYvg5zH2iGqULdM3WxthLRXJYtqrLtGcCzAewuHDA3gpMc2HNX1y6lOAmRcjCtcAwsectl1UCugwaQ9klR72na3xPY1"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "addressTypeIcon", "uvFaFMrzczblWRzbWMAh3ass4DCZvLYDvzuUBArmJMhJOwkSsl51QyKlNfA5egylEr2pWAueqXsqNwwjx5Utna56z3Avw4xknQmNhQ4UpzBoy41yxZYrH9UiSQ5ScCXal"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                AddressType addresstype = createAddressType(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = addresstype.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(addresstype, null);
                        validateAddressType(contraints, addresstype);
                        failureCount++;
                        break;
                    case 2:
                        addresstype.setAddressType(contraints.getNegativeValue().toString());
                        validateAddressType(contraints, addresstype);
                        failureCount++;
                        break;
                    case 3:
                        addresstype.setAddressTypeDesc(contraints.getNegativeValue().toString());
                        validateAddressType(contraints, addresstype);
                        failureCount++;
                        break;
                    case 4:
                        addresstype.setAddressTypeIcon(contraints.getNegativeValue().toString());
                        validateAddressType(contraints, addresstype);
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
