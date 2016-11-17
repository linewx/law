package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;

/**
 * Created by luganlin on 11/16/16.
 */
public class JurorState extends AbstractParseState implements ParseState{

    public JurorState() {
        ParseCondition condition = new ParseCondition(
            null,
            "^二.*"
        );
        conditions.put("date", condition);
    }

    @Override
    public String getState() {
        return "juror";
    }


}
