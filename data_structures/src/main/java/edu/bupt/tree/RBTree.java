package edu.bupt.tree;

/**
 * RBTree:红黑树
 * 1.每个节点要么是黑色要么是红色；
 * 2.根节点是黑色；
 * 3.每个叶子节点都是黑色的空节点（NIL），也就是说，叶子节点不存储数据；
 * 4.任何相邻的节点都不能同时为红色，也就是说，红色节点是被黑色节点隔开的；
 * 5.每个节点，从该节点到达其可达叶子节点的所有路径，都包含相同数目的黑色节点；
 *
 * 红黑树对应2-3-4树  (2-3-4树中的2、3、4的含义指的是一个节点可能含有的子节点数。)
 * 左倾红黑树对应2-3树，将3节点改为左连接是红链接(左孩子是红孩子)的两个2节点即可。
 * 而红黑树允许出现4节点(包含3个值得节点是4节点，所谓的2、3、4是指该链接可以有几条伸出的表，即可以有几个孩子)，红黑树没有限制只有左连接可以是红链接。
 * 对红黑树来说，3节点可以映射成左孩子是红节点的2个2节点，也可以映射为右孩子是红节点的2个2节点。
 * 对红黑树来说，4节点映射为根是黑色，左右孩子是红色的3个2节点。
 *
 * 我们惊奇的发下，要实现2-3-4树对应的红黑树，只需要改变LLRBTree put方法中的一行代码即可：
 * 将put方法中的flipColors()语句(连同if判断)那一行，移动到递归调用之前(null判断和比较操作直接)
 * 在多个进程可以同时访问同一颗树的应用中这个算法优于2-3树，因为它操作的总是当前节点的一个或2个谅解。
 *
 * 2-3-4树的插入 (参考：https://www.jianshu.com/p/ea71bb20eca8)：
 * 1. 如果2-3-4树中已存在当前插入的key，则插入失败，否则最终一定是在叶子节点中进行插入操作
 * 2. 如果待插入的节点不是4-节点，那么直接在该节点插入
 * 3. 如果待插入的节点是个4-节点，那么应该先分裂该节点然后再插入。一个4-节点可以分裂成一个根节点和两个子节点（这三个节点各含一个key）然后在子节点中插入，我们把分裂形成的根节点中的key看成向上层插入的key，然后重复第2步和第3步。
 * 如果是在4-节点中进行插入，每次插入会多出一个分支，如果插入操作导致根节点分裂，则2-3-4树会生长一层。
 *
 */

public class RBTree<Key extends Comparable<Key>, Value> {
  private static final boolean RED = true;
  private static final boolean BLACK = false;

  private class Node {
    Key key;//键
    Value value;//值
    Node left, right;//左右子树
    boolean color;

    //构造函数
    Node(Key key, Value value, boolean color) {
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

  private void flipColors(Node h) {
    h.color = !h.color;
    h.left.color = !h.left.color;
    h.right.color = !h.right.color;
  }

  public void put(Key key, Value value) {
    //查找key，找到则更新它的值，找不到就创建一个新的节点
    root = put(root, key, value);
    root.color = BLACK;//根节点永远是黑色的
  }

  private Node put(Node h, Key key, Value value) {
    if (h == null) //标准的插入操作，和父节点用红链接相连
      return new Node(key, value, RED);

    if (isRed(h.left) && isRed(h.right)) flipColors(h); //自顶向下的过程中，分裂4-节点。

    int cmp = key.compareTo(h.key);
    if (cmp < 0) h.left = put(h.left, key, value);
    else if (cmp > 0) h.right = put(h.right, key, value);
    else h.value = value;

//    if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
    if (isRed(h.right)) h = rotateLeft(h); // 这行与上面等价，因为h.left肯定是黑色的，否则左右都是红色，上面的flipColors会先执行的，所以到这里只需要判断右孩子是不是红色即可
    if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h); //连续的红色链接，右旋变化为符合定义的4-节点

    return h;
  }

  public static void main(String[] args) {
    RBTree<Integer, String> rbTree = new RBTree();
    rbTree.put(1, "A");
    rbTree.put(3, "C");
    rbTree.put(6, "F");
    rbTree.put(9, "J");
    rbTree.put(10, "K");
    rbTree.put(11, "M");
    rbTree.put(13, "O");
    System.out.println("----");
  }
}
