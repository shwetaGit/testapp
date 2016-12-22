package com.app.server.repository.core;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;

@Repository
public class CommonUtilRepositoryImpl implements CommonUtilRepository {

	@Autowired
	private ResourceFactoryManagerHelper emfResource;

	@Override
	public List<Object> search(String repositoryName, Map<String, Object> fields, Map<String, String> fieldMetaData) throws Exception {
		javax.persistence.EntityManager emanager = emfResource.getResource();
		Query query = emanager.createNamedQuery("DefaultFinders");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> metaData = new HashMap<String, String>();
		metaData = fieldMetaData;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String inputStr = "01-01-1850";
		Date date = formatter.parse(inputStr);
		for (Map.Entry<String, String> entry : metaData.entrySet()) {
			for (Map.Entry<String, Object> entry1 : fields.entrySet()) {
				if (entry.getKey() == entry1.getKey()) {
					map.put(entry1.getKey(), entry1.getValue());
				} else {
					if (entry.getValue().equalsIgnoreCase("String"))
						map.put(entry.getKey(), "%");
					if (entry.getValue().equalsIgnoreCase("Integer"))
						map.put(entry.getKey(), -1);
					if (entry.getValue().equalsIgnoreCase("Date"))
						map.put(entry.getKey(), date);
				}
			}
		}
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		List<Object> list = query.getResultList();
		return list;
	}

	public Date setFormattedDate(String date) throws ParseException {
		javax.servlet.http.HttpServletRequest request = ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder
				.getRequestAttributes()).getRequest();
		javax.servlet.http.HttpSession session = request.getSession();
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd-M-yyyy HH:mm:ss z");
		formatter.setTimeZone(TimeZone.getTimeZone(session.getAttribute("timeZoneId").toString()));
		return formatter.parse(date);
	}

}
