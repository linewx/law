package com.linewx.parser.action;

import com.linewx.parser.ParseContext;

import java.util.List;

/**
 * Created by lugan on 11/18/2016.
 */
public interface ActionTemplate {
    void execute(ParseContext context, List<String> parameters);
}