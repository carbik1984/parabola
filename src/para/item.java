/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package para;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author acer
 */
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class item {

    String textClanku;
    @XmlTransient
    Date datumtest;

    String link;
    String title;
    @XmlElement(name = "pubDate")
    String datumString;

    //String image;
    String description;

    @XmlElement(name = "image")
    Obrazek obrazek;

    public item() {
    }

    public item(String textClanku) {
        this.textClanku = textClanku;
    }

    public item(String odkazurl, String nazev, String datum, String obsrazekurl, String popisek) throws ParseException {

        System.out.println(obsrazekurl);
        this.obrazek = new Obrazek(obsrazekurl.replaceAll("<|||||", ""));
        this.obrazek = new Obrazek(obsrazekurl.replaceAll("<", ""));
        this.obrazek = new Obrazek(obsrazekurl.replaceAll("&lt;", ""));
        obrazek.setUrl(obsrazekurl.replaceAll("[\\n\\r]", ""));
        obrazek.setUrl(obsrazekurl.replaceAll("\n", ""));
        obrazek.setUrl(obsrazekurl.replaceAll("<img src='", "https://www.parabola.cz"));
        String[] pole = obrazek.getUrl().split(" ");
        obrazek.setUrl(pole[0]);
        obrazek.setUrl(obrazek.getUrl().substring(0, obrazek.getUrl().length() - 1));

        this.link = odkazurl;
        this.title = nazev;
        this.datumString = datum;

        //  this.image = obsrazekurl;
        this.description = popisek;
    }

    public void setObrazek(Obrazek obrazek) {
        this.obrazek = obrazek;
    }

    public Obrazek getObrazek() {
        return obrazek;
    }

    public void setObsrazekurl(String text) {
        obrazek.setUrl(text);
    }

    public String getObsrazekurl() {
        return obrazek.getUrl();
    }

    public String getTextClanku() {
        return textClanku;
    }

    public void setTextClanku(String textClanku) {
        this.textClanku = textClanku;
    }

    public String getOdkazurl() {
        return link;
    }

    public String getNazev() {
        return title;
    }

    public String getDatum() {
        return datumString;
    }

    public String getPopisek() {
        return description;
    }

    public void setOdkazurl(String odkazurl) {
        this.link = odkazurl;
    }

    public void setNazev(String nazev) {
        this.title = nazev;
    }

    public void setDatum(String datum) {
        this.datumString = datum;
    }

    public void setPopisek(String popisek) {
        this.description = popisek;
    }

    public void setDatumtest(String datum) {
        //DateFormat formatData;
        DateFormat formatData1 = new SimpleDateFormat("d.M.yyyy H:mm ");
        try {
            this.datumtest = formatData1.parse(datumString);
        } catch (ParseException ex) {
            Logger.getLogger(item.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Date getDatumtest() {
        return datumtest;
    }

    @Override
    public String toString() {
        String text = "";

        text += "odkazurl: " + link + "\n";
        text += "nazev: " + title + "\n";
        text += "datumString: " + datumString + "\n";
        text += "datumDate: " + datumtest + "\n";
        text += "obsrazekurl: " + obrazek + "\n";
        text += "popisek: " + description + "\n" + "\n";
        return text;
    }

}
