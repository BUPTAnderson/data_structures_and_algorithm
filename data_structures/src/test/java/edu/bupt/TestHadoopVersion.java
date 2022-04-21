package edu.bupt;

public class TestHadoopVersion {
  private static final String EC_VERSION = "3.0.0";

  public static void main(String[] args) {
    // CDH
    System.out.println("2.6.0-cdh5.6.1: " + ("2.6.0-cdh5.6.1".compareTo(EC_VERSION) >= 0));
    System.out.println("2.6.0-cdh5.8.5: " + ("2.6.0-cdh5.8.5".compareTo(EC_VERSION) >= 0));
    System.out.println("2.6.0-cdh5.11.2: " + ("2.6.0-cdh5.11.2".compareTo(EC_VERSION) >= 0));
    System.out.println("2.6.0-cdh5.12.2: " + ("2.6.0-cdh5.12.2".compareTo(EC_VERSION) >= 0));
    System.out.println("2.6.0-cdh5.13.2: " + ("2.6.0-cdh5.13.2".compareTo(EC_VERSION) >= 0));
    System.out.println("2.6.0-cdh5.14.0: " + ("2.6.0-cdh5.14.0".compareTo(EC_VERSION) >= 0));
    System.out.println("2.6.0-cdh5.15.0: " + ("2.6.0-cdh5.15.0".compareTo(EC_VERSION) >= 0));
    System.out.println("2.6.0-cdh5.16.0: " + ("2.6.0-cdh5.16.0".compareTo(EC_VERSION) >= 0));
    System.out.println("3.0.0-cdh6.0.0: " + ("3.0.0-cdh6.0.1".compareTo(EC_VERSION) >= 0));
    System.out.println("3.0.0-cdh6.0.1: " + ("3.0.0-cdh6.0.1".compareTo(EC_VERSION) >= 0));
    System.out.println("3.0.0-cdh6.1.1: " + ("3.0.0-cdh6.1.1".compareTo(EC_VERSION) >= 0));
    System.out.println("3.0.0-cdh6.1.0: " + ("3.0.0-cdh6.1.0".compareTo(EC_VERSION) >= 0));
    System.out.println("3.0.0-cdh6.2.1: " + ("3.0.0-cdh6.2.1".compareTo(EC_VERSION) >= 0));
    System.out.println("3.0.0-cdh6.3.1: " + ("3.0.0-cdh6.3.1".compareTo(EC_VERSION) >= 0));
    System.out.println("3.0.0-cdh6.3.2: " + ("3.0.0-cdh6.3.2".compareTo(EC_VERSION) >= 0));
    System.out.println("3.1.1.7.2.0.2-2: " + ("3.1.1.7.2.0.2-2".compareTo(EC_VERSION) >= 0));

    // HDP
    System.out.println("2.7.1.2.4.4.1-9: " + ("2.7.1.2.4.4.1-9".compareTo(EC_VERSION) >= 0)); // 2.4.0 -> apache hadoop-2.7.1
    System.out.println("2.7.3.2.5.5.5-2: " + ("2.7.3.2.5.5.5-2".compareTo(EC_VERSION) >= 0)); // 2.5.0
    System.out.println("2.7.3.2.6.1.0-129: " + ("2.7.3.2.6.1.0-129".compareTo(EC_VERSION) >= 0)); // 3.0.0
    System.out.println("3.0.0.3.0.0.0-829: " + ("3.0.0.3.0.0.0-829".compareTo(EC_VERSION) >= 0)); // 3.0.0
    System.out.println("3.0.0.3.0.0.3-2: " + ("3.0.0.3.0.0.3-2".compareTo(EC_VERSION) >= 0)); // 3.0.0
    System.out.println("3.1.0.3.0.0.8-1: " + ("3.1.0.3.0.0.8-1".compareTo(EC_VERSION) >= 0)); // 3.0.1 -> apache hadoop-3.1.0
    System.out.println("3.1.1.3.0.1.0-129: " + ("3.1.1.3.0.1.0-129".compareTo(EC_VERSION) >= 0)); // 3.0.2 -> apache hadoop-3.1.1
    System.out.println("3.1.1.3.0.2.0-50: " + ("3.1.1.3.0.2.0-50".compareTo(EC_VERSION) >= 0)); // 3.0.0
    System.out.println("3.1.1.3.1.0.0-78: " + ("3.1.1.3.1.0.0-78".compareTo(EC_VERSION) >= 0)); // 3.1.0
    System.out.println("3.1.1.3.1.2.0-4: " + ("3.1.1.3.1.2.0-4".compareTo(EC_VERSION) >= 0)); // 2.8
    System.out.println("3.1.1.3.1.3.0-79: " + ("3.1.1.3.1.3.0-79".compareTo(EC_VERSION) >= 0)); // 2.8
    System.out.println("3.1.1.3.1.4.0-315: " + ("3.1.1.3.1.4.0-315".compareTo(EC_VERSION) >= 0)); // 2.8
    System.out.println("3.1.1.3.1.5.0-152: " + ("3.1.1.3.1.5.0-152".compareTo(EC_VERSION) >= 0)); // 2.8


    // apache
    System.out.println("2.9.2: " + ("2.9.2".compareTo(EC_VERSION) >= 0)); // 2.8
    System.out.println("3.0.0: " + ("3.0.0".compareTo(EC_VERSION) >= 0)); // 2.8
    System.out.println("3.1.1: " + ("3.1.1".compareTo(EC_VERSION) >= 0)); // 2.8
    System.out.println("3.2.1: " + ("3.2.1".compareTo(EC_VERSION) >= 0)); // 2.8
    System.out.println("3.3.0: " + ("3.3.0".compareTo(EC_VERSION) >= 0)); // 2.8
  }
}
