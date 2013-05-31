package exam2;
 import java.util.*;
 public class Vinegar {
 public static void main(String[] args) {
 Set<Integer> mySet = new HashSet<Integer>();
 do1(mySet, "0"); do1(mySet, "a");//exception
 do2(mySet, "0"); do2(mySet, "a");
 System.out.println(Integer.parseInt("a"));
 }
 public static void do1(Set s, String st) {
 s.add(st);
 s.add(Integer.parseInt(st));//exception
 }
 public static void do2(Set<Integer> s, String st) {
 //s.add(st);
 s.add(Integer.parseInt(st));
 } }