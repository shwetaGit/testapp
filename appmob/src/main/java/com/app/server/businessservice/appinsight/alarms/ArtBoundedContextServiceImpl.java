package com.app.server.businessservice.appinsight.alarms;
import com.app.shared.appinsight.alarms.ArtBoundedContext;
import com.app.server.repository.appinsight.alarms.ArtBoundedContextRepository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Service for ArtBoundedContext Entity", complexity = Complexity.LOW)
@RequestMapping("/ArtBoundedContextService")
public class ArtBoundedContextServiceImpl implements ArtBoundedContextService {

	@Autowired
	private ArtBoundedContextRepository artBoundedContextRepository;

	@RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
	@Override
	public String findAll() {
		StringBuilder jsonData = new StringBuilder();
		List<ArtBoundedContext> allBoundedContexts = artBoundedContextRepository.findAll();
		jsonData.append("{").append(" 'boundedcontexts' : [ ");

		for (ArtBoundedContext artBoundedContext : allBoundedContexts) {
			jsonData.append(artBoundedContext.toJSON() + ",");
		}
		jsonData.deleteCharAt(jsonData.length() - 1);
		jsonData.append("]").append("}");

		return jsonData.toString();
	}
	
	@RequestMapping(value = "/findById", consumes = "application/json", method = RequestMethod.GET)
	@Override
	public ArtBoundedContext findById(String boundedContextId) {
		ArtBoundedContext boundedContext = artBoundedContextRepository.findById(boundedContextId);
		if(boundedContext!=null){
			return boundedContext;
		}
		return null;
	}
	
	@RequestMapping(value = "/findByPrefix", consumes = "application/json", method = RequestMethod.GET)
	@Override
	public ArtBoundedContext findByPrefix(String prefix) {
		ArtBoundedContext artBoundedContext = artBoundedContextRepository.findByBoundedContextPrefix(prefix);
		if(artBoundedContext != null) {
			return artBoundedContext;
		}
		return null;
	}
	
}
