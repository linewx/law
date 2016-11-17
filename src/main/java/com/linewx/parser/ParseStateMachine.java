package com.linewx.parser;

import com.linewx.parser.state.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luganlin on 11/16/16.
 */
class ParseStateMachine {
    Map<String, ParseState> parseStateMap = new HashMap<>();

    ParseStateMachine() {
        parseStateMap.put("start", new StartState());
        parseStateMap.put("court", new CourtState());
        parseStateMap.put("verdict", new VerdictState());
        parseStateMap.put("number", new NumberState());
        parseStateMap.put("accuser", new AccuserState());
        parseStateMap.put("accuseragent", new AccuserAgentState());
        parseStateMap.put("defendant", new DefendantState());
        parseStateMap.put("defendantagent", new DefendantAgentState());
        parseStateMap.put("content", new ContentState());
        parseStateMap.put("judge", new JudgeState());
        parseStateMap.put("juror", new JurorState());
        parseStateMap.put("date", new DateState());
        parseStateMap.put("clerk", new ClerkState());
        parseStateMap.put("attached", new AttachedState());



    }

    void parse(ParseContext context, List<String> content) {
       for (String statement: content) {
           System.out.println(statement);
           stepContext(context, statement);
           parse(context);
       }
    }

    void stepContext(ParseContext context, String statement) {
        if (statement == null || statement.isEmpty()) {
            return;
        }

        context.setPreStatement(context.getCurrentStatement());
        context.setCurrentStatement(statement);
    }

    void parse(ParseContext context) {
        if (context.getCurrentStatement() == null || context.getCurrentStatement().isEmpty()) {
            return;
        }
        ParseState currentState = parseStateMap.get(context.getCurrentState());
        String nextStateName = currentState.transform(context);
        if (nextStateName == null) {
            throw new RuntimeException("state is null");
        }else {
            ParseState nextState = parseStateMap.get(nextStateName);
            if (nextState == null) {
                throw new RuntimeException("unknown state " + nextStateName);
            }else {
                if (nextStateName.equals(currentState.getState())) {
                    currentState.onStay(context);
                }else {
                    currentState.onExit(context);
                    context.clearTempContent();
                    context.setCurrentState(nextStateName);
                    System.out.println(nextStateName);
                    nextState.onEntry(context);
                }
            }
        }
    }
}
