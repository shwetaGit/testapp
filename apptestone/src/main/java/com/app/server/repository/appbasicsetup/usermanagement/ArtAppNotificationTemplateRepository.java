package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.shared.appbasicsetup.usermanagement.ArtAppNotificationTemplate;

public interface ArtAppNotificationTemplateRepository {

	ArtAppNotificationTemplate findById(String templateId) throws Exception;
}
