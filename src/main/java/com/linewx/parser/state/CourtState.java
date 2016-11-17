package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;
import com.linewx.parser.ParseContext;

/**
 * Created by luganlin on 11/16/16.
 */
public class CourtState extends AbstractParseState implements ParseState{

    public CourtState() {
        ParseCondition verdictTransformCondition = new ParseCondition(
            ".*法院$",
            ".*判 决 书$"
        );
        conditions.put("verdict", verdictTransformCondition);
    }

    @Override
    public String getState() {
        return "court";
    }

    @Override
    public void onExit(ParseContext context) {
        //do something exit callback
        context.setCourt(context.getPreStatement());
    }

}
