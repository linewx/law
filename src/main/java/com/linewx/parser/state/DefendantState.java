package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;
import com.linewx.parser.ParseContext;

/**
 * Created by luganlin on 11/16/16.
 */
public class DefendantState extends AbstractParseState implements ParseState{

    public DefendantState() {
        ParseCondition condition = new ParseCondition(
            null,
            "^原告.*"
        );
        conditions.put("content", condition);
    }

    @Override
    public String getState() {
        return "defendant";
    }

    @Override
    public void onEntry(ParseContext context) {
        //do something exit callback
        context.setNumber(context.getCurrentStatement());
    }

}
