package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;
import com.linewx.parser.ParseContext;

/**
 * Created by luganlin on 11/16/16.
 */
public class NumberState extends AbstractParseState implements ParseState{

    public NumberState() {
        ParseCondition accuserCondition = new ParseCondition(
            null,
            "^原告.*"
        );
        conditions.put("accuser", accuserCondition);
    }

    @Override
    public String getState() {
        return "number";
    }

    @Override
    public void onEntry(ParseContext context) {
        //do something exit callback
        context.setNumber(context.getCurrentStatement());
    }

}
