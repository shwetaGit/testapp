package com.app.server.service.appbasicsetup.usermanagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.http.HttpStatus;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.app.server.repository.appbasicsetup.usermanagement.PasswordPolicyRepository;
import com.app.shared.appbasicsetup.usermanagement.PasswordPolicy;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import java.lang.Override;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;
import com.athena.server.pluggable.utils.bean.FindByBean;

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for PasswordPolicy Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/PasswordPolicy")
public class PasswordPolicyServiceImpl extends PasswordPolicyService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private PasswordPolicyRepository<PasswordPolicy> passwordPolicyrepo;

    /**
     * Retrieve list of  <PasswordPolicy> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<com.spartan.server.password.interfaces.PasswordPolicyInterface> lstpasswordpolicy = passwordPolicyrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("ABSUM124990200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PasswordPolicy"));
        responseBean.add("data", lstpasswordpolicy);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyServiceImpl", "findAll", "PasswordPolicy");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieve Page-wise list of  <PasswordPolicy> object
     * @return HttpEntity<ResponseBean>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findPageWiseData", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findPageWiseData(@RequestParam("page") Integer page, @RequestParam("start") Integer start, @RequestParam("limit") Integer limit) throws Exception {
        AppAlarm appAlarm = Log.getAlarm("ABSUM124990200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PasswordPolicy"));
        responseBean.add("data", passwordPolicyrepo.findPageWiseData(limit, page));
        responseBean.add("totalSize", passwordPolicyrepo.getTotalPageCount());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyServiceImpl", "findPageWiseData", "PasswordPolicy");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  <PasswordPolicy> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- PasswordPolicy
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody PasswordPolicy entity) throws Exception {
        passwordPolicyrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("ABSUM122990200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PasswordPolicy"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyServiceImpl", "save", "PasswordPolicy");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Saves the new  list of new <PasswordPolicy> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<PasswordPolicy>
     * @Params request :- boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<PasswordPolicy> entity, @RequestHeader("isArray") boolean request) throws Exception {
        passwordPolicyrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("ABSUM122990200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PasswordPolicy"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyServiceImpl", "save", "PasswordPolicy");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Deletes the   <PasswordPolicy> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- [@org.springframework.web.bind.annotation.PathVariable("cid") java.lang.String]
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        passwordPolicyrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("ABSUM128990200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PasswordPolicy"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyServiceImpl", "delete", "PasswordPolicy");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the  <PasswordPolicy> object
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- PasswordPolicy
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody PasswordPolicy entity) throws Exception {
        passwordPolicyrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("ABSUM121990200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PasswordPolicy"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyServiceImpl", "update", "PasswordPolicy");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Updates the list of  <PasswordPolicy> object.
     * @return HttpEntity<ResponseBean>
     * @Params entity type:- List<PasswordPolicy>
     * @Params  request type:-boolean
     * @throws java.lang.Exception
     */
    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<PasswordPolicy> entity, @RequestHeader("isArray") boolean request) throws Exception {
        passwordPolicyrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("ABSUM121990200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PasswordPolicy"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyServiceImpl", "update", "PasswordPolicy");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    /**
     * Retrieve list of  <PasswordPolicy> object
     * @return HttpEntity<ResponseBean>
     * @Params fieldData type :- Map<String,Object>
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/search", consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> search(@RequestBody Map<String, Object> fieldData) throws Exception {
        List<java.lang.Object> lstpasswordpolicy = passwordPolicyrepo.search("PasswordPolicy.DefaultFinders", fieldData, getFieldMetaData());
        AppAlarm appAlarm = Log.getAlarm("ABSUM124990200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PasswordPolicy"));
        responseBean.add("data", lstpasswordpolicy);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyServiceImpl", "search", "PasswordPolicy");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    private Map<String, String> getFieldMetaData() {
        java.util.Map<String, String> fieldMetaData = new java.util.HashMap<String, String>();
        fieldMetaData.put("policyName", "String");
        return fieldMetaData;
    }

    /**
     * Retrieves list of  <PasswordPolicy> object
     * @return HttpEntity<ResponseBean>
     * @Params findBean type:- FindBean
     * @throws java.lang.Exception
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        PasswordPolicy lstpasswordpolicy = passwordPolicyrepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("ABSUM124990200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "PasswordPolicy"));
        responseBean.add("data", lstpasswordpolicy);
        Log.out.println("ABSUM124990200", runtimeLogInfoHelper.getRequestHeaderBean(), "PasswordPolicyServiceImpl", "findById", "PasswordPolicy");
        return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}
