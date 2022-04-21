package edu.bupt;

public class Test {
  public static void main(String[] args) {
//    System.out.println("3.0.0-SNAPSHORT".compareTo("3.0.0") >= 0);
//    System.out.println(new java.io.File(".").getAbsolutePath());
//    System.out.println(Test.class.getClassLoader().getResource("").getPath());
//    System.out.println(Test.class.getResource("/").getPath());
//    System.out.println(new Test().getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
//    for (int i = 630; i <= 669; i++) {
//      System.out.println("nm-agent-docker-" + i + ".k8s.momo.com");
//    }

    System.out.println(System.getProperty("java.class.path"));//系统的classpaht路径
    System.out.println(System.getProperty("user.dir"));//用户的当前路径
    try {
      Class.forName("org.apache.hadoop.io.erasurecode.CodecRegistry");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
