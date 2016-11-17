package com.linewx.parser.state;


import com.linewx.parser.ParseCondition;

/**
 * Created by luganlin on 11/16/16.
 */
public class JudgeState extends AbstractParseState implements ParseState{

    public JudgeState() {
        ParseCondition condition = new ParseCondition(
            null,
            "^人民陪审员.*"
        );
        conditions.put("juror", condition);
    }

    @Override
    public String getState() {
        return "judge";
    }


}
