package com.app.server.repository.appbasicsetup.aaa;
import com.app.shared.appbasicsetup.aaa.ArtUserLastStatus;

import java.util.List;

public interface ArtUserStatusRepository {

	ArtUserLastStatus findById(final String id) throws Exception;

	void save(final ArtUserLastStatus userLastStatus) throws Exception;

	void update(final ArtUserLastStatus userLastStatus) throws Exception;

	void delete(final String id) throws Exception;

	List<ArtUserLastStatus> findByUserId(final String userId) throws Exception;

	List<ArtUserLastStatus> findByUserMenuId(final String userId, final String menuId) throws Exception;
}
