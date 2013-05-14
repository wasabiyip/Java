/*
 * banacast.java
 *
 * Created on 2009年4月23日, 下午3:36
 */

package com;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 *
 * @author  yll
 * @version
 */
public class banacast extends MIDlet {
    private Display display;
    public login LOGIN;
    public banacast() {
		super();
                display = Display.getDisplay(this);		
	}
    
  protected void startApp() 
	throws MIDletStateChangeException{
		
		try
		{
			LOGIN = new login(this);
			
                }
		catch(Exception exc)
		{
			setCurrent(
				new Alert(
						"初始化错误", 
						"错误原因为:"
								+ exc.getMessage() + "/" + exc.getClass(),
								null, AlertType.ERROR));
		}
	}
  
  	/**********************************************************
	//	 setCurrent()
	//
	//	 Description:
	//	  设置当前显示的界面
	//
	//	 Parameters:
	//	 Return Values:
	//	 Author:
	**********************************************************/  
  
    public void setCurrent(Displayable disp){
		display.setCurrent(disp);
	}
    
    public void setCurrent(Alert alert, Displayable disp){
		display.setCurrent(alert, disp);
    }
    
    public void pauseApp() {
    }
    /**********************************************************
	//	 getCurrentDisplay()
	//
	//	 Description:
	//	  获取当前的Display，这样可以让其他类能够控制要显示什么
	//
	//	 Parameters:
	//	 Return Values:
	//	 Author:
    **********************************************************/
    public Display getCurrentDisplay(){
		return display;
    }
    public void destroyApp(boolean unconditional){
    }
    public void exit(boolean arg0){
         destroyApp(arg0);
	 notifyDestroyed();
    }
}
