package com.linewx.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;

/**
 * Created by luganlin on 11/16/16.
 */
public class ParserTest {
    public static void main(String argv[]) throws Exception{
        File file = new File("/users/luganlin/Documents/download/test.html");
        Document doc = Jsoup.parse(file, "GBK");
        Element element = doc.getElementById("DivContent");
        System.out.println(element.child(1).ownText());
        System.out.println(element.child(1).ownText().endsWith("法院"));
        file.exists();
    }
}
