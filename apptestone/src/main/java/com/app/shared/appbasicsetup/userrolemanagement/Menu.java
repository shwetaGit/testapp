package com.app.shared.appbasicsetup.userrolemanagement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

public class Menu {
	private String menuId;
	private String menuTreeId;
	private String menuName;
	private String menuAction;
	private String menuIcon;
	private String userId;
	private String menuLabel;
	private String menuCommands;
	private String refId;
	private boolean autoSave;
	
	// Remove JSON Formatter 
	private ArrayList<Menu> children = new ArrayList<Menu>(); 
	private String icon;
	private String cls;
	private boolean leaf;
	
	

	private final Map<String, String> map = new HashMap<String, String>();

	public Menu() {
		super();
	}

	public Menu(final Long String, final String menuTreeId, final String menuName, final String menuAction) {
		super();
		this.menuTreeId = menuTreeId;
		this.menuName = menuName;
		this.menuAction = menuAction;
	}

	public Menu(final String menuId, final String menuTreeId, final String menuName, final String menuAction, final String menuIcon, final String menuLabel,
			final String menuCommands, final String refId, final boolean autoSave) {
		super();
		this.menuId = menuId;
		this.menuTreeId = menuTreeId;
		this.menuName = menuName;
		this.menuAction = menuAction;
		this.menuIcon = menuIcon;
		this.menuLabel = menuLabel;
		this.menuCommands = menuCommands;
		this.refId = refId;
		this.autoSave = autoSave;
		createMenuCommand(this.menuCommands);
	}

//	@Override
//	public String toString() {
//		return menuId + " " + menuName;
//	}

	public String getMenuTreeId() {
		return menuTreeId;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuTreeId=" + menuTreeId + ", menuName=" + menuName + ", menuAction="
				+ menuAction + ", menuIcon=" + menuIcon + ", userId=" + userId + ", menuLabel=" + menuLabel
				+ ", menuCommands=" + menuCommands + ", refId=" + refId + ", autoSave=" + autoSave + ","
			    + " icon=" + icon + ", cls=" + cls + ", leaf=" + leaf + ", children=" + children + "]";
	}

	public void setMenuTreeId(final String menuTreeId) {
		this.menuTreeId = menuTreeId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(final String menuName) {
		this.menuName = menuName;
	}

	public String getMenuAction() {
		return menuAction;
	}

	public void setMenuAction(final String menuAction) {
		this.menuAction = menuAction;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(final String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getMenuLabel() {
		return menuLabel;
	}

	public void setMenuLabel(final String menuLabel) {
		this.menuLabel = menuLabel;
	}

	public String getMenuCommands() {
		return menuCommands;
	}

	public void setMenuCommands(final String menuCommands) {
		this.menuCommands = menuCommands;
		createMenuCommand(menuCommands);
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(final String menuId) {
		this.menuId = menuId;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(final String refId) {
		this.refId = refId;
	}
	
	

	public boolean isAutoSave() {
		return autoSave;
	}

	public void setAutoSave(final boolean autoSave) {
		this.autoSave = autoSave;
	}

	public ArrayList<Menu> getChildren() { 
		return children;
	}

	public void setChildren(final ArrayList<Menu> children) { 
		this.children = children; 
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(final String icon) {
		this.icon = icon;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(final String cls) {
		this.cls = cls;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(final boolean leaf) {
		this.leaf = leaf;
	}

	/**
	 * createMenuCommand function accept menuCommand initialize the
	 * createMenuCommand with given configuration
	 * 
	 * @param : menuCommand
	 */
	private void createMenuCommand(final String menuCommand) {
		if (menuCommand != null && !menuCommand.isEmpty() && !menuCommand.equals(" ")) {
			final StringTokenizer token = new StringTokenizer(menuCommand, "#");
			while (token.hasMoreElements()) {
				final String command = (String) token.nextElement();

				final StringTokenizer keyValue = new StringTokenizer(command, "=");

				final String key = keyValue.hasMoreElements() ? (String) keyValue.nextElement() : "";
				final String value = keyValue.hasMoreElements() ? (String) keyValue.nextElement() : "";

				map.put(key.trim(), value.trim());

			}
		}
	}

	public String getCommandDetails(final String key) {
		return map.get(key);
	}

	public static void main(final String[] args) {
		new Menu().createMenuCommand("split = true#");
	}

	public JSONObject toJSON() throws JSONException {
		final JSONObject json = new JSONObject();

		json.put("menuId", menuId);
		json.put("menuTreeId", menuTreeId);
		json.put("menuName", menuName);
		json.put("menuAction", menuAction);
		json.put("menuIcon", menuIcon);
		json.put("userId", userId);
		json.put("menuLabel", menuLabel);
		json.put("text", menuLabel);
		json.put("refId", refId);
		json.put("autoSave", autoSave);
		json.put("menuCommands", menuCommands);

		return json;
	}
	
	
	
	public ArrayList<String> toList() throws Exception {
		final ArrayList<String> list = new ArrayList<String>();

		list.add(menuId);
		list.add(menuTreeId);
		list.add(menuName);
		list.add( menuAction);
		list.add(menuIcon);
		list.add(userId);
		list.add(menuLabel);
		list.add(menuLabel);
		list.add(refId);
		list.add("autoSave");
		list.add(menuCommands);

		return list;
	}
	
	
	public List<Menu>  listObject()  throws Exception{
		
		final Menu menu = new Menu(menuId, menuTreeId, menuName, menuAction, menuIcon, menuLabel, menuCommands, refId, autoSave); 
		menu.setMenuId(menuId);
		menu.setMenuTreeId(menuTreeId);
		menu.setMenuName(menuName);
		menu.setMenuAction(menuAction);
		menu.setMenuIcon(menuIcon);
		menu.setUserId(userId);
		menu.setMenuLabel(menuLabel);
		menu.setRefId(refId);
		menu.setMenuCommands(menuCommands);
		final List<Menu> listMenu = new ArrayList<Menu>();
		listMenu.add(menu);
		return listMenu;
		
		
	}
	
	public Menu toObject() throws Exception{
		final Menu menu = new Menu(menuId, menuTreeId, menuName, menuAction, menuIcon, menuLabel, menuCommands, refId, autoSave); 
		menu.setMenuId(menuId);
		menu.setMenuTreeId(menuTreeId);
		menu.setMenuName(menuName);
		menu.setMenuAction(menuAction);
		menu.setMenuIcon(menuIcon);
		menu.setUserId(userId);
		menu.setMenuLabel(menuLabel);
		menu.setRefId(refId);
		menu.setMenuCommands(menuCommands);
		return menu;
	}
}
