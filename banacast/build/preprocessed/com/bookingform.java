/*
 * bookingform.java
 *
 * Created on 2009��5��18��, ����9:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com;

import java.io.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.*;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/**
 *
 * @author yll
 *
 *Ҫsend�ܶ���Ϣ��ȥ
 *1.name 2.Ƶ��ID 3.��Ŀ���� 4.��ʼʱ�� 5.����ʱ�� 6.������/������ 7.Ȼ������Ҫ�۵ĵ��� +id��4
 *
 */
public class bookingform extends Form implements CommandListener  {
     private Command c_no;
     private Command c_yes;
     
    private read_xml read_xml_ = null;
    private banacast midlet = null;
    private programform programform_ = null;
    
    //private String messagebook;
    public    TextField begin;
    public    TextField end;
    
        public ChoiceGroup high_low;
    public String detail;
    private String message;
    private String date_time;
    private String channel_name;
    private String select_program_name;
    private String begin_time;
    private String end_time;
    public data a=new data();
    public String url=a.servlet_URL;

    private info info_;

    private int next;

    private Object Q;
    
    //bookingform_= new bookingform(date_time��channel_name��select_program_name��begin_time,end_time��name,midlet,read_xml_,this);
    /** Creates a new instance of bookingform */
    public bookingform(String select_date_time,String channel_name,String select_program_name,String begin_time,String end_time,String name,banacast c,read_xml b,programform d) {
        super("����Ԥ����");
        //****************************    
        this.message = name;
        this.date_time=date_time;
        this.channel_name=channel_name;
        this.select_program_name=select_program_name;
        this.begin_time=begin_time;
        this.end_time=end_time;
        
        detail=select_date_time+"#"+channel_name+"#"+select_program_name+"#"+begin_time+"#"+end_time+"#"+name;//���������Ϣ do post
         //**************************** 
        this.read_xml_=b;
        this.midlet=c;
        this.programform_=d;
        //****************************
        begin = new TextField("��ʼʱ��",begin_time ,20,TextField.ANY);
        end = new TextField("����ʱ��",end_time ,20,TextField.ANY);
      //  messagebook=a;
         c_no = new Command("ȡ��",Command.BACK,1);
         c_yes=new Command("ȷ��",Command.ITEM,1);
         this.addCommand(c_no);
         this.addCommand(c_yes);
         high_low  = new ChoiceGroup("",Choice.EXCLUSIVE);
        
         high_low.append("һ��������Ƶ��3GP��ʽ��",null);
          high_low.append("��������Ƶ��RM��ʽ��",null);
         this.append("������Ԥ���Ľ�Ŀ�ǣ�\n"+"���ڣ�"+select_date_time.substring(0,4)+"-"+select_date_time.substring(4,6)+"-"+select_date_time.substring(6,8)+"\n"+"Ƶ���� "+channel_name+"\n"+"��Ŀ�� "+select_program_name+"\n");
         this.append(begin);
         this.append(end);
         this.append(high_low);
       
        this.setCommandListener(this);
    }

    public void commandAction(Command command, Displayable displayable) {
        if(command==c_no)
        { 
      
            
            midlet.setCurrent(programform_);
          //alert.setCurrent(programform_);
       }
        else{//command==c_yes
         boolean[] array_time= new boolean[high_low.size()];
          high_low.getSelectedFlags(array_time); 
             for(int i=0;i<array_time.length;i++)
                {
                    if(array_time[i])
                    {
                        next=i+1;
                        Q=high_low.getString(i);
                    }
                }
        detail=detail+"#"+Q.toString();
        info_=new info(detail,midlet,read_xml_,programform_);
        midlet.setCurrent(info_);
        
        
        }
    }

   
        
}
