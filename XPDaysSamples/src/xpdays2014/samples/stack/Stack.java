package xpdays2014.samples.stack;

public class Stack {
  private int capacity;
  private int size;
  private int[] elements;

  public static Stack make(int capacity) {
    if (capacity < 0)
      throw new IllegalCapacity();
    return new Stack(capacity);
  }

  private Stack(int capacity) {
    this.capacity = capacity;
    this.elements = new int[capacity];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int getSize() {
    return size;
  }

  public void push(int element) {
    if (size == capacity)
      throw new Overflow();
    this.elements[size++] = element;
  }

  public int pop() {
    if (isEmpty())
      throw new Underflow();
    return elements[--size];
  }

  public int top() {
    if (isEmpty())
      throw new Empty();
    return elements[size - 1];
  }

  public Integer find(int element) {
    for (int i = size - 1; i >= 0; i--)
      if (elements[i] == element)
        return (size - 1) - i;
    return null;
  }

  public static class Overflow extends RuntimeException {
  }

  public static class Underflow extends RuntimeException {
  }

  public static class IllegalCapacity extends RuntimeException {
  }

  public static class Empty extends RuntimeException {
  }
}