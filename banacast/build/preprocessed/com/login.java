/*
 * login.java
 *
 * Created on 2009��4��23��, ����3:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com;
import javax.microedition.rms.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.*;
import java.io.*;

import javax.microedition.midlet.MIDletStateChangeException;
/**
 * ����rms+ʹrms֧������  ת����
 *
 * ������¼ ֻ����Ƶ����+��Ŀ�б�+����+����
 *������ 
 * @author yll
 */
public class login extends Form implements CommandListener,Runnable{
   
    
    private RecordStore banacast_data;
    
    private static boolean ini=false;
    private banacast midlet = null;
    public Form form;
    public Command cmd_exit;
    public Command cmd_login; 
    public Command about;
    public Command help; 
    public ImageItem image;
    public ChoiceGroup choice=new ChoiceGroup("",ChoiceGroup.BUTTON);
   // public send_login sender;
    
    public String buffer;
    public String buffer2;
    public String usernames;
    public String passwords;
    public String name;
    
    public    TextField username;
    public    TextField password;
    
    //public    TextField a;
    static String message;
    public read_xml rx;
    
    Alert splashScreenAlert; 
    Alert wrongScreenAlert; 
    String   splashScreen;
     static final int DefaultTimeout = 8000;
    public data a=new data();    
    public String url=a.servlet_URL;
 
    public StringBuffer strbuf = new StringBuffer();
    public HttpConnection c = null;
    public DataInputStream dis = null;
  
   public  helpform helpform_;
   public  aboutform aboutform_;
    /** Creates a new instance of login */
    public login(banacast a) throws RecordStoreException {
          
        //��Ҫȥ��һ�����ݿ��еĻ�������� û�еĻ�������
        super("banacast");    
        this.midlet=a;
       
        try {//�����ݿ� �վ͹ر� ����Ͷ�
                 
            banacast_data = RecordStore.openRecordStore("banacast_name",false);   
            //banacast_data.addRecordListener(new MyRecordListener());
            byte [] namepass = banacast_data.getRecord(1); 
        //�������� name#password
            buffer = new String(namepass);
            int pos1 = buffer.indexOf('#');
            usernames=buffer.substring(0,pos1);
            passwords=buffer.substring(pos1+1,buffer.length());
            ini=true;
          //  this.username.setString(this.usernames);
         //   this.password.setString(this.passwords);
            banacast_data.closeRecordStore();
            
        } catch (RecordStoreNotFoundException ex) {
          // this.username.setString("chu");
          
        }
        
        
        username = new TextField("�û���",usernames,20,TextField.ANY);
        password =new TextField("���� ",passwords,20,TextField.PASSWORD);
        Ticker url =new Ticker("http://vod.tv33.netݷݷ����");
        form = new Form("Banacast:����һ�����������ϵͳ��");
       
        cmd_exit = new Command("�˳�", Command.EXIT, 0);
        cmd_login = new Command("��¼",Command.SCREEN,1);
        help = new Command("����",Command.SCREEN,1);
        about = new Command("����",Command.SCREEN,1);
        
        try{
            image = new ImageItem ("",Image.createImage("/p/logo.png"),ImageItem.LAYOUT_CENTER+ImageItem.LAYOUT_NEWLINE_AFTER+ImageItem.LAYOUT_NEWLINE_BEFORE,"form");
            }catch(java.io.IOException e){}  
        
     
        choice.append("��ס����",null);
        choice.setSelectedIndex(0,ini);
        // form.setTicker(url);
        form.append(image);
        form.append(username) ;
        form.append(password);  
        form.append(choice);
        form.append("�����������¼���û����������Ϊ�� test����Сд���ɣ�");
        form.addCommand(cmd_exit);
        form.addCommand(cmd_login);
        
        form.addCommand(help);
        form.addCommand(about);
        form.setCommandListener(this);        
        setCurrent(form);

        
        
        
    
    }
    	public void setCurrent(Displayable disp){
		midlet.setCurrent(disp);
    }
        
	public void setCurrent(Alert alert, Displayable disp){
		midlet.setCurrent(alert, disp);
    }
    

     public void commandAction(Command cmd, Displayable d)
     {
         if(cmd == cmd_exit){
            midlet.destroyApp(false);
            midlet.notifyDestroyed();
         }
         else if(cmd == cmd_login)//ͬʱ������д�뱾��
         {
             name=username.getString();
             message = "Name=" + username.getString() + "&PSW=" + password.getString();
             if (choice.isSelected(0))//д�뱾��
             {
                    try {
                        banacast_data=RecordStore.openRecordStore("banacast_name",true);
                    } catch (RecordStoreException ex) {
                        ex.printStackTrace();
                    }
                    try {
                          
                        if(banacast_data.getNumRecords()!=0)//���ݿ�ǿ� �滻 д�뱾��
                         {
                            buffer2= username.getString()+"#"+password.getString();
                            byte[] data;
                                  try {
                                      
                                      data = buffer2.getBytes();
                                      try {
                                         
                                          banacast_data.setRecord(1,data,0,data.length);
                                          
                                      } catch (InvalidRecordIDException ex) {
                                          ex.printStackTrace();
                                      } catch (RecordStoreNotOpenException ex) {
                                          ex.printStackTrace();
                                      } catch (RecordStoreException ex) {
                                          ex.printStackTrace();
                                      }
                                  } catch (Exception ex) {
                                      ex.printStackTrace();
                                  }
                                  try {
                                            
                                      banacast_data.closeRecordStore();
                                  } catch (RecordStoreNotOpenException ex) {
                                      ex.printStackTrace();
                                  } catch (RecordStoreException ex) {
                                      ex.printStackTrace();
                                  }
                        }else//���ݿ�� �����µ�
                     {
                        try {
                           // banacast_data = RecordStore.openRecordStore("banacast_name",true);
                            buffer2= username.getString()+"#"+password.getString();
                            
                                byte [] data = buffer2.getBytes();
                                banacast_data.addRecord(data,0,data.length);
                         
                             
                        } catch (RecordStoreException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            banacast_data.closeRecordStore();
                        } catch (RecordStoreNotOpenException ex) {
                            ex.printStackTrace();
                        } catch (RecordStoreException ex) {
                            ex.printStackTrace();
                        }
              
                      }
                    } catch (RecordStoreNotOpenException ex) {
                        ex.printStackTrace();
                    }
             }
                    new Thread(this).start(); 
              
             }else if(cmd==help)
             {//���ҵİ���
                Image   Logo   =   null;   
          try   {   
              Logo   =   Image.createImage("/p/bbbb.png");   
          }   
          catch   (IOException   err)   {   
          } 
             String buffer3="�û��ڵ�¼�󣬽���Ƶ���б�Ϳ��Կ�ʼ���� \r\n"+
          "1.�鿴��Ŀ�����Կ�������Ƶ���Ľ�Ŀ�˵����������������˵���ѡ��Ҫ�鿴�����ڵĽ�Ŀ��\r\n"+
                "2.Ԥ���������Ŀ�б����Խ���Ԥ��������ѡ��ҪԤ���Ľ�Ŀ������Ԥ���Ŀ�ʼʱ��ͽ���ʱ�䡣\r\n"+
	"2.�ҵ�Ԥ����>���ţ����Բ鿴�Լ���Ԥ����Ϣ������ʱ�䡢���֡����˵ĵ���������ҵ�Ԥ����Ĳ��Ű�ť�������Բ����Ѿ�¼�ƺõ�Ԥ����\r\n"+
	"3.�ҵ�Ǯ�������Բ鿴�Լ�����\r\n"+
 	"5.������\r\n" +
            "6.����banacast,����banacast�ĸ�����ܡ�\r\n";
             Alert alert = new Alert("�ҵİ���",buffer3,Logo,AlertType.INFO);
              alert.setTimeout(Alert.FOREVER);
              midlet.setCurrent(alert); 
           
         }else if (cmd==about){ //���ҵ�
         Image   Logo   =   null;   
          try   {   
              Logo   =   Image.createImage("/p/bbbb.png");   
          }   
          catch   (IOException   err)   {   
          } 
             String buffer3="*�汾1.0��2009��5��14�գ�\r\n"+
            "*��Ȩ����2009 Banacast,SCUT.��������Ȩ����\r\n"+
            "*��ʹ��Ӧ�������֤���� \r\n"+
            "*Banacast��һ���ڽ�������������������ӣ����տ���۵��ӽ�Ŀ��"+
            "����P2P����������������Ƶֱ��ϵͳ������ϵͳֻ��Ҫ�൱�ٵ�Ӳ����Դ��"+
            "���֧�Ŵ����Ĳ����û��������Ż���P2P���繹������Դ�����㷨��"+
             "���û����ܼ�����Ƶֱ����Banacast֧��˫��Ԥ����ʱ�Ƶ��¹���. \r\n"+
            "*���ྫ�����ݣ������ \r\n"+
            "*http://www.tv33.net/";
             Alert alert = new Alert("����Banacast",buffer3,Logo,AlertType.INFO);
              alert.setTimeout(Alert.FOREVER);
              midlet.setCurrent(alert);
        
         }
           
           
                 
             
         }

      

    public void run() { //��¼ �������� ����+ID :1
        try{
            splashScreen ="���ڵ�¼�У����Ե�......";          
             splashScreenAlert = new Alert("��¼��...",splashScreen,null,AlertType.INFO);
             splashScreenAlert.setTimeout(DefaultTimeout);
              midlet.setCurrent(splashScreenAlert);
        
        //message name+password
            c = (HttpConnection)Connector.open(url + '?' + message.trim()+"&LABEL=1");
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
            if(strbuf.toString().trim().equalsIgnoreCase("true")){
                 rx= new read_xml(name,midlet);
                 midlet.setCurrent(rx);              
                }
                else{ 
                  
                             String wrongScreen="�û����������������������������";
                            wrongScreenAlert = new Alert("���ִ���",wrongScreen,null,AlertType.INFO);
                            wrongScreenAlert.setTimeout(DefaultTimeout);
                            midlet.setCurrent(wrongScreenAlert);
                }
                    
        }
        catch(Exception e){
            System.out.print("error"+e.getMessage());
        }
    }

    
    
}
//Name, PSW, channel_id, LABEL=1<> 2<> 3<> 4<> 