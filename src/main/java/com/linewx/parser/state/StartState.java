package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;

/**
 * Created by luganlin on 11/16/16.
 */
public class StartState extends AbstractParseState implements ParseState{

    public StartState() {
        super();
        ParseCondition courtCondition = new ParseCondition(
                null,
                ".*法院$"
        );
        conditions.put("court", courtCondition);
    }

    @Override
    public String getState() {
        return "start";
    }

}
