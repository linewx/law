package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;
import com.linewx.parser.ParseContext;

/**
 * Created by luganlin on 11/16/16.
 */
public class AccuserState extends AbstractParseState implements ParseState{

    public AccuserState() {

        //tranformation condtion
        ParseCondition condition = new ParseCondition(
            null,
            "^被告.*"
        );
        conditions.put("defendant", condition);
    }

    @Override
    public String getState() {
        return "accuser";
    }
}
