package com.app.shared.appbasicsetup.userrolemanagement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

public class MenuTreee {
	private List<Menu> menus = new ArrayList<>();
	private MenuNodes node = null;

	public MenuTreee(List<Menu> list) {
		this.menus = list;
	}

	/**
	 * createMenuPanel function to create a menu panel
	 */
	private void createMenuPanel() {
		HashMap<String, MenuNodes> map = new HashMap<String, MenuNodes>();
		for (Iterator<Menu> iterator = menus.iterator(); iterator.hasNext();) {
			Menu menu = (Menu) iterator.next();
			if (node == null) {
				node = new MenuNodes(menu);
				map.put(menu.getMenuTreeId(), node);
			} else {
				final String split[] = menu.getMenuTreeId().split("\\.");
				String check = "";
				for (int i = 0; i < split.length - 1; i++) {
					check = check + split[i] + ".";
				}
				if (map.get(check) != null) {
					MenuNodes root = (MenuNodes) map.get(check);
					MenuNodes inner = new MenuNodes(menu);
					root.addMenu(inner);
					map.put(menu.getMenuTreeId(), inner);
				}
			}
		}
	}

	/**
	 * createMenuTree function to create a Menu tree
	 */
	public JSONObject createMenuTree() throws JSONException {
		createMenuPanel();
		JSONObject json = new JSONObject();

		if (node != null) {
			json = node.getMenu().toJSON();
			final JSONArray array = createJson(new JSONArray(), node);

			if (array != null && !array.isEmpty()) {
				json.put("children", array);
				json.put("icon", "images/folder-database-icon.png");
				json.put("cls", "folderTitle");
				json.put("leaf", false);
			} else {
				json = new JSONObject();
			}
		}

		return json;
	}

	

	/**
	 * createJson function accept json and node and create json for menu leaf
	 * and children initialize the createJson with given configuration
	 * 
	 * @param :
	 *            json , node
	 * @returns : JSONArray
	 * @throws :
	 *             JSONException
	 */
	private JSONArray createJson(JSONArray json, MenuNodes node) throws JSONException {
		if (node.getMenu() != null) {
			if (node.getList().size() > 0) {
				for (Iterator<MenuNodes> iterator = node.getList().iterator(); iterator.hasNext();) {
					final MenuNodes type = (MenuNodes) iterator.next();

					if (type.getMenu() != null) {
						JSONObject inner = type.getMenu().toJSON();

						if (type.getList().size() > 0) {
							final JSONArray innerArray = createChilds(new JSONArray(), type);
							inner.put("children", innerArray);
							inner.put("leaf", false);
						} else {
							inner.put("leaf", true);
						}
						json.add(inner);
					}
				}
			}
		}
		return json;
	}
	
	

	/**
	 * createChilds function accept json, node and return json array for menu
	 * node initialize the createChilds with given configuration
	 * 
	 * @param :
	 *            json , node
	 * @returns JSONArray
	 * @throws :
	 *             JSONException
	 */
	private JSONArray createChilds(JSONArray json, MenuNodes node) throws JSONException {
		JSONObject inner = null;
		if (node.getList().size() > 0) {
			for (Iterator<MenuNodes> iterator = node.getList().iterator(); iterator.hasNext();) {
				MenuNodes type = (MenuNodes) iterator.next();
				if (type.getMenu() != null) {
					inner = type.getMenu().toJSON();
					if (type.getList().size() > 0) {
						final JSONArray innerArray = createJson(new JSONArray(), type);
						inner.put("children", innerArray);
						inner.put("leaf", false);
					} else {
						inner.put("leaf", true);
					}

					json.add(inner);
				}
			}
		}
		return json;
	}
	
	

	/**
	 * createMenuTree function to create a Menu tree
	 */
	public Menu createTree() throws Exception {
		createMenuPanel();
		Menu menuTreeObject = new Menu();

		if (node != null) {
			menuTreeObject = node.getMenu().toObject();
			final ArrayList<Menu> array = createObject(new ArrayList<Menu>(), node);
			
			if (array != null && !array.isEmpty()) {
				menuTreeObject.setChildren(array);
				menuTreeObject.setIcon("images/folder-database-icon.png"); 
				menuTreeObject.setCls("folderTitle"); 
				menuTreeObject.setLeaf(false); 
			} else {
				menuTreeObject = new Menu();
			}
		}

		return menuTreeObject;
	}
	
	

	private ArrayList<Menu> createObject(ArrayList<Menu> list, MenuNodes node) throws Exception {
		Menu menuTreeObject = null;
		if (node.getMenu() != null) {
			if (node.getList().size() > 0) {
				for (Iterator<MenuNodes> iterator = node.getList().iterator(); iterator.hasNext();) {
					final MenuNodes type = (MenuNodes) iterator.next();

					if (type.getMenu() != null) {
						 menuTreeObject = type.getMenu().toObject();
						 final ArrayList<Menu> array = createObjectChilds(new ArrayList<Menu>(), type);
						if (type.getList().size() > 0) {
							menuTreeObject.setChildren(array);
							menuTreeObject.setLeaf(false); 
						} else {
							menuTreeObject.setLeaf(true);
						}
						list.add(menuTreeObject);
					}
				}
			}
		}
		return list;
	}
	
	
	
	
	private ArrayList<Menu> createObjectChilds(ArrayList<Menu> list, MenuNodes node) throws Exception {
		
		Menu menuTreeObject = null;
		if (node.getList().size() > 0) {
			for (Iterator<MenuNodes> iterator = node.getList().iterator(); iterator.hasNext();) {
				MenuNodes type = (MenuNodes) iterator.next();
				if (type.getMenu() != null) {
					menuTreeObject = type.getMenu().toObject();
					final ArrayList<Menu> array = createObject(new ArrayList<Menu>(), type);
					if (type.getList().size() > 0) {
						menuTreeObject.setChildren(array);
						menuTreeObject.setLeaf(false);
					} else {
						menuTreeObject.setLeaf(true);
					}

					list.add(menuTreeObject);
				}
			}
		}
		return list;
	}



}
