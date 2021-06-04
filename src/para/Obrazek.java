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
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "image")
public class Obrazek {
    
    @XmlElement(name = "url")
    String url;
    
    
     Obrazek(){
    
       
    
    }
    
    Obrazek(String url){
    
        this.url = url;
    
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
       
        this.url = url;
    }
    
    
    
}
