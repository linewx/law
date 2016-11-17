package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;
import com.linewx.parser.ParseContext;

/**
 * Created by luganlin on 11/16/16.
 */
public class AccuserAgentState extends AbstractParseState implements ParseState{

    public AccuserAgentState() {
        ParseCondition condition = new ParseCondition(
            null,
            ".*被告$"
        );
        conditions.put("defendant", condition);
    }

    @Override
    public String getState() {
        return "accuseragent";
    }

    @Override
    public void onEntry(ParseContext context) {

    }

}
