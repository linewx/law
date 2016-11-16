package com.linewx.parser.state;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by luganlin on 11/16/16.
 */
public class CourtState implements ParseState{
    private Map<String, ParseCondition> conditions = new LinkedHashMap<>();

    CourtState() {
        ParseCondition plaintiffCondition = new ParseCondition(
            "法院$",
            "^原告"
        );
        conditions.put("plaintiff", plaintiffCondition);

    }

    @Override
    public String getState() {
        return "count";
    }

    @Override
    public void onEntry(ParseContext context) {
        //do something exit callback
    }

    @Override
    public void onExit(ParseContext context) {
        //do something exit callback
    }

    @Override
    public void onStay(ParseContext context) {
        //do something say callback
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
