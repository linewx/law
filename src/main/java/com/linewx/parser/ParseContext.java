package com.linewx.parser;

import java.util.*;

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
    private Map<String, List<String>> results = new HashMap<>();
    private Map<String, List<String>> stateResults = new HashMap<>();
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

    public Map<String, List<String>> getResults() {
        return results;
    }

    public void setResults(Map<String, List<String>> results) {
        this.results = results;
    }

    public Map<String, List<String>> getStateResults() {
        return stateResults;
    }

    public void setStateResults(Map<String, List<String>> stateResults) {
        this.stateResults = stateResults;
    }

    public void addResult(String key, String value) {
        if (!results.containsKey(key)) {
            results.put(key, new ArrayList<>());
        }
        results.get(key).add(value);
    }

    public void addStateResult(String key, String value) {
        if (!stateResults.containsKey(key)) {
            stateResults.put(key, new ArrayList<>());
        }
        stateResults.get(key).add(value);
    }

    public void printResult() {
        for (Map.Entry<String, List<String>> oneResult : this.results.entrySet()) {
            System.out.println(oneResult.getKey() + ":" + String.join("|", oneResult.getValue()));
        }
    }

    public void validate() {
        calculate();
        //validateState();
        //validateLevel();

        //validateField(Arrays.asList("accuserLawyer"), true);

       /* List<String> level = this.getResults().get("level");
        if (level != null && level.size()==1 && (level.get(0).equals("1") || !level.get(0).equals("2"))){
            //only validate 初，终案号
            validateField(Arrays.asList("number"), false);
        }
*/
        printContext();

    }

    private void calculate() {
        try {
            List<String> costs = this.getResults().get("cost");
            if (costs == null || costs.size() != 1) {
                throw new RuntimeException("invalid cost");
            }else {
                this.addResult("amount", ProcessorHandler.execute("amount", costs.get(0)));
            }
        }catch(Exception e) {
            this.addResult("amount", "0");
        }

    }

    public void validateState() {
        if ((!this.getCurrentState().equals("clerk")) && (!this.getCurrentState().equals("attached") && !this.getCurrentState().equals("dateclerk"))) {
            System.out.println("################## state error ####################");
            printMessage();
            System.out.println("################## end state error ####################");
        }
    }

    public void validateLevel() {
        List<String> level = this.getResults().get("level");
        if (level == null || level.size() != 1 || (!level.get(0).equals("1") && !level.get(0).equals("2"))) {
            System.out.println("################## level error ####################");
            printMessage();
            System.out.print("level:");
            System.out.println(level);
            System.out.println("################## end level error ####################");
        }
    }

    public void printContext() {
        System.out.println("############### start parse ##########");
        System.out.println(String.join("\n", this.getResults().get("rawdata")));
        System.out.println("------ result ------");
        System.out.println("状态:" + this.getCurrentState());
        System.out.println("文件名称:" + this.getResults().get("filename"));

        /**
         * status:
         0. state -- validate
         1. accsuer -- validated
         2. level -- validated
         3. court -- validated
         4. number(案号) -- validated
         5. instrumentType(文书类型) -- validated
         6. caseType(案件类型) -- validateds
         7. suiteDate(立案日期) -- validate
         8. accuserLawyer(原告律师) =
         */


        for (Map.Entry<String,String> entry: NameMapping.names.entrySet()) {
            this.printField(entry.getKey());
        }

        /*this.printField("level");
        this.printField("court");
        this.printField("number");
        this.printField("instrumentType");
        this.printField("caseType");
        this.printField("suiteDate");

        this.printField("accuser");
        this.printField("accuserLawyer");
        this.printField("accuserLegalEntity");
        this.printField("accuserLawyerOffice");

        this.printField("defendant");
        this.printField("defendantLegalEntity");
        this.printField("defendantLawyer");
        this.printField("defendantLawyerOffice");

        this.printField("date");
        this.printField("judge");
        this.printField("clerk");
        this.printField("attached");*/

        System.out.println("##############  end parse #############");
    }

    private void printField(String field) {
        System.out.println(NameMapping.get(field) + ":" + this.getResults().get(field));
    }

    public void validateField(List<String> fields, Boolean print) {
        List<String> missingFields = new ArrayList<>();
        if (print) {
            System.out.println("############ field value ###########");
        }
        for (String field : fields) {
            if (!this.getResults().containsKey(field) || this.getResults().get(field).isEmpty()) {
                missingFields.add(field);
            } else if (print) {
                System.out.println(this.getResults().get(field));
            }
        }

        if (print) {
            System.out.println(String.join("\n", this.getResults().get("rawdata")));
            System.out.println("current state: " + this.getCurrentState());
            System.out.println("file name: " + this.getResults().get("filename"));
            System.out.println("origin data");
            System.out.println("############ end field value ###########");
        }

        if (!missingFields.isEmpty()) {
            System.out.println("################## missing field error ####################");

            printMessage();
            System.out.print("missing fields");
            System.out.println(missingFields);
            System.out.println("################## end missing field error ####################");
        }
    }

    public void printMessage() {

        System.out.println(String.join("\n", this.getResults().get("rawdata")));
        System.out.println("current state: " + this.getCurrentState());
        System.out.println("file name: " + this.getResults().get("filename"));
        System.out.println("origin data");

    }
}
