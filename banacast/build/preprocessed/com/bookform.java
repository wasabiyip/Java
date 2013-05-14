/*
 * bookform.java
 *
 * Created on 2009年5月14日, 下午10:33
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
 *要传送name label:2
 */
public class bookform extends Form implements CommandListener,Runnable ,ItemStateListener{

    private Command c_back_ch;
    private Command c_play;
    private Command exitCmd,GetCmd;

    private banacast midlet = null;
    private read_xml read_xml_ = null;
    private programform programform_ = null;
    private playform playform_;
    private player player; 
    
    //private ChoiceGroup date_time5;
    //private ChoiceGroup date_time6;
    private ChoiceGroup mybook;

    
   private String name;
   private StringBuffer strbuf;//是xml的url地址

    private HttpConnection c;
    private InputStream dis;
    private HttpConnection conn;

    private InputStream iStrm;
    public data a =new data();
    public String url=a.servlet_URL;

    private boolean[] detail_;
   
     public  String detail_book=null;
     
    /** Creates a new instance of bookform */
    public bookform(String name,banacast a,read_xml b,programform c) {
         super("我的预订");
         this.name=name;
         this.midlet=a;
        this.read_xml_=b;
        this.programform_=c;
        //****************************
         c_back_ch = new Command("返回",Command.BACK,1);
         c_play=new Command("播放",Command.ITEM,1);
         this.addCommand(c_back_ch);
         this.addCommand(c_play);
          //****************************
        /* date_time5 = new ChoiceGroup("时间选择",Choice.POPUP); 
          date_time5.append("最近预订",null);
          date_time5.append("以往预订",null);
          this.append(date_time5);*/
           //****************************
         mybook = new ChoiceGroup("节目",Choice.EXCLUSIVE);
         this.append(mybook);
          //choiceGroup.setFitPolicy(Choice.TEXT_WRAP_ON);
           mybook.setFitPolicy(Choice.TEXT_WRAP_ON);

         //mybook.append("dsfds0",null);
         //**************************** 
        /* date_time6= new ChoiceGroup("时间选择",Choice.POPUP); 
          date_time6.append("最近预订",null);
          date_time6.append("以往预订",null);
          this.append(date_time6);*/
         //**************************** 
         this.setItemStateListener(this);
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
           else if(command==c_play)//播放 选中的
        {                
          playform_ = new playform(detail_book,midlet,read_xml_,programform_,this);
          //midlet.setCurrent(playform_);在playform里面有定义的了
        
        }
    }

    public void run() {//读取xml send name id:2 
       try{
           //先获得url
           
      
            String  message = "Name=" + name + "&LABEL=2" ;
            c = (HttpConnection)Connector.open(url + '?' + message.trim());
            c.setRequestProperty("User-Agent","Profile/MIDP-2.0 Configuration/CLDC-2.0");
            c.setRequestProperty("Accept","text/plain, */*");             
            dis =c.openInputStream();
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
              int l = -1;
              while ((l = dis.read()) != -1) {
               baos.write(l);
              }                          
               dis.close();
               c.close();

               //mybook.append(baos.toString(),null);
                //System.out.println(strbuf.toString());
          //********************************************************获得了xml地址，解读xml
             conn = (HttpConnection) Connector.open(baos.toString().trim());
   iStrm = conn.openInputStream();   
   KXmlParser parser = new KXmlParser();   
   parser.setInput(iStrm,null);
   int eventType = parser.getEventType();  
   String xml_= new String();
             //  mybook.append(baos.toString(),null);
               try
               {       
                   int node=0;
                   String mes=null;
                   
                while (eventType != XmlPullParser.END_DOCUMENT) 
                {        
                       //mybook.append(String.valueOf(eventType),null);
                    //if(eventType == XmlPullParser.START_TAG)   
                    if(eventType == XmlPullParser.START_TAG)
                    {          //mybook.append(String.valueOf(eventType),null);
                        xml_=parser.getName().trim();     
                                                 //mybook.append(xml_.toString().trim(),null);
                          if(xml_.equals("book"))//如果是频道信息
                          {             
                             node++;
                             mes="预订"+node+" ";
                            int attrCount = parser.getAttributeCount();

                             for (int i = 0; i < attrCount; i++) 
                             {

                                 if( parser.getAttributeName(i).trim().equals("vname"))
                                 {
                                     mes =mes+ "节目： " + parser.getAttributeValue(i)+"\n";  
                                 }
                                 else if( parser.getAttributeName(i).trim().equals("channel"))
                                 {
                                     mes =mes+ "频道： " + parser.getAttributeValue(i)+"\n";  
                                 }
                                    else if( parser.getAttributeName(i).trim().equals("date"))
                                 {
                                     mes =mes+ "日期 ： " + parser.getAttributeValue(i)+"\n";  
                                 }
                                        else if( parser.getAttributeName(i).trim().equals("begin"))
                                 {
                                     mes =mes+ "开始时间： " + parser.getAttributeValue(i)+"\n";  
                                 }

                                             else if( parser.getAttributeName(i).trim().equals("end"))
                                 {
                                     mes =mes+ "结束时间： " + parser.getAttributeValue(i)+"\n";  
                                 }

                                // else mes =mes+ "  " + parser.getAttributeValue(i)+"  "; 
                                //mes =mes+parser.getAttributeName(i) + ":" + parser.getAttributeValue(i)+"  ";  

                             }
                             mybook.append(mes,null);
                          }
                                  
                       }
                      //mybook.append("fgdgd",null); 
                      eventType = parser.next();    
                    }
                    
                 }
               catch(IOException ioe){}
             iStrm.close();
             conn.close(); 
       }
                catch(Exception e){
                    System.out.print("error"+e.getMessage());
                }
        
    }
    
    public void itemStateChanged(Item item)// 监听和解封装detail 发送给playform
    {
              //客户机：dopost：name，频道，节目，日期，label=5 details  dopost
        
          detail_= new boolean[mybook.size()];
    mybook.getSelectedFlags(detail_);
  
    
    for(int i=0;i<detail_.length;i++)
    {
        if(detail_[i]  )
        {
            detail_book=mybook.getString(i);
          
        }
    
    }
       // int pos1=detail_book.indexOf()   
        
        
    }
    
}
