/*
 * banacast.java
 *
 * Created on 2009��4��23��, ����3:36
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
						"��ʼ������", 
						"����ԭ��Ϊ:"
								+ exc.getMessage() + "/" + exc.getClass(),
								null, AlertType.ERROR));
		}
	}
  
  	/**********************************************************
	//	 setCurrent()
	//
	//	 Description:
	//	  ���õ�ǰ��ʾ�Ľ���
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
	//	  ��ȡ��ǰ��Display�������������������ܹ�����Ҫ��ʾʲô
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
