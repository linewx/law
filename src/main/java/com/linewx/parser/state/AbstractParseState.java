package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;
import com.linewx.parser.ParseContext;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by luganlin on 11/16/16.
 */
public abstract class AbstractParseState implements ParseState{
    protected Map<String, ParseCondition> conditions = new LinkedHashMap<>();


    @Override
    public void onEntry(ParseContext context) {

    }

    @Override
    public void onExit(ParseContext context) {

    }

    @Override
    public void onStay(ParseContext context) {

    }

    @Override
    public String transform(ParseContext context) {
        for (Map.Entry<String, ParseCondition> condition : conditions.entrySet()) {
            if (condition.getValue().match(context)) {
                return condition.getKey();
            }
        }

        return getState();
    }
}
