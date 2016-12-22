package com.app.server.repository.appinsight.alarms;
import com.app.shared.appinsight.alarms.ArtDomain;

import java.util.List;

public interface ArtDomainRepository {

	void save(ArtDomain artDomain);

	void update(ArtDomain artDomain);
	
	List<ArtDomain> findAll();
	
	List<ArtDomain> findByArtBoundedContext(String boundedContextId);

	ArtDomain findById(String domainId);	

	ArtDomain findByPrefix(String prefix);

}
