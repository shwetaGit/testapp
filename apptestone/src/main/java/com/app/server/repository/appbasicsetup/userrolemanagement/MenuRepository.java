package com.app.server.repository.appbasicsetup.userrolemanagement;
import java.util.List;

public interface MenuRepository {

	List<String> getHeaderByUser(String userId, Integer appType) throws Exception;

	List<Object[]> getSubMenus(String header, String userId, Integer appType) throws Exception;

	List<String> getHeaderByUser(String userId) throws Exception;

	List<Object[]> getSubMenus(String header, String userId) throws Exception;
}
