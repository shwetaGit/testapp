package com.app.server.repository.datavisualizer.queryengine;
import com.app.shared.datavisualizer.queryengine.ArtQuery;

import com.app.util.DateUtil;

import com.app.util.FwUtils;

import com.app.util.QueryShortCuts;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.Query;

import org.eclipse.persistence.internal.jpa.EJBQueryImpl;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.DatabaseQuery;
import org.eclipse.persistence.sessions.DatabaseRecord;
import org.eclipse.persistence.sessions.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.pluggable.exception.security.InvalidDataException;
import com.spartan.server.interfaces.ArtQueryInterface;
import com.spartan.server.session.bizService.SessionDataMgtService;

@Repository
@Transactional
public class ArtQueryRepositoryImpl implements com.spartan.server.interfaces.ArtQueryRepository {

	@Autowired
	private ResourceFactoryManagerHelper emfResource;

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SessionDataMgtService sessionDataMgtService;

	@Autowired
	private DateUtil queryCommonsUtility;

	@Autowired
	private QueryShortCuts queryShortCuts;

	Runtime runtime = Runtime.getRuntime();

	/**
	 * Use to find ArtQuery Object by passing query id
	 * */
	@Override
	public ArtQueryInterface findById(final String id) throws Exception {
		if (id == null) {
			throw new InvalidDataException();
		}
		try {
			final javax.persistence.EntityManager entityManager = emfResource.getResource();
			final Query query = entityManager.createQuery("SELECT e FROM ArtQuery e WHERE e.queryId=:queryId AND e.activeStatus=:activeStatus");
			query.setParameter("queryId", id);
			query.setParameter("activeStatus", true);
			final List<ArtQueryInterface> lstArtQuery = query.getResultList();
			if (lstArtQuery.isEmpty()) {
				return null;
			} else {
				return lstArtQuery.get(0);
			}
		} catch (final Exception e) {
			e.printStackTrace();
			throw new Exception(" in " + Thread.currentThread().getStackTrace()[1].getMethodName());

		}
	}

	// search engine uses
	@Override
	public List<ArtQueryInterface> getQueriesById(final String queryId) throws Exception {

		final javax.persistence.EntityManager entityManager = emfResource.getResource();

		List<ArtQueryInterface> list = null;
		final String selectQuery = "SELECT query FROM ArtQuery query WHERE query.queryId=:queryId  AND query.activeStatus = :activeStatus";
		final Query query = entityManager.createQuery(selectQuery);
		query.setParameter("queryId", queryId);
		query.setParameter("activeStatus", true);
		list = query.getResultList();

		return list;
	}

	/**
	 * Use to find the list of query
	 * */
	@Override
	public List<ArtQueryInterface> findSqlFromJPQL() {

		final javax.persistence.EntityManager entityManager = emfResource.getResource();

		final String selectQuery = "SELECT query FROM ArtQuery query WHERE query.activeStatus = :activeStatus ";// AND
		// query.sqlQuery
		// IS
		// EMPTY";
		final Query query = entityManager.createQuery(selectQuery);

		query.setParameter("activeStatus", true);
		final List<ArtQueryInterface> lst = query.getResultList();
		final List<ArtQueryInterface> returnList = new ArrayList<ArtQueryInterface>();
		for (final ArtQueryInterface artQuery : lst) {
			if (artQuery.getSqlQuery() == null || artQuery.getSqlQuery().length() == 0) {
				returnList.add(artQuery);
			}
		}
		return returnList;
	}

	/**
	 * Use to convert Query From JPQL to SQL w
	 */
	// @Transactional
	@Override
	public void updateSQLFromJPQL(final ArtQueryInterface artquery) {
		final javax.persistence.EntityManager entityManager = emfResource.getResource();
		try {

			final Query query1 = entityManager.createQuery(artquery.getJpqlQuery());
			final DatabaseQuery databaseQuery = ((EJBQueryImpl) query1).getDatabaseQuery();
			final String sqlString=databaseQuery.getSQLString();
			artquery.setSqlQuery(sqlString);
			entityManager.merge(artquery);
		} catch (final Exception ex) {
			System.err.println("Somthing is wrong with query Id:" + artquery.getQueryId());
			ex.printStackTrace();
		}
	}

	/*
	 * Use to get the SQL Query *
	 */
	@Override
	public String getSqlQuery(final String objectQuery, final JSONArray parameterArray) throws IllegalArgumentException, Exception {
		try {
			final javax.persistence.EntityManager entityManager = emfResource.getResource();
			final Query query = entityManager.createQuery(objectQuery);
			for (int i = 0; i < parameterArray.size(); i++) {
				final JSONObject input = parameterArray.getJSONObject(i);
				/**
				 * FOR THE FIELD WHICH IS STATIC WILL BE SKIPPED HERE AND USED
				 * TO SET CRITERIA IN CONVERTED SQL
				 */
				if (!input.has("isStaticField")) {
					final String placeHolder = input.getString("placeHolder");
					final String fieldDataType = input.getString("fieldType").trim();
					switch (fieldDataType) {
					case "java.lang.String":
						query.setParameter(placeHolder, input.getString("value"));
						break;
					case "java.lang.Short":
						query.setParameter(placeHolder, (short) input.getInt("value"));
						break;
					case "java.lang.Integer":
						query.setParameter(placeHolder, input.getInt("value"));
						break;
					case "java.lang.Long":
						query.setParameter(placeHolder, input.getLong("value"));
						break;
					case "java.lang.Float":
						query.setParameter(placeHolder, (float) input.getDouble("value"));
						break;
					case "java.lang.Double":
						query.setParameter(placeHolder, input.getDouble("value"));
						break;
					case "java.math.BigDecimal":
						query.setParameter(placeHolder, new BigDecimal(input.getDouble("value")));
						break;
					case "java.lang.Boolean":
						query.setParameter(placeHolder, input.getBoolean("value"));
						break;
					case "java.sql.Timestamp":
						query.setParameter(placeHolder, queryCommonsUtility.convertValueToTimeStamp(input.getString("value")));
						break;
					case "java.sql.Date":
						query.setParameter(placeHolder, queryCommonsUtility.convertValueToDate(input.getString("value")));
						break;
					default:
						query.setParameter(placeHolder, input.get("value"));
						break;
					}
				}
			}
			final Session session = entityManager.unwrap(JpaEntityManager.class).getActiveSession();
			final DatabaseQuery databaseQuery = ((EJBQueryImpl) query).getDatabaseQuery();
			databaseQuery.prepareCall(session, new DatabaseRecord());
			final String sqlQuery = databaseQuery.getSQLString();
			return sqlQuery;
		} catch (final Exception e) {
			if (e instanceof IllegalArgumentException) {
				throw new IllegalArgumentException(e.getMessage());
			} else if (e instanceof atg.taglib.json.util.JSONException) {
				throw new atg.taglib.json.util.JSONException("Data type and field value does not perfectly matched <br>Re-enter the value matched with selected datatype");
			} else if (e instanceof java.text.ParseException) {
				throw new java.text.ParseException(e.getMessage()
						+ "<br>You must enter the date in valid format only. <br>Valid date format : dd-MM-yyyy <br>Valid datetime or timestamp format : dd-MM-yyyy HH:mm:ss", 0);
			} else if (e instanceof SQLSyntaxErrorException) {
				throw new SQLSyntaxErrorException(e.getMessage());
			} else {
				e.printStackTrace();
				throw new Exception("Exception occure while excecuting query : " + e.getMessage());
			}
		}
	}

	@Override
	public List<ArtQueryInterface> getUserAccessQueries() throws Exception {

		final javax.persistence.EntityManager entityManager = emfResource.getResource();

		List<ArtQueryInterface> list = null;
		final String selectQuery = "SELECT query FROM ArtQuery query WHERE query.user_access=:user_access  and  query.activeStatus = :activeStatus";
		final Query query = entityManager.createQuery(selectQuery);
		query.setParameter("activeStatus", true);
		query.setParameter("user_access", true);

		list = query.getResultList();

		return list;
	}

	/** use to execute Query by passing actaul query and parameter of query */

	@Override
	public List<Object[]> excecuteQuery(final String queryString, final int pageNumber, final int pageSize, final JSONArray queryCriteria, final int queryType) throws Exception {
		List<Object[]> result = null;
		try {
			final javax.persistence.EntityManager entityManager = emfResource.getResource();
			Query query = null;
			if (queryType == 3) {
				query = entityManager.createNativeQuery(queryString);
				setQueryCriteriaForNativeQuery(queryCriteria, query, queryString);
			} else {
				query = entityManager.createQuery(queryString);
				setQueryCriteria(queryCriteria, query, queryString);
			}
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
			result = query.getResultList();
		} catch (final Exception e) {
			e.printStackTrace();
			throw new Exception("Exception occure while Excecuting query:" + e.getMessage());
		}

		return result;
	}

	/*
	 * get Total record of Query for pagination
	 */
	@Override
	public long getTotalRecords(String queryString, JSONArray queryCriteria, final int queryType) throws Exception {
		try {

			final javax.persistence.EntityManager entityManager = emfResource.getResource();
			Query queryTotal;
			if (queryType == 3) {
				queryString = "SELECT COUNT(*) FROM (" + queryString + ")  totalSize";
				queryTotal = entityManager.createNativeQuery(queryString);
				setQueryCriteriaForNativeQuery(queryCriteria, queryTotal, queryString);
			} else {
				final DatabaseQuery dbQuery = getDatabaseQueryObject(queryString);
				queryString = "SELECT COUNT(*) FROM (" + dbQuery.getSQLString() + ")  totalSize";
				queryTotal = entityManager.createNativeQuery(queryString);
				queryCriteria = rearrangeParametersForNativeQuery(queryCriteria, queryTotal, dbQuery);
				setQueryCriteriaForNativeQuery(queryCriteria, queryTotal, queryString);
			}
			// setQueryCriteria(queryCriteria, queryTotal, queryString);
			final String count = queryTotal.getSingleResult().toString();
			return new Long(count);
		} catch (final Exception e) {
			e.printStackTrace();
			throw new Exception("Exception occure while Excecuting total record query:" + e.getMessage());
		}
	}

	/*private Map<Integer, Object> getAutoCreatedParameters(DatabaseQuery dbQuery) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<Object> lstParams = dbQuery.getCall().getParameters();
		for (int i = 1; i <= lstParams.size(); i++) {
			if (lstParams.get(i - 1) instanceof org.eclipse.persistence.internal.expressions.ParameterExpression) {
				org.eclipse.persistence.internal.expressions.ParameterExpression pmExp = (org.eclipse.persistence.internal.expressions.ParameterExpression) lstParams.get(i - 1);

			} else {
				map.put(i, lstParams.get(i - 1));
			}
		}

		return map;
	}*/

	private void updateCriteriaIndexValue(final JSONArray jsonArray, final String name, final int indexVal) throws JSONException {
		for (int i = 0; i < jsonArray.size(); i++) {
			if(jsonArray.getJSONObject(i).has("name")) {
				if(name.equals(jsonArray.getJSONObject(i).getString("name"))) {
					jsonArray.getJSONObject(i).put("index", indexVal);
				}
			}
		}
	}

	/**
	 * Calling this function when CONCAT is used in JPQL with static values e.g.
	 * CONCAT(field1,'--',field2).JPQL to SQL conversion replaces '--' with ?
	 * (place holder). Here we are adding this parameter in existing query
	 * criteria
	 */
	private JSONArray rearrangeParametersForNativeQuery(final JSONArray queryCriteria, final Query query, final DatabaseQuery dbQuery) throws Exception {
		final List<Object> lstParams = dbQuery.getCall().getParameters();
		for (int i = 1; i <= lstParams.size(); i++) {
			if (lstParams.get(i - 1) instanceof org.eclipse.persistence.internal.expressions.ParameterExpression) {
				final org.eclipse.persistence.internal.expressions.ParameterExpression pmExp = (org.eclipse.persistence.internal.expressions.ParameterExpression) lstParams.get(i - 1);
				if(pmExp.getField().getName().equals("tenant.id")) {
					final JSONObject newJsonObj = new JSONObject();
					newJsonObj.put("index", i);
					newJsonObj.put("value", emfResource.getResource().getProperties().get("tenant.id"));
					newJsonObj.put("datatype", "varchar");
					queryCriteria.add(newJsonObj);
				} else {
					updateCriteriaIndexValue(queryCriteria, pmExp.getField().getName(), i);
				}
			} else {
				final JSONObject newJsonObj = new JSONObject();
				newJsonObj.put("index", i);
				newJsonObj.put("value", lstParams.get(i - 1));
				newJsonObj.put("datatype", "varchar");
				System.out.println(lstParams.get(i - 1));
				queryCriteria.add(newJsonObj);
			}
		}
		return queryCriteria;
	}

	/*private void increaseIndexValue(JSONArray queryCriteria, int fromIndex) {
		try {
			for (int x = 0; x < queryCriteria.size(); x++) {

				JSONObject queryCriteriaJSONObject = (JSONObject) queryCriteria.getJSONObject(x);
				int index = queryCriteriaJSONObject.getInt("index");
				queryCriteria.getJSONObject(x).put("index", fromIndex);
				fromIndex++;
				// queryCriteriaJSONObject.put("index", index + 1);
				// queryCriteria.put(x, queryCriteriaJSONObject);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}*/

	/** converting date by users time-zone */
	private long convertDateByUserTimezone(final long datetimemillis) {
		try {
			final SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
			final Date date = new Date(datetimemillis);
			final SimpleDateFormat sdfUTC = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
			final TimeZone tzUTC = TimeZone.getTimeZone("UTC");
			sdfUTC.setTimeZone(tzUTC);
			final String sDateInUtc = sdfUTC.format(date);
			final java.util.Date dateInUtc = formatter.parse(sDateInUtc);
			System.out.println("Orginal time [" + formatter.format(date) + "] Converted time [" + formatter.format(dateInUtc) + "]");
			return dateInUtc.getTime();
		} catch (final ParseException e) {
			System.out.println("Error occured while parsing date to user timezone " + e.getMessage());
			return datetimemillis;
		}

	}

	/** converting date by users time-zone */
	private long convertTimestampByUserTimezone(final long datetimemillis) {
		return convertDateByUserTimezone(datetimemillis);
	}

	/*
	 * set Query criteria parameter to query
	 */
	private void setQueryCriteria(final JSONArray queryCriteria, final Query query, final String queryString) throws Exception {
		try {
			// addUserQC(queryCriteria);
			for (int x = 0; x < queryCriteria.size(); x++) {
				final JSONObject queryCriteriaJSONObject = queryCriteria.getJSONObject(x);

				final String parameter = queryCriteriaJSONObject.getString("name");
				// check whether query contains parameter or not
				if (queryString.contains(":" + parameter)) {
					final String datatype = queryCriteriaJSONObject.get("datatype").toString().trim();

					if (datatype.equalsIgnoreCase("varchar") || datatype.equalsIgnoreCase("BLOB") || datatype.equalsIgnoreCase("CHAR") || datatype.equalsIgnoreCase("TEXT")
							|| datatype.equalsIgnoreCase("VARCHAR2") || datatype.equalsIgnoreCase("CLOB")) {
						query.setParameter(parameter, queryCriteriaJSONObject.getString("value"));
					} else if (datatype.equalsIgnoreCase("int") || datatype.equalsIgnoreCase("NUMBER")) {
						query.setParameter(parameter, queryCriteriaJSONObject.getInt("value"));
					} else if (datatype.equalsIgnoreCase("double") || datatype.equalsIgnoreCase("BINARY_DOUBLE")) {
						query.setParameter(parameter, queryCriteriaJSONObject.getDouble("value"));
					} else if (datatype.equalsIgnoreCase("datetime") || datatype.equalsIgnoreCase("timestamp")) {
						query.setParameter(parameter, covertValueToTimeStamp(getDateTimeLongValue(queryCriteriaJSONObject)));
					} else if (datatype.equalsIgnoreCase("date")) {
						if (queryCriteriaJSONObject.has("value") && !"null".equalsIgnoreCase("" + queryCriteriaJSONObject.get("value"))) {
							query.setParameter(parameter, covertValueToDate(getDateTimeLongValue(queryCriteriaJSONObject)));
						} else {
							query.setParameter(parameter, covertValueToDate(-1));
						}
					} else if (datatype.equalsIgnoreCase("BOOLEAN") || datatype.equalsIgnoreCase("TINYINT") || datatype.equalsIgnoreCase("BIT")) {
						query.setParameter(parameter, queryCriteriaJSONObject.getBoolean("value"));
					} else if (datatype.equalsIgnoreCase("FLOAT")) {
						query.setParameter(parameter, (float) queryCriteriaJSONObject.getDouble("value"));
					} else if (datatype.equalsIgnoreCase("BIGINT") || datatype.equalsIgnoreCase("MEDIUMINT") || datatype.equalsIgnoreCase("LONG")
							|| datatype.equalsIgnoreCase("MEDIUMINT")) {
						query.setParameter(parameter, queryCriteriaJSONObject.getLong("value"));
					} else if (datatype.equalsIgnoreCase("DECIMAL")) {
						query.setParameter(parameter, new BigDecimal(queryCriteriaJSONObject.getDouble("value")));
					}
				}

			}
		} catch (final Exception e) {
			e.printStackTrace();
			throw new Exception("Exception in setting Query Parameter:" + e.getMessage());
		}
	}

	/** This method is used to calculate date value send from ui */
	private long getDateTimeLongValue(final JSONObject queryCriteria) throws Exception {
		if (queryCriteria.has("dateFlag") && !queryCriteria.isNull("dateFlag")) {
			return queryShortCuts.getShortCutDateLongVal(queryCriteria);
		} else if (queryCriteria.has("customDateValue") && !queryCriteria.isNull("customDateValue")) {
			return queryShortCuts.getCustomDateLongVal(queryCriteria, sessionDataMgtService.getSessionData("timeZoneId").toString());
		} else {
			return convertTimestampByUserTimezone(queryCriteria.getLong("value"));
		}
	}

	/*
	 * set Query criteria parameter to NativeQuery
	 */

	private void setQueryCriteriaForNativeQuery(final JSONArray queryCriteria, final Query query, final String queryString) throws Exception {
		try {
			// addUserQC(queryCriteria);
			for (int x = 0; x < queryCriteria.size(); x++) {
				final JSONObject queryCriteriaJSONObject = queryCriteria.getJSONObject(x);

				final int index = queryCriteriaJSONObject.getInt("index");
				final String datatype = queryCriteriaJSONObject.get("datatype").toString().trim();

				if (datatype.equalsIgnoreCase("datetime") || datatype.equalsIgnoreCase("timestamp")) {

					if (queryCriteriaJSONObject.has("value") && !"null".equalsIgnoreCase("" + queryCriteriaJSONObject.get("value"))) {
						try {
							query.setParameter(index, covertValueToTimeStamp(getDateTimeLongValue(queryCriteriaJSONObject)));
						} catch (final Exception e) {
							query.setParameter(index, queryCriteriaJSONObject.get("value"));
						}
					} else {
						query.setParameter(index, covertValueToDate(-1));
					}
				} else if (datatype.equalsIgnoreCase("date")) {

					if (queryCriteriaJSONObject.has("value") && !"null".equalsIgnoreCase("" + queryCriteriaJSONObject.get("value"))) {
						try {
							query.setParameter(index, covertValueToDate(getDateTimeLongValue(queryCriteriaJSONObject)));
						} catch (final Exception e) {
							query.setParameter(index, queryCriteriaJSONObject.get("value"));
						}
					} else {
						query.setParameter(index, covertValueToDate(-1));
					}
				} else {
					if (datatype.equalsIgnoreCase("varchar") || datatype.equalsIgnoreCase("BLOB") || datatype.equalsIgnoreCase("CHAR") || datatype.equalsIgnoreCase("TEXT")
							|| datatype.equalsIgnoreCase("VARCHAR2") || datatype.equalsIgnoreCase("CLOB")) {
						query.setParameter(index, queryCriteriaJSONObject.getString("value"));
					} else if (datatype.equalsIgnoreCase("int") || datatype.equalsIgnoreCase("NUMBER")) {
						query.setParameter(index, queryCriteriaJSONObject.getInt("value"));
					} else if (datatype.equalsIgnoreCase("double") || datatype.equalsIgnoreCase("BINARY_DOUBLE")) {
						query.setParameter(index, queryCriteriaJSONObject.getDouble("value"));
					} else if (datatype.equalsIgnoreCase("datetime") || datatype.equalsIgnoreCase("timestamp")) {
						query.setParameter(index, covertValueToTimeStamp(getDateTimeLongValue(queryCriteriaJSONObject)));
					} else if (datatype.equalsIgnoreCase("date")) {
						if (queryCriteriaJSONObject.has("value") && !"null".equalsIgnoreCase("" + queryCriteriaJSONObject.get("value"))) {
							query.setParameter(index, covertValueToDate(getDateTimeLongValue(queryCriteriaJSONObject)));
						} else {
							query.setParameter(index, covertValueToDate(-1));
						}
					} else if (datatype.equalsIgnoreCase("BOOLEAN") || datatype.equalsIgnoreCase("TINYINT") || datatype.equalsIgnoreCase("BIT")) {
						query.setParameter(index, queryCriteriaJSONObject.getBoolean("value"));
					} else if (datatype.equalsIgnoreCase("FLOAT")) {
						query.setParameter(index, (float) queryCriteriaJSONObject.getDouble("value"));
					} else if (datatype.equalsIgnoreCase("BIGINT") || datatype.equalsIgnoreCase("MEDIUMINT") || datatype.equalsIgnoreCase("LONG")
							|| datatype.equalsIgnoreCase("MEDIUMINT")) {
						query.setParameter(index, queryCriteriaJSONObject.getLong("value"));
					} else if (datatype.equalsIgnoreCase("DECIMAL")) {
						query.setParameter(index, new BigDecimal(queryCriteriaJSONObject.getDouble("value")));
					}
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			throw new Exception("Exception in setting Query Parameter:" + e.getMessage());
		}
	}

	/*
	 * Used to set User data in Query Criteria
	 */

	/*
	 * this method is used to convert long datetime value to sql.TimeStamp
	 */
	private Timestamp covertValueToTimeStamp(final long timestamp) {
		Timestamp date = null;
		try {
			date = new Timestamp(timestamp);
		} catch (final Exception ex) {

		}
		return date;
	}

	/*
	 * this method is used to convert long date value to sql.TimeStamp
	 */
	private Date covertValueToDate(final long datevalue) {
		Date date = null;
		try {
			date = new Date(datevalue);
		} catch (final Exception ex) {

		}
		return date;
	}

	@Override
	public List getAllResultOfQuery(final String queryString, final JSONArray queryCriteria, final int queryType) throws Exception {
		List<Object[]> result = null;
		try {
			final javax.persistence.EntityManager entityManager = emfResource.getResource();
			/*
			 * Query queryTotal = entityManager.createQuery(queryString);
			 * setQueryCriteria(queryCriteria, queryTotal, queryString);
			 */

			Query queryTotal = null;
			if (queryType == 3) {
				queryTotal = entityManager.createNativeQuery(queryString);
				setQueryCriteriaForNativeQuery(queryCriteria, queryTotal, queryString);
			} else {
				queryTotal = entityManager.createQuery(queryString);
				setQueryCriteria(queryCriteria, queryTotal, queryString);
			}
			result = queryTotal.getResultList();
		} catch (final Exception e) {
			e.printStackTrace();
			throw new Exception("Exception occure while Excecuting total record query:" + e.getMessage());
		}
		return result;
	}

	@Override
	public List<Object[]> excecuteQuery(final String queryString, final JSONArray queryCriteria, final int queryType) throws Exception {
		List<Object[]> result = null;
		try {
			final javax.persistence.EntityManager entityManager = emfResource.getResource();
			Query query = null;
			if (queryType == 3) {
				query = entityManager.createNativeQuery(queryString);
				setQueryCriteriaForNativeQuery(queryCriteria, query, queryString);
			} else {
				query = entityManager.createQuery(queryString);
				setQueryCriteria(queryCriteria, query, queryString);
			}
			result = query.getResultList();
		} catch (final Exception e) {
			e.printStackTrace();
			throw new Exception("Exception occure while Excecuting query:" + e.getMessage());
		}
		return result;
	}

	/** use to execute SQL Query by passing actaul query and parameter of query */
	@Override
	@Transactional
	public JSONObject excecuteSqlQuery(final String query, final JSONArray parameterArray) throws Exception {
		try {
			final JSONObject result = jdbcTemplate.query(query, new PreparedStatementSetter() {
				@Override
				public void setValues(final PreparedStatement preparedStatement) throws SQLException {
					for (int i = 0; i < parameterArray.size(); i++) {
						try {
							final JSONObject param = (JSONObject) parameterArray.get(i);
							final JSONObject input = new JSONObject(param.toString());
							setSQLQueryParameter(preparedStatement, input);
						} catch (final Exception e) {
							if (e instanceof IllegalArgumentException) {
								throw new IllegalArgumentException(e.getMessage());
							} else if (e instanceof atg.taglib.json.util.JSONException) {
								throw new SQLException("Data type and field value does not perfectly matched <br>Re-enter the value matched with selected datatype");
							} else if (e instanceof java.text.ParseException) {
								throw new SQLException(
										e.getMessage()
										+ "<br>You must enter the date in valid format only. <br>Valid date format : dd-MM-yyyy <br>Valid datetime or timestamp format : dd-MM-yyyy HH:mm:ss");
							} else if (e instanceof SQLSyntaxErrorException) {
								throw new SQLSyntaxErrorException(e.getMessage());
							} else {
								e.printStackTrace();
								throw new SQLException("Exception occure while excecuting query : " + e.getMessage());
							}
						}
					}
				}
			}, new ResultSetExtractor<JSONObject>() {
				@Override
				public JSONObject extractData(final ResultSet rs) throws SQLException, DataAccessException {
					JSONObject result = null;
					try {
						result = prepareResultantRows(rs);
					} catch (final JSONException e) {
					}
					return result;
				}
			});
			return result;
		} catch (final Exception e) {
			if (e instanceof IllegalArgumentException) {
				throw new IllegalArgumentException(e.getMessage());
			} else if (e instanceof atg.taglib.json.util.JSONException) {
				throw new SQLException("Data type and field value does not perfectly matched <br>Re-enter the value matched with selected datatype");
			} else if (e instanceof java.text.ParseException) {
				throw new SQLException(e.getMessage()
						+ "<br>You must enter the date in valid format only. <br>Valid date format : dd-MM-yyyy <br>Valid datetime or timestamp format : dd-MM-yyyy HH:mm:ss");
			} else if (e instanceof SQLSyntaxErrorException) {
				throw new SQLSyntaxErrorException(e.getMessage());
			} else {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		}
	}

	/* Use to get the query metadata by passin query and query parameter */
	@Override
	@Transactional
	public JSONArray getQueryMetadata(final String query, final JSONArray parameterArray) throws Exception {
		try {
			final JSONArray result = jdbcTemplate.query(query, new PreparedStatementSetter() {
				@Override
				public void setValues(final PreparedStatement preparedStatement) throws SQLException {
					for (int i = 0; i < parameterArray.size(); i++) {
						try {
							final JSONObject param = (JSONObject) parameterArray.get(i);
							final JSONObject input = new JSONObject(param.toString());
							setSQLQueryParameter(preparedStatement, input);
						} catch (final Exception e) {
							if (e instanceof IllegalArgumentException) {
								throw new IllegalArgumentException(e.getMessage());
							} else if (e instanceof atg.taglib.json.util.JSONException) {
								throw new SQLException("Data type and field value does not perfectly matched <br>Re-enter the value matched with selected datatype");
							} else if (e instanceof java.text.ParseException) {
								throw new SQLException(
										e.getMessage()
										+ "<br>You must enter the date in valid format only. <br>Valid date format : dd-MM-yyyy <br>Valid datetime or timestamp format : dd-MM-yyyy HH:mm:ss");
							} else if (e instanceof SQLSyntaxErrorException) {
								throw new SQLSyntaxErrorException(e.getMessage());
							} else {
								e.printStackTrace();
								throw new SQLException("Exception occure while excecuting query : " + e.getMessage());
							}
						}
					}
				}
			}, new ResultSetExtractor<JSONArray>() {
				@Override
				public JSONArray extractData(final ResultSet rs) throws SQLException, DataAccessException {
					JSONArray result = new JSONArray();
					try {
						result = prepareQueryMetaData(rs);
					} catch (final JSONException e) {
					}
					return result;
				}
			});
			return result;
		} catch (final Exception e) {
			if (e instanceof IllegalArgumentException) {
				throw new IllegalArgumentException(e.getMessage());
			} else if (e instanceof atg.taglib.json.util.JSONException) {
				throw new SQLException("Data type and field value does not perfectly matched <br>Re-enter the value matched with selected datatype");
			} else if (e instanceof java.text.ParseException) {
				throw new SQLException(e.getMessage()
						+ "<br>You must enter the date in valid format only. <br>Valid date format : dd-MM-yyyy <br>Valid datetime or timestamp format : dd-MM-yyyy HH:mm:ss");
			} else if (e instanceof SQLSyntaxErrorException) {
				throw new SQLSyntaxErrorException(e.getMessage());
			} else {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		}
	}

	private JSONObject prepareResultantRows(final ResultSet resultSet) throws SQLException, JSONException {
		final ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		final int columnCount = resultSetMetaData.getColumnCount();

		final JSONObject resultSetJSON = new JSONObject();
		final JSONArray fieldsJSON = new JSONArray();
		final JSONArray dataJSON = new JSONArray();
		for (int i = 1; i <= columnCount; i++) {
			fieldsJSON.add(resultSetMetaData.getColumnLabel(i));
		}
		resultSetJSON.put("fields", fieldsJSON);

		while (resultSet.next()) {
			final JSONObject row = new JSONObject();
			for (int i = 1; i <= columnCount; i++) {
				row.put(resultSetMetaData.getColumnLabel(i), resultSet.getObject(i));
			}
			dataJSON.add(row);
		}
		resultSetJSON.put("data", dataJSON);
		return resultSetJSON;
	}

	private JSONArray prepareQueryMetaData(final ResultSet resultSet) throws SQLException, JSONException {
		final ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

		final JSONArray jsonArray = new JSONArray();
		final int columnCount = resultSetMetaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			final JSONObject jsonObject = new JSONObject();
			final String fieldName = FwUtils.toCamelCase(resultSetMetaData.getColumnName(i));
			final String displayName = FwUtils.toInitCapWithSpace(resultSetMetaData.getColumnLabel(i));
			final String alias = FwUtils.toCamelCase(resultSetMetaData.getColumnLabel(i)) + "." + fieldName;

			jsonObject.put("fieldName", fieldName);
			jsonObject.put("name", fieldName);
			jsonObject.put("initParentAlias", resultSetMetaData.getTableName(i));
			jsonObject.put("alias", alias);
			jsonObject.put("displayName", displayName);
			jsonObject.put("fieldType", resultSetMetaData.getColumnClassName(i));
			jsonObject.put("dataType", resultSetMetaData.getColumnTypeName(i));
			jsonObject.put("xtype", "textfield");
			jsonObject.put("fieldSize", "" + resultSetMetaData.getPrecision(i));
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	/**
	 * METHOD IS USED TO SET THE DYNAMIC PARAMETERS IN QUERY
	 *
	 * @throws Exception
	 */
	private void setSQLQueryParameter(final PreparedStatement preparedStatement, final JSONObject input) throws Exception {
		/**
		 * FOR THE FIELD WHICH IS STATIC WILL BE SKIPPED HERE AND USED TO SET
		 * CRITERIA IN CONVERTED SQL
		 */
		final int paramIndex = input.getInt("index");
		final String fieldDataType = input.getString("fieldType").trim();
		switch (fieldDataType) {
		case "java.lang.String":
			preparedStatement.setObject(paramIndex, input.getString("value"));
			break;
		case "java.lang.Short":
			preparedStatement.setShort(paramIndex, (short) input.getInt("value"));
			break;
		case "java.lang.Integer":
			preparedStatement.setInt(paramIndex, input.getInt("value"));
			break;
		case "java.lang.Long":
			preparedStatement.setLong(paramIndex, input.getLong("value"));
			break;
		case "java.lang.Float":
			preparedStatement.setFloat(paramIndex, (float) input.getDouble("value"));
			break;
		case "java.lang.Double":
			preparedStatement.setDouble(paramIndex, input.getDouble("value"));
			break;
		case "java.math.BigDecimal":
			preparedStatement.setBigDecimal(paramIndex, new BigDecimal(input.getDouble("value")));
			break;
		case "java.lang.Boolean":
			preparedStatement.setBoolean(paramIndex, input.getBoolean("value"));
			break;
		case "java.sql.Timestamp":
			preparedStatement.setTimestamp(paramIndex, queryCommonsUtility.convertValueToTimeStamp(input.getString("value")));
			break;
		case "java.sql.Date":
			preparedStatement.setDate(paramIndex, queryCommonsUtility.convertValueToDate(input.getString("value")));
			break;
		default:
			preparedStatement.setObject(paramIndex, input.get("value"));
			break;
		}
	}

	private DatabaseQuery getDatabaseQueryObject(final String jpql) throws Exception {
		final javax.persistence.EntityManager entityManager = emfResource.getResource();
		final Query query = entityManager.createQuery(jpql);
		// Session session =
		// entityManager.unwrap(JpaEntityManager.class).getActiveSession();
		final DatabaseQuery databaseQuery = ((EJBQueryImpl) query).getDatabaseQuery();
		// databaseQuery.prepareCall(session, new DatabaseRecord());
		// String sqlString = databaseQuery.getSQLString();
		return databaseQuery;
	}

	// @Override
	// public List<ArtQuery> findSqlFromJPQL() {
	//
	// javax.persistence.EntityManager entityManager =
	// emfResource.getResource();
	//
	// String selectQuery =
	// "SELECT query FROM ArtQuery query WHERE query.activeStatus = :activeStatus ";//
	// AND
	// // query.sqlQuery
	// // IS
	// // EMPTY";
	// Query query = entityManager.createQuery(selectQuery);
	//
	// query.setParameter("activeStatus", true);
	// List<ArtQuery> lst = query.getResultList();
	// List<ArtQuery> returnList = new ArrayList<ArtQuery>();
	// for (ArtQuery artQuery : lst) {
	// if (artQuery.getSqlQuery() == null || artQuery.getSqlQuery().length() ==
	// 0) {
	// returnList.add(artQuery);
	// }
	// }
	// return returnList;
	// }

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.athena.server.dataengine.repository.JpqlToSQLConverterRepository#
	 * updateSQLFromJPQL(com.athena.shared.dataengine.query.ArtQuery)
	 */
	// @Transactional
	// @Override
	// public void updateSQLFromJPQL(ArtQuery artquery) {
	// javax.persistence.EntityManager entityManager =
	// emfResource.getResource();
	// try {
	// Query query1 = entityManager.createQuery(artquery.getJpqlQuery());
	// DatabaseQuery databaseQuery = ((EJBQueryImpl) query1).getDatabaseQuery();
	// String sqlString = databaseQuery.getSQLString();
	// artquery.setSqlQuery(sqlString);
	// ArtQuery aaaa = entityManager.merge(artquery);
	// System.out.println(aaaa.getSqlQuery());
	// } catch (Exception ex) {
	// System.err.println("Somthing is wrong with query Id:" +
	// artquery.getQueryId());
	// ex.printStackTrace();
	// }
	// }

	public List<Object> getSQLParameterList(final ArtQueryInterface artquery) {
		List<Object> lstParams = new ArrayList<Object>();
		final javax.persistence.EntityManager entityManager = emfResource.getResource();
		try {
			final Query query1 = entityManager.createQuery(artquery.getJpqlQuery());
			final DatabaseQuery databaseQuery = ((EJBQueryImpl) query1).getDatabaseQuery();
			lstParams = databaseQuery.getCall().getParameters();
		} catch (final Exception ex) {
			System.err.println("Somthing is wrong with query Id:" + artquery.getQueryId());
			ex.printStackTrace();
		}

		return lstParams;
	}
}
