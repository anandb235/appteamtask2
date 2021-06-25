package com.anand.appteamtask2;

import java.util.List;

public class MenuInfoModel {
    List<MenuInfoResult> nutrition;

    public MenuInfoModel(List<MenuInfoResult> nutrition) {
        this.nutrition = nutrition;
    }

    public List<MenuInfoResult> getNutrition() {
        return nutrition;
    }

    public void setNutrition(List<MenuInfoResult> nutrition) {
        this.nutrition = nutrition;
    }
}
