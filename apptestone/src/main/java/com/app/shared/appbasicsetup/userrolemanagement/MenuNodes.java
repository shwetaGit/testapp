package com.app.shared.appbasicsetup.userrolemanagement;
import java.util.ArrayList;
import java.util.List;

public class MenuNodes {
	private final Menu menu;

	private List<MenuNodes> nodes = new ArrayList<MenuNodes>();

	public MenuNodes(final Menu menu) {
		this.menu = menu;
	}

	public void addMenu(final MenuNodes menu) {
		nodes.add(menu);
	}

	public Menu getMenu() {
		return menu;
	}

	public List<MenuNodes> getList() {
		return nodes;
	}

	@Override
	public String toString() {
		return menu.toString();
	}

}
