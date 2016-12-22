package com.app.server.service.datavisualizer.aspect;
import com.app.server.service.aspect.ServiceAspect;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.repository.MetricRepository;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import com.spartan.healthmeter.entity.scheduler.MethodCallDetails;
import com.spartan.healthmeter.msgWriter.config.ExecutionTimer;
import com.spartan.healthmeter.msgWriter.config.HealthConstants;
import com.spartan.healthmeter.msgWriter.core.Healthmeter;
import com.spartan.pluggable.exception.security.InvalidDataException;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.api.LogManagerFactory;

@Aspect
@Component
public class ServiceSpartanAspect extends ServiceAspect {
	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Autowired
	private ArtMethodCallStack methodCallStack;

	@Autowired
	private Healthmeter healthmeter;

	public HttpStatus httpStatusCode;

	@Autowired
	private CounterService counterService;

	@Autowired
	private GaugeService gaugeservice;

	@Autowired
	private ExecutionTimer executionTimer;

	@Autowired
	private MetricRepository repository;

	public AtomicLong autoRequestId = new AtomicLong(1);

	private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

	/**
	 * Handle all operation  in spartan package and add service login to it
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("spartanServiceAllOperation()")
	@Order(1)
	public Object aroundAdvice1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		ServletWebRequest servletWebRequest = new ServletWebRequest(request);
		HttpServletResponse response = servletWebRequest.getResponse();
		HttpSession session = request.getSession();
		long nextAutoNum = autoRequestId.getAndIncrement();
		methodCallStack.setRequestId(UUID.randomUUID().toString().toUpperCase());
		methodCallStack.setAppSessionId(getSessionId(request));
		methodCallStack.setCustomerId(getCustomerId(request));
		setRuntimeInfoObject(request, methodCallStack.getRequestId(), methodCallStack.getAppSessionId());
		ResponseEntity<ResponseBean> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		try {
			serviceLogic(session, request, response, methodCallStack.getRequestId(), methodCallStack.getAppSessionId());
			Object obj = proceedingJoinPoint.proceed();
			responseEntity = (ResponseEntity<ResponseBean>) obj;
			httpStatusCode = responseEntity.getStatusCode();
		} catch (Exception e) {
			AppAlarm appAlarm = Log.getAlarm("ABSAN154101400");
			ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", appAlarm.getMessage());
			httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), e, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
					.getSignature().getName(), String.format(appAlarm.getMessage(), e.getMessage()));
			return new ResponseEntity<ResponseBean>(exceptionbean, responseEntity.getStatusCode());
		}
		return responseEntity;
	}

	/**
	 * Handle Save service  in spartan package and set values to request after validating it .
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("spartanSaveServiceOperation()")
	@Order(2)
	public Object aroundAdvice2Save(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		boolean isValidEntity = true;
		Object returnObject = new Object();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		MethodCallDetails methodCallDetails = setMedhodCallDetails(methodCallStack, request, proceedingJoinPoint);
		ResponseEntity<ResponseBean> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		aroundAdviceSaveAndUpdateLogin(proceedingJoinPoint);
		if (isValidEntity) {
			try {
				Log.out.println("ABSAN112100100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
						.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
				returnObject = proceedingJoinPoint.proceed();
				responseEntity = (ResponseEntity<ResponseBean>) returnObject;
				httpStatusCode = responseEntity.getStatusCode();
				methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_NORMAL_EXECUTION, responseEntity.getStatusCode().name());
				Log.out.println("ABSAN112100100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
						.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
			} catch (InvalidDataException e) {
				AppAlarm appAlarm = Log.getAlarm("ABSAN154101400");
				ResponseBean exceptionbean = new ResponseBean(appAlarm);
				exceptionbean.add("message", appAlarm.getMessage());
				httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
				methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
				Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), e, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
						.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
				return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
			} catch (com.spartan.pluggable.exception.core.AppBaseException e) {
				AppAlarm appAlarm = Log.getAlarm(e.getAppAlarmId());
				ResponseBean exceptionbean = new ResponseBean(appAlarm);
				exceptionbean.add("message", appAlarm.getMessage());
				httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
				methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
				return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
			} catch (Exception e) {
				AppAlarm appAlarm = Log.getAlarm("ABSAN154101400");
				ResponseBean exceptionbean = new ResponseBean(appAlarm);
				exceptionbean.add("message", appAlarm.getMessage());
				httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
				methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
				Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), e, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
						.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
				return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
			} finally {
				setFinallyMethodCallDetails(methodCallStack, healthmeter, executionTimer, methodCallDetails, proceedingJoinPoint);
			}
		}
		return responseEntity;
	}

	/**
	 * Handle Update service  in spartan package and set values to request after validating it .
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("spartanUpdateServiceOperation()")
	@Order(2)
	public Object aroundAdvice2Update(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		boolean isValidEntity = true;
		Object returnObject = new Object();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		MethodCallDetails methodCallDetails = setMedhodCallDetails(methodCallStack, request, proceedingJoinPoint);
		ResponseEntity<ResponseBean> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		aroundAdviceSaveAndUpdateLogin(proceedingJoinPoint);
		if (isValidEntity) {
			try {
				Log.out.println("ABSAN112100100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
						.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
				returnObject = proceedingJoinPoint.proceed();
				responseEntity = (ResponseEntity<ResponseBean>) returnObject;
				httpStatusCode = responseEntity.getStatusCode();
				methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_NORMAL_EXECUTION, responseEntity.getStatusCode().name());
				Log.out.println("ABSAN112100100", runtimeLogInfoHelper.getRequestHeaderBean(),  proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
						.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
			} catch (InvalidDataException e) {
				AppAlarm appAlarm = Log.getAlarm("ABSAN154101400");
				ResponseBean exceptionbean = new ResponseBean(appAlarm);
				exceptionbean.add("message", appAlarm.getMessage());
				httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
				methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
				Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), e, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
						.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
				return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
			} catch (com.spartan.pluggable.exception.core.AppBaseException e) {
				AppAlarm appAlarm = Log.getAlarm(e.getAppAlarmId());
				ResponseBean exceptionbean = new ResponseBean(appAlarm);
				exceptionbean.add("message", appAlarm.getMessage());
				httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
				methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
				return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
			} catch (Exception e) {
				AppAlarm appAlarm = Log.getAlarm("ABSAN154101400");
				ResponseBean exceptionbean = new ResponseBean(appAlarm);
				exceptionbean.add("message", appAlarm.getMessage());
				httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
				methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
				Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), e, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
						.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
				return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
			} finally {
				setFinallyMethodCallDetails(methodCallStack, healthmeter, executionTimer, methodCallDetails, proceedingJoinPoint);
			}
		}
		return responseEntity;
	}

	/**
	 * Handle Delete service  in spartan package and set values to request after validating it .
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("spartanDeleteServiceOperation()")
	@Order(2)
	public Object aroundAdvice2Delete(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object returnObject = new Object();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		MethodCallDetails methodCallDetails = setMedhodCallDetails(methodCallStack, request, proceedingJoinPoint);
		ResponseEntity<ResponseBean> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		try {
			Log.out.println("ABSAN112100100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
					.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
			returnObject = proceedingJoinPoint.proceed();
			responseEntity = (ResponseEntity<ResponseBean>) returnObject;
			httpStatusCode = responseEntity.getStatusCode();
			methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_NORMAL_EXECUTION, responseEntity.getStatusCode().name());
			Log.out.println("ABSAN112100100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
					.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
		} catch (com.spartan.pluggable.exception.core.AppBaseException e) {
			AppAlarm appAlarm = Log.getAlarm(e.getAppAlarmId());
			ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", appAlarm.getMessage());
			httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
			methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
			return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
		} catch (Exception e) {
			AppAlarm appAlarm = Log.getAlarm("ABSAN154101400");
			ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", appAlarm.getMessage());
			httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
			methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), e, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
					.getSignature().getName(), String.format(appAlarm.getMessage(), e.getMessage()), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":"
					+ proceedingJoinPoint.getSignature().getName());
			return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
		} finally {
			setFinallyMethodCallDetails(methodCallStack, healthmeter, executionTimer, methodCallDetails, proceedingJoinPoint);
		}
		return returnObject;
	}

	/**
	 * Handle Fetch service  in spartan package and set values to request after validating it .
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("spartanFetchServiceOperation()")
	@Order(2)
	public Object aroundAdvicefindOperation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object returnObject = new Object();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		MethodCallDetails methodCallDetails = setMedhodCallDetails(methodCallStack, request, proceedingJoinPoint);
		ResponseEntity<ResponseBean> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		try {
			Log.out.println("ABSAN112100100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
					.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
			returnObject = proceedingJoinPoint.proceed();
			responseEntity = (ResponseEntity<ResponseBean>) returnObject;
			httpStatusCode = responseEntity.getStatusCode();
			methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_NORMAL_EXECUTION, responseEntity.getStatusCode().name());
			Log.out.println("ABSAN112100100", runtimeLogInfoHelper.getRequestHeaderBean(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
					.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
		} catch (com.spartan.pluggable.exception.core.AppBaseException e) {
			AppAlarm appAlarm = Log.getAlarm(e.getAppAlarmId());
			ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", appAlarm.getMessage());
			httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
			methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
			return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
		} catch (Exception e) {
			AppAlarm appAlarm = Log.getAlarm("ABSAN154101400");
			ResponseBean exceptionbean = new ResponseBean(appAlarm);
			exceptionbean.add("message", appAlarm.getMessage());
			httpStatusCode = HttpStatus.valueOf(appAlarm.getAlarmStatus());
			methodCallDetails = setMethodPostCallDetails(methodCallDetails, HealthConstants.METHOD_EXCEPTION, httpStatusCode.name());
			Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), e, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint
					.getSignature().getName(), proceedingJoinPoint.getSignature().getDeclaringTypeName() + ":" + proceedingJoinPoint.getSignature().getName());
			return new ResponseEntity<ResponseBean>(exceptionbean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
		} finally {
			setFinallyMethodCallDetails(methodCallStack, healthmeter, executionTimer, methodCallDetails, proceedingJoinPoint);
		}
		return returnObject;
	}

	/**
	 * Calculating counter for request in ehalth
	 * @param oinPoint
	 * @return
	 * @throws Throwable
	 */
	@AfterReturning("spartanServiceAllOperation()")
	public void afterReturning(JoinPoint join) throws IOException {
		counterService.increment("counter.HttpStatus." + httpStatusCode.name() + "." + join.getSignature().getDeclaringType().getSimpleName() + "." + join.getSignature().getName()
				+ ".calls");
		counterService.increment("counter.numberof.calls");
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

	@Pointcut("execution(* com.spartan.server..service..*(..))")
	protected void spartanServiceAllOperation() {
	}

	@Pointcut("execution(* com.spartan.server..service..save*(..))")
	protected void spartanSaveServiceOperation() {
	}

	@Pointcut("execution(* com.spartan.server..service..update*(..))")
	protected void spartanUpdateServiceOperation() {
	}

	@Pointcut("execution(* com.spartan.server..service..delete*(..))")
	protected void spartanDeleteServiceOperation() {
	}

	@Pointcut("execution(* com.spartan.server..service..find*(..)) || execution(* com.spartan.server..service..get*(..))")
	protected void spartanFetchServiceOperation() {
	}
}
