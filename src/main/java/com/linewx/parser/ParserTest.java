package com.linewx.parser;

import com.google.gson.Gson;
import com.linewx.parser.json.RuleJson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luganlin on 11/16/16.
 */
public class ParserTest {
    public static void main(String argv[]) throws Exception {
       /* System.out.println(testRe());
        return;*/

        final RuleJson rule = new ParserTest().readFile();
        //testRe();
        parseFiles(rule, "/users/luganlin/Documents/download");
        //parseFilesSync(rule, "/users/luganlin/Documents/download");
        //parseFile(rule, "/users/luganlin/Documents/download/ffa18ca2-dac2-4034-80b9-fa30e7d4872c.html").validate();


    }

    public static ParseContext parseFile (RuleJson rule, String fileName) throws Exception{
        File file = new File(fileName);
        Document doc = Jsoup.parse(file, "GBK");
        Element element = doc.getElementById("DivContent");
        Elements elements = element.children();
        List<String> statements = new ArrayList<>();
        ParseContext context = new ParseContext();
        for (Element oneElement : elements) {
            statements.add(oneElement.ownText());
            context.addResult("rawdata", oneElement.ownText());
        }

        context.setCurrentState("start");
        ParseStateMachine stateMachine = new ParseStateMachine(rule);
        stateMachine.run(context, statements);
        file.exists();
        return context;
    }


    public static void parseFiles(RuleJson rule, String folder) throws Exception{
        ExecutorService executor = Executors.newFixedThreadPool(8);
        File dir = new File(folder);

        List<Future> futures = new ArrayList<>();
        for (File file : dir.listFiles()) {
            Future<ParseContext> future = executor.submit(new Callable<ParseContext>() {
                @Override
                public ParseContext call() {
                    ParseContext context = new ParseContext();
                    try {
                        Document doc = Jsoup.parse(file, "GBK");
                        Element element = doc.getElementById("DivContent");
                        Elements elements = element.children();
                        List<String> statements = new ArrayList<>();

                        context.addResult("filename", file.getName());
                        for (Element oneElement : elements) {
                            statements.add(oneElement.ownText());
                            context.addResult("rawdata", oneElement.ownText());
                        }

                        context.setCurrentState("start");
                        ParseStateMachine stateMachine = new ParseStateMachine(rule);
                        stateMachine.run(context, statements);
                        //file.exists();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return context;
                }
            });

            futures.add(future);
        }

        for (Future future: futures) {
            ((ParseContext)future.get()).validate();
        }


        long endTime = System.currentTimeMillis();
        executor.shutdown();
    }


    public RuleJson readFile() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("rule.json");
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(is));
        RuleJson rule = gson.fromJson(bufferedReader, RuleJson.class);
        return rule;
    }

    static void testRe() {
        Pattern pattern = Pattern.compile(".*(?<!日|二)$");

        Matcher matcher = pattern.matcher("审判员日");
        System.out.println(matcher.matches());
        //System.out.println(matcher.group(1));
    }

    public static void parseFilesSync(RuleJson rule, String folder) throws Exception{

        File dir = new File(folder);

        List<Future> futures = new ArrayList<>();
        for (File file : dir.listFiles()) {
            ParseContext context = new ParseContext();
            try {
                Document doc = Jsoup.parse(file, "GBK");
                System.out.println(file.getName());
                Element element = doc.getElementById("DivContent");
                Elements elements = element.children();
                List<String> statements = new ArrayList<>();

                context.addResult("filename", file.getName());
                for (Element oneElement : elements) {
                    statements.add(oneElement.ownText());
                    context.addResult("rawdata", oneElement.ownText());
                }

                context.setCurrentState("start");
                ParseStateMachine stateMachine = new ParseStateMachine(rule);
                stateMachine.run(context, statements);
                //file.exists();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
