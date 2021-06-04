/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package para;

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javafx.scene.Node;
import javax.swing.text.Document;
import javax.xml.bind.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.stream.StreamResult;
//import static org.jsoup.nodes.Document.OutputSettings.Syntax.xml;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import javax.sql.rowset.spi.XmlReader;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
import jdk.internal.org.xml.sax.InputSource;
import jdk.internal.org.xml.sax.SAXException;

/**
 *
 * @author acer
 */
public class ParserClanku {

    URLReader reader = new URLReader();

    ArrayList<item> clankyRozdelene = new ArrayList<item>();

    void upravTextClanku() throws IOException, ParseException {

        reader.konfigraceUrladres();
        reader.rozdelZapis();

        for (Iterator<item> iterator = reader.clanky.iterator(); iterator.hasNext();) {

            item clanek = iterator.next();

            clanek.setTextClanku(clanek.getTextClanku().replaceAll("<span class='red text03'>DNES!</span>", ""));
            clanek.setTextClanku(clanek.getTextClanku().replaceAll("<span class='purple text03'>3dny</span>", ""));
            clanek.setTextClanku(clanek.getTextClanku().replaceAll("<p class='pocet_komentaru'", ""));

            clanek.setTextClanku(clanek.getTextClanku().replaceAll("</a>|</h2|<h2>?>", ""));

            if (clanek.getTextClanku().contains("img")) {
                clanek.setTextClanku(clanek.getTextClanku().replaceAll("<br", ""));
                String[] tokens = clanek.getTextClanku().split(">");
                item nextClanek = new item(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                nextClanek.setOdkazurl(nextClanek.getOdkazurl().replaceAll("<", ""));
                nextClanek.setOdkazurl(nextClanek.getOdkazurl().replaceAll("a href='", "https://www.parabola.cz"));
                nextClanek.setObsrazekurl(nextClanek.getObsrazekurl().replaceAll("<|||||", ""));
                nextClanek.setDatum(nextClanek.getDatum().replaceAll("[\\n\\r]", ""));
                nextClanek.setObsrazekurl(nextClanek.getObsrazekurl().replaceAll("[\\n\\r]", ""));
                nextClanek.setDatumtest(nextClanek.getDatum());
                nextClanek.setDatum(nextClanek.getDatumtest().toString());

                nextClanek.setOdkazurl(nextClanek.getOdkazurl().replace("<a href='", "https://www.parabola.cz"));
                nextClanek.setOdkazurl(nextClanek.getOdkazurl().substring(0, nextClanek.getOdkazurl().length() - 2));
                nextClanek.setObsrazekurl(nextClanek.getObsrazekurl().replace("img src='", "https://www.parabola.cz"));
                nextClanek.setObsrazekurl(nextClanek.getObsrazekurl().replace("img src='", "https://www.parabola.cz"));

                String[] pole = nextClanek.getObsrazekurl().split(" ");
                nextClanek.setObsrazekurl(pole[0]);
                nextClanek.setObsrazekurl(nextClanek.getObsrazekurl().substring(0, nextClanek.getObsrazekurl().length() - 1));

                clankyRozdelene.add(nextClanek);

            } else {
                clanek.setTextClanku(clanek.getTextClanku().replaceAll("<br", ">k"));
                try {
                    String[] tokens = clanek.getTextClanku().split(">");
                    item nextClanek = new item(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                    nextClanek.setOdkazurl(nextClanek.getOdkazurl().replaceAll("<", ""));

                    nextClanek.setObsrazekurl(nextClanek.getObsrazekurl().replaceAll("<|||||", ""));
                    nextClanek.setDatum(nextClanek.getDatum().replaceAll("[\\n\\r]", ""));
                    nextClanek.setObsrazekurl(nextClanek.getObsrazekurl().replaceAll("[\\n\\r]", ""));
                    nextClanek.setDatumtest(nextClanek.getDatum());
                    nextClanek.setDatum(nextClanek.getDatumtest().toString());

                    nextClanek.setOdkazurl(nextClanek.getOdkazurl().replace("a href='", "https://www.parabola.cz"));
                    nextClanek.setOdkazurl(nextClanek.getOdkazurl().substring(0, nextClanek.getOdkazurl().length() - 2));
                    nextClanek.setObsrazekurl(nextClanek.getObsrazekurl().replace("k", "null"));

                    String[] pole = nextClanek.getObsrazekurl().split(" ");
                    //nextClanek.setObsrazekurl(pole[0]);
                    // nextClanek.setObsrazekurl(nextClanek.getObsrazekurl().substring(0, nextClanek.getObsrazekurl().length() - 1));

                    clankyRozdelene.add(nextClanek);
                } catch (ParseException e) {
                    System.out.println("nastala vyjimka");
                }

            }

        }
        //System.out.println(clankyRozdelene);
    }

    /*
    void rozdelClankyproXml() throws IOException, ParseException {

        for (Clanek clanek : reader.clanky) {

            String radek;
            String text = clanek.getTextClanku();
            Reader inputString = new StringReader(text);
            BufferedReader reader = new BufferedReader(inputString);
            StringWriter sw = new StringWriter();

            while ((radek = reader.readLine()) != null) {
                sw.write(radek + "/");

            }
            String[] tokens = sw.toString().split("/");
            Clanek nextClanek = new Clanek(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
            clankyRozdelene.add(nextClanek);

            reader.close();
            sw.close();
            //System.out.println(sw);

        }
        // System.out.println(clankyRozdelene);
    }
    
    
     */
    void vytvorXML() throws Exception {

        channel list = new channel();
        list.setClankyList(clankyRozdelene);

        Writer writer = new FileWriter("output.txt");
        File file = new File("clanky.xml");

        JAXBContext jaxbContext;

        try {
            jaxbContext = JAXBContext.newInstance(channel.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, "RSS 2.0");
            jaxbMarshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
                    "<?xml-stylesheet type='text/xsl' href='clanky.xsl' ?>");

            jaxbMarshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NamespacePrefixMapper() {
                @Override
                public String getPreferredPrefix(String uri, String arg1, boolean arg2) {
                    return uri.equals("http://base.google.com/ns/1.0") ? "g" : "";
                }
            });
            StringWriter result = new StringWriter();
            OutputStream os = null;
            jaxbMarshaller.marshal(list, file);
            String xml = result.toString();
            System.out.println(xml);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void doplnrssTag() throws FileNotFoundException, IOException {
        String[] pole = new String[3000];
        String[] polevysledne = new String[3002];
        int pomocna = 0;

        BufferedReader br = new BufferedReader(new FileReader("clanky.xml"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("clanky.txt"), "UTF-8"
        ));

        while (br.readLine() != null) {
            for (int i = 0; i < pole.length; i++) {
                pole[i] = br.readLine();

            }
        }

        polevysledne[0] = "<rss version=\"2.0\">";

        for (int j = 0; j < pole.length; j++) {
            polevysledne[j + 1] = pole[j];

        }

        for (int i = 0; i < polevysledne.length; i++) {
            if (polevysledne[i] == null) {
                pomocna++;
            }
        }

        for (int i = 0; i < polevysledne.length; i++) {
            if (polevysledne[i] == null) {
                polevysledne[i] = "</rss>";
                break;
            }
        }

        for (int i = 0; i < polevysledne.length; i++) {
            if (polevysledne[i] != null) {
                bw.write(polevysledne[i]);
                bw.newLine();
            }

        }
        bw.close();
    }

}
