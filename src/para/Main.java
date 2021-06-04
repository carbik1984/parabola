/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package para;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;

/**
 *
 * @author acer
 */
public class Main {

    public static void main(String[] args) throws IOException, JAXBException, Exception {

        ParserClanku parser = new ParserClanku();
        parser.upravTextClanku();

        parser.vytvorXML();
        parser.doplnrssTag();

        //System.out.println( parser.clankyRozdelene);
    }
}
