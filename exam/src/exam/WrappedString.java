package exam;

 import java.util.*;
 public class WrappedString {
 public static void test(String str) {
 int check = 4;
 if (check == str.length()) {
 System.out.print(str.charAt(check -= 1) +", ");
 } else {
 System.out.print(str.charAt(0) + ", ");
 }
 }
 private String s;
 public WrappedString(String s) { this.s = s; }
 public static void main(String[] args) {
// HashSet<Object> hs = new HashSet<Object>();
// WrappedString ws1 = new WrappedString("aardvark");
// WrappedString ws2 = new WrappedString("aardvark");
// String s1 = new String("aardvark");
//  String s2 = new String("aardvark");
//   hs.add(ws1); hs.add(ws2); hs.add(s1); hs.add(s2);
//  System.out.println(hs.size());
//  System.out.println(ws1.hashCode() );
//  System.out.println(ws2.hashCode() );
//  System.out.println(s1.hashCode() );
//  System.out.println(s2.hashCode() );
  
  test("four");
  test("tee");
  test("to");
 }
 }
 