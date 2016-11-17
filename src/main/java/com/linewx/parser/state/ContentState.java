package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;
import com.linewx.parser.ParseContext;

/**
 * Created by luganlin on 11/16/16.
 */
public class ContentState extends AbstractParseState implements ParseState{

    public ContentState() {
        ParseCondition condition = new ParseCondition(
            null,
            "^审\\　判\\　长.*"
        );
        conditions.put("judge", condition);
    }

    @Override
    public String getState() {
        return "content";
    }


}
