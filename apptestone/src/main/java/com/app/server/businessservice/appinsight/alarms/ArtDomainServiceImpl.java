package com.app.server.businessservice.appinsight.alarms;import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;

import com.app.server.repository.appinsight.alarms.ArtDomainRepository;

import com.app.shared.appinsight.alarms.ArtDomain;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "3", comments = "Service for ArtDomain Entity", complexity = Complexity.LOW)
@RequestMapping("/ArtDomainService")
public class ArtDomainServiceImpl implements ArtDomainService {
	
	@Autowired
	private ArtDomainRepository artDomainRepository;

	@RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
	@Override
	public List<ArtDomain> findAll() {
		List<ArtDomain> artDomains = artDomainRepository.findAll();
		return (artDomains.size() > 0) ? artDomains : null;
	}
	
	@RequestMapping(value = "/findByArtBoundedContext", consumes = "application/json", method = RequestMethod.GET)
	@Override
	public List<ArtDomain> findByArtBoundedContext(String boundedContextId) {
		List<ArtDomain> artDomains = artDomainRepository.findByArtBoundedContext(boundedContextId);
		return (artDomains.size() > 0) ? artDomains : null;
	}
	
	@RequestMapping(value = "/findById", consumes = "application/json", method = RequestMethod.GET)
	@Override
	public ArtDomain findById(String domainId) {
		ArtDomain artDomain = artDomainRepository.findById(domainId);
		if(artDomain != null) {
			return artDomain;
		}
		return null;
	}
	
	@RequestMapping(value = "/findByPrefix", consumes = "application/json", method = RequestMethod.GET)
	@Override
	public ArtDomain findByPrefix(String prefix) {
		ArtDomain artDomain = artDomainRepository.findByPrefix(prefix);
		if(artDomain != null) {
			return artDomain;
		}
		return null;
	}

}
