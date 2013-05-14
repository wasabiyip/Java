/*
 * palyer_.java
 *
 * Created on 2009年5月18日, 下午10:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com;

/**
 *
 * @author yll
 */
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.StringItem;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VideoControl;

/**
 *
 * @author Administrator
 */
public class player extends Canvas  implements Runnable
{   private StringItem msg;
    private Player mplayer;
    private VideoControl vc;
    private String url_play;
    /**
     * Creates a new instance of player
     */
public player(String url_play) {
        this.url_play=url_play;
    }
public void playre()
{
    try {

            mplayer = Manager.createPlayer(url_play );
             mplayer.realize();
             vc = (VideoControl)mplayer.getControl("VideoControl");
            if( vc != null )
            {
              vc.initDisplayMode(VideoControl.USE_DIRECT_VIDEO,this);
              int frameW = vc.getSourceWidth();
              int frameH = vc.getSourceHeight();
              vc.setDisplayLocation( 0, 0 );
              vc.setDisplaySize(120, 180 );
              vc.setVisible(true);
              mplayer.start();
            }
  
        } catch (MediaException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
}
public void playstop()
{
        try {
            this.mplayer.stop();
           
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
}


public void closeplay()
{
    
            this.mplayer.close();    
       

}
public void startagain()//pause
{
 
   try {
            this.mplayer.start();
           
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
}


    protected void paint(Graphics g) {
      
        g.fillRect( 0, 0, getWidth(), getHeight() );

    }
public void startThread()
    {
        Thread thread = new Thread( this );
        thread.start();
    }


    public void run() {
      this.playre();
    }
    
}