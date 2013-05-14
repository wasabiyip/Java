/*
 * helpform.java
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
 *一个简单的帮助form
 */
public class helpform extends Form implements CommandListener{
    
    public Command c_back_ch;
    private banacast midlet = null;
    private read_xml read_xml_ = null;
    private programform programform_ = null;

    private ImageItem imageabout;
    
    
    /** Creates a new instance of helpform */
    public helpform(banacast a,read_xml b,programform c) {
        super("帮助信息");
        this.midlet=a;
        this.read_xml_=b;
        this.programform_=c;        
        c_back_ch = new Command("返回",Command.BACK,1);
         try {
             imageabout = new ImageItem ("",Image.createImage("/p/bbbb.png"),ImageItem.LAYOUT_CENTER+ImageItem.LAYOUT_NEWLINE_AFTER+ImageItem.LAYOUT_NEWLINE_BEFORE,"form");
             } catch (IOException ex) 
             {
                  ex.printStackTrace();
             }
            this.append(imageabout);         
        this.append("用户在登录后，进入频道列表就可以开始操作 \r\n"+
          "1.查看节目，可以看到具体频道的节目菜单，可以设置下来菜单来选择要查看的日期的节目。\r\n"+
                "2.预订，进入节目列表后可以进行预订。可以选择要预订的节目和设置预订的开始时间和结束时间。\r\n"+
	"2.我的预订—>播放，可以查看自己的预订信息，包括时间、名字、扣了的点数。点击我的预订里的播放按钮，还可以播放已经录制好的预订。\r\n"+
	"3.我的钱包，可以查看自己的余额。\r\n"+
 	"5.帮助。\r\n" +
            "6.关于banacast,关于banacast的更多介绍。\r\n");
        this.addCommand(c_back_ch);
        this.setCommandListener(this);
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
        
        
    }
    
}
