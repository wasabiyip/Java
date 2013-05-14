/*
 * playform.java
 *
 * Created on 2009年5月19日, 下午1:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 *
 *
 *
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
public class playform extends Form implements CommandListener,Runnable{
    
    //接受一个我的预订的当中的一个tiem的URL->player
    private player player_;
    
    private Command playagain;
    private Command backCmd;
    private Command stop_;
    
    private read_xml read_xml_ = null;
    private banacast midlet = null;
    private programform programform_ = null;
    private bookform bookform_ = null;
    
    private String detail_book =null;//接受bookform来的信息 来查url
    private String url_paly;//从服务器接收来的url 发给player
    public data a=new data();
    private String url=a.servlet_URL;
    private StringBuffer strbuf; 
    
    private HttpConnection c;
    private DataOutputStream os;
    private InputStream dis;
    /** Creates a new instance of playform */
    public playform(String detail_book,banacast a,read_xml b,programform c,bookform d) {
        
        super("banacast播放器");
        this.midlet=a;
        this.read_xml_=b;
        this.programform_=c;
        this.bookform_=d;
        this.detail_book=detail_book;
        midlet.setCurrent(this);
        playagain= new Command("播放",Command.SCREEN,1);
        
        backCmd=new Command("返回",Command.BACK,1);
        stop_=new Command("停止",Command.SCREEN,1);
        new Thread(this).start();
    // while(url_paly.equals(""))
   //  {
     
   //  }//阻塞下面的操作 等到url不为null的时候跳出
 
        
        
    }

    public void commandAction(Command command, Displayable displayable) {
        
        if(command==backCmd)// back to the bookform
        {
            player_.closeplay();
            midlet.setCurrent(bookform_);
       }
        else if(command==stop_)//stop playing
        {
            player_.playstop();
            player_.removeCommand(stop_);
            player_.addCommand(playagain);            
        }
        else if (command==playagain)
        {
            player_.startagain();
            player_.removeCommand(playagain);
            player_.addCommand(stop_);
        
        
        }
        
        
    }

    public void run() {//发送信息+id：5  服务器：返回一个URL
            try {

            c = (HttpConnection)Connector.open(url);
            c.setRequestMethod(HttpConnection.POST);
            c.setRequestProperty("Content-Language","en-US");
            c.setRequestProperty("User-Agent","Profile/MIDP-2.0 Configuration/CLDC-2.0");
            c.setRequestProperty("Accept","text/plain, */*");
            c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
            
            os =c.openDataOutputStream();
            detail_book="5#"+detail_book;
            os.write(detail_book.getBytes());
            os.flush();
            
            
             dis =c.openInputStream();
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
              int l = -1;
              while ((l = dis.read()) != -1) {
               baos.write(l);
              }
                dis.close();
                c.close();         
                
                url_paly=baos.toString().trim();//获得了url
        if(url_paly.trim().equals("false"))
        {
       this.setTitle("该节目还没有录制完毕，请选择已录制完的节目！"); ////////////////////////////
        this.addCommand(backCmd);
        this.setCommandListener(this);
        }
         else  
        {
 
       player_=new player(url_paly);
         player_.startThread();
        
        stop_=new Command("停止",Command.SCREEN,1);
        player_.addCommand(stop_);
        player_.addCommand(backCmd); 
        
//先获得链接地址 然后调用把 url传输给它 setcurrent（player）*/
        midlet.setCurrent(player_);   
         player_.setCommandListener(this);
        }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }

    
}
