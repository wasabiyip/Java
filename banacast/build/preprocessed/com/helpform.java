/*
 * helpform.java
 *
 * Created on 2009��5��14��, ����10:33
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
 *һ���򵥵İ���form
 */
public class helpform extends Form implements CommandListener{
    
    public Command c_back_ch;
    private banacast midlet = null;
    private read_xml read_xml_ = null;
    private programform programform_ = null;

    private ImageItem imageabout;
    
    
    /** Creates a new instance of helpform */
    public helpform(banacast a,read_xml b,programform c) {
        super("������Ϣ");
        this.midlet=a;
        this.read_xml_=b;
        this.programform_=c;        
        c_back_ch = new Command("����",Command.BACK,1);
         try {
             imageabout = new ImageItem ("",Image.createImage("/p/bbbb.png"),ImageItem.LAYOUT_CENTER+ImageItem.LAYOUT_NEWLINE_AFTER+ImageItem.LAYOUT_NEWLINE_BEFORE,"form");
             } catch (IOException ex) 
             {
                  ex.printStackTrace();
             }
            this.append(imageabout);         
        this.append("�û��ڵ�¼�󣬽���Ƶ���б�Ϳ��Կ�ʼ���� \r\n"+
          "1.�鿴��Ŀ�����Կ�������Ƶ���Ľ�Ŀ�˵����������������˵���ѡ��Ҫ�鿴�����ڵĽ�Ŀ��\r\n"+
                "2.Ԥ���������Ŀ�б����Խ���Ԥ��������ѡ��ҪԤ���Ľ�Ŀ������Ԥ���Ŀ�ʼʱ��ͽ���ʱ�䡣\r\n"+
	"2.�ҵ�Ԥ����>���ţ����Բ鿴�Լ���Ԥ����Ϣ������ʱ�䡢���֡����˵ĵ���������ҵ�Ԥ����Ĳ��Ű�ť�������Բ����Ѿ�¼�ƺõ�Ԥ����\r\n"+
	"3.�ҵ�Ǯ�������Բ鿴�Լ�����\r\n"+
 	"5.������\r\n" +
            "6.����banacast,����banacast�ĸ�����ܡ�\r\n");
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
