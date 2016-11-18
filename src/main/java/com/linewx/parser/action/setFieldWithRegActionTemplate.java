package com.linewx.parser.action;

import com.linewx.parser.ParseContext;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lugan on 11/18/2016.
 */
public class setFieldWithRegActionTemplate implements ActionTemplate{
    public void execute(ParseContext context, List<String> parameters) {
        if(parameters != null) {
            int length = parameters.size();
            if (length == 3) {
                String field = parameters.get(0);
                String statementContext = parameters.get(1);
                String condition = parameters.get(2);

                String statement = "";
                Pattern pattern = Pattern.compile(condition);
                if (statementContext.equals("pre")) {
                    statement = context.getPreStatement();
                }else if(statementContext.equals("cur")) {
                    statement = context.getCurrentStatement();
                }
                Matcher matcher = pattern.matcher(statement);

                if (matcher.find()) {
                    context.addResult(field, matcher.group(1));
                }
            }
        }

    }
}
