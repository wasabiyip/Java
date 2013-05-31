package exam2;
 public class Stealth {
 @SuppressWarnings("static-access")
public static void main(String[] args) {
 Integer i = 420;
 Integer i2;
 Integer i3;
 i2 = i.intValue();
 i3 = i.valueOf(420);
 System.out.println((i == i2) + " " + (i == i3));
 } }