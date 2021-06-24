package com.anand.appteamtask2;

import java.util.List;

public class Model {
    List<Result> result;

    public Model(List<Result> result) {
        this.result = result;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}