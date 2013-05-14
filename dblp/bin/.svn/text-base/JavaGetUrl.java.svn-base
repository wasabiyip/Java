//------------------------------------------------------------//
//  JavaGetUrl.java:                                          //
//------------------------------------------------------------//
//  A Java program that demonstrates a procedure that can be  //
//  used to download the contents of a specified URL.         //
//------------------------------------------------------------//
//  Code created by Developer's Daily                         //
//  http://www.DevDaily.com                                   //
//------------------------------------------------------------//

import java.io.*;
import java.net.*;

public class JavaGetUrl {

   public static void main (String[] args) {

      //-----------------------------------------------------//
      //  Step 1:  Start creating a few objects we'll need.
      //-----------------------------------------------------//

      URL u;
    //  InputStream is = null;
      //DataInputStream dis;
      //String s;

      try {

         //------------------------------------------------------------//
         // Step 2:  Create the URL.                                   //
         //------------------------------------------------------------//
         // Note: Put your real URL here, or better yet, read it as a  //
         // command-line arg, or read it from a file.                  //
         //------------------------------------------------------------//
    	 //4980
    	  BufferedWriter wr = new BufferedWriter(new FileWriter("2.txt"));
    	 for(int i=13078;i<=14980;i++){
    		 	String url="http://eventseer.net/e/"+i+"/";
    	         u = new URL(url);
    	         HttpURLConnection  conn = (HttpURLConnection)u.openConnection();
    	       String strMessage = conn.getResponseMessage();
    	       if (strMessage.compareTo("Not Found") == 0) 
    	    	   continue;


    	         
    	         BufferedReader reader =new BufferedReader (
    	        		 new InputStreamReader (conn.getInputStream()) );
    	         String line = null;
    	        
    	         while((line=reader.readLine())!=null){
    	        	 //int r=line.indexOf("class=\"t-link\">");
    	        	 
    	        		 int l=line.split("</a>").length;
    	        		 for(int k=0;k<l;k++)
    	        		 {
    	        			 if (line.split("</a>")[k].indexOf("class=\"t-link\">")>0){
    	        				 String[] temp=line.split("</a>")[k].split("class=\"t-link\">");
    	        				 if (temp.length>1)
    	        				 {
    	        					 wr.append(temp[1]+"\n");
    	        					// System.out.println(temp[1]+"\n");
    	        				 }
    	        			 }
    	        		 }
    	        	 

    	         }
    	         reader.close();
    	         System.out.println(i+" done");
    	 }
    	 wr.close();
    	
         //----------------------------------------------//
         // Step 3:  Open an input stream from the url.  //
         //----------------------------------------------//

        // is = u.openStream();         // throws an IOException

         //-------------------------------------------------------------//
         // Step 4:                                                     //
         //-------------------------------------------------------------//
         // Convert the InputStream to a buffered DataInputStream.      //
         // Buffering the stream makes the reading faster; the          //
         // readLine() method of the DataInputStream makes the reading  //
         // easier.                                                     //
         //-------------------------------------------------------------//

//         dis = new DataInputStream(new BufferedInputStream(is));

         //------------------------------------------------------------//
         // Step 5:                                                    //
         //------------------------------------------------------------//
         // Now just read each record of the input stream, and print   //
         // it out.  Note that it's assumed that this problem is run   //
         // from a command-line, not from an application or applet.    //
         //------------------------------------------------------------//

       /*  while ((s = dis.readLine()) != null) {
            System.out.println(s);
         }
*/
      } catch (MalformedURLException mue) {

         System.out.println("Ouch - a MalformedURLException happened.");
         mue.printStackTrace();
         System.exit(1);

      } catch (IOException ioe) {

         System.out.println("Oops- an IOException happened.");
         ioe.printStackTrace();
         System.exit(1);

      } finally {

         //---------------------------------//
         // Step 6:  Close the InputStream  //
         //---------------------------------//

      /*   try {
        //    is.close();
         } catch (IOException ioe) {
            // just going to ignore this one
         }
*/
      } // end of 'finally' clause

   }  // end of main

} // end of class definition