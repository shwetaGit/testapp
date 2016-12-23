package com.app.server.service.aspect;
import com.app.server.businessservice.appbasicsetup.aaa.SessionValidation;

import com.app.server.businessservice.appbasicsetup.aaa.CookieValidation;

import com.app.server.repository.appbasicsetup.aaa.PublicApiRepository;

import com.app.shared.appbasicsetup.aaa.PublicApi;

import com.app.server.businessservice.appbasicsetup.aaa.TokenValidation;

import com.app.config.appSetup.model.AppConfiguration;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.repository.MetricRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import atg.taglib.json.util.JSONObject;

import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import com.spartan.healthmeter.entity.scheduler.MethodCallDetails;
import com.spartan.healthmeter.msgWriter.config.ExecutionTimer;
import com.spartan.healthmeter.msgWriter.config.HealthConstants;
import com.spartan.healthmeter.msgWriter.core.Healthmeter;
import com.spartan.pluggable.exception.core.BaseSecurityException;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.server.interfaces.LoginSessionRepositoryInterface;
import com.spartan.server.interfaces.TokenValidatorInterface;

@Aspect
@Component
public abstract class ServiceAspect {

	@Autowired
	private EntityValidatorHelper<Object> entityValidator;

	@Autowired
	private SessionValidation sessionValidation;

	@Autowired
	private CookieValidation cookieValidation;

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;
	@Autowired
	private LoginSessionRepositoryInterface loginSessionRepo;

	@Autowired
	private CounterService counterService;

	@Autowired
	private GaugeService gaugeservice;

	@Autowired
	private MetricRepository repository;

	@Autowired
	private PublicApiRepository<PublicApi> publicApiRepository;

	@Autowired
	private TokenValidatorInterface tokenValidation;
	
	@Autowired
	private AppConfiguration appConfiguration;

	/****
	 * Method to validate the entity in save and update operation .
	 * @param proceedingJoinPoint
	 * @return
	 * @throws SecurityException
	 */
	public Boolean aroundAdviceSaveAndUpdateLogin(ProceedingJoinPoint proceedingJoinPoint) throws SecurityException {
		boolean isValidEntity = true;
		if (proceedingJoinPoint.getArgs().length > 0) {
			Object methodInputParam = proceedingJoinPoint.getArgs()[0];
			if (methodInputParam != null && methodInputParam instanceof CommonEntityInterface) {
				CommonEntityInterface entity = (CommonEntityInterface) methodInputParam;
				try {
					preSaveUpdateOperation(entity);
					entity.setSystemTxnCode(51000);
				} catch (SecurityException e) {
					isValidEntity = false;
				}
			} else if (methodInputParam != null && methodInputParam instanceof List) {
				List listOfEntities = (List) methodInputParam;
				if (listOfEntities.size() > 0) {
					/*
					 * Checking 0th element type. So no need to check type for
					 * each element in the loop.
					 */
					if (listOfEntities.get(0) instanceof CommonEntityInterface) {
						for (Object object : listOfEntities) {
							CommonEntityInterface entity = (CommonEntityInterface) object;
							try {
								preSaveUpdateOperation(entity);
								entity.setSystemTxnCode(51000);
							} catch (SecurityException e) {
								isValidEntity = false;
							}
						}
					}
				}
			}
		}
		return isValidEntity;
	}

	/****
	 * Method to set the values in runtimeLogInfoHelper
	 * Update last time access time to login table , Validate the Request
	 * @param session
	 * @param request
	 * @param response
	 * @param _requestId
	 * @param _sessionId
	 * @throws Throwable
	 */
	public void serviceLogic(HttpSession session, HttpServletRequest request, HttpServletResponse response, String _requestId, String _sessionId) throws Throwable {
		/*
		 * Needs to get user id from request header and pass it to entityAudit
		 * and RuntimeLogInfo
		 */
		/* create logging info object (Needs to call from login service only */
		//runtimeLogInfoHelper.setCustomerId(getCustomerId(request));
		//runtimeLogInfoHelper.createRuntimeLogUserInfo(getCustomerId(request), loggedInUserId(request), request.getRemoteHost());
		//runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean(getCustomerId(request), loggedInUserId(request), request.getRemoteHost(),
		//		request.getRemotePort(), 0, 0), _sessionId, _requestId));
		/* validate request */
		if (request.getHeader("Job-Execution") == null) {
			if (request.getHeader("isPublicApi") == null) {
				validateRequest(session, request, response);
				if (!sessionValidation.checkIgnoreURL(request)) {
					/** Sets user's last access time in table */
					java.sql.Timestamp currentTime = new java.sql.Timestamp(System.currentTimeMillis());
					loginSessionRepo.updateLastAccessTime(loggedInUserId(request), session.getAttribute("usidHash").toString(), currentTime);
					runtimeLogInfoHelper.setUserAccessCode(Integer.parseInt(session.getAttribute("userAccessCode").toString()));
				}
			}
		}
	}

	/**Set runtime info object to be used every where in all layer*/
	public void setRuntimeInfoObject(HttpServletRequest request, String _requestId, String _sessionId) {
		/* create logging info object (Needs to call from login service only */
		runtimeLogInfoHelper.setCustomerId(getCustomerId(request));
		runtimeLogInfoHelper.createRuntimeLogUserInfo(getCustomerId(request), loggedInUserId(request), request.getRemoteHost());
		runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean(getCustomerId(request), loggedInUserId(request), request.getRemoteHost(),
				request.getRemotePort(), 0, 0), _sessionId, _requestId));
	}

	/**
	 *  Method to check whether the request is validated .
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseSecurityException
	 */
	public boolean validateRequest(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws BaseSecurityException {

		if (!sessionValidation.checkIgnoreURL(request)) {
			sessionValidation.validateSession(session, request, response);
			cookieValidation.validateSessionCookie(session, request);
		}
		return true;
	}

	protected void preSaveUpdateOperation(CommonEntityInterface entity) throws SecurityException {
		prepareEntityAuditInfo(entity);
		/* validates the entity */
		validateEntity(entity);

	}

	public Object aroundAdvice2Update(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		return proceedingJoinPoint;
	}

	public Object aroundAdvice2Delete(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		return null;
	}

	/**
	 * Validate Entity
	 * @param entity
	 * @return
	 * @throws SecurityException
	 */
	private boolean validateEntity(CommonEntityInterface entity) throws SecurityException {
		boolean isValidEntity = true;
		/* set entity validator */
		entity.setEntityValidator(this.entityValidator);
		/* validates the entity */
		isValidEntity = entity.isValid();
		return isValidEntity;
	}

	protected Object aroundAdvicefindOperation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		return true;
	}

	public void afterReturning(JoinPoint join) throws IOException {
	}

	/**
	 * Fetch userId from session
	 * @param request
	 * @return
	 */
	public String loggedInUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("usidHash") != null) {
			String loggedInObject = (String) session.getAttribute(session.getAttribute("usidHash").toString());
			try {
				JSONObject json = new JSONObject(loggedInObject);
				JSONObject userJSON = json.getJSONObject("userDetail");
				return userJSON.getString("userId");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return "SYSTEM_USER";
		}
		return null;

	}

	/**
	 * Fetch sessionId from session.
	 * @param request
	 * @return
	 */
	public String getSessionId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("usidHash") != null) {
			return session.getAttribute("usidHash").toString();
		} else {
			return "";
		}

	}

	/**
	 * Sets the entityAudit value for entity.
	 * @param entity
	 */
	protected void prepareEntityAuditInfo(CommonEntityInterface entity) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String userId = loggedInUserId(request);
		entity.setEntityAudit(1, userId);
	}

	/**
	 * Fetching customer Id
	 * @param request
	 * @return
	 */
	public String getCustomerId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("multitenantId") != null) {
			return session.getAttribute("multitenantId").toString();
		} else {
			return "SYSTEM_CUSTOMER";
		}
	}

	/**
	 * Increment thr guage counter for particular request in health
	 * @param className
	 * @param methodName
	 * @return
	 */
	public String incrementUricounter(String className, String methodName) {
		counterService.increment(className + "." + methodName);
		Metric metric = repository.findOne("gauge." + className + "." + methodName + "");
		if (metric != null) {
			gaugeservice.submit(className + "." + methodName, (Double) metric.getValue() + 1);
		} else {
			gaugeservice.submit(className + "." + methodName, 1);
		}
		return className + "." + methodName;
	}

	/**Method for initializing the methodCallDetails for health
	 * @param methodCallStack
	 * @param request
	 * @param proceedingJoinPoint
	 * @return
	 */
	protected MethodCallDetails setMedhodCallDetails(ArtMethodCallStack methodCallStack, HttpServletRequest request, ProceedingJoinPoint proceedingJoinPoint) {
		MethodCallDetails methodCallDetails = new MethodCallDetails(methodCallStack.getRequestId(), methodCallStack.getCallSequence(), HealthConstants.CLASS_TYPE.SERVICE,
				request.getRemoteHost(), request.getMethod(), proceedingJoinPoint.getTarget().getClass().toString(), proceedingJoinPoint.getSignature().getName(),
				loggedInUserId(request), getSessionId(request),getCustomerId(request));
		return methodCallDetails;
	}

	/**Calculating the health after reuest is completed
	 * @param proceedingJoinPoint
	 * @param token
	 * @return
	 * @throws Exception
	 */
	protected MethodCallDetails setMethodPostCallDetails(MethodCallDetails methodCallDetails, String healthConstant, String statusCodeName) {
		methodCallDetails.setPostCallDetails(healthConstant, statusCodeName);
		return methodCallDetails;
	}

	/**
	 * Method for calulating the number of request after request completion .
	 * @param methodCallStack
	 * @param healthmeter
	 * @param executionTimer
	 * @param methodCallDetails
	 * @param proceedingJoinPoint
	 * @throws CloneNotSupportedException
	 */
	protected void setFinallyMethodCallDetails(ArtMethodCallStack methodCallStack, Healthmeter healthmeter, ExecutionTimer executionTimer, MethodCallDetails methodCallDetails,
			ProceedingJoinPoint proceedingJoinPoint) throws CloneNotSupportedException {
		String entityName = incrementUricounter(proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName(), proceedingJoinPoint.getSignature().getName());
		methodCallStack.addMethodCallDetails(methodCallDetails);
		healthmeter.apphealth.writeHealthLog((ArtMethodCallStack) methodCallStack.clone());
		Integer existingValue = 0;
		Metric metric = repository.findOne("gauge." + "total.Time" + entityName + "");
		if (metric != null) {
			existingValue = metric.getValue().intValue();
		}
		gaugeservice.submit("total.Time" + entityName + "", executionTimer.getSystemTime + existingValue);
	}

	/****
	 * Method to validate the token .
	 * @params ProceedingJoinPoint : taking request
	 *         token               : token passed from request
	 * @throws java.lang.Exception
	 */
	public boolean checkToken(final ProceedingJoinPoint proceedingJoinPoint,final String token) throws Exception {
		try {
			return tokenValidation.validateToken(token, appConfiguration.getJwtConfig().getTokenKey());
		} catch (final Exception e) {
			return false;
		}
	}

	/****
	 * Checks whether the request from the client is available in publicApi if the header contains 'publicApi' header
	 * @params HttpServletRequest
	 * @throws java.lang.exception
	 */
	public boolean validatePublicApi(HttpServletRequest request) throws Exception {
		List<PublicApi> publicApis = publicApiRepository.findAll();
		for (PublicApi publicApi : publicApis) {
			JSONObject json = new JSONObject(publicApi.getApiData());
			String api = json.has("resourceMapping") ? json.getString("resourceMapping") : null;
			if (api != null && request.getRequestURI().contains(api)) {
				return true;
			}
		}
		return false;
	}

	@Pointcut("execution(* com.spartan..service..*(..))")
	protected void spartanServiceOperation() {
	}

	@Pointcut("execution(* com.athena..service..update*(..)) || execution(* com.spartan..service..update*(..))")
	protected void athenaAndSpartanUpdateServiceOperation() {
	}

	@Pointcut("execution(* com.athena..service..*(..))")
	protected void athenaServiceOperation() {
	}

}
