/*
 * send_login.java
 *
 * Created on 2009年5月9日, 下午3:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com;

import java.io.*;
import javax.microedition.io.*;
import javax.microedition.*;
import javax.microedition.lcdui.Form;
/**
 *
 * @author yll
 */
public class send_login extends Thread {
     public Form form;
    private banacast midlet = null;
    public String url="http://localhost:8084/web/login";
    public String message;
    public StringBuffer strbuf = new StringBuffer();
    public HttpConnection c = null;
    public DataInputStream dis = null;
    /** Creates a new instance of send_login */
    public send_login(String message,banacast a) {
        this.midlet = a;
        this.message= message;
         start();
    }

    public void run() {
        
        try{
            //open object
            c = (HttpConnection)Connector.open(url + '?' + message);
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
                
       form.append(strbuf.toString());
       midlet.setCurrent(form);
       
           /* if(len>0)
            {
                byte[] data = new byte[len];
                dis.readFully(data);
                for(int i =0;i<data.length;i++)
                {
                    strbuf.append((char)data[i]);
                 }
                          
            }
            else{
                int ch;
                while((ch=dis.read()) != -1)
                {
                    strbuf.append((char)ch);
                }
                
            }*/
                     
        }
        catch(Exception e){
            System.out.print("error"+e.getMessage());
        }
      
    }
   
 public String check()
 {
     System.out.print(strbuf.toString());
     return strbuf.toString();
   //  if(strbuf.equals("true"))
    // return true;
   //      return false;
 }

    
    
}
