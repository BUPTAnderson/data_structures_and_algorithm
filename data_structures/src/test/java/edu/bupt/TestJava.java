package edu.bupt;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public class TestJava {
  public static void main(String[] args) {
    String a = "abc";
    String b = a;
    a = "def";
    System.out.println(b);
    int c = 10;
    int d = c;
    c = 20;
    System.out.println(d);


    LinkedHashMap<String, Integer> treeMap = new LinkedHashMap<>();
    treeMap.put("aaa", 111);
    treeMap.put("bbb", 222);
    treeMap.put("ccc", 333);
//    treeMap.inde

  }
}
