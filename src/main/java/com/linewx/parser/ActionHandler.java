package com.linewx.parser;

import com.linewx.parser.action.ActionTemplate;
import com.linewx.parser.action.setFieldActionTemplate;
import com.linewx.parser.action.setFieldWithRegActionTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lugan on 11/18/2016.
 */
public class ActionHandler {
    Map<String, ActionTemplate> actions = new HashMap<>();

    ActionHandler() {
        actions.put("setField", new setFieldActionTemplate());
        actions.put("setFieldWithReg", new setFieldWithRegActionTemplate());
    }

    public void execute(ParseContext context, String actionName, List<String> parameters) {
        ActionTemplate action = actions.get(actionName);
        action.execute(context, parameters);
    }
}