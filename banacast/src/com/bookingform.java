/*
 * bookingform.java
 *
 * Created on 2009年5月18日, 下午9:49
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
 *要send很多信息出去
 *1.name 2.频道ID 3.节目名字 4.开始时间 5.结束时间 6.高质量/低质量 7.然后计算出要扣的点数 +id：4
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
    
    //bookingform_= new bookingform(date_time，channel_name，select_program_name，begin_time,end_time，name,midlet,read_xml_,this);
    /** Creates a new instance of bookingform */
    public bookingform(String select_date_time,String channel_name,String select_program_name,String begin_time,String end_time,String name,banacast c,read_xml b,programform d) {
        super("进行预订中");
        //****************************    
        this.message = name;
        this.date_time=date_time;
        this.channel_name=channel_name;
        this.select_program_name=select_program_name;
        this.begin_time=begin_time;
        this.end_time=end_time;
        
        detail=select_date_time+"#"+channel_name+"#"+select_program_name+"#"+begin_time+"#"+end_time+"#"+name;//发送这个消息 do post
         //**************************** 
        this.read_xml_=b;
        this.midlet=c;
        this.programform_=d;
        //****************************
        begin = new TextField("开始时间",begin_time ,20,TextField.ANY);
        end = new TextField("结束时间",end_time ,20,TextField.ANY);
      //  messagebook=a;
         c_no = new Command("取消",Command.BACK,1);
         c_yes=new Command("确定",Command.ITEM,1);
         this.addCommand(c_no);
         this.addCommand(c_yes);
         high_low  = new ChoiceGroup("",Choice.EXCLUSIVE);
        
         high_low.append("一般质量视频（3GP格式）",null);
          high_low.append("高质量视频（RM格式）",null);
         this.append("你正在预订的节目是：\n"+"日期："+select_date_time.substring(0,4)+"-"+select_date_time.substring(4,6)+"-"+select_date_time.substring(6,8)+"\n"+"频道： "+channel_name+"\n"+"节目： "+select_program_name+"\n");
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
