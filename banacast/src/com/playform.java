/*
 * playform.java
 *
 * Created on 2009��5��19��, ����1:46
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
    
    //����һ���ҵ�Ԥ���ĵ��е�һ��tiem��URL->player
    private player player_;
    
    private Command playagain;
    private Command backCmd;
    private Command stop_;
    
    private read_xml read_xml_ = null;
    private banacast midlet = null;
    private programform programform_ = null;
    private bookform bookform_ = null;
    
    private String detail_book =null;//����bookform������Ϣ ����url
    private String url_paly;//�ӷ�������������url ����player
    public data a=new data();
    private String url=a.servlet_URL;
    private StringBuffer strbuf; 
    
    private HttpConnection c;
    private DataOutputStream os;
    private InputStream dis;
    /** Creates a new instance of playform */
    public playform(String detail_book,banacast a,read_xml b,programform c,bookform d) {
        
        super("banacast������");
        this.midlet=a;
        this.read_xml_=b;
        this.programform_=c;
        this.bookform_=d;
        this.detail_book=detail_book;
        midlet.setCurrent(this);
        playagain= new Command("����",Command.SCREEN,1);
        
        backCmd=new Command("����",Command.BACK,1);
        stop_=new Command("ֹͣ",Command.SCREEN,1);
        new Thread(this).start();
    // while(url_paly.equals(""))
   //  {
     
   //  }//��������Ĳ��� �ȵ�url��Ϊnull��ʱ������
 
        
        
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

    public void run() {//������Ϣ+id��5  ������������һ��URL
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
                
                url_paly=baos.toString().trim();//�����url
        if(url_paly.trim().equals("false"))
        {
       this.setTitle("�ý�Ŀ��û��¼����ϣ���ѡ����¼����Ľ�Ŀ��"); ////////////////////////////
        this.addCommand(backCmd);
        this.setCommandListener(this);
        }
         else  
        {
 
       player_=new player(url_paly);
         player_.startThread();
        
        stop_=new Command("ֹͣ",Command.SCREEN,1);
        player_.addCommand(stop_);
        player_.addCommand(backCmd); 
        
//�Ȼ�����ӵ�ַ Ȼ����ð� url������� setcurrent��player��*/
        midlet.setCurrent(player_);   
         player_.setCommandListener(this);
        }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }

    
}
