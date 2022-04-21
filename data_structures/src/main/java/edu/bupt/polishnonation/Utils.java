package edu.bupt.polishnonation;

import java.util.Stack;

/**
 * Created by Anderson on 2021/3/1
 */
public class Utils {
  public static Integer charParse(char c) {
    switch (c) {
      case '0':
        return 0;
      case '1':
        return 1;
      case '2':
        return 2;
      case '3':
        return 3;
      case '4':
        return 4;
      case '5':
        return 5;
      case '6':
        return 6;
      case '7':
        return 7;
      case '8':
        return 8;
      case '9':
        return 9;
      default:
        throw new IllegalArgumentException();
    }

  }

  public static Integer processRev(Stack<Integer> operandStack, char operator) {
    Integer p2 = operandStack.pop();
    Integer p1 = operandStack.pop();
    switch (operator) {
      case '+':
        return p1 + p2;
      case '-':
        return p1 - p2;
      case '*':
        return p1 * p2;
      case '/':
        return p1 / p2;
      default:
        throw new IllegalArgumentException();
    }
  }

  public static Integer process(Stack<Integer> operandStack, char operator) {
    Integer p1 = operandStack.pop();
    Integer p2 = operandStack.pop();
    switch (operator) {
      case '+':
        return p1 + p2;
      case '-':
        return p1 - p2;
      case '*':
        return p1 * p2;
      case '/':
        return p1 / p2;
      default:
        throw new IllegalArgumentException();
    }
  }

  public static int priority(char c) {
    switch (c) {
//      case ')':
//        return 0;
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
        return 2;
//      case '(':
//        return 3;
      default:
        throw new IllegalArgumentException();
    }
  }
}
