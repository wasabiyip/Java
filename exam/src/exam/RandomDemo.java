package exam;

import java.util.Random;

public class RandomDemo {

    public static void main(String[] args) { 
        // Random random = new Random(1234567L);
        Random random = new Random();
        boolean a = random.nextBoolean();   // true or false
        int     b = random.nextInt();       // between -2^31 and 2^31 - 1
        int     c = random.nextInt(100);    // between 0 and 99
        double  d = random.nextDouble();    // between 0.0 and 1.0
        double  e = random.nextGaussian();  // gaussian with mean 0 and stddev = 1
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
    }
}