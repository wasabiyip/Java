/*
 * moneyform.java
 *
 * Created on 2009��5��15��, ����3:41
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
 *�鿴Ǯ�� ��������
 *����һ��name id��3
 */
public class moneyform extends Form implements CommandListener,Runnable{
    public Command c_back_ch;
    private banacast midlet = null;
    private read_xml read_xml_ = null;
    private programform programform_ = null;
    
    private String name;
    public data  a=new data();
    public String url=a.servlet_URL;
    public StringBuffer strbuf = new StringBuffer();
   
    private HttpConnection c;
    private DataInputStream dis;
    
    /** Creates a new instance of moneyform */
    public moneyform(String name,banacast a,read_xml b,programform c) {
      super("�ҵ�Ǯ��");
      this.name=name;
      this.midlet=a;
      this.read_xml_=b;
      this.programform_=c;
      
      c_back_ch = new Command("����",Command.BACK,1);
      this.append("�������ǣ�\n");
      this.addCommand(c_back_ch);
      this.setCommandListener(this);
      new Thread(this).start();
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
    public void run() {//name +id:3
        try {
            //��������  strbuf������� Ȼ��ŵ�form����
            
            c = (HttpConnection)Connector.open(url + '?'+ "Name="+name+"&LABEL="+"3");
            c.setRequestProperty("User-Agent","Profile/MIDP-2.0 Configuration/CLDC-2.0");
            c.setRequestProperty("Accept","text/plain, */*");
            
                dis =c.openDataInputStream();
               int ch;
                while((ch=dis.read()) != -1)
                {
                    strbuf.append((char)ch);
                }
                dis.close();
                c.close();
                
                this.append(strbuf.toString());
                
        } catch (IOException ex) {
            ex.printStackTrace();
        }
           
    }
    
    
}
