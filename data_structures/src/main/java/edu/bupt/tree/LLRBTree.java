package edu.bupt.tree;

/**
 * 红黑树是2-3-4树的一种抽象表示，在1978年 Guibas 和 Sedgewick 发明最初的红黑树。
 * 2008年Sedgewick 对其进行了改进，并将此命名为 LLRBT(Left-leaning red–black tree 左倾红黑树)。
 * LLRBT 相比1978年的红黑树要简单很多，实现的代码量也少很多。Sedgewick的一篇 PPT 对此有非常详细的介绍。
 * 现在大部分工程中的红黑树都是基于1978发明的算法。
 * <p>
 * 这里我们先实现LLRBT (注：下面的参考都有部分错误，注意分辨)
 * <p>
 *
 * 红黑树的另一种定义是满足下列条件的二叉查找树：
 * ⑴红链接均为左链接。
 * ⑵没有任何一个结点同时和两条红链接相连。
 * ⑶该树是完美黑色平衡的，即任意空链接到根结点的路径上的黑链接数量相同。
 *
 * 链接： https://zhuanlan.zhihu.com/p/157113891; 这篇文章里面对于左旋和右旋操作代码不对，下面的代码中左旋和右旋的代码是正确的，代码来自算法第四版
 * 不管是左旋还是右旋，旋入的节点color与之前该位置的节点color相同，旋出的节点cllor置为红色。
 * 可参考算法第四版
 * 链接：https://www.jianshu.com/p/ea71bb20eca8
 * <p>
 * 删除操作可以参考：
 * https://www.cs.princeton.edu/~rs/talks/LLRB/08Dagstuhl/RedBlack.pdf
 * <p>
 * 参考：
 * https://zhuanlan.zhihu.com/p/94575830
 *
 *
 * #########################  2-3树的定义  ###########################
 * 一棵2-3树由以下节点组成：
 * 2-节点，含有一个键（及其对应的值）和两条链接，左连接指向的2-3树中的键都小于该节点，右链接指向的2-3树中的键都大于该节点。
 * 3-节点，含有两个键（及其对应的值）和三条链接，左链接指向的2-3树中的键都小于该节点，中链接指向的2-3树中的键都位于该节点的两个键之间，右链接指向的2-3树中的键都大于该节点。
 *
 * 2-3树的创建
 * 如果树为空，直接创建
 * 原则1. 加入新节点时，不会往空的位置添加节点，而是添加到最后一个叶子节点上(使2-节点变为3-节点，3-节点变为4-节点)
 * 原则2. 如果出现4-节点，要将它分解成三个2-节点组成的树，并且分解后新树的根节点需要向上和父节点融合（父节点变成3-节点或者4-节点）
 */

public class LLRBTree<KEY extends Comparable<KEY>, VALUE> {
  private static final boolean RED = true;
  private static final boolean BLACK = false;

  private class Node {
    KEY key;//键
    VALUE value;//值
    Node left, right;//左右子树
    boolean color;

    //构造函数
    Node(KEY key, VALUE value, boolean color) {
      this.key = key;
      this.value = value;
      this.color = color;
    }
  }

  private Node root;

  //判断节点x的颜色
  private boolean isRed(Node x) {
    if (x == null) return false;
    return x.color == RED;
  }

  private Node rotateLeft(Node h) {
    Node x = h.right;
    h.right = x.left;
    x.left = h;
    x.color = h.color;
    h.color = RED;
    return x;
  }

  private Node rotateRight(Node h) {
    Node x = h.left;
    h.left = x.right;
    x.right = h;
    x.color = h.color;
    h.color = RED;
    return x;
  }

//  private void flipColors(Node h) {
//    h.color = RED;
//    h.left.color = BLACK;
//    h.right.color = BLACK;
//  }

  private void flipColors(Node h) {
    h.color = !h.color;
    h.left.color = !h.left.color;
    h.right.color = !h.right.color;
  }

  public void put(KEY key, VALUE value) {
    //查找key，找到则更新它的值，找不到就创建一个新的节点
    root = put(root, key, value);
    root.color = BLACK;//根节点永远是黑色的
  }

  private Node put(Node h, KEY key, VALUE value) {
    if (h == null) //标准的插入操作，和父节点用红链接相连
      return new Node(key, value, RED);

    int cmp = key.compareTo(h.key);
    if (cmp < 0) h.left = put(h.left, key, value);
    else if (cmp > 0) h.right = put(h.right, key, value);
    else h.value = value;

    // 注意这里，比如一种情况是插入的节点等效为插入的父节点是三节点，插入的值是介入父节点(3节点)的2个值之间，这个时候是以此执行左旋右旋和改变颜色操作，所以下面三个操作每个能都会执行，所以是
    // 三个if操作，不是if else的三个选择一个执行。
    if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
    if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
    if (isRed(h.left) && isRed(h.right)) flipColors(h);

    return h;
  }

  private Node moveRedLeft(Node h) { // 又分为两种情况：h.right.left.color=BLACK和h.right.left.color=RED
    flipColors(h);                   // h.right.left.color=BLACK -> flipColors
    if (isRed(h.right.left)) {       // h.right.left.color=RED   -> flipColors -> rotateRight(h.right)) -> rotateLeft(h) -> flipColors
      h.right = rotateRight(h.right);
      h = rotateLeft(h);
      flipColors(h);
    }
    return h;
  }

  public void deleteMin() {
    if (!isRed(root.left) && !isRed(root.right))
      root.color = RED;

    root = deleteMin(root);
    if (!isEmpty()) root.color = BLACK;
  }

  /**
   * 归根节点，不管删除最大值还是最小值，我们期望删除的节点不是2节点，如果是2节点则进行处理。
   *
   * @param h
   * @return
   */
  private Node deleteMin(Node h) {
    if (h.left == null)
      return null;                             // 要处理h的左子节点(左子树)了，如果左子节点是2节点，进行处理
    if (!isRed(h.left) && !isRed(h.left.left)) // 当且仅当h.left和h.left.left都是黑色的时候，我们做调整（制造红色节点）。
      h = moveRedLeft(h);                      // 参考RedBlock.pdf
    h.left = deleteMin(h.left);                // 删除h的左子节点(左子树的最小值)
    return balance(h);
  }

  private Node moveRedRight(Node h) {
    flipColors(h);
    if (isRed(h.left.left)) {
      h = rotateRight(h);
      flipColors(h);
    }
    return h;
  }

  public void deleteMax() {
    if (!isRed(root.left) && !isRed(root.right))
      root.color = RED;

    root = deleteMax(root);
    if (!isEmpty()) root.color = BLACK;
  }

  /**
   * 删除要发生在非2-节点上才能保证树的平衡不被破坏。这就意味着删除一定要发生在一个被红色链接相连的节点上。
   * 在自顶向下搜索过程需要保证中间节点不是2-节点来使得叶子节点必然可以转化为非2-节点进行安全删除；
   * 反应在红黑树中，搜索路径的下一个节点，必须要被红色链接相连。如果不是的话，则要进行变化
   *
   * @param h
   * @return
   */
  private Node deleteMax(Node h) {
    if (isRed(h.left)) // Rotate red links to the right(当前节点有左倾斜红色链接时，将其进行右旋。)
      h = rotateRight(h);
    if (h.right == null) // 没有右子树了，删除该节点
      return null;                               // 下一个节点是2节点，我们进行处理
    if (!isRed(h.right) && !isRed(h.right.left)) // when h.right and h.right.left are both BLACK(因为我们要往右走来删除最大值, 搜索路径的下一节点为2-节点，转化为非-2节点。下一节点不是红节点并且下一节点的左孩子不是红色，则下一节点肯定是2节点)
      h = moveRedRight(h); // 参考RedBlock.pdf
    h.right = deleteMax(h.right);
    return balance(h);
  }

  public void delete(KEY key) {
    if (!isRed(root.left) && !isRed(root.right))
      root.color = RED;

    root = delete(root, key);
    if (!isEmpty()) root.color = BLACK;
  }

  private Node delete(Node h, KEY key) {
    if (key.compareTo(h.key) < 0) { // 向左走
      if (!isRed(h.left) && !isRed(h.left.left)) // 下推红链接(如果h的左子节是2节点，调用moveRedLeft处理)
        h = moveRedLeft(h);
      h.left = delete(h.left, key);
    } else { // 向右走
      if (isRed(h.left)) // (当前节点有左倾斜红色链接时，将其进行右旋。)
        h = rotateRight(h);
      if (key.compareTo(h.key) == 0 && (h.right == null)) // 找到了，且没有右孩子了(到底了)）
        return null;
      if (!isRed(h.right) && !isRed(h.right.left))
        h = moveRedRight(h);
      if (key.compareTo(h.key) == 0) { // 找到了，没到底
//        h.key = min(h.right);         // 这行和下一行是把右子树的最小值替换到当前节点，然后删除右子树的最小值即可。这两行有点啰嗦(https://www.cs.princeton.edu/~rs/talks/LLRB/Java/RedBlackBST.java)
//        h.value = get(h.right, h.key);// 直接找到最小值节点就可以了，这里还先找最小值key然后根据最小值key再找key对应的value ,这里我直接用下面三行替换
        Node min = minNode(h.right);
        h.key = min.key;
        h.value = min.value;
        h.right = deleteMin(h.right);
      } else {
        h.right = delete(h.right, key);
      }
    }
    return balance(h);
  }

  public KEY min() {
    if (root == null)
      return null;
    else
      return min(root);
  }

  private KEY min(Node h) {
    while (h.left != null) {
      h = h.left;
    }
    return h.key;
  }

  private Node minNode(Node h) {
    while (h.left != null) {
      h = h.left;
    }
    return h;
  }

  public VALUE get(KEY key) {
    return get(root, key);
  }

  private VALUE get(Node x, KEY key) {
    if (x == null)
      return null;
    if (eq(key, x.key))
      return x.value;
    if (less(key, x.key))
      return get(x.left, key);
    else
      return get(x.right, key);
  }

  private boolean eq(KEY a, KEY b) {
    return a.compareTo(b) == 0;
  }

  private boolean less(KEY a, KEY b) {
    return a.compareTo(b) < 0;
  }

  private Node balance(Node h) {
    if (isRed(h.right)) h = rotateLeft(h);
    if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
    if (isRed(h.left) && isRed(h.right)) flipColors(h);

    return h;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public static void main(String[] args) {
    LLRBTree<Integer, String> llrbTree = new LLRBTree();
    llrbTree.put(1, "A");
    llrbTree.put(3, "C");
    llrbTree.put(6, "F");
    llrbTree.put(9, "J");
    llrbTree.put(10, "K");
    llrbTree.put(11, "M");
    llrbTree.put(13, "O");
    System.out.println("----");
  }
}
