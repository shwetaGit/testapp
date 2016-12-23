package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.ArtDocumentTemplate;

public interface ArtDocumentTemplateRepository {
	ArtDocumentTemplate findById(final String docTempId) throws Exception;
}
