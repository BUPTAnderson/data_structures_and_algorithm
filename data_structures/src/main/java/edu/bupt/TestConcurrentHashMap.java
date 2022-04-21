package edu.bupt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestConcurrentHashMap {
  private static final Logger LOG = LoggerFactory.getLogger(TestConcurrentHashMap.class);
  public static void main(String[] args) {
    Set<String> set = Stream.of("html", "htm", "shtml", "css", "xml",
        "gif", "jpeg", "jpg", "js", "atom", "rss", "mml", "txt", "jad", "wml", "htc", "png", "tif",
        "tiff", "wbmp", "ico", "jng", "bmp", "svg", "svgz", "webp", "woff", "jar", "war", "ear",
        "json", "hqx", "pdf", "ps", "eps", "ai", "rtf", "m3u8", "xls", "eot", "ppt", "wmlc", "kml",
        "kmz", "7z", "cco", "jardiff", "jnlp", "run", "pl", "pm", "prc", "pdb", "rar", "rpm", "sea",
        "swf", "sit", "tcl", "tk", "der", "pem", "crt", "xpi", "xhtml", "xspf", "zip", "exe", "dll",
        "deb", "dmg", "iso", "img", "msi", "msp", "msm", "docx", "xlsx", "pptx", "mid", "doc",
        "midi", "kar", "mp3", "ogg", "m4a", "ra", "3gpp", "3gp", "ts", "mp4", "mpeg", "mpg", "mov",
        "webm", "flv", "m4v", "mng", "asx", "asf", "wmv", "avi").collect(Collectors.toCollection(TreeSet::new));
    set.forEach(item -> System.out.print("\"" + item + "\", "));
    String str = "aaaaaaa.jpg";
    System.out.println(str.lastIndexOf('.'));
    System.out.println(str.substring(str.lastIndexOf('.') + 1));
    System.out.println(str.indexOf('.', -1));
//    Map<Long, Resource> map = new ConcurrentHashMap<>();
//    map.put(1L, new Resource());
//
//    Thread thread = new Thread(new Process(map, 1L));
//    thread.start();
//
//    Thread thread1 = new Thread(new Worker(map));
//    thread1.start();
//
//    try {
//      thread.join();
//      thread1.join();
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
  }

  static class Process implements Runnable {
    Map<Long, Resource> map;
    Long key;

    public Process(Map<Long, Resource> map, Long key) {
      this.map = map;
      this.key = key;
    }

    @Override
    public void run() {
      map.compute(key, (k, v) -> {
        if (v != null) {
          v.mRefCount.incrementAndGet();
          v.mIsAccessed = true;
        }
        return v;
      });
    }
  }

  static class Worker implements Runnable {
    Map<Long, Resource> map;

    public Worker(Map<Long, Resource> map) {
      this.map = map;
    }

    @Override
    public void run() {
      Iterator<Map.Entry<Long, Resource>> iterator = map.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry<Long, Resource> entry = iterator.next();
        Resource resource = entry.getValue();
        resource.mIsAccessed = false;
        resource.mRefCount.compareAndSet(0, Integer.MIN_VALUE);
      }
    }
  }

  static class Resource {
    private volatile boolean mIsAccessed;
    private AtomicInteger mRefCount;

    private Resource() {
      mIsAccessed = false;
      mRefCount = new AtomicInteger(1);
    }
  }
}


