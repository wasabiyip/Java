/*
 * aboutform.java
 *
 * Created on 2009年5月14日, 下午10:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com;
import java.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import javax.microedition.*;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/**
 *
 * @author yll
 */
public class aboutform extends Form implements CommandListener {
    
    public Command c_back_ch;
    private banacast midlet = null;
    private read_xml read_xml_ = null;
    private programform programform_ = null;
    public ImageItem imageabout;
    public data aa=new data();
    
    
    /** Creates a new instance of aboutform */
    public aboutform(banacast a,read_xml b,programform c) {
        super("关于Banacast");
        this.midlet=a;
        this.read_xml_=b;
        this.programform_=c;
        c_back_ch = new Command("返回",Command.BACK,1);
        try {
             imageabout = new ImageItem ("",Image.createImage("/p/bbbb.png"),ImageItem.LAYOUT_CENTER+ImageItem.LAYOUT_NEWLINE_AFTER+ImageItem.LAYOUT_NEWLINE_BEFORE,"form");
             } catch (IOException ex) 
             {
                  ex.printStackTrace();
             }
            this.append(imageabout);
             this.append(aa.about);
             this.addCommand(c_back_ch);
             this.setCommandListener(this);
             
                  
    }

    public void commandAction(Command command, Displayable displayable) {
        if(command==c_back_ch)
        {
            if (programform_==null)
            { 
                midlet.setCurrent(read_xml_);
                
            
            }
            else 
            { 
                midlet.setCurrent(programform_);
                 
            }
        
        }
    }
    
}
