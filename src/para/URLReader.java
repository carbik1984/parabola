/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package para;

import java.sql.Connection;

/**
 *
 * @author acer
 */
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class URLReader {

    String zapis;
    BufferedReader br;
    StringBuffer sb = new StringBuffer();
    ArrayList<item> clanky = new ArrayList<item>();

    void konfigraceUrladres() throws IOException {
        ArrayList<String> listUrl = new ArrayList<String>();

        listUrl.add("https://www.parabola.cz/r/16/");
        listUrl.add("https://www.parabola.cz/r/16/stranka-2/");
        listUrl.add("https://www.parabola.cz/r/16/stranka-3/");
        listUrl.add("https://www.parabola.cz/r/16/stranka-4/");
        listUrl.add("https://www.parabola.cz/r/16/stranka-5/");
        listUrl.add("https://www.parabola.cz/r/17/");
        listUrl.add("https://www.parabola.cz/r/17/stranka-2/");
        listUrl.add("https://www.parabola.cz/r/17/stranka-3/");
        listUrl.add("https://www.parabola.cz/r/17/stranka-4/");
        listUrl.add("https://www.parabola.cz/r/17/stranka-5/");

        for (int i = 0; i < listUrl.toArray().length; i++) {

            nactiData(listUrl.get(i));
        }

    }

    // tato metoda cte obsah z url adres a pridava je do StringBuffer sb resp. String zapis;
    void nactiData(String adresa) throws MalformedURLException, IOException {

        try {
            URL url = new URL(adresa);
            br = new BufferedReader(new InputStreamReader(url.openStream(), "Windows-1250"));

            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

        } finally {

            if (br != null) {
                br.close();
                zapis = sb.toString();

            }
        }

    }

    //tady rozdelime obsah z nactenych url na jednolive clanky a pridava je do Arraylistu Clanky
    void rozdelZapis() throws UnsupportedEncodingException {

        // byte[] b = zapis.getBytes(StandardCharsets.UTF_8);
        //  String zapisutf8 = new String(b, "UTF-8");
        String[] textClanku = zapis.split("<br class='clear_both;'>");
        for (String string : textClanku) {

            if (string.contains("<h2><a href='/clanky/") && !string.contains("<h3>")) {
                item clanek = new item(string);

                clanky.add(clanek);
            }
        }

    }
}
