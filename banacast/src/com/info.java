/*
 * info.java
 *
 * Created on 2009��5��25��, ����1:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com;

import java.io.*;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.*;

/**
 *
 * @author yll
 * *Ҫsend�ܶ���Ϣ��ȥ
 *1.name 2.Ƶ��ID 3.��Ŀ���� 4.��ʼʱ�� 5.����ʱ�� 6.������/������  +id��4
 */
public class info extends Form implements CommandListener,Runnable {
    
    private read_xml read_xml_ = null;
    private banacast midlet = null;
    private programform programform_ = null;
    private String detail= null;
    
    public Command cmd_cancel;
    public Command cmd_again; 
    public Command cmd_back;

   private HttpConnection c;
    private DataInputStream dis;
  private  OutputStream os;
  public data a=new data();  
   public String url=a.servlet_URL;
    public StringBuffer strbuf = new StringBuffer();
    
    /** Creates a new instance of info */
    public info(String detail,banacast c,read_xml b,programform d) {
        super("");
        this.detail=detail;
        this.midlet=c;
        this.read_xml_=b;
        this.programform_=d;
      
        cmd_back = new Command("����",Command.BACK,1);
        cmd_again=new Command("�ٷ���һ��",Command.ITEM,1);
        cmd_cancel=new Command("ȡ��",Command.BACK,1);
        //this.addCommand(cmd_back);
         //this.setCommandListener(this);
         new Thread(this).start();
      
    }

    public void commandAction(Command command, Displayable displayable) {
        if(command == cmd_back)
        {
            midlet.setCurrent(programform_);
        }
        else if(command == cmd_again)
        {
            new Thread(this).start();
        }
        else if(command == cmd_cancel)
        {
            midlet.setCurrent(programform_);
        }
    }

    public void run() {//send detail +id 4 ���ɹ��� append ���ء���ʧ�ܾ�append �ٷ���һ�κ�ȡ����
        try {
        detail="4#"+detail;
        
          this.append("���ڷ�����Ϣ�У���ȴ�һ��....");
           //+ '?'+ "#LABEL=4"
            c = (HttpConnection)Connector.open(url);
            c.setRequestMethod(HttpConnection.POST);
            c.setRequestProperty("Content-Language","en-US");
            c.setRequestProperty("User-Agent","Profile/MIDP-2.0 Configuration/CLDC-2.0");
            c.setRequestProperty("Accept","text/plain, */*");
            c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
            
            os =c.openDataOutputStream();
            os.write(detail.getBytes());
            os.flush();
            
            
                dis =c.openDataInputStream();
               int ch;
                while((ch=dis.read()) != -1)
                {
                    strbuf.append((char)ch);
                }
                dis.close();
                c.close();
                
                
                if(strbuf.toString().trim().equalsIgnoreCase("true"))
                {
                    this.deleteAll();
                    this.append("��ϲ�㣬�ν�ĿԤ���ɹ���");
                    this.addCommand(cmd_back);
                   this.setCommandListener(this);
                }
                else if(strbuf.toString().trim().equalsIgnoreCase("false"))
                {
                this.deleteAll();
                this.append("Ԥ��ʧ�ܣ�");
                this.addCommand(cmd_again);
                this.addCommand(cmd_cancel);
                this.setCommandListener(this);
                }
                else{
                     this.deleteAll();
                this.append("�������㱾��Ԥ����");
                this.addCommand(cmd_again);
                this.addCommand(cmd_cancel);
                this.setCommandListener(this);
                }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
           
       
    }
    
}
