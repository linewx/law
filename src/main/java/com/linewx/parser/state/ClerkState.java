package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;

/**
 * Created by luganlin on 11/16/16.
 */
public class ClerkState extends AbstractParseState implements ParseState{

    public ClerkState() {
        ParseCondition condition = new ParseCondition(
            null,
            "^附.*"
        );
        conditions.put("attached", condition);
    }

    @Override
    public String getState() {
        return "clerk";
    }


}
