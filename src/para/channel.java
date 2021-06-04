/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package para;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import javax.xml.namespace.QName;
import static java.lang.System.in;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;

/**
 *
 * @author acer
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "channel")

public class channel {

    Image image = new Image();

    @XmlElement(name = "title")
    String title = "Parabola.cz - Váš satelitní server";

    @XmlElement(name = "link")
    String link = "https://www.parabola.cz/";

    @XmlElement(name = "description")
    String description = "Deník o satelitním, terestrickém a kabelovém vysílání a příjmu";

    @XmlElement(name = "language")
    String language = "cs";

    @XmlElement(name = "category")
    String category = "RSS feed";

    @XmlElement(name = "pubDate")
    String pubDate = "Fri, 27 Dec 2019 20:20:50 GMT";

    @XmlElement(name = "copyright")
    String copyright = "2004 - 2019 Parabola.cz. All rights reserved.";

    @XmlElement(name = "lastBuildDate")
    String lastBuildDate = "Fri, 27 Dec 2019 20:20:28 GMT";

    @XmlElement(name = "item")
    ArrayList<item> clanky = new ArrayList<item>();

    public ArrayList getClankyList() {
        return clanky;
    }

    public void setClankyList(ArrayList listClanku) {
        this.clanky = listClanku;
    }

}
