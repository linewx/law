package com.linewx.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luganlin on 11/16/16.
 */
public class ParserTest {
    public static void main(String argv[]) throws Exception{
/*        String content = "中级人民法院";
        String patternString = ".*法院$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(content);
        boolean isMatched = matcher.matches();
        System.out.println("Is it a Match?" + isMatched);*/
        long startTime = System.currentTimeMillis();
        for (int i=0; i<1000; i++) {
            File file = new File("C:\\Users\\lugan\\git\\law\\sourcefile\\test.html");
            Document doc = Jsoup.parse(file, "GBK");
            Element element = doc.getElementById("DivContent");
            Elements elements = element.children();
            List<String> statements = new ArrayList<>();
            for(Element oneElement : elements) {
                statements.add(oneElement.ownText());
            }
            ParseContext context = new ParseContext();
            context.setCurrentState("start");
            ParseStateMachine stateMachine = new ParseStateMachine();
            stateMachine.parse(context,statements);
            //System.out.println(context.getCurrentState());
            //System.out.println(context.getCourt());
            file.exists();
        }

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);


    }
}
