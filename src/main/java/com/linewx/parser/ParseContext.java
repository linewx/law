package com.linewx.parser;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by luganlin on 11/16/16.
 */
public class ParseContext {
    //parse result
    private String court; //法院
    private String verdict; //判决书
    private String number; //案号
    private List<String> accusers = new LinkedList<>(); //原告
    private List<String> accuserAgents = new LinkedList<>(); //原告委托代理人
    private List<String> defendant = new LinkedList<>(); //被告
    private List<String> defendantAgents = new LinkedList<>(); //被告委托代理人
    private String judge; //审判长
    private List<String> jurors; //陪审员
    private String date; //日期
    private String clerk; //书记员
    private StringBuffer content = new StringBuffer();
    private StringBuffer attached = new StringBuffer();

    //parse state
    private String currentState;
    private String currentStatement;
    private String preStatement;
    private StringBuffer tempContent = new StringBuffer();


    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<String> getAccusers() {
        return accusers;
    }

    public void setAccusers(List<String> accusers) {
        this.accusers = accusers;
    }

    public List<String> getAccuserAgents() {
        return accuserAgents;
    }

    public void setAccuserAgents(List<String> accuserAgents) {
        this.accuserAgents = accuserAgents;
    }

    public List<String> getDefendant() {
        return defendant;
    }

    public void setDefendant(List<String> defendant) {
        this.defendant = defendant;
    }

    public List<String> getDefendantAgents() {
        return defendantAgents;
    }

    public void setDefendantAgents(List<String> defendantAgents) {
        this.defendantAgents = defendantAgents;
    }

    public StringBuffer getContent() {
        return content;
    }

    public void setContent(StringBuffer content) {
        this.content = content;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public List<String> getJurors() {
        return jurors;
    }

    public void setJurors(List<String> jurors) {
        this.jurors = jurors;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClerk() {
        return clerk;
    }

    public void setClerk(String clerk) {
        this.clerk = clerk;
    }

    public StringBuffer getAttached() {
        return attached;
    }

    public void setAttached(StringBuffer attached) {
        this.attached = attached;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getCurrentStatement() {
        return currentStatement;
    }

    public void setCurrentStatement(String currentStatement) {
        this.currentStatement = currentStatement;
    }

    public String getPreStatement() {
        return preStatement;
    }

    public void setPreStatement(String preStatement) {
        this.preStatement = preStatement;
    }

    public StringBuffer getTempContent() {
        return tempContent;
    }

    public void setTempContent(StringBuffer tempContent) {
        this.tempContent = tempContent;
    }

    public void addAccuser(String accuser) {
        this.accusers.add(accuser);
    }

    public void addAccuserAgent(String accuserAgent) {
        this.accuserAgents.add(accuserAgent);
    }

    public void addDefendant(String defendant) {
        this.defendant.add(defendant);
    }

    public void addDefendantAgent(String defendantAgent) {
        this.defendantAgents.add(defendantAgent);
    }

    public void addContent(String oneContent) {
        this.content.append(oneContent);
    }

    public void addAttached(String oneAttached) {
        this.attached.append(oneAttached);
    }

    public void addTempContent(String tempContent) {
        this.tempContent.append(tempContent);
    }

    public void clearTempContent() {
        this.tempContent.setLength(0);
    }
}
