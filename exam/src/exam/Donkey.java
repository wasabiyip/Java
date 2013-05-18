package exam;

import java.net.URL;
import java.util.Locale;

public class Donkey {
    public static void main(String[] args) {
        boolean assertsOn = false;
        assert (assertsOn) : assertsOn = true;
        if(assertsOn) {
            System.out.println("assert is on");
        }
    }
}
