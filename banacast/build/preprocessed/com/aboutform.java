/*
 * aboutform.java
 *
 * Created on 2009年5月14日, 下午10:31
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
 */
public class aboutform extends Form implements CommandListener {
    
    public Command c_back_ch;
    private banacast midlet = null;
    private read_xml read_xml_ = null;
    private programform programform_ = null;
    public ImageItem imageabout;
    
    
    /** Creates a new instance of aboutform */
    public aboutform(banacast a,read_xml b,programform c) {
        super("关于Banacast");
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
             this.append("*版本1.0（2009年5月14日）\r\n"+
            "*版权所有2009 Banacast,SCUT.保留所有权利。\r\n"+
            "*其使用应遵守许可证条款 \r\n"+
            "*Banacast是一款在教育网运行最快的网络电视，能收看香港电视节目。"+
            "基于P2P技术的新型网络视频直播系统，服务系统只需要相当少的硬件资源，"+
            "便可支撑大量的并发用户。采用优化的P2P网络构建和资源搜索算法，"+
             "让用户感受极速视频直播。Banacast支持双屏预览、时移等新功能. \r\n"+
            "*更多精彩内容，请访问 \r\n"+
            "*http://www.tv33.net/");
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
