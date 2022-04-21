package edu.bupt.tree;

/**
 * 见图片ccp.png
 */
public class ClosestCommonParent {
  public long ccp(long m, long n) {
    long i = m;
    long j = n;
    while (i != j) {
      if (i < j) {
        j = getParent(j);
      } else {
        i = getParent(i);
      }
    }

    return i;
  }

  private long getParent(long node) {
    long i = (node - 1) / 3; // 如果树每层都是按照从左到右编号的话，i为node的父节点编号
    long level = ((long) (Math.log(2 * i + 1) / Math.log(3))) + 1; // i所在的层, root是第一层
    return (long) (((Math.pow(3, level) - 1) / 2 - 1) - (i - (Math.pow(3, level - 1) - 1) / 2));// i所在的层肯定和node层顺序相反，所以父节点正确序号为：（level层最大编号 - (i - level层最小编号)）
  }

  public static void main(String[] args) {
    ClosestCommonParent closestCommonParent = new ClosestCommonParent();
    System.out.println(closestCommonParent.ccp(13, 15));
    System.out.println(closestCommonParent.ccp(16, 10));
    System.out.println(closestCommonParent.ccp(13, 9));
  }
}
