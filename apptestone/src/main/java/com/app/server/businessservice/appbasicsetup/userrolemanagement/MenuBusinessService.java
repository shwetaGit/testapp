package com.app.server.businessservice.appbasicsetup.userrolemanagement;
import com.app.shared.appbasicsetup.userrolemanagement.MainMenuPage;


public interface MenuBusinessService {

	String getUserMenu(final String userid, final Integer appType) throws Exception;

	void storeMenu(final String userId, final String menuId, final String json) throws Exception;

	String fetchStoreMenus(final String userId) throws Exception;

	void deleteMenu(final String userId, final String menuId) throws Exception;

	MainMenuPage findMainScreenMenus(final String userId, final Integer appType) throws Exception;
	
//	String findMainScreenMenus(final String userId, final Integer appType) throws Exception;

}
