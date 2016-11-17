package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;
import com.linewx.parser.ParseContext;

/**
 * Created by luganlin on 11/16/16.
 */
public class DefendantAgentState extends AbstractParseState implements ParseState{

    public DefendantAgentState() {
        ParseCondition condition = new ParseCondition(
            null,
            ".*委托搭理人$"
        );
        conditions.put("defendantagent", condition);
    }

    @Override
    public String getState() {
        return "defendantagent";
    }

    @Override
    public void onEntry(ParseContext context) {
        //do something exit callback
        context.setNumber(context.getCurrentStatement());
    }

}
