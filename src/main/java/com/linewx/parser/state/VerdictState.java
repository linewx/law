package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;
import com.linewx.parser.ParseContext;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by luganlin on 11/16/16.
 */
public class VerdictState extends AbstractParseState implements ParseState{

    public VerdictState() {
        ParseCondition numberTransformCondition = new ParseCondition(
            ".*判 决 书$",
            ".*号$"
        );
        conditions.put("number", numberTransformCondition);
    }

    @Override
    public String getState() {
        return "verdict";
    }

    @Override
    public void onEntry(ParseContext context) {
        //do something exit callback
    }

    @Override
    public void onExit(ParseContext context) {
        //do something exit callback
        //context.setCourt(context.getPreStatement());
    }

    @Override
    public void onStay(ParseContext context) {
        //do something say callback
    }
}
