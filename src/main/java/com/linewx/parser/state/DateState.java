package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;

/**
 * Created by luganlin on 11/16/16.
 */
public class DateState extends AbstractParseState implements ParseState{

    public DateState() {
        ParseCondition condition = new ParseCondition(
            null,
            "^书　记　员.*"
        );
        conditions.put("clerk", condition);
    }

    @Override
    public String getState() {
        return "date";
    }


}
