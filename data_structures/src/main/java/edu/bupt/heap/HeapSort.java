package edu.bupt.heap;

import java.util.Arrays;

public class HeapSort {
  public static void heapSort(int[] a) {
    int heapSize = a.length;
    // 堆化,从最后一个非叶子节点向上堆化，构建大顶堆
    for (int i = (heapSize / 2 - 1); i >= 0; i--) {
      // 循环非递归
//      maxHeapify(a, heapSize, i);
      // 递归方式
      maxHeapifyReverse(a, heapSize, i);
    }

    // 排序
    while (heapSize > 1) {
      swap(a, 0, --heapSize);
      maxHeapify(a, heapSize, 0);
    }
  }

  // 遍历方式
  private static void maxHeapify(int[] a, int heapSize, int position) {
    int maxPos = position;
    while (true) {
      int left = position * 2 + 1;
      if (left < heapSize && a[left] > a[maxPos]) {
        maxPos = left;
      }
      int right = position * 2 + 2;
      if (right < heapSize && a[right] > a[maxPos]) {
        maxPos = right;
      }

      if (maxPos != position) {
        swap(a, position, maxPos);
        position = maxPos;
      } else {
        break;
      }
    }
  }

  // 递归方式
  private static void maxHeapifyReverse(int[] a, int heapSize, int position) {
    int maxPos = position;
    int left = position * 2 + 1;
    if (left < heapSize && a[left] > a[maxPos]) {
      maxPos = left;
    }
    int right = position * 2 + 2;
    if (right < heapSize && a[right] > a[maxPos]) {
      maxPos = right;
    }
    if (maxPos != position) {
      swap(a, position, maxPos);
      maxHeapifyReverse(a, heapSize, maxPos);
    }
  }


  private static void swap(int[] a, int i, int j) {
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  public static void main(String[] args) {
    int[] a = new int[] {1, 7, 2, 4, 9, 6, 3, 5, 8};
    heapSort(a);
    Arrays.stream(a).forEach(System.out::println);
  }
}
