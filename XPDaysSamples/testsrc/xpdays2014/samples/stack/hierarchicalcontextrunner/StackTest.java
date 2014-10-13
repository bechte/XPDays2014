package xpdays2014.samples.stack.hierarchicalcontextrunner;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import xpdays2014.samples.stack.Stack;

import static org.junit.Assert.*;

@RunWith(HierarchicalContextRunner.class)
public class StackTest {
  private Stack stack;

  @Test(expected = Stack.IllegalCapacity.class)
  public void whenCreatingAStackWithNegativeSize_ShouldThrowIllegalCapacity() throws Exception {
    Stack.make(-1);
  }

  public class ZeroSizeStackContext {
    @Before
    public void setUp() throws Exception {
      stack = Stack.make(0);
    }

    @Test(expected = Stack.Overflow.class)
    public void anyPushShouldOverflow() throws Exception {
      stack.push(1);
    }

    @Test(expected = Stack.Empty.class)
    public void topShouldThrowEmpty() throws Exception {
      stack.top();
    }
  }

  public class GivenNewStack {
    @Before
    public void setUp() throws Exception {
      stack = Stack.make(2);
    }

    @Test
    public void shouldBeEmpty() throws Exception {
      assertTrue(stack.isEmpty());
      assertEquals(0, stack.getSize());
    }

    @Test(expected = Stack.Underflow.class)
    public void popShouldThrowUnderflow() throws Exception {
      stack.pop();
    }

    @Test(expected = Stack.Empty.class)
    public void topShouldThrowEmpty() throws Exception {
      stack.top();
    }

    @Test
    public void findTwoShouldReturnNull() throws Exception {
      assertNull(stack.find(2));
    }

    public class GivenOnePushed {
      @Before
      public void pushOne() {
        stack.push(1);
      }

      @Test
      public void stackSizeShouldBeOne() throws Exception {
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.getSize());
      }

      @Test
      public void oneIsOnTop() throws Exception {
        assertEquals(1, stack.top());
      }

      public class GivenOnePopped {
        private int poppedElement;

        @Before
        public void popOne() {
          poppedElement = stack.pop();
        }

        @Test
        public void oneIsPopped() throws Exception {
          assertEquals(1, poppedElement);
        }

        @Test
        public void stackShouldBeEmpty() throws Exception {
          assertTrue(stack.isEmpty());
          assertEquals(0, stack.getSize());
        }
      }

      public class GivenTwoPushed {
        @Before
        public void pushTwo() {
          stack.push(2);
        }

        @Test
        public void givenStackWithOneTwoPushed_FindOneAndTwo() throws Exception {
          int oneIndex = stack.find(1);
          int twoIndex = stack.find(2);
          assertEquals(1, oneIndex);
          assertEquals(0, twoIndex);
        }

        @Test
        public void twoAndOneArePopped() throws Exception {
          assertEquals(2, stack.pop());
          assertEquals(1, stack.pop());
        }

        @Test(expected = Stack.Overflow.class)
        public void anotherPushShouldThrowOverflow() throws Exception {
          stack.push(1);
        }
      }
    }
  }
}