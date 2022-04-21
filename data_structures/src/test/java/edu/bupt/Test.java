package edu.bupt;

public class Test {
  public static void main(String[] args) {
//		int a[]={3,4,8,1,12,34,68,21,56};
//		mergeSort(a,0,8);
//		for(int i=0;i<a.length;i++){
//			System.out.println(a[i]+" ");
//		}
    System.out.println(yuesefu(5, 2));
  }

  //约瑟夫问题
  public static int yuesefu(int m, int n) {
    int size = m, weizhi = 0, k;
    int array[] = new int[m]; //k是用来计数的，weizhi记录数数到的位置
    for (int i = 0; i < m; i++) {
      array[i] = 1;
    }

    while (size > 1) {
      k = 1;
      while (k < n) {
        //weizhi++;
        //if(weizhi>m-1)
        //weizhi=weizhi-m;
        weizhi = (weizhi + 1) % m;
        if (array[weizhi] == 1)
          k++;
      }
      array[weizhi] = 0;
      //System.out.println(weizhi+1);
      while (array[weizhi] == 0) {
        weizhi = (weizhi + 1) % m;
      }
      size--;
    }
    for (int i = 0; i < m; i++) {
      if (array[i] == 1)
        return i + 1;
    }
    return -1;
  }

  //快速排序
  public static void quickSort(int a[], int low, int high) {
    if (low < high) {
      int pos = findPos(a, low, high);
      quickSort(a, low, pos - 1);
      quickSort(a, pos + 1, high);
    }
  }

  public static int findPos(int a[], int low, int high) {
    int val = a[low];
    while (low < high) {
      while (low < high && a[high] >= val)
        high--;
      a[low] = a[high];
      while (low < high && a[low] <= val)
        low++;
      a[high] = a[low];

    }
    a[low] = val;
    return low;
  }

  //冒泡排序
  public static void Bubble(int[] a) {
    for (int i = 0; i < a.length - 1; i++) {
      for (int j = 0; j < a.length - 1 - i; j++) {
        if (a[j] > a[j + 1]) {
          int tmp = a[j];
          a[j] = a[j + 1];
          a[j + 1] = tmp;
        }
      }
    }
  }

  //简单选择排序
  public static void selectSort(int[] a) {
    int min = 0;
    for (int i = 0; i < a.length - 1; i++) {
      min = i;
      for (int j = i + 1; j < a.length; j++) {
        if (a[j] < a[min]) {
          min = j;
        }
      }
      if (min != i) {
        int tmp = a[min];
        a[min] = a[i];
        a[i] = tmp;
      }
    }
  }

  //直接插入排序
  public static void insertSort(int[] a) {
    for (int i = 1; i < a.length; i++) {
      if (a[i] < a[i - 1]) {
        int tmp = a[i];
        int j = 0;
        for (j = i - 1; j >= 0 && a[j] > tmp; j--) {
          a[j + 1] = a[j];
        }
        a[j + 1] = tmp;
      }
    }
  }

  //希尔排序
  public static void shellSort(int[] a) {
    int i, j, tmp;
    int increment = a.length;
    do {
      increment = increment / 3 + 1;
      for (i = increment; i < a.length; i++) {
        if (a[i] < a[i - increment]) {
          tmp = a[i];
          for (j = i - increment; j >= 0 && a[j] > tmp; j--) {
            a[j + increment] = a[j];
          }
          a[j + increment] = tmp;
        }
      }
    } while (increment > 1);
  }

  //堆排序#################################################
  public static void heapSort(int[] a) {
    int heapSize = a.length;
    // 构建大顶堆(堆化)
    for (int i = heapSize / 2 - 1; i >= 0; i--) {
      maxHeap(a, i, heapSize);
    }
    // 排序, 每次把最大值放到最后，对剩余的部分重新堆化
    for (int i = heapSize - 1; i > 0; i--) {
      swap(a, 0, i);
      heapSize--;
      maxHeap(a, 0, heapSize);
    }
  }

  public static void maxHeap(int[] a, int position, int heapSize) {
    int left = left(position);
    int right = right(position);
    int maxPosition = position;

    if (left < heapSize && a[left] > a[maxPosition])
      maxPosition = left;
    if (right < heapSize && a[right] > a[maxPosition])
      maxPosition = right;

    if (maxPosition != position) {
      swap(a, position, maxPosition);
      maxHeap(a, maxPosition, heapSize);
    }
  }

  public static int left(int i) {
    return 2 * i + 1;
  }

  public static int right(int i) {
    return 2 * i + 2;
  }

  public static void swap(int[] a, int i, int j) {
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

//############################################
  //二路归并排序  start  ##################################
  public static void mergeSort(int[] array, int start, int end) {
    if (start < end) {
      int mid = start + (end - start) / 2;
      mergeSort(array, start, mid);
      mergeSort(array, mid + 1, end);
      merge(array, start, mid, end);
    }
  }

  //merge将已经排好序的 array[start~mid]和arrat[(mid+1)~end]归并为有序的arrya[start~end]
  public static void merge(int[] array, int start, int mid, int end) {
    int[] temp1 = new int[mid - start + 2];  //将array[start~mid]复制到temp[0~(mid-start)]
    int[] temp2 = new int[end - mid + 1];  //将array[(mid+1)~end]复制到temp2[0~(end-mid-1)]
    //复制
    for (int i = start; i <= mid; i++) {
      temp1[i - start] = array[i];
    }
    temp1[mid - start + 1] = Integer.MAX_VALUE;

    for (int i = mid + 1; i <= end; i++) {
      temp2[i - mid - 1] = array[i];
    }
    temp2[end - mid] = Integer.MAX_VALUE;

    int p1 = 0;  //temp1的指针
    int p2 = 0;  //temp2的指针

    int index = start;  ////array的指针

    while ((p1 < mid - start + 1) && (p2 < end - mid)) {
      if (temp1[p1] <= temp2[p2]) {
        array[index++] = temp1[p1++];
      } else {
        array[index++] = temp2[p2++];
      }
    }

    if (p1 == mid - start + 1) {
      while (p2 != end - mid) {
        array[index++] = temp2[p2++];
      }
    }

    if (p2 == end - mid) {
      while (p1 != mid - start + 1) {
        array[index++] = temp1[p1++];
      }
    }
  }
// 二路归并排序 end##########################################
}

