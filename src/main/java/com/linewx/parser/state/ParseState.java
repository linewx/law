package com.linewx.parser.state;

/**
 * Created by luganlin on 11/16/16.
 */
public interface ParseState {
    String  getState();

    void onEntry(ParseContext context);

    void onExit(ParseContext context);

    void onStay(ParseContext context);

    String transform(ParseContext context);

}
