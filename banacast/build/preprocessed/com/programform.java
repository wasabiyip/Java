/*
 * programform.java
 *
 * Created on 2009��5��14��, ����10:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 *
 *���� 0:00-12:00�������绹������ choice groupʱ�任 ����Ķ���
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
 */
public class programform extends Form implements CommandListener,Runnable,ItemStateListener {
   
    public static int next=0;
    public String select_chnl="";//Ƶ��
    public String select_date_time="";   //����   
    public String name;//�û����� 
    public String select_program_name;
    
    public String time_string;
    public static String select_program;
    private String url;//��Ŀ�б� ���ӵ�ַ
   private String channel_name;
   public String begin_time=null;
   public String end_time;
    
   public Command moring;
   public Command afternoon;
   public Command evening;
   
    public Command out;
    public Command book;
    public Command money;
    public Command help;
    public Command about;
    public Command c_back_ch;
    public Command booksure;
    
    public ChoiceGroup pro_m;
    public ChoiceGroup pro_a;
    public ChoiceGroup pro_e;
    public ChoiceGroup face;
    
    public  ChoiceGroup date_time3;
   // public  ChoiceGroup date_time4;
    
    
    private read_xml read_xml_ = null;
    private banacast midlet = null;
    
    

    private DataInputStream din;
    private HttpConnection conn = null;
    private OutputStream oStrm = null;
    private InputStream iStrm;
    static int a=0;
    
    public  aboutform aboutform_;
    public  helpform helpform_;
    private moneyform moneyform_;
    private bookform bookform_;
    private bookingform bookingform_;
    
    
    /** Creates a new instance of programform */
    public programform(String url,String date_time,String channel_id,String channel_name,String name ,read_xml b,banacast c) {
          
          super(channel_name);
          this.read_xml_=b;  
          this.midlet=c;
          this.name=name ;
          this.select_date_time = date_time;
          this.select_chnl = channel_id;          
          this.url=url;
          this.channel_name=channel_name;
          //super(channel_name);
         moring=new Command("���� ",Command.ITEM,1);
         afternoon=new Command("����",Command.ITEM,1);
         evening=new Command("����",Command.ITEM,1);
       
         c_back_ch = new Command("����",Command.BACK,1);
         booksure =new Command("*����ҪԤ���˽�Ŀ��*",Command.ITEM,1);
         book = new Command("�ҵ�Ԥ��",Command.ITEM,1);
         money = new Command("�ҵ�Ǯ��",Command.ITEM,1);
         help = new Command("����",Command.ITEM,1);
         about = new Command("����",Command.ITEM,1);  
         out= new Command("�˳�",Command.ITEM,1); 
          //********************************
          date_time3 = new ChoiceGroup("ʱ��ѡ��",Choice.POPUP); 
          date_time3.append("���� 0:00-12:00",null);
          date_time3.append("����12:00-17:30",null);
          date_time3.append("���� 17:30-0:00",null);
          //***************************** ��Ŀ choice group
         pro_m = new ChoiceGroup("",Choice.EXCLUSIVE);
          pro_a = new ChoiceGroup("",Choice.EXCLUSIVE);
          pro_e = new ChoiceGroup("",Choice.EXCLUSIVE);
          /*  pro_m.setFitPolicy(Choice.TEXT_WRAP_ON);
           pro_a.setFitPolicy(Choice.TEXT_WRAP_ON);
           pro_e.setFitPolicy(Choice.TEXT_WRAP_ON);*/
          face=pro_m;
          // face.setFitPolicy(Choice.TEXT_WRAP_ON);
        this.addCommand(booksure);
         this.addCommand(moring);
         this.addCommand(afternoon);
         this.addCommand(evening);
          this.addCommand(c_back_ch);
       
          this.addCommand(book);
          this.addCommand(money);
          this.addCommand(help);
          this.addCommand(about);          
          this.addCommand(out);
           this.append(date_time3);
          this.append(face);//Ĭ����append ���� 0:00-12:00��
         
           //this.append(pro_m);
          new Thread(this).start();      
          this.setCommandListener(this);
          this.setItemStateListener(this);//��Ӽ���choice group
          
          
    }

    public void commandAction(Command command, Displayable displayable) {
         if(command==c_back_ch)// back
         {
         midlet.setCurrent(read_xml_);
         }
         else if(command==book)//my book**********************************************!!!!
         {
           bookform_=new bookform(name,midlet,read_xml_,this);
           midlet.setCurrent(bookform_);
         }
         else if(command==money)//money**********************************************!!!!
         {
           moneyform_=new moneyform(name,midlet,read_xml_,this);
           midlet.setCurrent(moneyform_);
         }
         else if(command==help)//help
         {
         helpform_ = new helpform(midlet,read_xml_,this);
         midlet.setCurrent(helpform_);
         }
         else if (command==about)//about
         {
          aboutform_ = new aboutform(midlet,read_xml_,this);
          midlet.setCurrent(aboutform_);
         }
          else if (command==booksure)//booking ���� Ƶ�� ��Ŀ���� ��ʼʱ�� name 
         {
         bookingform_= new bookingform(select_date_time,channel_name,select_program_name,begin_time,end_time,name,midlet,read_xml_,this);
          midlet.setCurrent(bookingform_);
          
         }else if(command==out)
         {
           midlet.destroyApp(false);
            midlet.notifyDestroyed();
         }
         else if(command==moring)
         {
             date_time3.setSelectedIndex(0,true);
            face=pro_m;
            this.deleteAll();
           this.append(date_time3);
            this.append(face);
         }
         else if(command==afternoon)
         {
              date_time3.setSelectedIndex(1,true);
              face=pro_a;
             this.deleteAll();
             this.append(date_time3);
             this.append(face);
         }
         else 
         {
              date_time3.setSelectedIndex(2,true);
              face=pro_e;
              this.deleteAll();
              this.append(date_time3);
              this.append(face);
         }
    }

    public void run() {
        
         try {
             
               Interpret1();
           
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (XmlPullParserException ex) {
                ex.printStackTrace();
            }  
        
    }
    
     public void Interpret1() throws XmlPullParserException, IOException//������Ŀ�� ���� Ƶ��ID+id��2��������
{
   //��Ҫ�����ݵĴ���
   //URL/����/Ƶ��ID.xml
   //conn = (HttpConnection) Connector.open("http://125.216.242.30:8084/20090512/20080002.xml");
  conn = (HttpConnection) Connector.open(url.trim()+select_date_time.trim()+"/"+select_chnl.trim()+".xml");
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
        if(eventType == XmlPullParser.START_TAG)//����xml ��ʼ
        {   
           
            
            xml_=parser.getName().trim();             
            if(xml_.equals("item"))//��һ����¼������
           {     
                node++;
                 int attrCount = parser.getAttributeCount();
                 String mes="��Ŀ"+node+" ";
                 for (int i = 0; i < attrCount; i++) 
                 {
                     if(parser.getAttributeName(i).trim().equals("label"))//�����label ��ô append ����ͬ������MORNING AFTERNOON EVENING
                     {
                         if(parser.getAttributeValue(i).trim().equals("m"))
                         {
                         pro_m .append(mes,null);
                         }
                         else if(parser.getAttributeValue(i).trim().equals("a"))
                         {
                         pro_a .append(mes,null);
                         }
                         else if(parser.getAttributeValue(i).trim().equals("e"))
                         {
                         pro_e .append(mes,null);
                         }
                         
                     }
                         else mes=mes+ " " + parser.getAttributeValue(i);   //������Ƕ�ȡ���� ��ŵ�string mes�� �ȴ� append                  
                 }
                          
             }  
            
        }
        eventType = parser.next(); 
     } 
   }
   catch(IOException ioe)
   {}
  iStrm.close();
  conn.close();

}
     
       public void itemStateChanged(Item item)//����ؼ�����
    {
           
          // date_time4 date_time3 pro_m pro_a pro_e
   //*******************************************
        boolean[] array_time3= new boolean[date_time3.size()];
        date_time3.getSelectedFlags(array_time3);  

        for(int i=0;i<array_time3.length;i++)
        {
            if(array_time3[i])
            {
                time_string=date_time3.getString(i);

            }

        }
    //*******************************************�����ж� ��m ����a ����e
      if (time_string.trim().equals("���� 0:00-12:00".trim()))
      {
           // ce=pro_m;
           // this.deleteAll();face.deleteAll();
            face=pro_m;
            this.deleteAll();
           this.append(date_time3);
            this.append(face);
      }
      else if(time_string.trim().equals("����12:00-17:30".trim()))
      {
           
            face=pro_a;
            this.deleteAll();
            this.append(date_time3);
            this.append(face);
      }
      else 
      {
            face=pro_e;
            this.deleteAll();
            this.append(date_time3);
            this.append(face);
      }
        
    //*******************************************��������choice ��Ŀ
       
        boolean[] array_time5= new boolean[pro_m.size()];
        face.getSelectedFlags(array_time5); 
     for(int i=0;i<array_time5.length;i++)
        {
            if(array_time5[i])
            {
                next=i+1;
                select_program=face.getString(i);
                 int pos1=select_program.indexOf(" ");//ҪԤ����
        int pos2=select_program.indexOf(" ",pos1+2);
        select_program_name=select_program.substring(pos1+1,pos2);
        begin_time=select_program.substring(pos2+1,select_program.length()).trim();//ҪԤ���Ŀ�ʼʱ��


        String next_p =face.getString(next);
        int pos3=next_p.indexOf(" "); 
        int pos4=next_p.indexOf(" ",pos3+2);
        end_time=next_p.substring(pos4+1,next_p.length()).trim();//ҪԤ���Ľ���ʱ��
        
        String alldetail="  ���ڣ�"+select_date_time.substring(0,4)+"-"+select_date_time.substring(4,6)+"-"+select_date_time.substring(6,8)+
                "\r\n"+"  Ƶ���� "+channel_name+
                "\r\n"+"  ��Ŀ�� "+select_program_name+
                "\r\n"+"  ��ʼʱ�䣺"+begin_time+
                "\r\n"+"  ����ʱ�䣺"+end_time;
                
        
           Alert alert = new Alert(select_program_name,alldetail,null,AlertType.INFO);
          alert.setTimeout(Alert.FOREVER);
          midlet.setCurrent(alert);
            }
        }
        
        

        
        
        
       
       // select_date_time,channel_name,select_program_name,begin_time,end_time,name
   
     }
     
     
}
