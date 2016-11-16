package com.linewx.parser.state;

/**
 * Created by luganlin on 11/16/16.
 */
public class ParseCondition {
    String preStateMather;
    String curStateMather;

    ParseCondition(String preStateMather, String curStateMather) {
        this.preStateMather = preStateMather;
        this.curStateMather = curStateMather;
    }

    Boolean match(ParseContext context) {
        return true;
    }
}
