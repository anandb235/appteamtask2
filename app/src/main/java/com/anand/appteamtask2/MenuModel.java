package com.anand.appteamtask2;

import java.util.List;

public class MenuModel {
    List<MenuResults> menuItems;

    public MenuModel(List<MenuResults> menuItems) {
        this.menuItems = menuItems;
    }

    public List<MenuResults> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuResults> menuItems) {
        this.menuItems = menuItems;
    }
}
