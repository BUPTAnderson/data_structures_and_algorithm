package edu.bupt.polishnonation;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Anderson on 2021/3/1
 * 1.前缀表达式又称波兰式，前缀表达式的运算符位于操作数之前。比如:- × + 3 4 5 6
 * 2.中缀表达式就是常见的运算表达式，如(3+4)×5-6
 * 3.后缀表达式又称逆波兰表达式,与前缀表达式相似，只是运算符位于操作数之后,比如:3 4 + 5 × 6 -
 *
 * 中缀表达式转后缀表达式 操作符优先级 > 操作符栈栈顶的优先级 才可以入栈(或栈顶为')'或栈空)
 * 中缀表达式转前缀表达式 操作符优先级 >= 操作符栈栈顶的优先级 才可以入栈(或栈顶为'('或栈空)
 * <p>
 * 参考：https://www.jianshu.com/p/649c12a80fe8
 */
public class Polish {
  /**
   * 通过逆波兰表达式求值
   * 前缀表达式和后缀表达式计算方式类似，都要用到操作数栈
   * 后缀表达式从前向后扫描，前缀表达式从后向前扫描，遇到操作符弹出操作数，后缀表达式弹出的2个操作数位置是反的，前缀表达式弹出的是正常顺序(即后缀表达式依次弹出A B做减法，则是B-A, 前缀表达式依次弹出A B做减法，则是A-B)
   * <p>
   * <p>
   * 我们先看一个例子...后缀表达式3 4 + 5 × 6 -的计算
   * 1.从左至右扫描，将3和4压入堆栈；
   * 2.遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素，注意与前缀表达式做比较），计算出3+4的值，得7，再将7入栈；
   * 3.将5入栈；
   * 4.接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
   * 5.将6入栈；
   * 6.最后是-运算符，计算出35-6的值，即29，由此得出最终结果。
   * 这里假定字符串中的数值都是位数是1
   *
   * @param express
   * @return
   */
  public static int calcRevPolishNotation(String express) {
    if (express.trim().length() == 0) {
      throw new IllegalArgumentException("express: " + express + " is invalid.");
    }
    Stack<Integer> operandStack = new Stack<Integer>();
    for (int i = 0; i < express.length(); i++) {
      char c = express.charAt(i);
      if (c == ' ') {
        continue;
      }
      if (c >= '0' && c < '9') {
        operandStack.push(Utils.charParse(c));
      } else if (c == '+' || c == '-' || c == '*' || c == '/') {
        operandStack.push(Utils.processRev(operandStack, c));
      } else {
        continue;
      }
    }

    return operandStack.pop();
  }

  public static int calcPolishNotation(String express) {
    if (express.trim().length() == 0) {
      throw new IllegalArgumentException("express: " + express + " is invalid.");
    }
    // Stack
//    Deque<Integer> stack = new ArrayDeque<>();
//    Deque<Integer> stack = new LinkedList<>();
    // Stack继承自Vector，Stack和Vector都是线程安全的，但是不推荐使用了，因为都是在方法上加synchronized 锁比较重
    Stack<Integer> operandStack = new Stack<Integer>();
    for (int i = express.length() - 1; i >= 0; i--) {
      char c = express.charAt(i);
      if (c == ' ') {
        continue;
      }
      if (c >= '0' && c < '9') {
        operandStack.push(Utils.charParse(c));
      } else if (c == '+' || c == '-' || c == '*' || c == '/') {
        operandStack.push(Utils.process(operandStack, c));
      } else {
        continue;
      }
    }

    return operandStack.pop();
  }

  /**
   * 中缀表达式转后缀表达式(逆波兰表达式)
   * <p>
   * 中缀表达式转后缀表达式主要用到了栈进行运算符处理，队列进行排序输出，规则为：
   * <p>
   * 1.数字直接入队列
   * 2.运算符要与栈顶元素比较
   * ①栈为空直接入栈
   * ②运算符优先级大于栈顶元素优先级则直接入栈
   * ③小于或等于则出栈入列，再与栈顶元素进行比较，直到运算符优先级大于栈顶元素优先级(或栈为空或栈顶为'(')后，操作符再入栈 // 即保证当前运算符比栈顶运算符优先级高
   * 3.操作符是 ( 则无条件入栈
   * 4.操作符为 )，则依次出栈入列，直到匹配到第一个(为止，此操作符直接舍弃，(直接出栈舍弃
   * 5.表达式处理完后，如果栈不为空，依次出栈入队列，直到栈为空。
   * <p>
   * 输出队列内容即可
   *
   * @param express
   * @return
   */
  public static String transfer2RevPolishNonation(String express) {
    StringBuffer sb = new StringBuffer();
    Stack<Character> operand = new Stack<>();
    for (int i = 0; i < express.length(); i++) {
      char c = express.charAt(i);
      if (c == ' ') {
        continue;
      } else if (c >= '0' && c < '9') {
        sb.append(c).append(' ');
      } else if (c == '+' || c == '-' || c == '*' || c == '/') {
        if (operand.empty()) { // 对应①
          operand.push(c);
          continue;
        }

        // 注意遍历的时候要判断栈是否为空切要先判空和是否为'(' 因为我们的priority方法中不会判断'('的优先级
        while (!operand.empty() && operand.peek() != '(' && Utils.priority(c) <= Utils.priority(operand.peek())) {
          sb.append(operand.pop()).append(' '); // 对应③
        }
        operand.push(c); // 对应②
      } else if (c == ')') {
        while (operand.peek() != '(') {
          sb.append(operand.pop()).append(' ');
        }
        operand.pop();
      } else if (c == '(') {
        operand.push(c);
      }
    }

    while (!operand.isEmpty()) {
      sb.append(operand.pop()).append(' ');
    }
    return sb.toString();
  }

  /**
   *
   * 中缀表达式转前缀表达式(波兰表达式)
   * <p>
   * 中缀表达式转前缀表达式主要用到了2个栈分别处理操作数和运算符，最后弹出操作符栈即可，规则为：
   * <p>
   * 1.数字直接入操作数栈
   * 2.运算符要与运算符栈顶元素比较
   * ①运算符栈为空直接入运算栈
   * ②运算符优先级大于等于运算栈顶元素优先级则直接入运算栈
   * ③小于则出运算栈入列，再与运算栈顶元素进行比较，直到运算符优先级大于等于运算栈顶元素优先级(或栈为空或栈顶为')')后，操作符再入栈 // 即保证当前运算符比栈顶运算符优先级高或相等
   * 3.操作符是 ) 则无条件入栈
   * 4.操作符为 (，则依次出栈入列，直到匹配到第一个)为止，此操作符直接舍弃，)直接出栈舍弃
   * 5.表达式处理完后，如果运算符栈不为空，依次出运算符栈入操作数栈，直到运算符栈为空。
   * 6.依次弹出操作数栈内容，即为前缀表达式
   * <p>
   */
  public static String transfer2PolishNonation(String express) {
    return null;
  }

  /**
   * 中缀表达式计算值
   *
   * @return
   */
  public static int getValueFromInfixNonation(String express) {
    if (express == null || express.isEmpty()) {
      throw new IllegalArgumentException();
    }

    Stack<Integer> operator = new Stack<>(); // 操作数
    Stack<Character> operand = new Stack<>(); // 操作符
    for (int i = 0; i < express.length(); i++) {
      char c = express.charAt(i);
      if (c == ' ') {
        continue;
      }
      if (c > '0' && c < '9') {
        operator.push(Utils.charParse(c));
      } else if (c == '+' || c == '-' || c == '*' || c == '/') {
        if (operand.isEmpty()) {
          operand.push(c);
          continue;
        }
        while (!operand.isEmpty() && operand.peek() != '(' && Utils.priority(c) <= Utils.priority(operand.peek())) {
          int value = Utils.processRev(operator, operand.pop());
          operator.push(value);
        }
        operand.push(c);
      } else if (c == ')') {
        while (operand.peek() != '(') {
          int value = Utils.processRev(operator, operand.pop());
          operator.push(value);
        }
        operand.pop();
      } else if (c == '(') {
        operand.push(c);
      }
    }

    while (!operand.isEmpty()) {
      int value = Utils.processRev(operator, operand.pop());
      operator.push(value);
    }

    return operator.pop();
  }

  public static void main(String[] args) {
//    String str = "(11+(44+5+21)-32)+(6+8)";
//    Pattern pattern = Pattern.compile("\\d+");
//    Matcher matcher = pattern.matcher(str);
//    while (matcher.find()) {
//      System.out.println(matcher.group());
//      System.out.println("index: " + matcher.end()); // 匹配字符串最后字符的索引
//    }

//    System.out.println(calcRevPolishNotation("3 4 + 5 * 6 -"));
//    System.out.println(transfer2PolishNonation("(3+4)*5-6"));
//    System.out.println(getValueFromInfixNonation("(3+4)*5-(6 - 2 * 2) + 5"));
    System.out.println(calcPolishNotation("- * + 3 4 5 6"));
  }
}
