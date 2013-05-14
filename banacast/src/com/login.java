/*
 * login.java
 *
 * Created on 2009年4月23日, 下午3:37
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
 * 包括rms+使rms支持中文  转码器
 *
 * 匿名登录 只允许看频道表+节目列表+关于+帮助
 *如果点击 
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
          
        //先要去读一下数据库有的话就提出来 没有的话就算了
        super("banacast");    
        this.midlet=a;
       
        try {//打开数据库 空就关闭 否则就读
                 
            banacast_data = RecordStore.openRecordStore("banacast_name",false);   
            //banacast_data.addRecordListener(new MyRecordListener());
            byte [] namepass = banacast_data.getRecord(1); 
        //处理数据 name#password
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
        
        
        username = new TextField("用户名",usernames,20,TextField.ANY);
        password =new TextField("密码 ",passwords,20,TextField.PASSWORD);
        Ticker url =new Ticker("http://vod.tv33.net莘莘网视");
        form = new Form("Banacast:世界一流的网络电视系统！");
       
        cmd_exit = new Command("退出", Command.EXIT, 0);
        cmd_login = new Command("登录",Command.SCREEN,1);
        help = new Command("帮助",Command.SCREEN,1);
        about = new Command("关于",Command.SCREEN,1);
        
        try{
            image = new ImageItem ("",Image.createImage("/p/logo.png"),ImageItem.LAYOUT_CENTER+ImageItem.LAYOUT_NEWLINE_AFTER+ImageItem.LAYOUT_NEWLINE_BEFORE,"form");
            }catch(java.io.IOException e){}  
        
     
        choice.append("记住密码",null);
        choice.setSelectedIndex(0,ini);
        // form.setTicker(url);
        form.append(image);
        form.append(username) ;
        form.append(password);  
        form.append(choice);
        form.append("如果想匿名登录，用户名和密码均为： test（大小写均可）");
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
         else if(cmd == cmd_login)//同时把数据写入本地
         {
             name=username.getString();
             message = "Name=" + username.getString() + "&PSW=" + password.getString();
             if (choice.isSelected(0))//写入本地
             {
                    try {
                        banacast_data=RecordStore.openRecordStore("banacast_name",true);
                    } catch (RecordStoreException ex) {
                        ex.printStackTrace();
                    }
                    try {
                          
                        if(banacast_data.getNumRecords()!=0)//数据库非空 替换 写入本地
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
                        }else//数据库空 建立新的
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
             {//看我的帮助
                Image   Logo   =   null;   
          try   {   
              Logo   =   Image.createImage("/p/bbbb.png");   
          }   
          catch   (IOException   err)   {   
          } 
             String buffer3=a.help;
             Alert alert = new Alert("我的帮助",buffer3,Logo,AlertType.INFO);
              alert.setTimeout(Alert.FOREVER);
              midlet.setCurrent(alert); 
           
         }else if (cmd==about){ //看我的
         Image   Logo   =   null;   
          try   {   
              Logo   =   Image.createImage("/p/bbbb.png");   
          }   
          catch   (IOException   err)   {   
          } 
             String buffer3=a.about;
             Alert alert = new Alert("关于Banacast",buffer3,Logo,AlertType.INFO);
              alert.setTimeout(Alert.FOREVER);
              midlet.setCurrent(alert);
        
         }
           
           
                 
             
         }

      

    public void run() { //登录 发送密码 名字+ID :1
        try{
            splashScreen ="正在登录中，请稍等......";          
             splashScreenAlert = new Alert("登录中...",splashScreen,null,AlertType.INFO);
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
                  
                             String wrongScreen="用户名或者密码输入错误，请重新输入";
                            wrongScreenAlert = new Alert("出现错误",wrongScreen,null,AlertType.INFO);
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