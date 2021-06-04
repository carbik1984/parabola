/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package para;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author acer
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "image")
class Image {

    String link;
    String title;
    String url;
    int width;
    int height;

    Image() {
        this.link = "https://www.parabola.cz/";
        this.title = ">Parabola.cz";
        this.url = "https://www.parabola.cz/img_menu/logorss2_parabola-cz.gif";
        this.width = 144;
        this.height = 31;
    }

    @Override
    public String toString() {
        return "Image{" + "link=" + link + ", title=" + title + ", url=" + url + ", widht=" + width + ", height=" + height + '}';
    }

}
