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

/**
 * Created by luganlin on 11/16/16.
 */
public class ParserTest {
    public static void main(String argv[]) throws Exception {
        long startTime = System.currentTimeMillis();

        final RuleJson rule = new ParserTest().readFile();


        ExecutorService executor = Executors.newFixedThreadPool(8);
        File dir = new File("/users/luganlin/Documents/download");

        List<Future> futures = new ArrayList<>();
        for (File file : dir.listFiles()) {
            Future<ParseContext> future = executor.submit(new Callable<ParseContext>() {
                @Override
                public ParseContext call() throws Exception {
                    long oneStartTime = System.currentTimeMillis();
                    //File file = new File("/users/luganlin/Documents/download/test.html");

                    Document doc = Jsoup.parse(file, "GBK");
                    Element element = doc.getElementById("DivContent");
                    Elements elements = element.children();
                    List<String> statements = new ArrayList<>();
                    for (Element oneElement : elements) {
                        statements.add(oneElement.ownText());
                    }
                    ParseContext context = new ParseContext();
                    context.setCurrentState("start");
                    ParseStateMachine stateMachine = new ParseStateMachine(rule);
                    stateMachine.run(context, statements);
                    //System.out.println(context.getCurrentState());
                    //System.out.println(context.getCourt());
                    //System.out.println(String.join("\n", context.getResults().get("court")));
                    //context.printResult();
                    file.exists();
                    long oneEndTime = System.currentTimeMillis();
                    //System.out.println(oneEndTime - oneStartTime);
                    return context;

                }
            });

            futures.add(future);
        }

        List<String> result = new ArrayList<>();
        for (Future future: futures) {
            result.add(((ParseContext)future.get()).getCurrentState());
        }


        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        System.out.println(String.join("|", result));
        System.out.println(result.size());
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
}
