package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;

/**
 * Created by luganlin on 11/16/16.
 */
public class AttachedState extends AbstractParseState implements ParseState{

    public AttachedState() {

    }

    @Override
    public String getState() {
        return "attached";
    }


}
