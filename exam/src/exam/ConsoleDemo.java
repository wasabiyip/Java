package exam;

import java.io.Console;  

public class ConsoleDemo {  
    public static void main(String[] args) {  
          
        Console console = System.console();  
        if (console != null) {  
            Console cons = System.console();  
            String username = cons.readLine("User name: ");  
            char[] passwd = cons.readPassword("Password: ");  
            System.out.println("your name is: " + username);  
            System.out.println("your password is: " + new String(passwd));  
        } else {  
            System.out.println("Console is unavailable!");  
        }  
    }  
}