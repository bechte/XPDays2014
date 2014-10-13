package xpdays2014.samples.stack.defaultrunner;

import org.junit.Before;
import org.junit.Test;
import xpdays2014.samples.stack.Stack;

import static org.junit.Assert.*;

public class StackTest {
  private Stack stack;

  @Before
  public void setUp() throws Exception {
    stack = Stack.make(2);
  }

  @Test(expected = Stack.IllegalCapacity.class)
  public void whenCreatingAStackWithNegativeSize_ShouldThrowIllegalCapacity() throws Exception {
    Stack.make(-1);
  }

  @Test(expected = Stack.Overflow.class)
  public void whenCreatingStackWithZeroCapacity_AnyPushShouldOverflow() throws Exception {
    stack = Stack.make(0);
    stack.push(1);
  }

  @Test
  public void newlyCreatedStack_ShouldBeEmpty() throws Exception {
    assertTrue(stack.isEmpty());
    assertEquals(0, stack.getSize());
  }

  @Test(expected = Stack.Underflow.class)
  public void whenEmptyStackIsPopped_ShouldThrowUnderflow() throws Exception {
    stack.pop();
  }

  @Test(expected = Stack.Empty.class)
  public void whenStackIsEmpty_TopThrowsEmpty() throws Exception {
    stack.top();
  }

  @Test(expected = Stack.Empty.class)
  public void withZeroCapacityStack_topThrowsEmpty() throws Exception {
    stack = Stack.make(0);
    stack.top();
  }

  @Test
  public void givenStackWithNoTwo_FindTwoShouldReturnNull() throws Exception {
    assertNull(stack.find(2));
  }

  @Test
  public void afterOnePush_StackSizeShouldBeOne() throws Exception {
    stack.push(1);
    assertFalse(stack.isEmpty());
    assertEquals(1, stack.getSize());
  }

  @Test
  public void whenOneIsPushed_OneIsOnTop() throws Exception {
    stack.push(1);
    assertEquals(1, stack.top());
  }

  @Test
  public void whenOneIsPushed_OneIsPopped() throws Exception {
    stack.push(1);
    assertEquals(1, stack.pop());
  }

  @Test
  public void afterOnePushAndOnePop_ShouldBeEmpty() throws Exception {
    stack.push(1);
    stack.pop();
    assertTrue(stack.isEmpty());
    assertEquals(0, stack.getSize());
  }

  @Test
  public void whenOneAndTwoArePushed_TwoAndOneArePopped() throws Exception {
    stack.push(1);
    stack.push(2);
    assertEquals(2, stack.pop());
    assertEquals(1, stack.pop());
  }

  @Test
  public void givenStackWithOneTwoPushed_FindOneAndTwo() throws Exception {
    stack.push(1);
    stack.push(2);
    int oneIndex = stack.find(1);
    int twoIndex = stack.find(2);
    assertEquals(1, oneIndex);
    assertEquals(0, twoIndex);
  }

  @Test(expected = Stack.Overflow.class)
  public void whenPushedPastLimit_ShouldThrowOverflow() throws Exception {
    stack.push(1);
    stack.push(1);
    stack.push(1);
  }
}