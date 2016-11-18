package com.linewx.parser;
import java.util.regex.*;

/**
 * Created by luganlin on 11/16/16.
 */
public class ParseCondition {
   private Pattern preStatePattern;
   private Pattern curStatePattern;

    public ParseCondition(String preStatePattern, String curStatePattern) {
        if (preStatePattern == null) {
            this.preStatePattern = null;
        }else {
            this.preStatePattern = Pattern.compile(preStatePattern);
        }
        if (curStatePattern == null) {
            this.curStatePattern = null;
        }else {
            this.curStatePattern = Pattern.compile(curStatePattern);
        }

    }

    public Boolean match(ParseContext context) {
        Boolean preMatch;
        Boolean curMatch;
        if (preStatePattern == null) {
            preMatch = true;
        }else {
            preMatch = preStatePattern.matcher(context.getPreStatement()).matches();
        }

        if (curStatePattern == null) {
            curMatch = true;
        }else {
            curMatch = curStatePattern.matcher(context.getCurrentStatement()).matches();
        }
        return preMatch && curMatch;
    }


}
