/*
 * read_xml.java
 *
 * Created on 2009��4��23��, ����4:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 *
 *�Ȼ������ Ȼ���жϳ��м��� ��ʼ�� choice group
 *ѡ����һ������
 *����ѡ����һ��Ƶ��  ����Ƶ��������+����+name
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
            
    public static String select_date_time="";   //���� 
   public String url="";
   public String select_chnl="";//Ƶ��
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
        super("����Ƶ��");
        this.message=a; //�û���
        this.midlet=b;     
       // c_back_ch = new Command("����",Command.BACK,1);
        c_exit = new Command("�˳�", Command.EXIT, 0);
        programe = new Command("�鿴��Ŀ",Command.ITEM,1);
        book = new Command("�ҵ�Ԥ��",Command.ITEM,1);
        money = new Command("�ҵ�Ǯ��",Command.ITEM,1);
        help = new Command("����",Command.ITEM,1);
        about = new Command("����",Command.ITEM,1);    
        
        //******************************ʱ��  ����Ҫ��װһ��
  
        date_time = new ChoiceGroup("����ѡ��",Choice.POPUP);        
        date_time.append("2009-05-12",null);
        date_time.append("2009-05-13",null);
        date_time.append("2009-05-14",null);
        date_time.append("2009-05-15",null);
        date_time.append("2009-05-16",null);
        date_time.append("2009-05-17",null);
        date_time.append("2009-05-18",null);
        
        this.append(date_time);
        //******************************Ƶ��  
        chnl = new ChoiceGroup("",Choice.EXCLUSIVE);
        this.append(chnl);

        // chnl.setFitPolicy(Choice.TEXT_WRAP_ON);
        //*****************************ʱ��2
        /*
        date_time2=new ChoiceGroup("����ѡ��",Choice.POPUP);
        date_time2.append("2009-05-12",null);
        date_time2.append("2009-05-13",null);
        date_time2.append("2009-05-14",null);
        date_time2.append("2009-05-15",null);
        date_time2.append("2009-05-16",null);
        date_time2.append("2009-05-17",null);
        date_time2.append("2009-05-18",null);
        
        
        this.append(date_time2);*/
        //***************************** 
       
  //*****************************�˵�
        this.addCommand(c_exit);
        this.addCommand(programe);
        this.addCommand(book);
        this.addCommand(money);
        this.addCommand(help);
        this.addCommand(about);
        
        this.setItemStateListener(this);//��Ӽ���choice group
        this.setCommandListener(this);//��Ӳ˵�����
        new Thread(this).start();
        

    }

    public void commandAction(Command command, Displayable displayable) {
         if(command == c_exit){
             
           midlet.destroyApp(false);
           midlet.notifyDestroyed();
           
         }else if(command==programe){//����Ŀ Ҫ����
          // url+select_date_time +select_chnl 
        programform_=new programform(url,select_date_time,select_chnl,channel_name,message,this,this.midlet);
        midlet.setCurrent(programform_);
             
         }else if(command== book){//���ҵ�Ԥ��
           bookform_=new bookform(message,midlet,this,null);
           midlet.setCurrent(bookform_);
           
         }else if(command==money){//���ҵ�Ǯ��
          moneyform_=new moneyform(message,midlet,this,null);
           midlet.setCurrent(moneyform_);
        
         
         }else if(command==help){//���ҵİ���
           helpform_=new helpform(midlet,this,null);
           midlet.setCurrent(helpform_);
           
         }else if (command==about){ //���ҵ�
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

    
 public void Interpret() throws XmlPullParserException, IOException//����Ƶ����  ���URL
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
            
              if(xml_.equals("channel"))//�����Ƶ����Ϣ
              {             
                node++;
                 String mes="Ƶ��"+node+" ";
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
              else if(xml_.equals("date"))//�����������Ϣ
             {
                String buffer3 = parser.nextText().toString();//�ж����ڼ� Ȼ���������ڵ������˵�
             
             }          
              else if(xml_.equals("url"))
              {//��ȡurl  
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
    
    public void itemStateChanged(Item item)//����ؼ�����
    {

//����� date_time ���� date_time2 �и��¶�Ҫͬ��һ��  chnl ֻҪ�����ʲǫ̂�Ϳ�����

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
    
    //send select_chnl(ID��)+select_date_time +id
    
    }
 



    
}
