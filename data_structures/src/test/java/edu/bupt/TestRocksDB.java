package edu.bupt;

import com.google.common.primitives.Longs;
import org.rocksdb.Options;
import org.rocksdb.ReadOptions;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.RocksIterator;
import org.rocksdb.Slice;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;

import static sun.misc.Unsafe.ARRAY_BYTE_BASE_OFFSET;

public class TestRocksDB {
  private static final String dbPath   = "./rocksdb-data/";
  private static final String cfdbPath = "./rocksdb-data-cf/";
  private static final long arrayByteBaseOffset = (long) ARRAY_BYTE_BASE_OFFSET; // 16
  private final Unsafe unsafe;

  static {
    RocksDB.loadLibrary();
  }

  private static Unsafe getUnsafe(){
    try {
      Field field = Unsafe.class.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      Unsafe unsafe = (Unsafe) field.get(null);
      return unsafe;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public TestRocksDB() {
    Field unsafeField = Unsafe.class.getDeclaredFields()[0];
    unsafeField.setAccessible(true);
    unsafe = getUnsafe();
  }

  public void testDefaultColumnFamily() {
    System.out.println("testDefaultColumnFamily begin...");
    // 文件不存在，则先创建文件
    try (final Options options = new Options().setCreateIfMissing(true)) {
      try (final RocksDB rocksDB = RocksDB.open(options, dbPath)) {
        // 简单key-value
//        byte[] key = "Hello".getBytes();
//        rocksDB.put(key, "World".getBytes());
//
//        System.out.println(new String(rocksDB.get(key)));
//
//        rocksDB.put("SecondKey".getBytes(), "SecondValue".getBytes());
//
//        // 通过List做主键查询
//        List<byte[]> keys = Arrays.asList(key, "SecondKey".getBytes(), "missKey".getBytes());
//        List<byte[]> values = rocksDB.multiGetAsList(keys);
//        for (int i = 0; i < keys.size(); i++) {
//          System.out.println("multiGet " + new String(keys.get(i)) + ":" + (values.get(i) != null ? new String(values.get(i)) : null));
//        }
//
//        // 打印全部[key - value]
//        RocksIterator iter = rocksDB.newIterator();
//        for (iter.seekToFirst(); iter.isValid(); iter.next()) {
//          System.out.println("iterator key:" + new String(iter.key()) + ", iter value:" + new String(iter.value()));
//        }
//
//        // 删除一个key
//        rocksDB.delete(key);
//        System.out.println("after remove key:" + new String(key));


//        byte[] key1 = toByteArray(1L, 11L);
//        rocksDB.put(key1, "1_11".getBytes());
//        byte[] key2 = toByteArray(1L, 12L);
//        rocksDB.put(key2, "1_12".getBytes());
//        byte[] key3 = toByteArray(1L, 13L);
//        rocksDB.put(key3, "1_13".getBytes());


        //
//        byte[] key4 = toByteArray(2L, 11L);
//        rocksDB.put(key4, "2_11".getBytes());
//        byte[] key5 = toByteArray(2L, 12L);
//        rocksDB.put(key5, "2_12".getBytes());
//        byte[] key6 = toByteArray(2L, 13L);
//        rocksDB.put(key6, "2_13".getBytes());
//
//        byte[] key7 = toByteArray(3L, 11L);
//        rocksDB.put(key7, "3_11".getBytes());
//        byte[] key8 = toByteArray(3L, 12L);
//        rocksDB.put(key8, "3_12".getBytes());
//        byte[] key9 = toByteArray(3L, 13L);
//        rocksDB.put(key9, "3_13".getBytes());

        byte[] start = toByteArray(2L, 0L);

//        RocksIterator iter = rocksDB.newIterator(new ReadOptions().setIterateUpperBound(new Slice(toByteArray(2L, Long.MAX_VALUE))));
        RocksIterator iter = rocksDB.newIterator();
        iter.seekToFirst();

        while (iter.isValid()) {
          System.out.println(new String(iter.value()));
          iter.next();
        }
//        iter.seek(start);

//        long prefix = unsafe.getLong(start, arrayByteBaseOffset);
//        System.out.println("prefix: " + prefix);

//        for (iter.seek(start); iter.isValid(); iter.next()) {
//          final byte[] key = iter.key();
//          long a = unsafe.getLong(key, arrayByteBaseOffset);
//          long aa = ByteBuffer.wrap(key, 0, 8).order(ByteOrder.LITTLE_ENDIAN).getLong();
//          System.out.println(a);
//          if (a == prefix) {
//            System.out.println("iter value:" + new String(iter.value()));
//          } else {
//            break;
//          }
//        }
        iter.close();
//        iter.next();
      }
    } catch (RocksDBException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    TestRocksDB testRocksDB = new TestRocksDB();
    testRocksDB.testDefaultColumnFamily();
  }


  /**
   * @param long1 a long value
   * @param long2 a long value
   * @return a byte array formed by writing the two long values as bytes
   */
  public static byte[] toByteArray(long long1, long long2) {
    byte[] key = new byte[2 * Longs.BYTES];
    for (int i = Longs.BYTES - 1; i >= 0; i--) {
      key[i] = (byte) (long1 & 0xffL);
      long1 >>= Byte.SIZE;
    }
    for (int i = 2 * Longs.BYTES - 1; i >= Longs.BYTES; i--) {
      key[i] = (byte) (long2 & 0xffL);
      long2 >>= Byte.SIZE;
    }
    return key;
  }

  public static byte[] toPrefix(long long1) {
    byte[] key = new byte[Longs.BYTES];
    for (int i = Longs.BYTES - 1; i >= 0; i--) {
      key[i] = (byte) (long1 & 0xffL);
      long1 >>= Byte.SIZE;
    }

    return key;
  }
}
