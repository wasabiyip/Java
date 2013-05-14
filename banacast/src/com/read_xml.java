/*
 * read_xml.java
 *
 * Created on 2009年4月23日, 下午4:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 *
 *先获得日期 然后判断出有几天 初始化 choice group
 *选择哪一个日期
 *监听选择哪一个频道  发送频道的名称+日期+name
 *
 *
 *
 */

package com;

import java.io.*;
import java.util.Calendar;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/**
 *
 * @author yll
 */
public class read_xml extends Form implements CommandListener,Runnable ,ItemStateListener {
    public Command programe;
    public Command book;
    public Command money;
    public Command help;
    public Command about;
    public Command c_exit;
   // public Command c_back_ch;
    
    boolean[] array_chnl;
    boolean[] array_date_time;
            
    public static String select_date_time="";   //日期 
   public String url="";
   public String select_chnl="";//频道
    public String channel_name="";
    
    public ImageItem imageabout;
    
    public String message;
   // public  TextField test;
    
    public  ChoiceGroup date_time;
  //  public  ChoiceGroup date_time2;
    public ChoiceGroup chnl;
    //public ChoiceGroup pro;
    
    public ChoiceGroup pro_buffer;
    //public  ChoiceGroup date_time3;
    //public  ChoiceGroup date_time4;
    
    private banacast midlet = null;
    private DataInputStream din;
    private HttpConnection conn = null;
    private OutputStream oStrm = null;
    private InputStream iStrm;
  
    public data a=new data();
    public String servlet_url="http://125.216.242.30:8084/channel2.xml";
    public  programform programform_;
    public  helpform helpform_;
    public  aboutform aboutform_;
    public  bookform bookform_;
    public  moneyform moneyform_;
   
    
    public read_xml(String a,banacast b) {
        super("电视频道");
        this.message=a; //用户名
        this.midlet=b;     
       // c_back_ch = new Command("返回",Command.BACK,1);
        c_exit = new Command("退出", Command.EXIT, 0);
        programe = new Command("查看节目",Command.ITEM,1);
        book = new Command("我的预订",Command.ITEM,1);
        money = new Command("我的钱包",Command.ITEM,1);
        help = new Command("帮助",Command.ITEM,1);
        about = new Command("关于",Command.ITEM,1);    
        
        //******************************时间  这里要封装一下
  
        date_time = new ChoiceGroup("日期选择",Choice.POPUP);        
        date_time.append("2009-05-12",null);
        date_time.append("2009-05-13",null);
        date_time.append("2009-05-14",null);
        date_time.append("2009-05-15",null);
        date_time.append("2009-05-16",null);
        date_time.append("2009-05-17",null);
        date_time.append("2009-05-18",null);
        
        this.append(date_time);
        //******************************频道  
        chnl = new ChoiceGroup("",Choice.EXCLUSIVE);
        this.append(chnl);

        // chnl.setFitPolicy(Choice.TEXT_WRAP_ON);
        //*****************************时间2
        /*
        date_time2=new ChoiceGroup("日期选择",Choice.POPUP);
        date_time2.append("2009-05-12",null);
        date_time2.append("2009-05-13",null);
        date_time2.append("2009-05-14",null);
        date_time2.append("2009-05-15",null);
        date_time2.append("2009-05-16",null);
        date_time2.append("2009-05-17",null);
        date_time2.append("2009-05-18",null);
        
        
        this.append(date_time2);*/
        //***************************** 
       
  //*****************************菜单
        this.addCommand(c_exit);
        this.addCommand(programe);
        this.addCommand(book);
        this.addCommand(money);
        this.addCommand(help);
        this.addCommand(about);
        
        this.setItemStateListener(this);//添加监听choice group
        this.setCommandListener(this);//添加菜单监听
        new Thread(this).start();
        

    }

    public void commandAction(Command command, Displayable displayable) {
         if(command == c_exit){
             
           midlet.destroyApp(false);
           midlet.notifyDestroyed();
           
         }else if(command==programe){//看节目 要传送
          // url+select_date_time +select_chnl 
        programform_=new programform(url,select_date_time,select_chnl,channel_name,message,this,this.midlet);
        midlet.setCurrent(programform_);
             
         }else if(command== book){//看我的预订
           bookform_=new bookform(message,midlet,this,null);
           midlet.setCurrent(bookform_);
           
         }else if(command==money){//看我的钱包
          moneyform_=new moneyform(message,midlet,this,null);
           midlet.setCurrent(moneyform_);
        
         
         }else if(command==help){//看我的帮助
           helpform_=new helpform(midlet,this,null);
           midlet.setCurrent(helpform_);
           
         }else if (command==about){ //看我的
           aboutform_ = new aboutform(midlet,this,null);
           midlet.setCurrent(aboutform_);
        
         }
                 
    }

    public void run() {
          try {
             
                Interpret();
            
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (XmlPullParserException ex) {
                ex.printStackTrace();
            }  
        
    }

    
 public void Interpret() throws XmlPullParserException, IOException//解析频道单  获得URL
{
   
   conn = (HttpConnection) Connector.open(servlet_url);
   iStrm = conn.openInputStream();   
   KXmlParser parser = new KXmlParser();   
   parser.setInput(iStrm,null);
   int eventType = parser.getEventType();  
   String xml_= new String();
   try
   {       
       int node=0;
    while (eventType != XmlPullParser.END_DOCUMENT) 
    {        
        if(eventType == XmlPullParser.START_TAG)
        {         
            xml_=parser.getName().trim();     
            
              if(xml_.equals("channel"))//如果是频道信息
              {             
                node++;
                 String mes="频道"+node+" ";
                 int attrCount = parser.getAttributeCount();
                 for (int i = 0; i < attrCount; i++) 
                 {
                     
                     if( parser.getAttributeName(i).trim().equals("id"))
                     {
                         mes =mes+ "(" + parser.getAttributeValue(i)+")";  
                     }
                     else mes =mes+ "  " + parser.getAttributeValue(i)+"  "; 
                    //mes =mes+parser.getAttributeName(i) + ":" + parser.getAttributeValue(i)+"  ";  
                      
                 }
                 chnl.append(mes,null);              
              }
              else if(xml_.equals("date"))//如果是日期信息
             {
                String buffer3 = parser.nextText().toString();//判断星期几 然后计算出日期的下来菜单
             
             }          
              else if(xml_.equals("url"))
              {//读取url  
                url = parser.nextText().toString();                             }         
              }
        eventType = parser.next();     
     }
    
   }
   catch(IOException ioe){}
 iStrm.close();
 conn.close();
 //************************************************************
  array_date_time= new boolean[date_time.size()];
    date_time.getSelectedFlags(array_date_time);
  
    
    for(int i=0;i<array_date_time.length;i++)
    {
        if(array_date_time[i]& !(select_date_time.trim().equals(date_time.getString(i).trim())))
        {
            select_date_time=date_time.getString(i);
          
        }
    
    }
                String bufferr =select_date_time.trim();
    select_date_time=bufferr.substring(0,4)+bufferr.substring(5,7)+bufferr.substring(8,10);
 //************************************************************    
       array_chnl= new boolean[chnl.size()];
        chnl.getSelectedFlags(array_chnl);
        for(int i=0;i<array_date_time.length;i++)
    {
        if(array_chnl[i])
        {
            String buffer=chnl.getString(i);
            int pos1=buffer.indexOf("(");
            int pos2=buffer.indexOf(")");           
            select_chnl= buffer.substring(pos1+1,pos2);
             channel_name= buffer.substring(3,pos1);
        }
    
    }
}   
    
    public void itemStateChanged(Item item)//定义控件监听
    {

//如果是 date_time 或者 date_time2 有更新都要同步一下  chnl 只要获得是什么台就可以了

    //*******************************************
     array_date_time= new boolean[date_time.size()];
    date_time.getSelectedFlags(array_date_time);
  
    
    for(int i=0;i<array_date_time.length;i++)
    {
        if(array_date_time[i]& !(select_date_time.trim().equals(date_time.getString(i).trim())))
        {
            select_date_time=date_time.getString(i);
          
        }
    
    }
                String bufferr =select_date_time.trim();
    select_date_time=bufferr.substring(0,4)+bufferr.substring(5,7)+bufferr.substring(8,10);
       
     //*******************************************
         array_chnl= new boolean[chnl.size()];
        chnl.getSelectedFlags(array_chnl);
        for(int i=0;i<array_date_time.length;i++)
    {
        if(array_chnl[i])
        {
            String buffer=chnl.getString(i);
            int pos1=buffer.indexOf("(");
            int pos2=buffer.indexOf(")");           
            select_chnl= buffer.substring(pos1+1,pos2);
             channel_name= buffer.substring(3,pos1);
        }
    
    }
    
    //send select_chnl(ID号)+select_date_time +id
    
    }
 



    
}
