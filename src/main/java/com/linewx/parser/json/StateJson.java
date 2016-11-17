package com.linewx.parser.json;

/**
 * Created by luganlin on 11/17/16.
 */
public class StateJson {
    private String id;
    private ActionJson onEntry;
    private ActionJson onStay;
    private ActionJson onExit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ActionJson getOnEntry() {
        return onEntry;
    }

    public void setOnEntry(ActionJson onEntry) {
        this.onEntry = onEntry;
    }

    public ActionJson getOnStay() {
        return onStay;
    }

    public void setOnStay(ActionJson onStay) {
        this.onStay = onStay;
    }

    public ActionJson getOnExit() {
        return onExit;
    }

    public void setOnExit(ActionJson onExit) {
        this.onExit = onExit;
    }
}
