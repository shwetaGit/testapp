package com.app.shared.appbasicsetup.userrolemanagement;

import java.util.ArrayList;

public class MainMenuPage {
	
	private ArrayList<Menu> menus;
	
	private ArrayList<Menu> homeScreenMenus;
	
	private String menuCommands;

	public MainMenuPage(String menuCommands) { 
		this.setMenuCommands(menuCommands);
	}

	public ArrayList<Menu> getMenus() {
		return menus;
	}

	public void setMenus(ArrayList<Menu> menus) {
		this.menus = menus;
	}

	public ArrayList<Menu> getHomeScreenMenus() {
		return homeScreenMenus;
	}

	public void setHomeScreenMenus(ArrayList<Menu> homeScreenMenus) {
		this.homeScreenMenus = homeScreenMenus;
	}

	
	@Override
	public String toString() {
		return "MainMenuPage [menus=" + menus + ", homeScreenMenus=" + homeScreenMenus + "]";
	}

	public String getMenuCommands() {
		return menuCommands;
	}

	public void setMenuCommands(String menuCommands) {
		this.menuCommands = menuCommands;
	}
	
	
	
	
	
	

}
